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



import it.biagio.patientsmanager.model.entities.Doctor;
import it.biagio.patientsmanager.model.entities.info.personal.DoctorPersonalInfo;
import it.biagio.patientsmanager.view.Const;
import it.biagio.patientsmanager.view.panel.info.accountinfo.UneditableAccountInfo;
import it.biagio.patientsmanager.view.panel.info.address.UneditableAddressInfo;
import it.biagio.patientsmanager.view.panel.info.contacts.UneditableContactsInfo;
import it.biagio.patientsmanager.view.panel.info.personal.UneditableDoctorPersonalInfo;



@SuppressWarnings("serial")
public class ShowMaleDoctorPanel extends APanel
{
	private UneditableAccountInfo uneditableAccountInfo;
	
	private UneditableDoctorPersonalInfo uneditableDoctorPersonalInfo;
	
	private UneditableAddressInfo uneditableAddressInfo;
	
	private UneditableContactsInfo uneditableContactsInfo;
	
	
	
	public ShowMaleDoctorPanel() {
		super(
			Const.SHOW_MALE_PANEL_TITLE_ICON,
			Const.MALE_FOREGROUND_COLOR,
			Const.MALE_BACKGROUND_COLOR,
			Const.MALE_BORDER_COLOR
		);
		
		uneditableAccountInfo = new UneditableAccountInfo();
		uneditableDoctorPersonalInfo = new UneditableDoctorPersonalInfo();
		uneditableAddressInfo = new UneditableAddressInfo();
		uneditableContactsInfo = new UneditableContactsInfo();
		
		add(uneditableAccountInfo);
		add(uneditableDoctorPersonalInfo);
		add(uneditableAddressInfo);
		add(uneditableContactsInfo);
	}
	
	
	
	public void setDoctor(Doctor doctor) {
		if (doctor != null) {
			DoctorPersonalInfo doctorPersonalInfo = doctor.getPersonalInfo();
			setTitle(doctorPersonalInfo.getSurname() + " " + doctorPersonalInfo.getName());
			uneditableAccountInfo.setAccountInfo(doctor.getAccountInfo());
			uneditableDoctorPersonalInfo.setPersonalInfo(doctorPersonalInfo);
			uneditableAddressInfo.setAddressInfo(doctor.getAddressInfo());
			uneditableContactsInfo.setContactsInfo(doctor.getContactsInfo());
		}
	}
}