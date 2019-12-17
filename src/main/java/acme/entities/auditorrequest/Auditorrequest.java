
package acme.entities.auditorrequest;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.Authenticated;
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

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Authenticated		authenticated;

}
