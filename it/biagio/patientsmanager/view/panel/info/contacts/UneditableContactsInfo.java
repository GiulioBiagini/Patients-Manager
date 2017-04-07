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



package it.biagio.patientsmanager.view.panel.info.contacts;



import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

import it.biagio.patientsmanager.Const;
import it.biagio.patientsmanager.model.entities.info.contacts.ContactsInfo;
import it.biagio.patientsmanager.view.panel.info.AInfo;



@SuppressWarnings("serial")
public class UneditableContactsInfo extends AInfo
{
	private JLabel telephoneNumberLabel;
	
	private JLabel telephoneNumberValue;
	
	private JLabel mobileNumberLabel;
	
	private JLabel mobileNumberValue;
	
	
	
	public UneditableContactsInfo() {
		super(Const.CONTACTS_INFO_TITLE);
		
		telephoneNumberLabel = new JLabel(Const.CONTACTS_INFO_TELEPHONE_NUMBER);
		telephoneNumberValue = new JLabel();
		
		mobileNumberLabel = new JLabel(Const.CONTACTS_INFO_MOBILE_NUMBER);
		mobileNumberValue = new JLabel();
		
		add(telephoneNumberLabel, new GridBagConstraints(0, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
		add(telephoneNumberValue, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 5), 0, 0));
		
		add(mobileNumberLabel, new GridBagConstraints(2, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 5), 0, 0));
		add(mobileNumberValue, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
	}
	
	
	
	public void setContactsInfo(ContactsInfo contactsInfo) {
		if (contactsInfo != null) {
			telephoneNumberValue.setText(contactsInfo.getTelephoneNumber());
			mobileNumberValue.setText(contactsInfo.getMobileNumber());
		}
	}
}