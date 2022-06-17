package business.spaces;

import static javax.persistence.CascadeType.ALL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import business.activities.Activity;
import business.activities.TimeFrame;
import business.clients.exceptions.InvalidClientException;
import business.sports.Sport;
import business.utils.DateUtils;

/**
 * A class representing a space
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Space.FIND_BY_ID, query="SELECT s FROM Space s WHERE s.id = :" + Space.SPACE_ID),
	@NamedQuery(name=Space.FIND_BY_NAME, query="SELECT s FROM Space s WHERE s.name = :" + Space.SPACE_NAME),
	@NamedQuery(name=Space.GET_ALL_NAMES, query="SELECT s FROM Space s"),
})
public class Space {
	
	// Named query name constants

	public static final String FIND_BY_ID = "Space.findById";
	public static final String SPACE_ID = "id";
	
	public static final String FIND_BY_NAME = "Space.findByName";
	public static final String SPACE_NAME = "name";
	
	public static final String GET_ALL_NAMES = "Space.getAllNames";
	
	/**
	 * Space primary key. Needed by JPA.
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private int maxCapacity;
	
	@Column(nullable = false)
	private double price;
	
	/**
	 * The space's opening time on weekdays
	 */
	@Temporal(TemporalType.TIME) @Column(nullable = false)
	private Date startTimeWeekdays;
	
	/**
	 * The space's closing time on weekdays
	 */
	@Temporal(TemporalType.TIME) @Column(nullable = false)
	private Date endTimeWeekdays;
	
	/**
	 * The space's opening time on weekends
	 */
	@Temporal(TemporalType.TIME) @Column(nullable = false)
	private Date startTimeWeekends;
	
	/**
	 * The space's closing time on weekends
	 */
	@Temporal(TemporalType.TIME) @Column(nullable = false)
	private Date endTimeWeekends;
	
	/**
	 * The sports that can be played in this space
	 */
	@ManyToMany
	@JoinTable(name = "ALLOWS_SPORT")
	private List<Sport> allowedSports;
	
	/**
	 * The occupation records of this space
	 */
	@OneToMany(cascade = ALL, mappedBy = "space")
	private List<OccupationRecord> records;
	
	/**
	 * Constructor needed by JPA.
	 */
	Space() {
	}
	
	/**
	 * Creates a space given its data
	 * @param name The name of the space
	 * @param maxCapacity The maximum capacity of the space
	 * @param price The price per hour of using the space
	 * @param startTimeWeekdays The opening time on weekdays
	 * @param endTimeWeekdays The closing time on weekdays
	 * @param startTimeWeekends The opening time on weekends
	 * @param endTimeWeekends The closing time on weekends
	 */
	public Space(String name, int maxCapacity, double price, Date startTimeWeekdays, Date endTimeWeekdays,
			Date startTimeWeekends, Date endTimeWeekends) {
		this.name = name;
		this.maxCapacity = maxCapacity;
		this.price = price;
		this.startTimeWeekdays = startTimeWeekdays;
		this.endTimeWeekdays = endTimeWeekdays;
		this.startTimeWeekends = startTimeWeekends;
		this.endTimeWeekends = endTimeWeekends;
		this.allowedSports = new ArrayList<>();
		records = new ArrayList<>();
	}
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	public double getPrice() {
		return price;
	}
	
	
	public List<Sport> getAllowedSport(){
		return allowedSports;
	}
	
	public List<OccupationRecord> getRecord(){
		return records;
	}
	/**
	 * Returns the space's opening time on weekdays
	 * @return The space's opening time on weekdays
	 */
	public LocalTime getStartTimeWeekdays() {
		return DateUtils.toLocalTime(startTimeWeekdays);
	}

	/**
	 * Returns the space's closing time on weekdays
	 * @return The space's closing time on weekdays
	 */
	public LocalTime getEndTimeWeekdays() {
		return DateUtils.toLocalTime(endTimeWeekdays);
	}

	/**
	 * Returns the space's opening time on weekends
	 * @return The space's opening time on weekends
	 */
	public LocalTime getStartTimeWeekends() {
		return DateUtils.toLocalTime(startTimeWeekends);
	}

	/**
	 * Returns the space's closing time on weekends
	 * @return The space's closing time on weekends
	 */
	public LocalTime getEndTimeWeekends() {
		return DateUtils.toLocalTime(endTimeWeekends);
	}
	
	
	/**
	 * Checks if the space is free in given period for given client type
	 * @param period The period to check
	 * @param client A string representing the client type
	 * @return True if space is free in given period, false otherwise
	 * @throws InvalidClientException 
	 */
	public boolean isFree(TimeFrame period, String client) throws InvalidClientException {
		
		switch (client) {
		
		case "Individual":
			return isFreeIndividual(period);
				
		case "Club":
			return isFreeClub(period);

		default:
			throw new InvalidClientException("Client with type " + client + " does not exist.");
		}
		
	}

	/**
	 * Checks if the space is free on given period considering that the client
	 * is an individual client (which means that the given time frame will always have the same
	 * start and end date)
	 * @param period The period to check
	 * @return True if the space is free on given period
	 */
	private boolean isFreeIndividual(TimeFrame period) {
		LocalDate date = period.getStartDate();
		
		if (DateUtils.isHoliday(date) || DateUtils.isWeekend(date)) {
			
			return isFreeOn(date, period.getStartTime(), period.getEndTime(), 
					getStartTimeWeekends(), getEndTimeWeekends());
		}
		else {
			return isFreeOn(date, period.getStartTime(), period.getEndTime(), 
					getStartTimeWeekdays(), getEndTimeWeekdays());
		}
	}
	
	/**
	 * Checks if the space is free on given period considering that the client
	 * is a 'club' client
	 * @param period The period to check
	 * @return True if the space is free on given period
	 */
	private boolean isFreeClub(TimeFrame period) {
		if (!isFreeOnFirstDayClub(period)) {
			return false;
		}
		else {
			LocalDate curr = period.getStartDate().plusDays(1);
			while (curr.isBefore(period.getEndDate())) {
				for (OccupationRecord rec : records) {
					if (rec.isSameDay(curr)) {
						return false;
					}
				}
				curr = curr.plusDays(1);
			}
			
			return isFreeOnLastDay(period);
		}
	}
	
	/**
	 * Checks if the space is free on the first day of given period considering that the client
	 * is a 'club' client (which implies that the end time on the first day equals the closing time 
	 * of the space)
	 * @param period The period with the day to check
	 * @return True if the space is free on the first day of given period
	 */
	private boolean isFreeOnFirstDayClub(TimeFrame period) {
		LocalDate date = period.getStartDate();
		
		if (DateUtils.isHoliday(date) || DateUtils.isWeekend(date)) {
			
			return isFreeOn(date, period.getStartTime(), getEndTimeWeekends(), 
					getStartTimeWeekends(), getEndTimeWeekends());
		}
		else {
			return isFreeOn(date, period.getStartTime(), getEndTimeWeekdays(), 
					getStartTimeWeekdays(), getEndTimeWeekdays());
		}
	}
	
	
	/**
	 * Checks if the space is free on the first day of given period considering that the client
	 * is a 'club' client (which implies that the start time on the last day equals the opening time 
	 * of the space)
	 * @param period The period with the day to check
	 * @return if the space is free on the last day of given period
	 */
	private boolean isFreeOnLastDay(TimeFrame period) {
		LocalDate date = period.getEndDate();
		
		if (DateUtils.isHoliday(date) || DateUtils.isWeekend(date)) {
			
			return isFreeOn(date, getStartTimeWeekends(), period.getEndTime(), 
					getStartTimeWeekends(), getEndTimeWeekends());
		}
		else {
			return isFreeOn(date, getStartTimeWeekdays(), period.getEndTime(), 
					getStartTimeWeekdays(), getEndTimeWeekdays());
		}
	}
	
	/**
	 * Checks if the space is free on given date from given start time to given end time (considering 
	 * given space's opening and closing times). Note that this is necessary because the opening and 
	 * closing times vary and such variation is not computed in this function
	 * @param date The date to check
	 * @param startTime The start time
	 * @param endTime The end time
	 * @param spaceStartTime The space's opening time to consider
	 * @param spaceEndTime The space's closing time to consider
	 * @return True if the space is free on given date from given start time to given end time (considering 
	 * given space's opening and closing times)
	 */
	private boolean isFreeOn(LocalDate date, LocalTime startTime, LocalTime endTime, LocalTime spaceStartTime, LocalTime spaceEndTime) {
		
		LocalTime start = startTime.isAfter(spaceStartTime) ? startTime : spaceStartTime;
		LocalTime end = endTime.isBefore(spaceEndTime) ? endTime : spaceEndTime;
		
		for (OccupationRecord rec : records) {
			if (rec.isSameDay(date) && !rec.isFree(start, end)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if the practice of given sport is allowed in this space
	 * @param sport The name of the sport to check
	 * @return True If the practice of the sport is allowed
	 */
	public boolean allowsSport(String sport) {
		for (Sport s : allowedSports) {
			if (s.getName().equals(sport)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a sport to the list of allowed sports
	 * @param sport The sport to add to the list
	 */
	public void addAllowedSport(Sport sport) {
		allowedSports.add(sport);
	}
	
	/**
	 * Creates the occupation records of this space for a given activity
	 * @param activity The activity
	 * @param user The user that reserved the activity
	 */
	public void createRecords(Activity activity, String user) {
		TimeFrame period = activity.getPeriod();
		
		switch (user) {
		case "Individual":
			OccupationRecord rec = new OccupationRecord(period.getStartDate(), period.getStartTime(), 
					period.getEndTime(), activity, this);
			records.add(rec);
			break;
		case "Club":
			
			// first day
			createFirstDay(activity, period);
			
			// middle days
			createMiddleDays(activity, period);

			// last day
			createLastDay(activity, period);
			
			break;
		}
	}

	/**
	 * Creates a occupation record for the start date of the period
	 * @param activity The activity
	 * @param period The period of the activity
	 */
	private void createFirstDay(Activity activity, TimeFrame period) {
		LocalDate startDate = period.getStartDate();
		if (DateUtils.isWeekend(startDate) || DateUtils.isHoliday(startDate)) {
			OccupationRecord firstDay = new OccupationRecord(period.getStartDate(), 
					period.getStartTime(), getEndTimeWeekends(), activity, this);
			records.add(firstDay);
		}
		
		else {
			OccupationRecord firstDay = new OccupationRecord(period.getStartDate(), 
					period.getStartTime(), getEndTimeWeekdays(), activity, this);
			records.add(firstDay);
		}
	}

	/**
	 * Creates occupation records for all the dates in between the 
	 * start and end date of the period
	 * @param activity The activity
	 * @param period The period of the activity
	 */
	private void createMiddleDays(Activity activity, TimeFrame period) {
		LocalDate curr = period.getStartDate().plusDays(1);
		
		OccupationRecord currDay;
		while (curr.isBefore(period.getEndDate())) {
			if (DateUtils.isWeekend(curr) || DateUtils.isHoliday(curr)) {
				currDay = new OccupationRecord(curr, getStartTimeWeekends(), 
						getEndTimeWeekends(), activity, this);
			}
			else {
				currDay = new OccupationRecord(curr, getStartTimeWeekdays(), 
						getEndTimeWeekdays(), activity, this);
			}
			records.add(currDay);
			curr = curr.plusDays(1);
		}
	}

	/**
	 * Creates a occupation record for the end date of the period
	 * @param activity The activity
	 * @param period The period of the activity
	 */
	private void createLastDay(Activity activity, TimeFrame period) {
		LocalDate lastDate = period.getEndDate();
		if (DateUtils.isWeekend(lastDate) || DateUtils.isHoliday(lastDate)) {
			OccupationRecord lastDay = new OccupationRecord(period.getEndDate(), getStartTimeWeekends(), 
					period.getEndTime(), activity, this);
			records.add(lastDay);
		}
		
		else {
			OccupationRecord lastDay = new OccupationRecord(period.getEndDate(), getStartTimeWeekdays(), 
					period.getEndTime(), activity, this);
			records.add(lastDay);
		}
	}
	
	/**
	 * Returns a list of wrapper objects representing
	 * this space's occupation records within the given period
	 * @param period The period
	 * @return A list of wrapper objects representing
	 * this space's occupation records within the given period
	 */
//	public List<Record> getRecordsIn(TimeFrame period) {
//		List<Record> result = new ArrayList<>();
//		for (OccupationRecord rec : records) {
//			if (rec.isBetween(period)) {
//				result.add(rec.getRecord());
//			}
//		}
//		return result;
//	}

}
