
package acme.entities.messagethreads;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Messagethread extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Atributes ------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@NotBlank
	private String				title;

	//Relationships ---------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		owner;

}
