package pe.izipay.pgs.core.infrastructure.repositories.jpa.seeddata.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.CrudRepository;
import pe.izipay.common.beans.adapters.json.JsonAdapter;
import pe.izipay.common.beans.adapters.json.JsonPort;
import pe.izipay.common.core.exceptions.AppRuntimeException;
import pe.izipay.common.core.exceptions.CommonModuleException;
import pe.izipay.common.core.helpers.TextHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
public class BaseSeedData<T, K, C extends CrudRepository<T, K>> implements ISeedData {

    private final JsonPort jsonPort = new JsonAdapter(new ObjectMapper());

    private final Class<T> typeParameterClass;
    private final C fachada;
    private final File recurso;

    protected static File obtenerRecurso(String recurso) {
        try {
            return new ClassPathResource("seeddata/" + recurso).getFile();
        } catch (IOException ex) {
            throw new AppRuntimeException(ex);
        }
    }

    protected T convertirRegistro(Map<String, String> atributos) {
        return jsonPort.mapToObject(atributos, typeParameterClass);
    }

    private List<String> obtenerColumnas(Row fila) {
        String valor;
        var columnas = new ArrayList<String>(20);
        for (Cell celda : fila) {
            valor = TextHelper.trim(celda.getStringCellValue());
            if(valor.isEmpty()) {
                throw new CommonModuleException("Seedata: no puede existir una cabecera vacia");
            }
            columnas.add(valor);
        }
        return columnas;
    }

    @Override
    public long cargarExcel() {
        try {
            var workbook = new XSSFWorkbook(new FileInputStream(recurso));
            var sheet = workbook.getSheetAt(0);
            var filas = sheet.rowIterator();
            if(!filas.hasNext()) {
                throw new CommonModuleException("Seedata: el archivo excel esta vacio");
            }
            var columnas = obtenerColumnas(filas.next());
            int indiceColumna;
            int cantidadFilas = 0;
            Map<String, String> atributos;
            while (filas.hasNext()) {
                indiceColumna = 0;
                atributos = new HashMap<>();
                for (Cell celda : filas.next()) {
                    atributos.put(columnas.get(indiceColumna), celda.getStringCellValue());
                    indiceColumna++;
                }
                fachada.save(convertirRegistro(atributos));
                cantidadFilas++;
            }
            return cantidadFilas;
        } catch (IOException ex) {
            throw new AppRuntimeException(ex);
        }
    }
}