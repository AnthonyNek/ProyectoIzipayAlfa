package pe.izipay.common.beans.repositories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DbProperties {
	public static final String MONGO_ID = "_id";
	public static final String ID = "id";
	public static final String STATUS = "estado";
	public static final String NAME = "nombre";
}
