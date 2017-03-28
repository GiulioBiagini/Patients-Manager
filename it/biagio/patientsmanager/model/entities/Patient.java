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



package it.biagio.patientsmanager.model.entities;



import it.biagio.patientsmanager.model.entities.info.account.AccountInfo;
import it.biagio.patientsmanager.model.entities.info.address.AddressInfo;
import it.biagio.patientsmanager.model.entities.info.contacts.ContactsInfo;
import it.biagio.patientsmanager.model.entities.info.medicalrecord.MedicalRecordInfo;
import it.biagio.patientsmanager.model.entities.info.personal.PatientPersonalInfo;
import it.biagio.patientsmanager.model.entities.info.problems.ProblemsInfo;
import it.biagio.patientsmanager.utils.DateConverter;



/**
 * Class for the patient.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class Patient extends AEntity
{
	/** The info object related to the medical record */
	private MedicalRecordInfo medicalRecordInfo;
	
	/** The the referring physician */
	private Doctor referringPhysician;
	
	/** The info object related to the problems */
	private ProblemsInfo problemsInfo;
	
	
	
	/**
	 * Create a new patient
	 * 
	 * @param accountInfo - the info related to the account (can't be null)
	 * @param personalInfo - the info related to the patient's personal data (can't be null)
	 * @param medicalRecordInfo - the info related to the medical record (can't be null)
	 * @param addressInfo - the info related to the address (can't be null)
	 * @param contactsInfo - the info related to the contacts (can't be null)
	 * @param referringPhysician - the referring physician, null if no doctor has been selected
	 * @param problemsInfo - the info related to the problems (can't be null)
	 */
	public Patient(AccountInfo accountInfo, PatientPersonalInfo personalInfo, MedicalRecordInfo medicalRecordInfo, AddressInfo addressInfo, ContactsInfo contactsInfo, Doctor referringPhysician, ProblemsInfo problemsInfo) {
		super(accountInfo, personalInfo, addressInfo, contactsInfo);
	}
	
	
	
	/**
	 * Set the info related to the medical record
	 * 
	 * @param medicalRecordInfo - the info related to the medical record (can't be null)
	 */
	public void setMedicalRecordInfo(MedicalRecordInfo medicalRecordInfo) {
		if (medicalRecordInfo == null)
			throw new IllegalArgumentException(
				"Specificare le informazioni relative alla cartella clinica"
			);
		this.medicalRecordInfo = medicalRecordInfo;
	}
	
	/**
	 * Set the referring physician
	 * 
	 * @param referringPhysician - the referring physician, null if no doctor has been selected
	 */
	public void setReferringPhysician(Doctor referringPhysician) {
		this.referringPhysician = referringPhysician;
	}
	
	/**
	 * Set the info related to the problems
	 * 
	 * @param problemsInfo - the info related to the problems (can't be null)
	 */
	public void setProblemsInfo(ProblemsInfo problemsInfo) {
		if (problemsInfo == null)
			throw new IllegalArgumentException(
				"Specificare le informazioni relative alle problematiche"
			);
		this.problemsInfo = problemsInfo;
	}
	
	
	
	/**
	 * Get the info related to the patient's personal data
	 * 
	 * @return the info related to the patient's personal data
	 */
	@Override
	public PatientPersonalInfo getPersonalInfo() {
		return (PatientPersonalInfo) super.getPersonalInfo();
	}
	
	/**
	 * Get the info related to the medical record
	 * 
	 * @return the info related to the medical record
	 */
	public MedicalRecordInfo getMedicalRecordInfo() {
		return medicalRecordInfo;
	}
	
	/**
	 * Get the referring physician
	 * 
	 * @return the referring physician, null if no doctor has been selected
	 */
	public Doctor getReferringPhysician() {
		return referringPhysician;
	}
	
	/**
	 * Get the info related to the problems
	 * 
	 * @return the info related to the problems
	 */
	public ProblemsInfo getProblemsInfo() {
		return problemsInfo;
	}
	
	
	
	@Override
	public String toString() {
		PatientPersonalInfo personalInfo = getPersonalInfo();
		return medicalRecordInfo.getNumber() + " - " + medicalRecordInfo.getType() + " - " + personalInfo.getSurname() + personalInfo.getName() +
			(medicalRecordInfo.getLastVisitDate() == null ? "" : " (" + DateConverter.dateToString(medicalRecordInfo.getLastVisitDate()) + ")");
	}
}