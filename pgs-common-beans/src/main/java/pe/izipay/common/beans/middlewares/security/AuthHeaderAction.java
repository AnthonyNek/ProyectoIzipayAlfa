package pe.izipay.common.beans.middlewares.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface AuthHeaderAction {

    void action(HttpServletRequest request, HttpServletResponse response);

}
