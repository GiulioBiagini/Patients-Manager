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



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.HashMap;

import it.biagio.patientsmanager.model.entities.Doctor;
import it.biagio.patientsmanager.model.entities.Patient;
import it.biagio.patientsmanager.model.entities.info.account.AccountInfo;
import it.biagio.patientsmanager.model.entities.info.address.AddressInfo;
import it.biagio.patientsmanager.model.entities.info.contacts.ContactsInfo;
import it.biagio.patientsmanager.model.entities.info.medicalrecord.MedicalRecordInfo;
import it.biagio.patientsmanager.model.entities.info.personal.DoctorPersonalInfo;
import it.biagio.patientsmanager.model.entities.info.personal.PatientPersonalInfo;
import it.biagio.patientsmanager.model.entities.info.problems.ProblemsInfo;
import it.biagio.patientsmanager.utils.DateConverter;



/**
 * Class for the input/output operations.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class IO
{
	/**
	 * String for line separator
	 */
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	
	
	/**
	 * The string to write if patient/doctor is male
	 */
	private static final String MALE = "m";
	
	/**
	 * The string to write if patient/doctor is female
	 */
	private static final String FEMALE = "f";
	
	
	
	/*
	 * WRITE PATIENT/DOCTOR
	 */
	
	
	
	/**
	 * Create a file and store the info related to the patient (overwrite
	 * it if already exists)
	 * 
	 * @param patient - the patient to save
	 * @param referringPhysicianId - the id to write as info for the referring physician
	 * @param file - the file into which save the patient's info
	 * @throws IOException - if an error occurs while writing the patient's data
	 */
	public static void writePatient(Patient patient, String referringPhysicianId, File file) throws IOException {
		if (patient == null)
			throw new IOException("impossibile scrivere i dati del paziente", "paziente nullo");
		if (file == null)
			throw new IOException("impossibile scrivere i dati del paziente", "file nullo");
		if (file.exists() && !file.isFile())
			throw new IOException("impossibile scrivere i dati del paziente", "file non regolare con lo stesso nome già esistente");
		
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			
			// account info
			AccountInfo accountInfo = patient.getAccountInfo();
			bufferedWriter.write(DateConverter.dateToString(accountInfo.getCreationDate()) + LINE_SEPARATOR);
			bufferedWriter.write(DateConverter.dateToString(accountInfo.getClosingDate()) + LINE_SEPARATOR);
			// personal info
			PatientPersonalInfo personalInfo = patient.getPersonalInfo();
			bufferedWriter.write(personalInfo.getSurname().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(personalInfo.getName().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(DateConverter.dateToString(personalInfo.getBirthdate()) + LINE_SEPARATOR);
			bufferedWriter.write((personalInfo.isMale() ? MALE : FEMALE) + LINE_SEPARATOR);
			bufferedWriter.write(personalInfo.getTaxcode().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(personalInfo.getProfession().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			// medical record info
			MedicalRecordInfo medicalRecordInfo = patient.getMedicalRecordInfo();
			bufferedWriter.write(medicalRecordInfo.getType().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(medicalRecordInfo.getNumber().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(DateConverter.dateToString(medicalRecordInfo.getLastVisitDate()) + LINE_SEPARATOR);
			// address info
			AddressInfo addressInfo = patient.getAddressInfo();
			bufferedWriter.write(addressInfo.getAddress().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(addressInfo.getCivicNumber().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(addressInfo.getCity().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(addressInfo.getZipCode().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(addressInfo.getProvince().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			// contacts info
			ContactsInfo contactsInfo = patient.getContactsInfo();
			bufferedWriter.write(contactsInfo.getTelephoneNumber().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(contactsInfo.getMobileNumber().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			// doctor info
			bufferedWriter.write((referringPhysicianId == null ? "" : referringPhysicianId) + LINE_SEPARATOR);
			// problems info
			ProblemsInfo problemsInfo = patient.getProblemsInfo();
			bufferedWriter.write(problemsInfo.getHeartProblems());
			
			bufferedWriter.close();
		} catch (IllegalArgumentException ex) {
			throw new IOException("impossibile scrivere i dati del paziente", ex.getMessage());
		} catch (java.io.IOException ex) {
			throw new IOException("impossibile scrivere i dati del paziente (errore di input/output)", ex.getMessage());
		} catch (Exception ex) {
			throw new IOException("impossibile scrivere i dati del paziente (errore sconosciuto)", ex.getMessage());
		}
	}
	
	/**
	 * Create a file and store the info related to the doctor (overwrite
	 * it if already exists)
	 * 
	 * @param doctor - the doctor to save
	 * @param file - the file into which save the doctor's info
	 * @throws IOException - if an error occurs while writing the doctor's data
	 */
	public static void writeDoctor(Doctor doctor, File file) throws IOException {
		if (doctor == null)
			throw new IOException("impossibile scrivere i dati del medico", "paziente nullo");
		if (file == null)
			throw new IOException("impossibile scrivere i dati del medico", "file nullo");
		if (file.exists() && !file.isFile())
			throw new IOException("impossibile scrivere i dati del medico", "file non regolare con lo stesso nome già esistente");
		
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			
			// account info
			AccountInfo accountInfo = doctor.getAccountInfo();
			bufferedWriter.write(DateConverter.dateToString(accountInfo.getCreationDate()) + LINE_SEPARATOR);
			bufferedWriter.write(DateConverter.dateToString(accountInfo.getClosingDate()) + LINE_SEPARATOR);
			// personal info
			DoctorPersonalInfo personalInfo = doctor.getPersonalInfo();
			bufferedWriter.write(personalInfo.getSurname().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(personalInfo.getName().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(DateConverter.dateToString(personalInfo.getBirthdate()) + LINE_SEPARATOR);
			bufferedWriter.write((personalInfo.isMale() ? MALE : FEMALE) + LINE_SEPARATOR);
			bufferedWriter.write(personalInfo.getTaxcode().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(personalInfo.getSpecialization().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			// address info
			AddressInfo addressInfo = doctor.getAddressInfo();
			bufferedWriter.write(addressInfo.getAddress().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(addressInfo.getCivicNumber().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(addressInfo.getCity().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(addressInfo.getZipCode().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(addressInfo.getProvince().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			// contacts info
			ContactsInfo contactsInfo = doctor.getContactsInfo();
			bufferedWriter.write(contactsInfo.getTelephoneNumber().replace("\r", "").replace("\n", "") + LINE_SEPARATOR);
			bufferedWriter.write(contactsInfo.getMobileNumber().replace("\r", "").replace("\n", ""));
			
			bufferedWriter.close();
		} catch (IllegalArgumentException ex) {
			throw new IOException("impossibile scrivere i dati del medico", ex.getMessage());
		} catch (java.io.IOException ex) {
			throw new IOException("impossibile scrivere i dati del medico (errore di input/output)", ex.getMessage());
		} catch (Exception ex) {
			throw new IOException("impossibile scrivere i dati del medico (errore sconosciuto)", ex.getMessage());
		}
	}
	
	
	
	/*
	 * READ PATIENT/DOCTOR
	 */
	
	
	
	/**
	 * Read the patient's info stored in the related file
	 * 
	 * @param file - the file to read
	 * @param referringPhysicians - a list of doctors: search if the info related
	 * to the referring physician corresponds to an id in this list and, in this
	 * case, link the patient with this doctor; otherwise the field referring
	 * physician in the patient's structure will be set as null
	 * @return the patient read
	 * @throws IOException - if an error occurs while reading the patient's data
	 */
	public static Patient readPatient(File file, HashMap<String, Doctor> referringPhysicians) throws IOException {
		if (file == null)
			throw new IOException("impossibile leggere i dati del paziente", "file nullo");
		if (file.exists() && !file.isFile())
			throw new IOException("impossibile leggere i dati del paziente", "file non regolare");
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			
			AccountInfo accountInfo = new AccountInfo(
				DateConverter.stringToDate(bufferedReader.readLine()),// creationDate
				DateConverter.stringToDate(bufferedReader.readLine())// closingDate
			);
			PatientPersonalInfo personalInfo = new PatientPersonalInfo(
				bufferedReader.readLine(),// surname
				bufferedReader.readLine(),// name
				DateConverter.stringToDate(bufferedReader.readLine()),// birthdate
				bufferedReader.readLine().equals(MALE),// isMale
				bufferedReader.readLine(),// taxcode
				bufferedReader.readLine()// profession
			);
			MedicalRecordInfo medicalRecordInfo = new MedicalRecordInfo(
				bufferedReader.readLine(),// type
				bufferedReader.readLine(),// number
				DateConverter.stringToDate(bufferedReader.readLine())// lastVisitDate
			);
			AddressInfo addressInfo = new AddressInfo(
				bufferedReader.readLine(),// address
				bufferedReader.readLine(),// civicNumber
				bufferedReader.readLine(),// city
				bufferedReader.readLine(),// zipCode
				bufferedReader.readLine()// province
			);
			ContactsInfo contactsInfo = new ContactsInfo(
				bufferedReader.readLine(),// telephoneNumber
				bufferedReader.readLine()// mobileNumber
			);
			Doctor referringPhysician = referringPhysicians.get(bufferedReader.readLine());// referringPhysician
			// heart problems
			String heartProblems = bufferedReader.readLine();
			while (bufferedReader.ready())
				heartProblems += LINE_SEPARATOR + bufferedReader.readLine();
			ProblemsInfo problemsInfo = new ProblemsInfo(
				heartProblems
			);
			
			bufferedReader.close();
			
			return new Patient(accountInfo, personalInfo, medicalRecordInfo, addressInfo, contactsInfo, referringPhysician, problemsInfo);
		} catch (IllegalArgumentException ex) {
			throw new IOException("impossibile leggere i dati del paziente", ex.getMessage());
		} catch (ParseException ex) {
			throw new IOException("impossibile leggere i dati del paziente", "formato date non corretto (" + DateConverter.DATE_FORMAT + ")");
		} catch (java.io.IOException ex) {
			throw new IOException("impossibile leggere i dati del paziente (errore di input/output)", ex.getMessage());
		} catch (Exception ex) {
			throw new IOException("impossibile leggere i dati del paziente (errore sconosciuto)", ex.getMessage());
		}
	}
	
	/**
	 * Read the doctor's info stored in the related file
	 * 
	 * @param file - the file to read
	 * @return the doctor read
	 * @throws ParseException - if the date stored in the file do not respect the format
	 * @throws IOException - if an error occurs while reading the doctor's data
	 */
	public static Doctor readDoctor(File file) throws IOException {
		if (file == null)
			throw new IOException("impossibile leggere i dati del medico", "file nullo");
		if (file.exists() && !file.isFile())
			throw new IOException("impossibile leggere i dati del medico", "file non regolare");
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			
			AccountInfo accountInfo = new AccountInfo(
				DateConverter.stringToDate(bufferedReader.readLine()),// creationDate
				DateConverter.stringToDate(bufferedReader.readLine())// closingDate
			);
			DoctorPersonalInfo personalInfo = new DoctorPersonalInfo(
				bufferedReader.readLine(),// surname
				bufferedReader.readLine(),// name
				DateConverter.stringToDate(bufferedReader.readLine()),// birthdate
				bufferedReader.readLine().equals(MALE),// isMale
				bufferedReader.readLine(),// taxcode
				bufferedReader.readLine()// specialization
			);
			AddressInfo addressInfo = new AddressInfo(
				bufferedReader.readLine(),// address
				bufferedReader.readLine(),// civicNumber
				bufferedReader.readLine(),// city
				bufferedReader.readLine(),// zipCode
				bufferedReader.readLine()// province
			);
			ContactsInfo contactsInfo = new ContactsInfo(
				bufferedReader.readLine(),// telephoneNumber
				bufferedReader.readLine()// mobileNumber
			);
			
			bufferedReader.close();
			
			return new Doctor(accountInfo, personalInfo, addressInfo, contactsInfo);
		} catch (IllegalArgumentException ex) {
			throw new IOException("impossibile leggere i dati del medico", ex.getMessage());
		} catch (ParseException ex) {
			throw new IOException("I/O - impossibile leggere i dati del medico", "formato date non corretto (" + DateConverter.DATE_FORMAT + ")");
		} catch (java.io.IOException ex) {
			throw new IOException("impossibile leggere i dati del medico (errore di input/output)", ex.getMessage());
		} catch (Exception ex) {
			throw new IOException("impossibile leggere i dati del medico (errore sconosciuto)", ex.getMessage());
		}
	}
	
	
	
	/*
	 * DELETE PATIENT/DOCTOR
	 */
	
	
	
	/**
	 * Delete the the file containing the entity's info
	 * 
	 * @param file - the file into which entity's info are stored
	 * @return true if the file containing the entity's info has been deleted, false otherwise
	 * @throws IOException - if an error occurs while deleting the entity's data
	 */
	private static void deleteEntity(File file, String entityName) throws IOException {
		if (file == null)
			throw new IOException("impossibile eliminare il " + entityName, "file nullo");
		if (!file.exists())
			throw new IOException("impossibile eliminare il " + entityName, "file inesistente");
		if (!file.isFile())
			throw new IOException("impossibile eliminare il " + entityName, "file non regolare");
		
		try {
			if (!file.delete())
				throw new IOException("impossibile eliminare il " + entityName, "errore sconosciuto");
		} catch (Exception ex) {
			throw new IOException("impossibile eliminare il " + entityName + " (errore sconosciuto)", ex.getMessage());
		}
	}
	
	/**
	 * Delete the the file containing the patient's info
	 * 
	 * @param file - the file into which patient's info are stored
	 * @throws IOException - if an error occurs while deleting the patient's data
	 */
	public static void deletePatient(File file) throws IOException {
		deleteEntity(file, "paziente");
	}
	
	/**
	 * Delete the the file containing the doctor's info
	 * 
	 * @param file - the file into which doctor's info are stored
	 * @throws IOException - if an error occurs while deleting the doctor's data
	 */
	public static void deleteDoctor(File file) throws IOException {
		deleteEntity(file, "medico");
	}
}