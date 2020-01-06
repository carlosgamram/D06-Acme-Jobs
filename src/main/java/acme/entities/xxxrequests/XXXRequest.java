
package acme.entities.xxxrequests;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class XXXRequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Atributes ------------------------------------------
	@NotBlank
	//Todo: Cambiar
	@Length(max = 10)
	private String				text;

	@URL
	private String				xxx;

	@NotBlank
	//TODO: Cambiar
	@Pattern(regexp = "([A-Z])\\w+")
	private String				password;

}
