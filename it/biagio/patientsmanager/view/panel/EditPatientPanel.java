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



package it.biagio.patientsmanager.view.panel;



import java.text.ParseException;
import java.util.Date;

import it.biagio.patientsmanager.Const;
import it.biagio.patientsmanager.model.entities.Doctor;
import it.biagio.patientsmanager.model.entities.Patient;
import it.biagio.patientsmanager.model.entities.info.account.AccountInfo;
import it.biagio.patientsmanager.model.entities.info.personal.PatientPersonalInfo;
import it.biagio.patientsmanager.view.panel.info.address.EditableAddressInfo;
import it.biagio.patientsmanager.view.panel.info.contacts.EditableContactsInfo;
import it.biagio.patientsmanager.view.panel.info.medicalrecord.EditableMedicalRecordInfo;
import it.biagio.patientsmanager.view.panel.info.personal.EditablePatientPersonalInfo;
import it.biagio.patientsmanager.view.panel.info.problems.EditableProblemsInfo;
import it.biagio.patientsmanager.view.panel.info.referringphysician.EditableReferringPhysician;



@SuppressWarnings("serial")
public class EditPatientPanel extends APanel
{
	private EditablePatientPersonalInfo editablePatientPersonalInfo;
	
	private EditableMedicalRecordInfo editableMedicalRecordInfo;
	
	private EditableAddressInfo editableAddressInfo;
	
	private EditableContactsInfo editableContactsInfo;
	
	private EditableReferringPhysician editableReferringPhysician;
	
	private EditableProblemsInfo editableProblemsInfo;
	
	
	
	public EditPatientPanel() {
		super(
			Const.EDIT_PANEL_TITLE_ICON,
			Const.BUTTON_FOREGROUND_COLOR,
			Const.WARNING_BUTTON_BACKGROUND_COLOR,
			Const.WARNING_BUTTON_BORDER_COLOR
		);
		
		editablePatientPersonalInfo = new EditablePatientPersonalInfo();
		editableMedicalRecordInfo = new EditableMedicalRecordInfo();
		editableAddressInfo = new EditableAddressInfo();
		editableContactsInfo = new EditableContactsInfo();
		editableReferringPhysician = new EditableReferringPhysician();
		editableProblemsInfo = new EditableProblemsInfo();
		
		add(editablePatientPersonalInfo);
		add(editableMedicalRecordInfo);
		add(editableAddressInfo);
		add(editableContactsInfo);
		add(editableReferringPhysician);
		add(editableProblemsInfo);
	}
	
	
	
	public void setPatient(Patient patient) {
		if (patient != null) {
			PatientPersonalInfo patientPersonalInfo = patient.getPersonalInfo();
			setTitle(patientPersonalInfo.getName() + " " + patientPersonalInfo.getSurname());
			editablePatientPersonalInfo.setPersonalInfo(patientPersonalInfo);
			editableMedicalRecordInfo.setMedicalRecordInfo(patient.getMedicalRecordInfo());
			editableAddressInfo.setAddressInfo(patient.getAddressInfo());
			editableContactsInfo.setContactsInfo(patient.getContactsInfo());
			editableReferringPhysician.setReferringPhysician(patient.getReferringPhysician());
			editableProblemsInfo.setProblemsInfo(patient.getProblemsInfo());
		}
	}
	
	public Patient getPatient() throws ParseException, IllegalArgumentException {
		return new Patient(
			new AccountInfo(new Date(), null),
			editablePatientPersonalInfo.getPersonalInfo(),
			editableMedicalRecordInfo.getMedicalRecordInfo(),
			editableAddressInfo.getAddressInfo(),
			editableContactsInfo.getContactsInfo(),
			editableReferringPhysician.getReferringPhysician(),
			editableProblemsInfo.getProblemsInfo()
		);
	}
	
	public void setReferringPhysicians(Doctor[] referringPhysicians) {
		editableReferringPhysician.setReferringPhysicians(referringPhysicians);
	}
}