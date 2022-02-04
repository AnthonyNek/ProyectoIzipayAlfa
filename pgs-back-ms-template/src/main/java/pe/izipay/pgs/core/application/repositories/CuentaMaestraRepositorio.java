package pe.izipay.pgs.core.application.repositories;

public interface CuentaMaestraRepositorio {
    void guardarHash(String idCuenta, String newHash);
}
