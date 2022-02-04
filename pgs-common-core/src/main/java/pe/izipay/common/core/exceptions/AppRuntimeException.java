package pe.izipay.common.core.exceptions;

import java.io.Serializable;

import pe.izipay.common.core.helpers.TextHelper;
import pe.izipay.common.core.domainobjects.cruds.WrappedValuePayload;

public class AppRuntimeException extends RuntimeException implements Serializable {
	
	private static final WrappedValuePayload<String> separator = new WrappedValuePayload<>(" *** ");
	private static final long serialVersionUID = 731103227322272500L;

	public AppRuntimeException() {
        super();
    }
	
	public AppRuntimeException(String message) {
        super(message);
    }

    public AppRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppRuntimeException(Throwable cause) {
        super(cause);
    }

    public AppRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
	public static void setSeparator(String separator) {
		AppRuntimeException.separator.setValor(TextHelper.isNullOrEmpty(separator) ? "\n" : separator);
	}

	public static boolean hasChildException(Throwable ex) {
        return ex.getCause() != ex && ex.getCause() != null;
    }

    public boolean hasChildException() {
        return this.getCause() != this && this.getCause() != null;
    }

    public static void readAll(StringBuilder builder, Throwable ex, String separator) {
        boolean hasChildException = hasChildException(ex);
        builder.append("Class: ").append(ex.getClass()).append(separator);
        builder.append("HasCause: ").append(hasChildException).append(separator);
        builder.append("Message: ").append(ex.getMessage()).append(separator);
        builder.append("LocalizedMessage: ").append(ex.getLocalizedMessage()).append(separator);
        var traces = ex.getStackTrace();
        if (traces != null && traces.length > 0) {
        	builder.append("traces: ").append(traces[0]).append(separator);
            for (var i = 1; i < traces.length; i++) {
                builder.append("    at ").append(traces[i]).append(separator);
            }
        }
        var suppress = ex.getSuppressed();
        if (suppress != null && suppress.length > 0) {
        	builder.append("suppress: ").append(suppress[0]).append(separator);
            for (var i = 1; i < suppress.length; i++) {
                builder.append("    at ").append(suppress[i]).append(separator);
            }
        }
        if (hasChildException) {
            builder.append("---------- CAUSE EXCEPTION ----------").append(separator);
            readAll(builder, ex.getCause(), separator);
        }
    }
    
    public static void readAll(StringBuilder builder, Throwable ex) {
    	readAll(builder, ex, separator.getValor());
    }
    
    public static String readAll(Throwable ex, String sepataror) {
        var builder = new StringBuilder(1000);
        readAll(builder, ex, sepataror);
        return builder.toString();
    }
    
    public static String readAll(Throwable ex) {
    	return readAll(ex, separator.getValor());
    }
    
    public String readAll() {
        return readAll(this, separator.getValor());
    }

    public static void readAllMessages(StringBuilder builder, Throwable ex, String separator) {
        boolean hasChildException = hasChildException(ex);
        builder.append(ex.getMessage()).append("; ").append(separator);
        if (hasChildException) {
            readAllMessages(builder, ex.getCause(), separator);
        }
    }
    
    public static void readAllMessages(StringBuilder builder, Throwable ex) {
    	readAllMessages(builder, ex, separator.getValor());
    }

    public static String readAllMessages(Throwable ex, String separator) {
        var builder = new StringBuilder(300);
        readAllMessages(builder, ex, separator);
        return builder.toString();
    }

    public String readAllMessages() {
        return readAllMessages(this, separator.getValor());
    }	
}