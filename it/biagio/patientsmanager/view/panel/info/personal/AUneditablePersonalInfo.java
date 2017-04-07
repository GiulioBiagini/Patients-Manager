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



package it.biagio.patientsmanager.view.panel.info.personal;



import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

import it.biagio.patientsmanager.Const;
import it.biagio.patientsmanager.model.entities.info.personal.APersonalInfo;
import it.biagio.patientsmanager.utils.DateConverter;
import it.biagio.patientsmanager.view.panel.info.AInfo;



@SuppressWarnings("serial")
public abstract class AUneditablePersonalInfo extends AInfo
{
	private JLabel surnameLabel;
	
	private JLabel surnameValue;
	
	private JLabel nameLabel;
	
	private JLabel nameValue;
	
	private JLabel birthdateLabel;
	
	private JLabel birthdateValue;
	
	private JLabel genderLabel;
	
	private JLabel genderValue;
	
	private JLabel taxcodeLabel;
	
	private JLabel taxcodeValue;
	
	
	
	public AUneditablePersonalInfo() {
		super(Const.PERSONAL_INFO_TITLE);
		
		surnameLabel = new JLabel(Const.PERSONAL_INFO_SURNAME);
		surnameLabel.setFont(Const.PLAIN_FONT);
		surnameValue = new JLabel();
		surnameValue.setFont(Const.BOLD_FONT);
		
		nameLabel = new JLabel(Const.PERSONAL_INFO_NAME);
		nameLabel.setFont(Const.PLAIN_FONT);
		nameValue = new JLabel();
		nameValue.setFont(Const.BOLD_FONT);
		
		birthdateLabel = new JLabel(Const.PERSONAL_INFO_BIRTHDATE);
		birthdateLabel.setFont(Const.PLAIN_FONT);
		birthdateValue = new JLabel();
		birthdateValue.setFont(Const.BOLD_FONT);
		
		genderLabel = new JLabel(Const.PERSONAL_INFO_GENDER);
		genderLabel.setFont(Const.PLAIN_FONT);
		genderValue = new JLabel();
		genderValue.setFont(Const.BOLD_FONT);
		
		taxcodeLabel = new JLabel(Const.PERSONAL_INFO_TAXCODE);
		taxcodeLabel.setFont(Const.PLAIN_FONT);
		taxcodeValue = new JLabel();
		taxcodeValue.setFont(Const.BOLD_FONT);
		
		add(surnameLabel, new GridBagConstraints(0, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
		add(surnameValue, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
		
		add(nameLabel, new GridBagConstraints(2, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
		add(nameValue, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 0), 0, 0));
		
		add(birthdateLabel, new GridBagConstraints(0, 1, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
		add(birthdateValue, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		add(genderLabel, new GridBagConstraints(2, 1, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		add(genderValue, new GridBagConstraints(3, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 0), 0, 0));
		
		add(taxcodeLabel, new GridBagConstraints(0, 2, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		add(taxcodeValue, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
	}
	
	
	
	public void setPersonalInfo(APersonalInfo personalInfo) {
		if (personalInfo != null) {
			surnameValue.setText(personalInfo.getSurname());
			nameValue.setText(personalInfo.getName());
			birthdateValue.setText(DateConverter.dateToString(personalInfo.getBirthdate()));
			genderValue.setText(personalInfo.isMale() ? Const.PERSONAL_INFO_MALE : Const.PERSONAL_INFO_FEMALE);
			taxcodeValue.setText(personalInfo.getTaxcode());
		}
	}
}