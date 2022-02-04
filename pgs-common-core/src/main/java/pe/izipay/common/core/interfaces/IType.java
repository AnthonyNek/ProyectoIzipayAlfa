package pe.izipay.common.core.interfaces;

public interface IType {
	
	String name();
	int ordinal();

	default int id() {
		return  ordinal() + 1;
	}

	default String filter() {
		return name();
	}
	
	default boolean equalsType(String enumName) {
		return name().equals(enumName);
	}	
	
	default byte byteValue() {
		return (byte)this.ordinal();
	}		
	
	default short shortValue() {
        return (short)this.ordinal();
    }
	
	default boolean match(byte value) {
		return this.ordinal() == value;
	}
	
	default boolean match(int value) {
		return this.ordinal() == value;
	}
}
