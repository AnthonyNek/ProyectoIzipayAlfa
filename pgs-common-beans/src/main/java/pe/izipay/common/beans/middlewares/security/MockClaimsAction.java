package pe.izipay.common.beans.middlewares.security;

import java.util.Map;

@FunctionalInterface
public interface MockClaimsAction {

    Map<String, Object> action();

}
