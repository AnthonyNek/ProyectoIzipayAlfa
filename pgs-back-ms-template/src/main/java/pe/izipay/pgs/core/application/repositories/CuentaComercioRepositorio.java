package pe.izipay.pgs.core.application.repositories;

public interface CuentaComercioRepositorio {
    void guardarHash(String idCuenta, String newHash);
}
