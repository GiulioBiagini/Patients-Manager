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



package it.biagio.patientsmanager.model.entities.info.address;



import it.biagio.patientsmanager.model.entities.info.AInfo;



/**
 * Class for the info related to the address.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class AddressInfo extends AInfo
{
	/** The address */
	private String address;
	
	/** The civic number */
	private String civicNumber;
	
	/** The city */
	private String city;
	
	/** The zip code */
	private String zipCode;
	
	/** The province */
	private String province;
	
	
	
	/**
	 * Create a new info object for the address
	 * 
	 * @param address - the address
	 * @param civicNumber - the civic number
	 * @param city - the city
	 * @param zipCode - the zip code
	 * @param province - the province
	 */
	public AddressInfo(String address, String civicNumber, String city, String zipCode, String province) {
		setAddress(address);
		setCivicNumber(civicNumber);
		setCity(city);
		setZipCode(zipCode);
		setProvince(province);
	}
	
	
	
	/**
	 * Set the address
	 * 
	 * @param address - the address
	 */
	public void setAddress(String address) {
		this.address = trim(address);
	}
	
	/**
	 * Ste the civic number
	 * 
	 * @param civicNumber - the civic number
	 */
	public void setCivicNumber(String civicNumber) {
		this.civicNumber = trim(civicNumber);
	}
	
	/**
	 * Ste the city
	 * 
	 * @param city - the city
	 */
	public void setCity(String city) {
		this.city = trim(city);
	}
	
	/**
	 * Set the zip code
	 * 
	 * @param zipCode - the zip code
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = trim(zipCode);
	}
	
	/**
	 * Set the province
	 * 
	 * @param province - the province
	 */
	public void setProvince(String province) {
		this.province = trim(province);
	}
	
	
	
	/**
	 * Get the address
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Get the civic number
	 * 
	 * @return the civic number
	 */
	public String getCivicNumber() {
		return civicNumber;
	}
	
	/**
	 * Get the city
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Get the zip code
	 * 
	 * @return the zip code
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * Get the province
	 * 
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
}