
package acme.entities.auditorrequest;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auditorrequest extends DomainEntity {

	// Serialization identifier -----------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ---------------------------------------

	@NotBlank
	private String				firm;

	@NotBlank
	private String				responsibility;

	private Boolean				status;

	//Derived attributes --------------------------------

	//Relationships -------------------------------------

}
