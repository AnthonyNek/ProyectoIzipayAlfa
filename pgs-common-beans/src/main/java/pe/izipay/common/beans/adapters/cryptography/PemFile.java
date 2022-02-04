package pe.izipay.common.beans.adapters.cryptography;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import pe.izipay.common.core.exceptions.AppRuntimeException;

public class PemFile {
	
	private PemObject pemObject;
	 
	public PemFile(String filename) {
		try (PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)))) {			
			this.pemObject = pemReader.readPemObject();
		}catch (Exception ex) {
			throw new AppRuntimeException(ex);
		}
	}
 
	public PemObject getPemObject() {
		return pemObject;
	}

}
