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
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import it.biagio.patientsmanager.model.entities.Doctor;
import it.biagio.patientsmanager.model.entities.Patient;
import it.biagio.patientsmanager.utils.DoctorsOrderedList;
import it.biagio.patientsmanager.utils.PatientsOrderedList;



/**
 * Class for the database.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class Database
{
	/**
	 * The directory of the database
	 */
	private static final File DATABASE_DIR = new File("." + File.separator + "database");
	
	/**
	 * The directory where the patients are stored
	 */
	private static final File PATIENTS_DIR = new File(DATABASE_DIR + File.separator + "patients");
	
	/**
	 * The directory where the doctors are stored
	 */
	private static final File DOCTORS_DIR = new File(DATABASE_DIR + File.separator + "doctors");
	
	
	
	/** The hashmap <id, patient> of all patients */
	private static HashMap<String, Patient> patientsHashMap;
	
	/** The hashmap <id, doctor> of all doctors */
	private static HashMap<String, Doctor> doctorsHashMap;
	
	/** The ordered list of patients */
	private static PatientsOrderedList patientsOrderedList;
	
	/** The ordered list of doctors */
	private static DoctorsOrderedList doctorsOrderedList;
	
	
	
	/*
	 * ID MANAGEMENT
	 */
	
	
	
	/**
	 * Calculate the id of the given patient (taxcode)
	 * 
	 * @param patient - the patient of which compute the id
	 * @return the id of the patient, null if the patient is null
	 */
	private static final String getPatientId(Patient patient) {
		return patient == null ? null : patient.getPersonalInfo().getTaxcode();
	}
	
	/**
	 * Calculate the id of the given doctor (surname + name)
	 * 
	 * @param doctor - the doctor of which compute the id
	 * @return the id of the doctor, null if the doctor is null
	 */
	private static final String getDoctorId(Doctor doctor) {
		return doctor == null ? null : doctor.getPersonalInfo().getSurname() + "_" + doctor.getPersonalInfo().getName();
	}
	
	
	
	/*
	 * INIT
	 */
	
	
	
	public static synchronized void init() throws IOException, DatabaseException {
		if (!DATABASE_DIR.exists() && !DATABASE_DIR.mkdirs())
			throw new DatabaseException("impossibile inizializzare il database", "impossibile creare la directory principale");
		if (!DATABASE_DIR.isDirectory())
			throw new DatabaseException("impossibile inizializzare il database", "directory principale non regolare");
		if (!PATIENTS_DIR.exists() && !PATIENTS_DIR.mkdirs())
			throw new DatabaseException("impossibile inizializzare il database", "impossibile creare la directory per i pazienti");
		if (!PATIENTS_DIR.isDirectory())
			throw new DatabaseException("impossibile inizializzare il database", "directory per i pazienti non regolare");
		if (!DOCTORS_DIR.exists() && !DOCTORS_DIR.mkdirs())
			throw new DatabaseException("impossibile inizializzare il database", "impossibile creare la directory per i medici");
		if (!DOCTORS_DIR.isDirectory())
			throw new DatabaseException("impossibile inizializzare il database", "directory per i medici non regolare");
		
		// read doctors
		doctorsHashMap = new HashMap<String, Doctor>();
		doctorsOrderedList = new DoctorsOrderedList();
		for (File file : DOCTORS_DIR.listFiles()) {
			Doctor doctor = IO.readDoctor(file);
			if (doctor != null) {
				doctorsHashMap.put(getDoctorId(doctor), doctor);
				doctorsOrderedList.add(doctor);
			}
		}
		
		// read patients
		patientsHashMap = new HashMap<String, Patient>();
		patientsOrderedList = new PatientsOrderedList();
		for (File file : PATIENTS_DIR.listFiles()) {
			Patient patient = IO.readPatient(file, doctorsHashMap);
			if (patient != null) {
				patientsHashMap.put(getPatientId(patient), patient);
				patientsOrderedList.add(patient);
			}
		}
	}
	
	
	
	/*
	 * ADD PATIENT/DOCTOR
	 */
	
	
	
	public static synchronized void addPatient(Patient patient) throws IOException, DatabaseException {
		String patientId = getPatientId(patient);
		// patient null
		if (patientId == null)
			throw new DatabaseException("impossibile aggiungere il paziente", "paziente nullo");
		// patient already exists
		if (patientsHashMap.get(patientId) != null)
			throw new DatabaseException("impossibile aggiungere il paziente", "un paziente con lo stesso codice fiscale è già presente all'interno del database");
		
		IO.writePatient(patient, getDoctorId(patient.getReferringPhysician()), new File(PATIENTS_DIR, patientId));
		patientsHashMap.put(patientId, patient);
		patientsOrderedList.add(patient);
	}
	
	public static synchronized void addDoctor(Doctor doctor) throws IOException, DatabaseException {
		String doctorId = getDoctorId(doctor);
		// doctor null
		if (doctorId == null)
			throw new DatabaseException("impossibile aggiungere il medico", "medico nullo");
		// doctor already exists
		if (doctorsHashMap.get(doctorId) != null)
			throw new DatabaseException("impossibile aggiungere il medico", "un medico con lo stesso nome+cognome è già presente all'interno del database");
		
		IO.writeDoctor(doctor, new File(DOCTORS_DIR, doctorId));
		doctorsHashMap.put(doctorId, doctor);
		doctorsOrderedList.add(doctor);
	}
	
	
	
	/*
	 * EDIT PATIENT/DOCTOR
	 */
	
	
	
	public static synchronized void editPatient(Patient oldPatient, Patient newPatient) throws IOException, DatabaseException {
		// old patient null
		String oldPatientId = getPatientId(oldPatient);
		if (oldPatientId == null)
			throw new DatabaseException("impossibile modificare il paziente", "vecchio paziente nullo");
		// old patient does not exist
		if (patientsHashMap.get(oldPatientId) == null)
			throw new DatabaseException("impossibile modificare il paziente", "un paziente con codice fiscale specificato non è presente all'interno del database");
		// new patient null
		String newPatientId = getPatientId(newPatient);
		if (newPatientId == null)
			throw new DatabaseException("impossibile modificare il paziente", "nuovo paziente nullo");
		// different ids and new patient already exists
		if (!oldPatientId.equals(newPatientId) && patientsHashMap.get(newPatientId) != null)
			throw new DatabaseException("impossibile modificare il paziente", "un paziente con lo stesso codice fiscale è già presente all'interno del database");
		
		// keep old account info data
		newPatient.setAccountInfo(oldPatient.getAccountInfo());
		
		IO.writePatient(newPatient, getDoctorId(newPatient.getReferringPhysician()), new File(PATIENTS_DIR, newPatientId));
		patientsHashMap.put(newPatientId, newPatient);
		patientsOrderedList.add(newPatient);
		
		// different ids
		if (!oldPatientId.equals(newPatientId)) {
			IO.deletePatient(new File(PATIENTS_DIR, oldPatientId));
			patientsHashMap.remove(oldPatientId);
		}
		patientsOrderedList.remove(oldPatient);
	}
	
	public static synchronized void editDoctor(Doctor oldDoctor, Doctor newDoctor) throws IOException, DatabaseException {
		// old doctor null
		String oldDoctorId = getDoctorId(oldDoctor);
		if (oldDoctorId == null)
			throw new DatabaseException("impossibile modificare il medico", "vecchio medico nullo");
		// old doctor does not exist
		if (doctorsHashMap.get(oldDoctorId) == null)
			throw new DatabaseException("impossibile modificare il medico", "un medico con nome+cognome specificato non è presente all'interno del database");
		// new doctor null
		String newDoctorId = getDoctorId(newDoctor);
		if (newDoctorId == null)
			throw new DatabaseException("impossibile modificare il medico", "nuovo medico nullo");
		// different ids and new doctor already exists
		if (!oldDoctorId.equals(newDoctorId) && doctorsHashMap.get(newDoctorId) != null)
			throw new DatabaseException("impossibile modificare il medico", "un medico con lo stesso nome+cognome è già presente all'interno del database");
		
		// keep old account info data
		newDoctor.setAccountInfo(oldDoctor.getAccountInfo());
		
		IO.writeDoctor(newDoctor, new File(DOCTORS_DIR, newDoctorId));
		doctorsHashMap.put(newDoctorId, newDoctor);
		doctorsOrderedList.add(newDoctor);
		
		// different ids
		if (!oldDoctorId.equals(newDoctorId)) {
			IO.deleteDoctor(new File(DOCTORS_DIR, oldDoctorId));
			doctorsHashMap.remove(oldDoctorId);
		}
		doctorsOrderedList.remove(oldDoctor);
		
		// update patients whose referring physician is the edited doctor
		int patientsToEdit = 0;
		int errorsWhileEditing = 0;
		for (Patient patient : patientsOrderedList) {
			Doctor referringPhysician = patient.getReferringPhysician();
			if (referringPhysician != null && referringPhysician.equals(oldDoctor)) {
				patientsToEdit++;
				try {
					// write only if id changed
					if (!oldDoctorId.equals(newDoctorId))
						IO.writePatient(patient, newDoctorId, new File(PATIENTS_DIR, getPatientId(patient)));
					// update always
					patient.setReferringPhysician(newDoctor);
				} catch (IOException ex) {
					errorsWhileEditing++;
				}
			}
		}
		if (errorsWhileEditing > 0)
			throw new DatabaseException(
				"errori nell'aggiornamento del medico di riferimento dei pazienti",
				"non aggiornat" + (errorsWhileEditing == 1 ? "o un paziente" : "i " + errorsWhileEditing + " pazienti") +
				" su " + (patientsToEdit == 1 ? "uno trovato" : patientsToEdit + " trovati")
			);
	}
	
	
	
	/*
	 * DELETE PATIENT/DOCTOR
	 */
	
	
	
	public static synchronized void deletePatient(Patient patient) throws IOException, DatabaseException {
		String patientId = getPatientId(patient);
		// patient null
		if (patientId == null)
			throw new DatabaseException("impossibile eliminare il paziente", "paziente nullo");
		// patient does not exist
		if (patientsHashMap.get(patientId) == null)
			throw new DatabaseException("impossibile eliminare il paziente", "un paziente con codice fiscale specificato non è presente all'interno del database");
		
		IO.deletePatient(new File(PATIENTS_DIR, patientId));
		patientsHashMap.remove(patientId);
		patientsOrderedList.remove(patient);
	}
	
	public static synchronized void deleteDoctor(Doctor doctor) throws IOException, DatabaseException {
		String doctorId = getDoctorId(doctor);
		// doctor null
		if (doctorId == null)
			throw new DatabaseException("impossibile eliminare il medico", "medico nullo");
		// doctor does not exist
		if (doctorsHashMap.get(doctorId) == null)
			throw new DatabaseException("impossibile eliminare il medico", "un medico con nome+cognome specificato non è presente all'interno del database");
		
		IO.deletePatient(new File(DOCTORS_DIR, doctorId));
		doctorsHashMap.remove(doctorId);
		doctorsOrderedList.remove(doctor);
		
		// update patients whose referring physician is the deleted doctor
		int patientsToEdit = 0;
		int errorsWhileEditing = 0;
		for (Patient patient : patientsOrderedList) {
			Doctor referringPhysician = patient.getReferringPhysician();
			if (referringPhysician != null && referringPhysician.equals(doctor)) {
				patientsToEdit++;
				try {
					IO.writePatient(patient, null, new File(PATIENTS_DIR, getPatientId(patient)));
					patient.setReferringPhysician(null);
				} catch (IOException ex) {
					errorsWhileEditing++;
				}
			}
		}
		if (errorsWhileEditing > 0)
			throw new DatabaseException(
				"errori nell'aggiornamento del medico di riferimento dei pazienti",
				"non aggiornat" + (errorsWhileEditing == 1 ? "o un paziente" : "i " + errorsWhileEditing + " pazienti") +
				" su " + (patientsToEdit == 1 ? "uno trovato" : patientsToEdit + " trovati")
			);
	}
	
	
	
	/*
	 * GET ALL PATIENTS/DOCTORS
	 */
	
	
	
	public static synchronized Patient[] getAllPatients() {
		return new ArrayList<Patient>(patientsOrderedList).toArray(new Patient[patientsOrderedList.size()]);
	}
	
	public static synchronized Doctor[] getAllDoctors() {
		return new ArrayList<Doctor>(doctorsOrderedList).toArray(new Doctor[doctorsOrderedList.size()]);
	}
	
	
	
	/*
	 * GET FILTERED PATIENTS/DOCTORS
	 */
	
	
	
	public static synchronized Patient[] getFilteredPatients(String text, boolean lastVisitCurrentYear) {
		ArrayList<Patient> output = new ArrayList<Patient>(patientsOrderedList);
		
		// filter by last visit date and text
		if (lastVisitCurrentYear && text != null && !text.isEmpty()) {
			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar lastVisitDate = new GregorianCalendar();
			text = text.toUpperCase();
			for (Patient patient : patientsOrderedList) {
				try {
					lastVisitDate.setTime(patient.getMedicalRecordInfo().getLastVisitDate());
					if (now.get(GregorianCalendar.YEAR) != lastVisitDate.get(GregorianCalendar.YEAR))
						output.remove(patient);
					else if (!patient.toString().toUpperCase().contains(text))
						output.remove(patient);
				// if last visit date is null
				} catch (NullPointerException ex) {
					output.remove(patient);
				}
			}
		// filter by last visit date
		} else if (lastVisitCurrentYear) {
			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar lastVisitDate = new GregorianCalendar();
			for (Patient patient : patientsOrderedList) {
				try {
					lastVisitDate.setTime(patient.getMedicalRecordInfo().getLastVisitDate());
					if (now.get(GregorianCalendar.YEAR) != lastVisitDate.get(GregorianCalendar.YEAR))
						output.remove(patient);
				// if last visit date is null
				} catch (NullPointerException ex) {
					output.remove(patient);
				}
			}
		// filter by text
		} else if (text != null && !text.isEmpty()) {
			text = text.toUpperCase();
			for (Patient patient : patientsOrderedList)
				if (!patient.toString().toUpperCase().contains(text))
					output.remove(patient);
		}
		
		return output.toArray(new Patient[output.size()]);
	}
	
	public static synchronized Doctor[] getFilteredDoctors(String text) {
		ArrayList<Doctor> output = new ArrayList<Doctor>(doctorsOrderedList);
		
		// filter by text
		if (text != null && !text.isEmpty()) {
			text = text.toUpperCase();
			for (Doctor doctor : doctorsOrderedList)
				if (!doctor.toString().toUpperCase().contains(text))
					output.remove(doctor);
		}
		
		return output.toArray(new Doctor[output.size()]);
	}
}