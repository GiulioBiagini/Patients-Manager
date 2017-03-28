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



package it.biagio.patientsmanager.model.entities;



import it.biagio.patientsmanager.model.entities.info.account.AccountInfo;
import it.biagio.patientsmanager.model.entities.info.address.AddressInfo;
import it.biagio.patientsmanager.model.entities.info.contacts.ContactsInfo;
import it.biagio.patientsmanager.model.entities.info.personal.DoctorPersonalInfo;



/**
 * Class for the doctor.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class Doctor extends AEntity
{
	/**
	 * Create a new doctor
	 * 
	 * @param accountInfo - the info related to the account (can't be null)
	 * @param personalInfo - the info related to the doctor's personal data (can't be null)
	 * @param addressInfo - the info related to the address (can't be null)
	 * @param contactsInfo - the info related to the contacts (can't be null)
	 */
	public Doctor(AccountInfo accountInfo, DoctorPersonalInfo personalInfo, AddressInfo addressInfo, ContactsInfo contactsInfo) {
		super(accountInfo, personalInfo, addressInfo, contactsInfo);
	}
	
	
	
	/**
	 * Get the info related to the doctor's personal data
	 * 
	 * @return the info related to the doctor's personal data
	 */
	@Override
	public DoctorPersonalInfo getPersonalInfo() {
		return (DoctorPersonalInfo) super.getPersonalInfo();
	}
	
	
	
	@Override
	public String toString() {
		DoctorPersonalInfo personalInfo = getPersonalInfo();
		return personalInfo.getSurname() + personalInfo.getName() +
			(((DoctorPersonalInfo) personalInfo).getSpecialization().isEmpty() ? "" : " (" + ((DoctorPersonalInfo) personalInfo).getSpecialization() + ")");
	}
}