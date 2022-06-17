package business.sports;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Column;

/**
 * A class representing a Sport 
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Sport.FIND_BY_ID, query="SELECT s FROM Sport s WHERE s.id = :" + Sport.SPORT_ID),
	@NamedQuery(name=Sport.FIND_BY_NAME, query="SELECT s FROM Sport s WHERE s.name = :" + Sport.SPORT_NAME),
	@NamedQuery(name=Sport.GET_ALL_NAMES, query="SELECT s FROM Sport s"),
})
public class Sport {
	
	// Named query name constants

	public static final String FIND_BY_ID = "Sport.findById";
	public static final String SPORT_ID = "id";
	
	public static final String FIND_BY_NAME = "Sport.findByName";
	public static final String SPORT_NAME = "name";
	
	public static final String GET_ALL_NAMES = "Sport.getAllNames";

	/**
	 * Sport primary key. Needed by JPA.
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private int minParticipants;
	
	@Column(nullable = false)
	private int minMinutes;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SportType sportType;
	
	/**
	 * Constructor needed by JPA.
	 */
	Sport() {
	}
	
	/**
	 * Constructs a Sport given its data
	 * @param name The name of the sport
	 * @param minParticipants The sport's minimum participants
	 * @param minMinutes The sport's minimum minutes 
	 * @param sportType The type of the sport
	 */
	public Sport(String name, int minParticipants, int minMinutes, SportType sportType) {
		this.name = name;
		this.minParticipants = minParticipants;
		this.minMinutes = minMinutes;
		this.sportType = sportType;
	}
	
	/**
	 * Returns the sport's id
	 * @return The sport's id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the name of the sport
	 * @return The name of the sport
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the minimum allowed participants
	 * @return The minimum allowed participants
	 */
	public int getMinParticipants() {
		return minParticipants;
	}

	/**
	 * Returns the minimum allowed minutes
	 * @return The minimum allowed minutes
	 */
	public int getMinMinutes() {
		return minMinutes;
	}
	
	public SportType getSportType() {
		return sportType;
	}
}
