package pe.izipay.common.core.types;

public enum RelativePathType {		
	CURRENT("./"),
	PARENT_CURRENT("*/"),	
	RELATIVE("../"),
	TEMPORARY("?/"),
	CLASSPATH(":/"),
	ENVIROMENT("$/"),
	ABSOLUTE("");
	
	private String prefix;
	
	private RelativePathType(String prefix) {
		this.prefix = prefix;
	}
	
	public String prefix() {
		return prefix;
	}
}
