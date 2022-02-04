package pe.izipay.common.core.interfaces.errors;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pe.izipay.common.core.interfaces.IType;

public interface IFeatureType extends IType {
	
	@JsonIgnore
	default IFeatureType getFeature() {
		return this;
	}
	
	@SuppressWarnings("unchecked")
	static <T extends IReadOnlyError<?> & IFeatureType> Map<String, IReadOnlyError<?>>[] splitErrorsByFeatures(
		IFeatureType[] featuresArray, T[] errorArray ,  IFeatureType noneFeature) {		
		
		var features = new Map[featuresArray.length];
		for (var index = 0; index < featuresArray.length; index++) {
			features[index] = new HashMap<>();
		}
		for (T item : errorArray) {
            if(item.getFeature() != noneFeature) {
            	features[item.getFeature().ordinal()].put(item.name(), item);	
            }
        }
		return features;
	}
}
