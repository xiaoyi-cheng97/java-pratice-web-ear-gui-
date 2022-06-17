package business.clients;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import business.utils.DateUtils;

/**
 * A class representing a User of the type Individual
 *
 */
@Entity
@DiscriminatorValue(value = "Individual")
public class Individual extends Client {

	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	/**
	 * Constructor needed by JPA 
	 */
	Individual() {
	}

	/**
	 * Creates an individual client given its data
	 * @param name The name of the client
	 * @param nif The NIF of the client
	 * @param address The address of the client
	 * @param birthDate The client's birth date
	 */
	public Individual(String name, int nif, String address, Date birthDate) {
		super(name, nif, address);
		this.birthDate = birthDate;
	}

	/**
	 * Verifies if this individual is an adult
	 * @return True if user age >= 18, false otherwise
	 */
	public boolean isAdult() {
		return calculateAge(DateUtils.toLocalDate(birthDate), LocalDate.now()) >= 18;
	}

	/**
	 * Calculates the age of this individual
	 * @param birthDate The birth date of this individual
	 * @param currentDate The date representing today
	 * @return The age of this individual
	 */
	private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

	@Override
	public String getType() {
		return "Individual";
	}
}
