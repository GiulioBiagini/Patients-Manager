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



import it.biagio.patientsmanager.Const;
import it.biagio.patientsmanager.model.entities.Patient;
import it.biagio.patientsmanager.model.entities.info.personal.PatientPersonalInfo;
import it.biagio.patientsmanager.view.panel.info.accountinfo.UneditableAccountInfo;
import it.biagio.patientsmanager.view.panel.info.address.UneditableAddressInfo;
import it.biagio.patientsmanager.view.panel.info.contacts.UneditableContactsInfo;
import it.biagio.patientsmanager.view.panel.info.medicalrecord.UneditableMedicalRecordInfo;
import it.biagio.patientsmanager.view.panel.info.personal.UneditablePatientPersonalInfo;
import it.biagio.patientsmanager.view.panel.info.problems.UneditableProblemsInfo;
import it.biagio.patientsmanager.view.panel.info.referringphysician.UneditableReferringPhysician;



@SuppressWarnings("serial")
public class ShowMalePatientPanel extends APanel
{
	private UneditableAccountInfo uneditableAccountInfo;
	
	private UneditablePatientPersonalInfo uneditablePatientPersonalInfo;
	
	private UneditableMedicalRecordInfo uneditableMedicalRecordInfo;
	
	private UneditableAddressInfo uneditableAddressInfo;
	
	private UneditableContactsInfo uneditableContactsInfo;
	
	private UneditableReferringPhysician uneditableReferringPhysician;
	
	private UneditableProblemsInfo uneditableProblemsInfo;
	
	
	
	public ShowMalePatientPanel() {
		super(
			Const.SHOW_MALE_PANEL_TITLE_ICON,
			Const.MALE_FOREGROUND_COLOR,
			Const.MALE_BACKGROUND_COLOR,
			Const.MALE_BORDER_COLOR
		);
		
		uneditableAccountInfo = new UneditableAccountInfo();
		uneditablePatientPersonalInfo = new UneditablePatientPersonalInfo();
		uneditableMedicalRecordInfo = new UneditableMedicalRecordInfo();
		uneditableAddressInfo = new UneditableAddressInfo();
		uneditableContactsInfo = new UneditableContactsInfo();
		uneditableReferringPhysician = new UneditableReferringPhysician();
		uneditableProblemsInfo = new UneditableProblemsInfo();
		
		add(uneditableAccountInfo);
		add(uneditablePatientPersonalInfo);
		add(uneditableMedicalRecordInfo);
		add(uneditableAddressInfo);
		add(uneditableContactsInfo);
		add(uneditableReferringPhysician);
		add(uneditableProblemsInfo);
	}
	
	
	
	public void setPatient(Patient patient) {
		if (patient != null) {
			PatientPersonalInfo patientPersonalInfo = patient.getPersonalInfo();
			setTitle(patientPersonalInfo.getName() + " " + patientPersonalInfo.getSurname());
			uneditableAccountInfo.setAccountInfo(patient.getAccountInfo());
			uneditablePatientPersonalInfo.setPersonalInfo(patientPersonalInfo);
			uneditableMedicalRecordInfo.setMedicalRecordInfo(patient.getMedicalRecordInfo());
			uneditableAddressInfo.setAddressInfo(patient.getAddressInfo());
			uneditableContactsInfo.setContactsInfo(patient.getContactsInfo());
			uneditableReferringPhysician.setReferringPhysician(patient.getReferringPhysician());
			uneditableProblemsInfo.setProblemsInfo(patient.getProblemsInfo());
		}
	}
}