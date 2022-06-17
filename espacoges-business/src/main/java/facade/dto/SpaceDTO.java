package facade.dto;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import business.spaces.OccupationRecord;
import business.sports.Sport;

public class SpaceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -743458663641461193L;

	
	private int id;
	private String name;
	private int maxCapacity;
	private double price;
	private LocalTime startTimeWeekdays;
	private LocalTime endTimeWeekdays;
	private LocalTime startTimeWeekends;
	private LocalTime endTimeWeekends;
	private List<Sport> allowedSports;
	private List<OccupationRecord> records;
	public SpaceDTO(int id, String name, int maxCapacity, double price, LocalTime localTime, LocalTime localTime2,
			LocalTime localTime3, LocalTime localTime4, List<Sport> allowedSports, List<OccupationRecord> records) {
		this.id = id;
		this.name = name;
		this.maxCapacity = maxCapacity;
		this.price = price;
		this.startTimeWeekdays = localTime;
		this.endTimeWeekdays = localTime2;
		this.startTimeWeekends = localTime3;
		this.endTimeWeekends = localTime4;
		this.allowedSports = allowedSports;
		this.records = records;
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
	public LocalTime getStartTimeWeekdays() {
		return startTimeWeekdays;
	}
	public LocalTime getEndTimeWeekdays() {
		return endTimeWeekdays;
	}
	public LocalTime getStartTimeWeekends() {
		return startTimeWeekends;
	}
	public LocalTime getEndTimeWeekends() {
		return endTimeWeekends;
	}
	public List<Sport> getAllowedSports() {
		return allowedSports;
	}
	public List<OccupationRecord> getRecords() {
		return records;
	}
	
	
}
