package presentation.fx.model;

import facade.exceptions.ApplicationException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RequestModel {

	private final IntegerProperty code;
	private final StringProperty date;
	private final StringProperty begin;
	private final StringProperty end;
	private final StringProperty description;

	public RequestModel() {
		this.code =  new SimpleIntegerProperty();
		this.date = new SimpleStringProperty();
		this.begin = new SimpleStringProperty();
		this.end = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
	
	}

	public int getCode() {
		return code.get();
	}
	
	public void setCode(int code) {
		this.code.set(code);
	}
	
	public IntegerProperty codeProperty() {
		return code;
	}
	
	public String getDate() {
		return date.get();
	}
	
	public void setDate(String date ) {
		this.date.set(date);;
	}
	
	public StringProperty dateProperty() {
		return date;
	}

	public String getBegin() {
		return begin.get();
	}
	
	public void setBegin(String begin ) {
		this.begin.set(begin);;
	}
	
	public StringProperty beginProperty() {
		return begin;
	}
	
	public String getEnd() {
		return end.get();
	}
	
	public void setEnd(String end ) {
		this.end.set(end);;
	}
	
	public StringProperty endProperty() {
		return end;
	}
	
	public String getdescription() {
		return description.get();
	}
	
	public void setDescription(String description ) {
		this.description.set(description);;
	}
	
	public StringProperty descriptionProperty() {
		return description;
	}

	public void clearProperties() {
		description.set("");
		code.set(0);
		date.set("");
		begin.set("");
		end.set("");
	}
}
