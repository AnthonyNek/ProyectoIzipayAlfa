package pe.izipay.common.core.interfaces.errors;

import pe.izipay.common.core.interfaces.IType;

public interface IErrorType<T> extends IReadOnlyError<T>, IType, IGroupErrorId {

    default String getInput() {
        return null;
    }

    interface IErrorTypeString extends IErrorType<String> {

        default String getCode() {
            return name();
        }
    }

    interface IErrorTypeInteger extends IErrorType<Integer> {

        default Integer getCode() {
            return getId();
        }

    }
}