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

import it.biagio.patientsmanager.model.entities.Doctor;
import it.biagio.patientsmanager.model.entities.info.account.AccountInfo;
import it.biagio.patientsmanager.model.entities.info.personal.DoctorPersonalInfo;
import it.biagio.patientsmanager.view.Const;
import it.biagio.patientsmanager.view.panel.info.address.EditableAddressInfo;
import it.biagio.patientsmanager.view.panel.info.contacts.EditableContactsInfo;
import it.biagio.patientsmanager.view.panel.info.personal.EditableDoctorPersonalInfo;



@SuppressWarnings("serial")
public class EditDoctorPanel extends APanel
{
	private EditableDoctorPersonalInfo editableDoctorPersonalInfo;
	
	private EditableAddressInfo editableAddressInfo;
	
	private EditableContactsInfo editableContactsInfo;
	
	
	
	public EditDoctorPanel() {
		super(
			Const.EDIT_PANEL_TITLE_ICON,
			Const.BUTTON_FOREGROUND_COLOR,
			Const.WARNING_BUTTON_BACKGROUND_COLOR,
			Const.WARNING_BUTTON_BORDER_COLOR
		);
		
		editableDoctorPersonalInfo = new EditableDoctorPersonalInfo();
		editableAddressInfo = new EditableAddressInfo();
		editableContactsInfo = new EditableContactsInfo();
		
		add(editableDoctorPersonalInfo);
		add(editableAddressInfo);
		add(editableContactsInfo);
	}
	
	
	
	public void setDoctor(Doctor doctor) {
		if (doctor != null) {
			DoctorPersonalInfo doctorPersonalInfo = doctor.getPersonalInfo();
			setTitle(doctorPersonalInfo.getName() + " " + doctorPersonalInfo.getSurname());
			editableDoctorPersonalInfo.setPersonalInfo(doctorPersonalInfo);
			editableAddressInfo.setAddressInfo(doctor.getAddressInfo());
			editableContactsInfo.setContactsInfo(doctor.getContactsInfo());
		}
	}
	
	public Doctor getDoctor() throws ParseException, IllegalArgumentException {
		return new Doctor(
			new AccountInfo(new Date(), null),
			editableDoctorPersonalInfo.getPersonalInfo(),
			editableAddressInfo.getAddressInfo(),
			editableContactsInfo.getContactsInfo()
		);
	}
}