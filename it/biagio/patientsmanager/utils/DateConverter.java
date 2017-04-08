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



package it.biagio.patientsmanager.utils;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * Class for simple utilities to convert a Data object into a String object
 * and viceversa.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class DateConverter
{
	/**
	 * The format of the date
	 */
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	/**
	 * The format of the time
	 */
	public static final String TIME_FORMAT = "hh:mm";
	
	/**
	 * The format of the date and time together
	 */
	public static final String DATE_TIME_FORMAT = DATE_FORMAT + " - " + TIME_FORMAT;
	
	/**
	 * The object used to convert data to String and viceversa
	 */
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);
	
	/**
	 * The object used to convert time to String and viceversa
	 */
	private static final SimpleDateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat(TIME_FORMAT);
	
	/**
	 * The object used to convert data and time to String and viceversa
	 */
	private static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT);
	
	
	
	/**
	 * Convert a string representing a date to a Date object
	 * 
	 * @param date - the string to be converted
	 * @return a Date object, null if the string is null or empty
	 * @throws ParseException - if the string doesn't match the DATE_FORMAT
	 */
	public static Date stringToDate(String date) throws ParseException {
		if (date == null || date.isEmpty())
			return null;
		return SIMPLE_DATE_FORMAT.parse(date);
	}
	
	/**
	 * Convert a string representing time to a Date object
	 * 
	 * @param date - the string to be converted
	 * @return a Date object, null if the string is null or empty
	 * @throws ParseException - if the string doesn't match the TIME_FORMAT
	 */
	public static Date stringToTime(String date) throws ParseException {
		if (date == null || date.isEmpty())
			return null;
		return SIMPLE_TIME_FORMAT.parse(date);
	}
	
	/**
	 * Convert a string representing a date and time to a Date object
	 * 
	 * @param date - the string to be converted
	 * @return a Date object, null if the string is null or empty
	 * @throws ParseException - if the string doesn't match the DATE_TIME_FORMAT
	 */
	public static Date stringToDateTime(String date) throws ParseException {
		if (date == null || date.isEmpty())
			return null;
		return SIMPLE_DATE_TIME_FORMAT.parse(date);
	}
	
	/**
	 * Convert a Date to a string according to the DATE_FORMAT
	 * 
	 * @param date - the date to be converted
	 * @return a String object, an empty string if the date is null
	 */
	public static String dateToString(Date date) {
		return date == null ? "" : SIMPLE_DATE_FORMAT.format(date);
	}
	
	/**
	 * Convert a Date to a string according to the DATE_FORMAT
	 * 
	 * @param date - the date to be converted
	 * @return a String object, an empty string if the date is null
	 */
	public static String timeToString(Date date) {
		return date == null ? "" : SIMPLE_TIME_FORMAT.format(date);
	}
	
	/**
	 * Convert a Date to a string according to the DATE_FORMAT
	 * 
	 * @param date - the date to be converted
	 * @return a String object, an empty string if the date is null
	 */
	public static String dateTimeToString(Date date) {
		return date == null ? "" : SIMPLE_DATE_TIME_FORMAT.format(date);
	}
}