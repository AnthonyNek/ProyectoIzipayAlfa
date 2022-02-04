package pe.izipay.common.beans.adapters.jwt;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectExpiration {
	
	private String subject;
	private Date expiration;

}