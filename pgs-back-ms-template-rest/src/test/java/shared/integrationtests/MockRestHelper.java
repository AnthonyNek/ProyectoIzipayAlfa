package shared.integrationtests;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pe.izipay.common.beans.adapters.json.JsonPort;
import pe.izipay.common.beans.repositories.audit.Auditable;
import pe.izipay.common.beans.repositories.mongodb.FacadeMongoDbRepository;
import pe.izipay.common.core.helpers.TextHelper;
import pe.izipay.common.core.responses.WrappedResponse;
import pe.izipay.common.core.types.DataStatusType;
import shared.TestsResources;

import java.io.File;
import java.nio.file.Path;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockRestHelper {

    public static MockHttpServletResponse runAndReadResponse(MockMvc mockMvc, MockHttpServletRequestBuilder request) throws Exception {
        return mockMvc.perform(request).andReturn().getResponse();
    }

    public static String joinUrl(String base, String partial) {
        return base + "/" + partial;
    }

    public static File readFilePayload(String basePath, String resource) {
        Path path;
        if(resource.startsWith("../")) {
            path = Path.of(TestsResources.FOLDER_RESOURCES_PAYLOADS, resource.substring(3));
        } else {
            path = Path.of(TestsResources.FOLDER_RESOURCES_PAYLOADS, basePath, resource);
        }
        return path.toFile();
    }

    public static  <T extends Auditable> MockHttpServletResponse createAndDelete(MockMvc mockMvc,
                                                                                 MockHttpServletRequestBuilder request, FacadeMongoDbRepository<T, String> mongoDbfacade, JsonPort jsonAdapter) throws Exception {

        var response = runAndReadResponse(mockMvc, request);
        if(response.getStatus() == 201) {
            var body = response.getContentAsString();
            String id = jsonAdapter.toObject(body, WrappedResponse.StringWrappedResponse.class).getData();
            mongoDbfacade.deleteById(id);
        }
        return response;
    }

    public static  <T extends Auditable> void restoreRecord(String id, FacadeMongoDbRepository<T, String> mongoDbfacade) {
        var result = mongoDbfacade.findById(id);
        if(result.isPresent()) {
            var model = result.get();
            model.setEstado((DataStatusType.ENABLED.byteValue()));
            mongoDbfacade.save(model);
        }
    }

    public static  <T extends Auditable> MockHttpServletResponse deleteAndRestore(MockMvc mockMvc,
        MockHttpServletRequestBuilder request, String id, FacadeMongoDbRepository<T, String> mongoDbfacade) throws Exception {

        var response = runAndReadResponse(mockMvc, request);
        if(response.getStatus() == 200) {
            restoreRecord(id, mongoDbfacade);
        }
        return response;
    }

    public static void printActiveProfile(Environment environment) {
        log.info("Perfil activo: {}", TextHelper.join(",", (Object[]) environment.getActiveProfiles()));
    }
}