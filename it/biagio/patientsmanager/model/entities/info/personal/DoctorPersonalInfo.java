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
 * Class for the info related to the doctor's personal data.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class DoctorPersonalInfo extends APersonalInfo
{
	/** The specialization */
	private String specialization;
	
	
	
	/**
	 * Create a new info object related to the doctor's personal data
	 * 
	 * @param surname - the surname
	 * @param name - the name
	 * @param birthdate - the birthdate
	 * @param isMale - if the gender is male
	 * @param taxcode - the taxcode
	 * @param specialization - the specialization
	 * @throws IllegalArgumentException - if surname or name are null or empty
	 */
	public DoctorPersonalInfo(String surname, String name, Date birthdate, boolean isMale, String taxcode, String specialization) {
		super(surname, name, birthdate, isMale, taxcode);
		setSpecialization(specialization);
	}
	
	
	
	/**
	 * Set the birthdate
	 * 
	 * @param birthdare - the birthdate
	 */
	@Override
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	/**
	 * Set the taxcode
	 * 
	 * @param taxcode - the taxcode
	 */
	@Override
	public void setTaxcode(String taxcode) {
		this.taxcode = trim(taxcode).toUpperCase();
	}
	
	/**
	 * Set the specialization
	 * 
	 * @param specialization - the specialization
	 */
	public void setSpecialization(String specialization) {
		this.specialization = trim(specialization);
	}
	
	
	
	/**
	 * Get the specialization
	 * 
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}
}