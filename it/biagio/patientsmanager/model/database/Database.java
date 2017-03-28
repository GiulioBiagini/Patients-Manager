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



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import it.biagio.patientsmanager.model.entities.Doctor;
import it.biagio.patientsmanager.model.entities.Patient;
import it.biagio.patientsmanager.model.utils.DoctorsOrderedList;
import it.biagio.patientsmanager.model.utils.PatientsOrderedList;



/**
 * Class for the database.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class Database
{
	private static final String LOG_CLASS_NAME = IO.class.getName();
	
	private static final String LOG_DATABASE_INIT = "database init";
	
	
	
	private static final File DATABASE_DIR = new File("." + File.separator + "database");
	
	private static final File PATIENTS_DIR = new File(DATABASE_DIR + File.separator + "patients");
	
	private static final File DOCTORS_DIR = new File(DATABASE_DIR + File.separator + "doctors");
	
	
	
	private static HashMap<String, Patient> patientsHashMap;
	
	private static HashMap<String, Doctor> doctorsHashMap;
	
	private static PatientsOrderedList patientsOrderedList;
	
	private static DoctorsOrderedList doctorsOrderedList;
	
	
	
	/*
	 * ID MANAGEMENT
	 */
	
	private static final String getPatientId(Patient patient) {
		return patient == null ? null : patient.getPersonalInfo().getTaxcode();
	}
	
	private static final String getDoctorId(Doctor doctor) {
		return doctor == null ? null : doctor.getPersonalInfo().getSurname() + doctor.getPersonalInfo().getName();
	}
	
	
	
	/*
	 * INIT
	 */
	
	/**
	 * Init the database checking the related dirs and files, reading the
	 * patients and doctors stored. If the structure does not exist, create it
	 * 
	 * @return false if errors in the dirs structure, true otherwise
	 * @throws IOException - if an I/O errors while reading patients/doctors
	 */
	public static synchronized boolean init() throws IOException {
		// check the database dir
		if (!DATABASE_DIR.exists()) {
			Log.w(LOG_CLASS_NAME, LOG_DATABASE_INIT, "directory " + DATABASE_DIR.getAbsolutePath() + " does not exist");
			if (!DATABASE_DIR.mkdirs()) {
				Log.e(LOG_CLASS_NAME, LOG_DATABASE_INIT, "unable to create the directory " + DATABASE_DIR.getAbsolutePath());
				return false;
			} else
				Log.i(LOG_CLASS_NAME, LOG_DATABASE_INIT, "directory " + DATABASE_DIR.getAbsolutePath() + " created");
		} else if (!DATABASE_DIR.isDirectory()) {
			Log.e(LOG_CLASS_NAME, LOG_DATABASE_INIT, "directory " + DATABASE_DIR.getAbsolutePath() + " is not a folder");
			return false;
		}
		
		// check the patients dir
		if (!PATIENTS_DIR.exists()) {
			Log.w(LOG_CLASS_NAME, LOG_DATABASE_INIT, "directory " + PATIENTS_DIR.getAbsolutePath() + " does not exist");
			if (!PATIENTS_DIR.mkdirs()) {
				Log.e(LOG_CLASS_NAME, LOG_DATABASE_INIT, "unable to create the directory " + PATIENTS_DIR.getAbsolutePath());
				return false;
			} else
				Log.i(LOG_CLASS_NAME, LOG_DATABASE_INIT, "directory " + PATIENTS_DIR.getAbsolutePath() + " created");
		} else if (!PATIENTS_DIR.isDirectory()) {
			Log.e(LOG_CLASS_NAME, LOG_DATABASE_INIT, "directory " + PATIENTS_DIR.getAbsolutePath() + " is not a folder");
			return false;
		}
		
		// check the doctors dir
		if (!DOCTORS_DIR.exists()) {
			Log.w(LOG_CLASS_NAME, LOG_DATABASE_INIT, "directory " + DOCTORS_DIR.getAbsolutePath() + " does not exist");
			if (!DOCTORS_DIR.mkdirs()) {
				Log.e(LOG_CLASS_NAME, LOG_DATABASE_INIT, "unable to create the directory " + DOCTORS_DIR.getAbsolutePath());
				return false;
			} else
				Log.i(LOG_CLASS_NAME, LOG_DATABASE_INIT, "directory " + DOCTORS_DIR.getAbsolutePath() + " created");
		} else if (!DOCTORS_DIR.isDirectory()) {
			Log.e(LOG_CLASS_NAME, LOG_DATABASE_INIT, "directory " + DOCTORS_DIR.getAbsolutePath() + " is not a folder");
			return false;
		}
		
		// read doctors
		for (File file : DOCTORS_DIR.listFiles()) {
			Doctor doctor = IO.readDoctor(file);
			if (doctor != null) {
				doctorsHashMap.put(getDoctorId(doctor), doctor);
				doctorsOrderedList.add(doctor);
			}
		}
		// read patients
		for (File file : PATIENTS_DIR.listFiles()) {
			Patient patient = IO.readPatient(file, doctorsHashMap);
			if (patient != null) {
				patientsHashMap.put(getPatientId(patient), patient);
				patientsOrderedList.add(patient);
			}
		}
		
		return true;
	}
	
	
	
	/*
	 * ADD PATIENT/DOCTOR
	 */
	
	public static synchronized boolean addPatient(Patient patient) throws IOException {
		String patientId = getPatientId(patient);
		// patient == null
		if (patientId == null)
			return false;
		// patient already exists
		if (patientsHashMap.get(patientId) != null)
			throw new IllegalArgumentException("Il paziente da creare (codice fiscale) è già presente all'interno del database");
		
		// unable to create the patient
		if (!IO.createPatient(patient, getDoctorId(patient.getReferringPhysician()), patientId, PATIENTS_DIR))
			return false;
		// patient created
		patientsHashMap.put(patientId, patient);
		patientsOrderedList.add(patient);
		
		return true;
	}
	
	public static synchronized boolean addDoctor(Doctor doctor) throws IOException {
		String doctorId = getDoctorId(doctor);
		// doctor == null
		if (doctorId == null)
			return false;
		// doctor already exists
		if (doctorsHashMap.get(doctorId) != null)
			throw new IllegalArgumentException("Il medico da creare (cognome + nome) è già presente all'interno del database");
		
		// unable to create doctor
		if (!IO.createDoctor(doctor, doctorId, DOCTORS_DIR))
			return false;
		// doctor created
		doctorsHashMap.put(doctorId, doctor);
		doctorsOrderedList.add(doctor);
		
		return true;
	}
	
	
	
	/*
	 * EDIT PATIENT/DOCTOR
	 */
	
	public static synchronized boolean editPatient(Patient oldPatient, Patient newPatient) throws IOException {
		String oldPatientId = getPatientId(oldPatient);
		String newPatientId = getPatientId(newPatient);
		// oldPatient == null || newPatient == null
		if (oldPatientId == null || newPatientId == null)
			return false;
		// old patient does not exist
		if (patientsHashMap.get(oldPatientId) == null)
			throw new IllegalArgumentException("Il paziente da modificare (codice fiscale) non è presente all'interno del database");
		// new patient already exists
		if (patientsHashMap.get(newPatientId) != null)
			throw new IllegalArgumentException("I dati del nuovo paziente (codice fiscale) sono già presenti all'interno del database");
		
		// different ids
		if (!oldPatientId.equals(newPatientId)) {
			// unable to delete patient
			if (!IO.deletePatient(oldPatientId, PATIENTS_DIR))
				return false;
			// patient deleted
			patientsHashMap.remove(oldPatientId);
			patientsOrderedList.remove(oldPatient);
		}
		// unable to create patient
		if (!IO.createPatient(newPatient, getDoctorId(newPatient.getReferringPhysician()), newPatientId, PATIENTS_DIR))
			return false;
		// patient created
		patientsHashMap.put(newPatientId, newPatient);
		patientsOrderedList.add(newPatient);
		
		return true;
	}
	
	public static synchronized boolean editDoctor(Doctor oldDoctor, Doctor newDoctor) throws IOException {
		String oldDoctorId = getDoctorId(oldDoctor);
		String newDoctorId = getDoctorId(newDoctor);
		// oldDoctor == null || newDoctor == null
		if (oldDoctorId == null || newDoctorId == null)
			return false;
		// old doctor does not exist
		if (doctorsHashMap.get(oldDoctorId) == null)
			throw new IllegalArgumentException("Il medico da modificare (nome + cognome) non è presente all'interno del database");
		// new doctor already exists
		if (doctorsHashMap.get(newDoctorId) != null)
			throw new IllegalArgumentException("I dati del nuovo medico (nome + cognome) sono già presenti all'interno del database");
		
		// different ids
		if (!oldDoctorId.equals(newDoctorId)) {
			// unable to delete doctor
			if (!IO.deleteDoctor(oldDoctorId, DOCTORS_DIR))
				return false;
			// doctor deleted
			doctorsHashMap.remove(oldDoctorId);
			doctorsOrderedList.remove(oldDoctor);
		}
		// unable to create patient
		if (!IO.createDoctor(newDoctor, newDoctorId, DOCTORS_DIR))
			return false;
		// doctor created
		doctorsHashMap.put(newDoctorId, newDoctor);
		doctorsOrderedList.add(newDoctor);
		
		return true;
	}
	
	
	
	/*
	 * DELETE PATIENT/DOCTOR
	 */
	
	public static synchronized boolean deletePatient(Patient patient) {
		String patientId = getPatientId(patient);
		// patient == null
		if (patientId == null)
			return false;
		// patient does not exist
		if (patientsHashMap.get(patientId) == null)
			throw new IllegalArgumentException("Il paziente da eliminare (codice fiscale) non è presente all'interno del database");
		
		// unable to delete patient
		if (!IO.deletePatient(patientId, PATIENTS_DIR))
			return false;
		// patient deleted
		patientsHashMap.remove(patientId);
		patientsOrderedList.remove(patient);
		
		return true;
	}
	
	public static synchronized boolean deleteDoctor(Doctor doctor) {
		String doctorId = getDoctorId(doctor);
		// doctor == null
		if (doctorId == null)
			return false;
		// doctor does not exist
		if (doctorsHashMap.get(doctorId) == null)
			throw new IllegalArgumentException("Il medico da eliminare (nome + cognome) non è presente all'interno del database");
		
		// unable to delete doctor
		if (!IO.deletePatient(doctorId, DOCTORS_DIR))
			return false;
		// doctor deleted
		doctorsHashMap.remove(doctorId);
		doctorsOrderedList.remove(doctor);
		
		return true;
	}
	
	
	
	/*
	 * GET ALL PATIENTS/DOCTORS
	 */
	
	public static synchronized ArrayList<Patient> getAllPatients() {
		return new ArrayList<Patient>(patientsOrderedList);
	}
	
	public static synchronized ArrayList<Doctor> getAllDoctors() {
		return new ArrayList<Doctor>(doctorsOrderedList);
	}
	
	
	
	/*
	 * GET FILTERED PATIENTS/DOCTORS
	 */
	
	// TODO
}