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



package it.biagio.patientsmanager.model.utils;



import java.util.ArrayList;

import it.biagio.patientsmanager.model.entities.Patient;
import it.biagio.patientsmanager.model.entities.info.personal.PatientPersonalInfo;



/**
 * Class for the ArrayList of patients stored ordered by surname and name.
 * The add() method uses an algorithm based on the binarySearch, so that each
 * patient can be added in O(log n) time and the list is always kept ordered.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
@SuppressWarnings("serial")
public class PatientsOrderedList extends ArrayList<Patient>
{
	/**
	 * Create an empty ordered patients list
	 */
	public PatientsOrderedList() {
		super();
	}
	
	
	
	/**
	 * Check if patient a is lower than (comes before) patient b
	 * 
	 * @param a - the first patient
	 * @param b - the second patient
	 * @return true if the first patient (a) il lower than the second one (b)
	 */
	private static boolean lower(Patient a, Patient b) {
		PatientPersonalInfo aa = a.getPersonalInfo();
		PatientPersonalInfo bb = b.getPersonalInfo();
		return (aa.getSurname() + aa.getName()).compareToIgnoreCase(bb.getSurname() + bb.getName()) < 0;
	}
	
	
	
	/**
	 * Add the specified patient in an ordered way (surname + name)
	 * 
	 * @param patient - the patient to be added
	 * @return true if the patient is not null and is added, false otherwise
	 */
	@Override
	public boolean add(Patient patient) {
		if (patient == null)
			return false;
		addOrdered(patient, 0, size() - 1);
		return true;
	}
	
	/**
	 * Add a patient to this ArrayList in an ordered way accordind
	 * to the lower() function
	 * 
	 * @param patient - the patient to be added
	 * @param s - the first index of this list
	 * @param e - the last index of this list
	 */
	private void addOrdered(Patient patient, int s, int e) {
		if (s > e)
			add(s, patient);
		else {
			int m = (s + e) / 2;
			if (lower(get(m), patient))
				addOrdered(patient, m + 1, e);
			else
				addOrdered(patient, s, m - 1);
		}
	}
}