package model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Language {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int languageId;
	
	private String name;
	
	public Language() {}

	public Language(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLanguageId() {
		return languageId;
	}
	
	@Override
	public String toString() {
	    return String.format("%s[languageId=%d]", getClass().getSimpleName(), getLanguageId());
	}
}
