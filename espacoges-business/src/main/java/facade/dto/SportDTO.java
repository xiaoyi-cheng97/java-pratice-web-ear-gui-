package facade.dto;

import java.io.Serializable;

import business.sports.SportType;

public class SportDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3535213442548591957L;
	
	
	private int id;
	private String name;
	private int minParticipantes;
	private int minMinutes;
	private SportType sportType;
	public SportDTO(int id, String name, int minParticipantes, int minMinutes, SportType sportType) {
		
		this.id = id;
		this.name = name;
		this.minParticipantes = minParticipantes;
		this.minMinutes = minMinutes;
		this.sportType = sportType;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getMinParticipantes() {
		return minParticipantes;
	}
	public int getMinMinutes() {
		return minMinutes;
	}
	public SportType getSportType() {
		return sportType;
	}
	
	
}
