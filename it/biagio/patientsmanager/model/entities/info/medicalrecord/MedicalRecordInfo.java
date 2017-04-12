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



package it.biagio.patientsmanager.model.entities.info.medicalrecord;



import java.util.Date;

import it.biagio.patientsmanager.model.entities.info.AInfo;



/**
 * Class for the info related to the medical record.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class MedicalRecordInfo extends AInfo
{
	/** The type of medical record */
	private String type;
	
	/** The medical record number */
	private String number;
	
	/** The date of the las visit */
	private Date lastVisitDate;
	
	
	
	/**
	 * Create a new info object for the medical record
	 * 
	 * @param type - the type of medical record (can't be null/empty)
	 * @param number - the medical record number (can't be null/empty)
	 * @param lastVisitDate - the date of the last visit, null if none visit has been done
	 * @throws IllegalArgumentException - if type or number are null or empty
	 */
	public MedicalRecordInfo(String type, String number, Date lastVisitDate) throws IllegalArgumentException {
		setType(type);
		setNumber(number);
		setLastVisitDate(lastVisitDate);
	}
	
	
	
	/**
	 * Set the type of medical record
	 * 
	 * @param type - the type of medical record (can't be null/empty)
	 * @throws IllegalArgumentException - if type is null or empty
	 */
	public void setType(String type) throws IllegalArgumentException {
		this.type = trim(type);
		if (this.type.isEmpty())
			throw new IllegalArgumentException(
				"tipologia della cartella non presente"
			);
	}
	
	/**
	 * Ste the medical record number
	 * 
	 * @param number - the medical record number (can't be null/empty)
	 * @throws IllegalArgumentException - if number is null or empty
	 */
	public void setNumber(String number) throws IllegalArgumentException {
		this.number = trim(number);
		if (this.number.isEmpty())
			throw new IllegalArgumentException(
				"numero della cartella clinica non presente"
			);
	}
	
	/**
	 * Set the date of the last visit
	 * 
	 * @param lastVisitDate - the date of the last visit, null if none visit has been done
	 */
	public void setLastVisitDate(Date lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}
	
	
	
	/**
	 * Get the type of the medical record
	 * 
	 * @return the type of the medical record
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Get the medical record number
	 * 
	 * @return the medical record number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * Get the date of the last visit
	 * 
	 * @return the date of the last visit, null if none visit has been made
	 */
	public Date getLastVisitDate() {
		return lastVisitDate;
	}
}