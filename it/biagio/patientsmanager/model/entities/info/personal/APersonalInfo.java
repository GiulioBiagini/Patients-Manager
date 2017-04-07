/*
 * Patients Manager is a software which allows doctors to manage their
 * patients: they can be registered, edited, deleted and easy-searched
 * thanks to some filters options. A nice summary patient-information
 * panel is also provided.
 * 
 * Copyright (C) 2017 - Giulio Biagini - giulio.biagini90@gmail.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */



package it.biagio.patientsmanager.model.entities.info.personal;



import java.util.Date;

import it.biagio.patientsmanager.model.entities.info.AInfo;



/**
 * Class for the info related to the personal data.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public abstract class APersonalInfo extends AInfo
{
	/** The surname */
	private String surname;
	
	/** The name */
	private String name;
	
	/** The birthdate */
	protected Date birthdate;
	
	/** If the gender is male */
	private boolean isMale;
	
	/** The taxcode */
	protected String taxcode;
	
	
	
	/**
	 * Create a new info object related to the user's personal data
	 * 
	 * @param surname - the surname (can't be null/empty)
	 * @param name - the name (can't be null/empty)
	 * @param birthdate - the birthdate
	 * @param isMale - true if the gender is male, false if it's female
	 * @param taxcode - the taxcode
	 * @throws IllegalArgumentException - if surname or name are null or empty
	 */
	public APersonalInfo(String surname, String name, Date birthdate, boolean isMale, String taxcode) {
		setSurname(surname);
		setName(name);
		setBirthdate(birthdate);
		setMale(isMale);
		setTaxcode(taxcode);
	}
	
	
	
	/**
	 * Set the surname
	 * 
	 * @param surname - the surname (can't be null/empty)
	 * @throws IllegalArgumentException - if surname is null or empty
	 */
	public void setSurname(String surname) {
		if (isEmptyString(surname))
			throw new IllegalArgumentException(
				"cognome non presente"
			);
		this.surname = surname;
	}
	
	/**
	 * Set the name
	 * 
	 * @param name - the name (can't be null/empty)
	 * @throws IllegalArgumentException - if name is null or empty
	 */
	public void setName(String name) {
		if (isEmptyString(name))
			throw new IllegalArgumentException(
				"nome non presente"
			);
		this.name = name;
	}
	
	public abstract void setBirthdate(Date birthdate);
	
	/**
	 * Set if gender is male
	 * 
	 * @param isMale - true if gender is male, false if it'is female
	 */
	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}
	
	public abstract void setTaxcode(String taxcode);
	
	
	
	/**
	 * Get the surname
	 * 
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Get the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the birthdate
	 * 
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}
	
	/**
	 * Check if gender is male
	 * 
	 * @return true if gender is male, false if it's female
	 */
	public boolean isMale() {
		return isMale;
	}
	
	/**
	 * Get the taxcode
	 * 
	 * @return the taxcode
	 */
	public String getTaxcode() {
		return taxcode;
	}
}