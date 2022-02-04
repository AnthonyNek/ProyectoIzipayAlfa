package pe.izypay.pgs.mstemplate.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pe.izypay.pgs.mstemplate.domain.types.FeatureType;

@Target({ ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureGroup {
		
	FeatureType value() default FeatureType.DEFAULT;

}
