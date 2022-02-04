package pe.izipay.common.core.domainobjects.tuples;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TupleThreeValue<A, B, C> extends TuplePairValue<A, B> {
	protected C c;

	public TupleThreeValue(A a, B b, C c) {
		super(a, b);
		this.c = c;
	}	
}
