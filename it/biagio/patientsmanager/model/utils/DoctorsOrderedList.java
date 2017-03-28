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

import it.biagio.patientsmanager.model.entities.Doctor;
import it.biagio.patientsmanager.model.entities.info.personal.DoctorPersonalInfo;



/**
 * Class for the ArrayList of doctors stored ordered by surname and name.
 * The add() method uses an algorithm based on the binarySearch, so that each
 * doctor can be added in O(log n) time and the list is always kept ordered.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
@SuppressWarnings("serial")
public class DoctorsOrderedList extends ArrayList<Doctor>
{
	/**
	 * Create an empty ordered doctors list
	 */
	public DoctorsOrderedList() {
		super();
	}
	
	
	
	/**
	 * Check if doctor a is lower than (comes before) doctor b
	 * 
	 * @param a - the first doctor
	 * @param b - the second doctor
	 * @return true if the first doctor (a) il lower than the second one (b)
	 */
	private static boolean lower(Doctor a, Doctor b) {
		DoctorPersonalInfo aa = a.getPersonalInfo();
		DoctorPersonalInfo bb = b.getPersonalInfo();
		return (aa.getSurname() + aa.getName()).compareToIgnoreCase(bb.getSurname() + bb.getName()) < 0;
	}
	
	
	
	/**
	 * Add the specified doctor in an ordered way (surname + name)
	 * 
	 * @param doctor - the doctor to be added
	 * @return true if the doctor is not null and is added, false otherwise
	 */
	@Override
	public boolean add(Doctor doctor) {
		if (doctor == null)
			return false;
		addOrdered(doctor, 0, size() - 1);
		return true;
	}
	
	/**
	 * Add a doctor to this ArrayList in an ordered way accordind
	 * to the lower() function
	 * 
	 * @param doctor - the doctor to be added
	 * @param s - the first index of this list
	 * @param e - the last index of this list
	 */
	private void addOrdered(Doctor doctor, int s, int e) {
		if (s > e)
			add(s, doctor);
		else {
			int m = (s + e) / 2;
			if (lower(get(m), doctor))
				addOrdered(doctor, m + 1, e);
			else
				addOrdered(doctor, s, m - 1);
		}
	}
}