
package acme.entities.banners;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Noncommercial extends Banner {

	private static final long	serialVersionUID	= 1L;

	//Atributes ------------------------------------------

	@URL
	private String				jingle;

}
