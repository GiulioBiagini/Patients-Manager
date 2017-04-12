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



package it.biagio.patientsmanager.model.entities.info.contacts;



import it.biagio.patientsmanager.model.entities.info.AInfo;



/**
 * Class for the info related to the contacts.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class ContactsInfo extends AInfo
{
	/** The telephone number */
	private String telephoneNumber;
	
	/** The mobile number */
	private String mobileNumber;
	
	
	
	/**
	 * Create a new info object for the contacts
	 * 
	 * @param telephoneNumber - the telephone number
	 * @param mobileNumber - the mobile number
	 */
	public ContactsInfo(String telephoneNumber, String mobileNumber) {
		setTelephoneNumber(telephoneNumber);
		setMobileNumber(mobileNumber);
	}
	
	
	
	/**
	 * Set the telephone number
	 * 
	 * @param telephoneNumber - the telephone number
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = trim(telephoneNumber);
	}
	
	/**
	 * Set the mobile number
	 * 
	 * @param mobileNumber - the mobile number
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = trim(mobileNumber);
	}
	
	
	
	/**
	 * Get the telephone number
	 * 
	 * @return the telephone number
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	
	/**
	 * Get the mobile number
	 * 
	 * @return the mobile number
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
}