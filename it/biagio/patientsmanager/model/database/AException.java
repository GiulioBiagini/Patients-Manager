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



package it.biagio.patientsmanager.model.database;



/**
 * Class for the exceptions.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
@SuppressWarnings("serial")
public abstract class AException extends Exception
{
	/**
	 * String for line separator
	 */
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	
	
	/**
	 * Create an exception
	 * 
	 * @param subject - who raise the exception
	 * @param message - the message
	 * @param cause - the cause
	 */
	public AException(String subject, String message, String cause) {
		super(subject + " - " + message + ":" + LINE_SEPARATOR + cause);
	}
}