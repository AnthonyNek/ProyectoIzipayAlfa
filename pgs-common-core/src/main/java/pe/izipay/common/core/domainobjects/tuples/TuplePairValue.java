package pe.izipay.common.core.domainobjects.tuples;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TuplePairValue<A, B> {
	protected A a;
	protected B b;
}
