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
import it.biagio.patientsmanager.model.entities.info.personal.APersonalInfo;



/**
 * Class for the abstract entity.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public abstract class AEntity
{
	/** The info object related to the account management */
	private AccountInfo accountInfo;
	
	/** The info object related to the personal data */
	private APersonalInfo personalInfo;
	
	/** The info object related to the address */
	private AddressInfo addressInfo;
	
	/** The info object related to the contacts */
	private ContactsInfo contactsInfo;
	
	
	
	/**
	 * Create a new abstract entity
	 * 
	 * @param accountInfo - the info related to the account management (can't be null)
	 * @param personalInfo - the info related to the personal data (can't be null)
	 * @param addressInfo - the info related to the address (can't be null)
	 * @param contactsInfo - the info related to the contacts (can't be null)
	 * @throws IllegalArgumentException - if info related to account, personal data,
	 * address and contacts are null
	 */
	public AEntity(AccountInfo accountInfo, APersonalInfo personalInfo, AddressInfo addressInfo, ContactsInfo contactsInfo) throws IllegalArgumentException {
		setAccountInfo(accountInfo);
		setPersonalInfo(personalInfo);
		setAddressInfo(addressInfo);
		setContactsInfo(contactsInfo);
	}
	
	
	
	/**
	 * Set the info related to the account management
	 * 
	 * @param accountInfo - the info related to the account management (can't be null)
	 * @throws IllegalArgumentException - if info related to account is null
	 */
	public void setAccountInfo(AccountInfo accountInfo) throws IllegalArgumentException {
		if (accountInfo == null)
			throw new IllegalArgumentException(
				"informazioni relative all'account non presenti"
			);
		this.accountInfo = accountInfo;
	}
	
	/**
	 * Set the info related to the personal data
	 * 
	 * @param personalInfo - the info related to the personal data (can't be null)
	 * @throws IllegalArgumentException - if info related to personal data is null
	 */
	public void setPersonalInfo(APersonalInfo personalInfo) throws IllegalArgumentException {
		if (personalInfo == null)
			throw new IllegalArgumentException(
				"informazioni personali non presenti"
			);
		this.personalInfo = personalInfo;
	}
	
	/**
	 * Set the info related to the address
	 * 
	 * @param addressInfo - the info related to the address (can't be null)
	 * @throws IllegalArgumentException - if info related to address is null
	 */
	public void setAddressInfo(AddressInfo addressInfo) throws IllegalArgumentException {
		if (addressInfo == null)
			throw new IllegalArgumentException(
				"informazioni relative all'indirizzo non presenti"
			);
		this.addressInfo = addressInfo;
	}
	
	/**
	 * Set the info related to the contacts
	 * 
	 * @param contactsInfo - the info related to the contacts (can't be null)
	 * @throws IllegalArgumentException - if info related to contacts is null
	 */
	public void setContactsInfo(ContactsInfo contactsInfo) throws IllegalArgumentException {
		if (contactsInfo == null)
			throw new IllegalArgumentException(
				"informazioni relative ai contatti non presenti"
			);
		this.contactsInfo = contactsInfo;
	}
	
	
	
	/**
	 * Get the info related to the account management
	 * 
	 * @return the info related to the account management
	 */
	public AccountInfo getAccountInfo() {
		return accountInfo;
	}
	
	/**
	 * Get the info related to the personal data
	 * 
	 * @return the info related to the personal data
	 */
	public APersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	
	/**
	 * Get the info related to the address
	 * 
	 * @return the info related to the address
	 */
	public AddressInfo getAddressInfo() {
		return addressInfo;
	}
	
	/**
	 * Get the info related to the contacts
	 * 
	 * @return the info related to the contacts
	 */
	public ContactsInfo getContactsInfo() {
		return contactsInfo;
	}
}