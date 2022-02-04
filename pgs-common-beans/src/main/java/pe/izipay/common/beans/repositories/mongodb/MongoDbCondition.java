package pe.izipay.common.beans.repositories.mongodb;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MongoDbCondition {
	public static final String IN= "$in";
}
