
package acme.entities.banners;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Commercial extends Banner {

	private static final long	serialVersionUID	= 1L;

	//Atributes ------------------------------------------

	@NotBlank
	@CreditCardNumber
	private String				creditCardNumber;

	@NotBlank
	private String				creditCardName;

	@Range(min = 1, max = 12)
	@NotNull
	private Integer				creditCardMonth;

	@Min(value = 2019)
	@NotNull
	private Integer				creditCardYear;

	@Range(min = 1, max = 999)
	@NotNull
	private Integer				creditCardCVV;

	@NotBlank
	private String				creditCardType;

}
