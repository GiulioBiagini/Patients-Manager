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



package it.biagio.patientsmanager.model.entities.info.account;



import java.util.Date;

import it.biagio.patientsmanager.model.entities.info.AInfo;



/**
 * Class for the info related to the account management.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class AccountInfo extends AInfo
{
	/** The creation date */
	private Date creationDate;
	
	/** The closing date */
	private Date closingDate;
	
	
	
	/**
	 * Create a new info object for the account management
	 * 
	 * @param creationDate - the date of creation (can't be null)
	 * @param closingDate - the date of closing, null if the account is still open
	 */
	public AccountInfo(Date creationDate, Date closingDate) {
		setCreationDate(creationDate);
		setClosingDate(closingDate);
	}
	
	
	
	/**
	 * Set a creation date
	 * 
	 * @param creationDate - the date of creation (can't be null)
	 */
	public void setCreationDate(Date creationDate) {
		if (creationDate == null)
			throw new IllegalArgumentException(
				"Specificare una data di creazione del profilo"
			);
		this.creationDate = creationDate;
	}
	
	/**
	 * Set a closing date
	 * 
	 * @param closingDate - the date of closing, null if the account is still open
	 */
	public void setClosingDate(Date closingDate) {
		if (closingDate != null && closingDate.before(creationDate))
			throw new IllegalArgumentException(
				"Specificare una data di chiusura del profilo non precedente alla data di creazione dello stesso"
			);
		this.closingDate = closingDate;
	}
	
	
	
	/**
	 * Get the creation date
	 * 
	 * @return the date of creation
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * Get the closing date
	 * 
	 * @return the date of closing, null if the account is still open
	 */
	public Date getClosingDate() {
		return closingDate;
	}
}