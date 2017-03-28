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



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * Class for the log.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class Log
{
	private static final File LOG_FILE = new File("." + File.separator + "log");
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]");
	
	
	
	private static final String INFO = "info   ";
	
	private static final String WARNINIG = "WARNING";
	
	private static final String ERROR = "ERROR  ";
	
	
	
	private static BufferedWriter bufferedWriter;
	
	
	
	static {
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(LOG_FILE, true));
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException ex) {
			System.err.println("Impossibile scrivere sul file di log: " + ex.getMessage());
		}
	}
	
	
	
	private static synchronized void write(String type, String className, String operation, String message) {
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(LOG_FILE, true));
			bufferedWriter.write(DATE_FORMAT.format(new Date()) + " " + type + " " + className + " -> " + operation + ": " + message);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException ex) {
			System.err.println("Impossibile scrivere sul file di log: " + ex.getMessage());
		}
	}
	
	
	
	public static void i(String className, String operation, String message) {
		write(INFO, className, operation, message);
	}
	
	public static void w(String className, String operation, String message) {
		write(WARNINIG, className, operation, message);
	}
	
	public static void e(String className, String operation, String message) {
		write(ERROR, className, operation, message);
	}
}