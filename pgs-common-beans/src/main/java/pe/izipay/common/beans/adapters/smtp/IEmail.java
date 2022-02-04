package pe.izipay.common.beans.adapters.smtp;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IEmail {
	
	@JsonIgnore
	String getEmail();
		
	static <E extends IEmail> String[] toArray(Collection<E> collection) {
		var array = new String[collection.size()];
		var index = 0;
		for (var item : collection) {
			array[index] = item.getEmail();
			index++;
		}
		return array;
	}
}
