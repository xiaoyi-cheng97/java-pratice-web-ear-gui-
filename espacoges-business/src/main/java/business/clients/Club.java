package business.clients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * A class representing a User of the type Club
 *
 */
@Entity
@DiscriminatorValue(value = "Club")
public class Club extends Client {
	/**
	 * A constant used in determining the club's status
	 */
	public static final int K = 30;
	
	/**
	 * The total reserved hours by the club
	 */
	private long totalHours;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	/**
	 * Constructor needed by JPA 
	 */
	Club() {
	}
	
	/**
	 * Creates a club given its data
	 * @param name The name of the club
	 * @param nif The NIF of the club
	 * @param address The address of the club
	 */
	public Club(String name, int nif, String address) {
		super(name, nif, address);
		totalHours = 0;
		status = Status.BRONZE;
	}
	
	@Override
	public String getType() {
		return "Club";
	}
	
	/**
	 * Returns the club's status
	 * @return The club's status
	 */
	public String getStatus() {
		return status.toString();
	}

	/**
	 * Adds more hours to the total of reserved hours
	 * @param reservedHours The amount of hours to add
	 */
	public void addHours(long reservedHours) {
		totalHours += reservedHours;
	}

	/**
	 * Updates this club's status based on its reserved hours
	 */
	public void updateStatus() {
		if (totalHours >= K && totalHours <= 100 * K) {
			status = Status.SILVER;
		}
		else if (totalHours > 100 * K) {
			status = Status.GOLD;
		}	
	}

}
