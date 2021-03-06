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
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import it.biagio.patientsmanager.model.entities.info.personal.APersonalInfo;
import it.biagio.patientsmanager.utils.DateConverter;
import it.biagio.patientsmanager.utils.TransparentPanel;
import it.biagio.patientsmanager.view.Const;
import it.biagio.patientsmanager.view.panel.info.AInfo;



@SuppressWarnings("serial")
public abstract class AEditablePersonalInfo extends AInfo
{
	protected JLabel surnameLabel;
	
	protected JTextField surnameValue;
	
	protected JLabel nameLabel;
	
	protected JTextField nameValue;
	
	protected JLabel birthdateLabel;
	
	private UtilDateModel utilDateModel;
	
	private Properties properties;
	
	private JDatePanelImpl datePanelImpl;
	
	protected JDatePickerImpl birthdateValue;
	
	protected JLabel genderLabel;
	
	private TransparentPanel genderPanel;
	
	private ButtonGroup genderButtonGroup;
	
	protected JRadioButton maleValue;
	
	protected JRadioButton femaleValue;
	
	protected JLabel taxcodeLabel;
	
	protected JTextField taxcodeValue;
	
	
	
	public AEditablePersonalInfo() {
		super(Const.PERSONAL_INFO_TITLE);
		
		surnameLabel = new JLabel(Const.PERSONAL_INFO_SURNAME);
		surnameLabel.setFont(Const.BOLD_FONT);
		surnameLabel.setForeground(Const.INFO_PANELS_REQUIRED_FIELD_FOREGROUND_COLOR);
		surnameValue = new JTextField();
		surnameValue.setFont(Const.PLAIN_FONT);
		surnameValue.setBorder(BorderFactory.createLineBorder(Const.PANEL_BORDER_COLOR, 1, true));
		
		nameLabel = new JLabel(Const.PERSONAL_INFO_NAME);
		nameLabel.setFont(Const.BOLD_FONT);
		nameLabel.setForeground(Const.INFO_PANELS_REQUIRED_FIELD_FOREGROUND_COLOR);
		nameValue = new JTextField();
		nameValue.setFont(Const.PLAIN_FONT);
		nameValue.setBorder(BorderFactory.createLineBorder(Const.PANEL_BORDER_COLOR, 1, true));
		
		birthdateLabel = new JLabel(Const.PERSONAL_INFO_BIRTHDATE);
		birthdateLabel.setFont(Const.BOLD_FONT);
		utilDateModel = new UtilDateModel();
		utilDateModel.setSelected(true);
		properties = new Properties();
		properties.put("text.today", "Oggi");
		properties.put("text.month", "Mese");
		properties.put("text.year", "Anno");
		datePanelImpl = new JDatePanelImpl(utilDateModel, properties);
		birthdateValue = new JDatePickerImpl(datePanelImpl, DateConverter.DATE_PICKER_FORMATTER);
		
		genderLabel = new JLabel(Const.PERSONAL_INFO_GENDER);
		genderLabel.setFont(Const.BOLD_FONT);
		genderLabel.setForeground(Const.INFO_PANELS_REQUIRED_FIELD_FOREGROUND_COLOR);
		maleValue = new JRadioButton(Const.PERSONAL_INFO_MALE, true);
		maleValue.setFont(Const.PLAIN_FONT);
		maleValue.setOpaque(false);
		femaleValue = new JRadioButton(Const.PERSONAL_INFO_FEMALE, false);
		femaleValue.setFont(Const.PLAIN_FONT);
		femaleValue.setOpaque(false);
		genderButtonGroup = new ButtonGroup();
		genderButtonGroup.add(maleValue);
		genderButtonGroup.add(femaleValue);
		genderPanel = new TransparentPanel(new GridLayout(1, 2, 10, 0));
		genderPanel.add(maleValue);
		genderPanel.add(femaleValue);
		
		taxcodeLabel = new JLabel(Const.PERSONAL_INFO_TAXCODE);
		taxcodeLabel.setFont(Const.BOLD_FONT);
		taxcodeValue = new JTextField();
		taxcodeValue.setFont(Const.PLAIN_FONT);
		taxcodeValue.setBorder(BorderFactory.createLineBorder(Const.PANEL_BORDER_COLOR, 1, true));
		
		add(surnameLabel, new GridBagConstraints(0, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
		add(surnameValue, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
		
		add(nameLabel, new GridBagConstraints(2, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
		add(nameValue, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 0), 0, 0));
		
		add(birthdateLabel, new GridBagConstraints(0, 1, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
		add(birthdateValue, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		add(genderLabel, new GridBagConstraints(2, 1, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		add(genderPanel, new GridBagConstraints(3, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 0), 0, 0));
		
		add(taxcodeLabel, new GridBagConstraints(0, 2, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		add(taxcodeValue, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
	}
	
	
	
	public void setPersonalInfo(APersonalInfo personalInfo) {
		if (personalInfo != null) {
			surnameValue.setText(personalInfo.getSurname());
			nameValue.setText(personalInfo.getName());
			((UtilDateModel) birthdateValue.getModel()).setValue(personalInfo.getBirthdate());
			maleValue.setSelected(personalInfo.isMale());
			femaleValue.setSelected(!personalInfo.isMale());
			taxcodeValue.setText(personalInfo.getTaxcode());
		}
	}
	
	public abstract APersonalInfo getPersonalInfo() throws ParseException, IllegalArgumentException;
	
	public void reset() {
		surnameValue.setText("");
		nameValue.setText("");
		((UtilDateModel) birthdateValue.getModel()).setValue(null);
		maleValue.setSelected(true);
		taxcodeValue.setText("");
		
	}
}