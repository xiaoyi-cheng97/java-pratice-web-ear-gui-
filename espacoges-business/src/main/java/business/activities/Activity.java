package business.activities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import business.clients.Client;
import business.sports.Sport;

/**
 * A class representing an activity
 *
 */
@Entity
public class Activity {
	
	/**
	 * Client primary key. Needed by JPA.
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private int nParticipants;
	
	@ManyToOne
	private Sport sport;
	
	@Embedded
	private TimeFrame period;
	
	@ManyToOne
	private Client client;
	
	/**
	 * Constructor needed by JPA.
	 */
	Activity() {
	}
	
	/**
	 * Creates an activity given its data
	 * @param description The description of the activity
	 * @param nParticipants The number of participants
	 * @param sport The sport that will be played
	 * @param period The time frame in which the activity will take place
	 * @param client The client that requested the activity
	 */
	public Activity(String description, int nParticipants, Sport sport, TimeFrame period, Client client) {
		this.description = description;
		this.sport = sport;
		this.nParticipants = nParticipants;
		this.period = period;
		this.client = client;
	}

	/**
	 * Returns the description of the activity
	 * @return The description of the activity
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns the number of participants of the activity
	 * @return The number of participants of the activity
	 */
	public int getNParticipants() {
		return nParticipants;
	}
	
	/**
	 * Returns the name of the sport that will be played in this activity
	 * @return The name of the sport that will be played in this activity
	 */
	public String getSport() {
		return sport.getName();
	}

	/**
	 * Returns the time frame in which the activity will take place
	 * @return The time frame in which the activity will take place
	 */
	public TimeFrame getPeriod() {
		return period;
	}

	/**
	 * The client that requested this activity 
	 * @return The client that requested this activity 
	 */
	public Client getClient() {
		return client;
	}

}
