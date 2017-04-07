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



/**
 * Class for the info related to the patient's personal data.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class PatientPersonalInfo extends APersonalInfo
{
	/** The profession */
	private String profession;
	
	
	
	/**
	 * Create a new info object related to the patient's personal data
	 * 
	 * @param surname - the surname (can't be null/empty)
	 * @param name - the name (can't be null/empty)
	 * @param birthdate - the birthdate (can't be null/empty)
	 * @param isMale - true if the gender is male, false if it's female
	 * @param taxcode - the taxcode (can't be null/empty)
	 * @param profession - the profession (can't be null/empty)
	 * @throws IllegalArgumentException - if surname, name, taxcode or profession
	 * are null or empty, if birthdate is null
	 */
	public PatientPersonalInfo(String surname, String name, Date birthdate, boolean isMale, String taxcode, String profession) {
		super(surname, name, birthdate, isMale, taxcode);
		setProfession(profession);
	}
	
	
	
	/**
	 * Set the birthdate
	 * 
	 * @param birthdate - the birthdate (can't be null/empty)
	 * @throws IllegalArgumentException - if birthdate is null
	 */
	@Override
	public void setBirthdate(Date birthdate) throws IllegalArgumentException {
		if (birthdate == null)
			throw new IllegalArgumentException(
				"data di nascita non presente"
			);
		this.birthdate = birthdate;
	}
	
	/**
	 * Set the taxcode
	 * 
	 * @param taxcode - the taxcode (can't be null/empty)
	 * @throws IllegalArgumentException - if taxcode is null or empty
	 */
	@Override
	public void setTaxcode(String taxcode) {
		if (isEmptyString(taxcode))
			throw new IllegalArgumentException(
				"codice fiscale non presente"
			);
		this.taxcode = taxcode.toUpperCase();
	}
	
	/**
	 * Set the profession
	 * 
	 * @param profession - the profession (can't be null/empty)
	 * @throws IllegalArgumentException - if profession is null
	 */
	public void setProfession(String profession) {
		if (isEmptyString(profession))
			throw new IllegalArgumentException(
				"professione non presente"
			);
		this.profession = profession;
	}
	
	
	
	/**
	 * Get the profession
	 * 
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}
}