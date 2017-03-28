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
import java.io.IOException;
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
	private static final String LOG_CLASS_NAME = IO.class.getName();
	
	private static final String LOG_PATIENT_CREATION = "patient creation";
	
	private static final String LOG_DOCTOR_CREATION = "doctor creation";
	
	private static final String LOG_PATIENT_DELETION = "patient deletion";
	
	private static final String LOG_DOCTOR_DELETION = "doctor deletion";
	
	private static final String LOG_PATIENT_READING = "patient reading";
	
	private static final String LOG_DOCTOR_READING = "doctor reading";
	
	
	
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	
	
	private static final String MALE = "m";
	
	private static final String FEMALE = "f";
	
	
	
	/*
	 * CREATE PATIENT/DOCTOR
	 */
	
	/**
	 * Create a file and store the info related to the patient
	 * 
	 * @param patient - the patient to save
	 * @param referringPhysician - the data related to the referring physician
	 * @param fileName - the name of the file into which save the patient's info
	 * @param directory - the directory into which create the file
	 * @return true if the patient has been correctly saved, false otherwise (check log)
	 * @throws IOException - if an I/O error occurs while writing the patient's data
	 */
	public static boolean createPatient(Patient patient, String referringPhysician, String fileName, File directory) throws IOException {
		if (patient == null)
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_CREATION, "null patient");
		else if (fileName == null)
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_CREATION, "null filename");
		else if (fileName.isEmpty())
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_CREATION, "empty filename");
		else if (!directory.exists())
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_CREATION, "directory " + directory.getAbsolutePath() + " does not exist");
		else if (!directory.isDirectory())
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_CREATION, "directory " + directory.getAbsolutePath() + " is not a folder");
		else {
			File file = new File(directory, fileName);
			
			if (file.isFile())
				Log.w(LOG_CLASS_NAME, LOG_PATIENT_CREATION, "file " + file.getAbsolutePath() + " already exists (will be overwritten)");
			
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
				bufferedWriter.write((referringPhysician == null ? "" : referringPhysician) + LINE_SEPARATOR);
				// problems info
				ProblemsInfo problemsInfo = patient.getProblemsInfo();
				bufferedWriter.write(problemsInfo.getHeartProblems());
				
				bufferedWriter.close();
				
				Log.i(LOG_CLASS_NAME, LOG_PATIENT_CREATION, "file " + file.getAbsolutePath() + " created");
				
				return true;
			} catch (Exception ex) {
				Log.e(LOG_CLASS_NAME, LOG_PATIENT_CREATION, "unable to write the patient's data on " + file.getAbsolutePath() + ": " + ex.getMessage());
				throw ex;
			}
		}
		
		return false;
	}
	
	/**
	 * Create a file and store the info related to the doctor
	 * 
	 * @param doctor - the doctor to save
	 * @param fileName - the name of the file into which save the doctor's info
	 * @param directory - the directory into which create the file
	 * @return true if the doctor has been correctly saved, false otherwise (check log)
	 * @throws IOException - if an I/O error occurs while writing the doctor's data
	 */
	public static boolean createDoctor(Doctor doctor, String fileName, File directory) throws IOException {
		if (doctor == null)
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_CREATION, "null doctor");
		else if (fileName == null)
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_CREATION, "null filename");
		else if (fileName.isEmpty())
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_CREATION, "empty filename");
		else if (!directory.exists())
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_CREATION, "directory " + directory.getAbsolutePath() + " does not exist");
		else if (!directory.isDirectory())
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_CREATION, "directory " + directory.getAbsolutePath() + " is not a folder");
		else {
			File file = new File(directory, fileName);
			
			if (file.isFile())
				Log.w(LOG_CLASS_NAME, LOG_DOCTOR_CREATION, "file " + file.getAbsolutePath() + " already exists (will be overwritten)");
			
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
				
				Log.i(LOG_CLASS_NAME, LOG_DOCTOR_CREATION, "file " + file.getAbsolutePath() + " created");
				
				return true;
			} catch (Exception ex) {
				Log.e(LOG_CLASS_NAME, LOG_DOCTOR_CREATION, "unable to write the doctor's data on " + file.getAbsolutePath() + ": " + ex.getMessage());
				throw ex;
			}
		}
		
		return false;
	}
	
	
	
	/*
	 * DELETE PATIENT/DOCTOR
	 */
	
	public static boolean deletePatient(String fileName, File directory) {
		if (fileName == null)
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_DELETION, "null filename");
		else if (fileName.isEmpty())
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_DELETION, "empty filename");
		else if (!directory.exists())
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_DELETION, "directory " + directory.getAbsolutePath() + " does not exist");
		else if (!directory.isDirectory())
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_DELETION, "directory " + directory.getAbsolutePath() + " is not a folder");
		else {
			File file = new File(directory, fileName);
			
			if (!file.exists())
				Log.e(LOG_CLASS_NAME, LOG_PATIENT_DELETION, "file " + file.getAbsolutePath() + " does not exist");
			else if (!file.isFile())
				Log.e(LOG_CLASS_NAME, LOG_PATIENT_DELETION, "file " + file.getAbsolutePath() + " is not a regular file");
			else if (!file.delete())
				Log.e(LOG_CLASS_NAME, LOG_PATIENT_DELETION, "unable to delete file " + file.getAbsolutePath());
			else {
				Log.i(LOG_CLASS_NAME, LOG_PATIENT_DELETION, "file " + file.getAbsolutePath() + " deleted");
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean deleteDoctor(String fileName, File directory) {
		if (fileName == null)
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_DELETION, "null filename");
		else if (fileName.isEmpty())
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_DELETION, "empty filename");
		else if (!directory.exists())
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_DELETION, "directory " + directory.getAbsolutePath() + " does not exist");
		else if (!directory.isDirectory())
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_DELETION, "directory " + directory.getAbsolutePath() + " is not a folder");
		else {
			File file = new File(directory, fileName);
			
			if (!file.exists())
				Log.e(LOG_CLASS_NAME, LOG_DOCTOR_DELETION, "file " + file.getAbsolutePath() + " does not exist");
			else if (!file.isFile())
				Log.e(LOG_CLASS_NAME, LOG_DOCTOR_DELETION, "file " + file.getAbsolutePath() + " is not a regular file");
			else if (!file.delete())
				Log.e(LOG_CLASS_NAME, LOG_DOCTOR_DELETION, "unable to delete the file " + file.getAbsolutePath());
			else {
				Log.i(LOG_CLASS_NAME, LOG_DOCTOR_DELETION, "file " + file.getAbsolutePath() + " deleted");
				return true;
			}
		}
		
		return false;
	}
	
	
	
	/*
	 * READ PATIENT/DOCTOR
	 */
	
	/**
	 * 
	 * @param file
	 * @param referringPhysicians
	 * @return
	 * @throws IOException
	 */
	public static Patient readPatient(File file, HashMap<String, Doctor> referringPhysicians) throws IOException {
		if (file == null)
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_READING, "null file");
		else if (!file.exists())
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_READING, "file " + file.getAbsolutePath() + " does not exist");
		else if (!file.isFile())
			Log.e(LOG_CLASS_NAME, LOG_PATIENT_READING, "file " + file.getAbsolutePath() + " is not a regular file");
		else {
			
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
				
				Log.i(LOG_CLASS_NAME, LOG_PATIENT_READING, "file " + file.getAbsolutePath() + " read");
				
				return new Patient(accountInfo, personalInfo, medicalRecordInfo, addressInfo, contactsInfo, referringPhysician, problemsInfo);
			} catch (ParseException ex) {
				Log.e(LOG_CLASS_NAME, LOG_PATIENT_READING, "unable to read the patient's data from " + file.getAbsolutePath() + ": " + ex.getMessage());
			} catch (IllegalArgumentException ex) {
				Log.e(LOG_CLASS_NAME, LOG_PATIENT_READING, "unable to read the patient's data from " + file.getAbsolutePath() + ": " + ex.getMessage());
			} catch (IOException ex) {
				Log.e(LOG_CLASS_NAME, LOG_PATIENT_READING, "unable to read the patient's data from " + file.getAbsolutePath() + ": " + ex.getMessage());
				throw ex;
			}
		}
		
		return null;
	}
	
	public static Doctor readDoctor(File file) throws IOException {
		if (file == null)
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_READING, "null file");
		else if (!file.exists())
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_READING, "file " + file.getAbsolutePath() + " does not exist");
		else if (!file.isFile())
			Log.e(LOG_CLASS_NAME, LOG_DOCTOR_READING, "file " + file.getAbsolutePath() + " is not a regular file");
		else {
			
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
				
				Log.i(LOG_CLASS_NAME, LOG_DOCTOR_READING, "file " + file.getAbsolutePath() + " read");
				
				return new Doctor(accountInfo, personalInfo, addressInfo, contactsInfo);
			} catch (ParseException ex) {
				Log.e(LOG_CLASS_NAME, LOG_DOCTOR_READING, "unable to read the doctor's data from " + file.getAbsolutePath() + ": " + ex.getMessage());
			} catch (IllegalArgumentException ex) {
				Log.e(LOG_CLASS_NAME, LOG_DOCTOR_READING, "unable to read the doctor's data from " + file.getAbsolutePath() + ": " + ex.getMessage());
			} catch (IOException ex) {
				Log.e(LOG_CLASS_NAME, LOG_DOCTOR_READING, "unable to read the doctor's data from " + file.getAbsolutePath() + ": " + ex.getMessage());
				throw ex;
			}
		}
		
		return null;
	}
}