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



package it.biagio.patientsmanager.view.panel.info.medicalrecord;



import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

import it.biagio.patientsmanager.model.entities.info.medicalrecord.MedicalRecordInfo;
import it.biagio.patientsmanager.utils.DateConverter;
import it.biagio.patientsmanager.view.Const;
import it.biagio.patientsmanager.view.panel.info.AInfo;



@SuppressWarnings("serial")
public class UneditableMedicalRecordInfo extends AInfo
{
	private JLabel typeLabel;
	
	private JLabel typeValue;
	
	private JLabel numberLabel;
	
	private JLabel numberValue;
	
	private JLabel lastVisitDateLabel;
	
	private JLabel lastVisitDateValue;
	
	
	
	public UneditableMedicalRecordInfo() {
		super(Const.MEDICAL_RECORD_INFO_TITLE);
		
		typeLabel = new JLabel(Const.MEDICAL_RECORD_INFO_TYPE);
		typeLabel.setFont(Const.PLAIN_FONT);
		typeValue = new JLabel();
		typeValue.setFont(Const.BOLD_FONT);
		
		numberLabel = new JLabel(Const.MEDICAL_RECORD_INFO_NUMBER);
		numberLabel.setFont(Const.PLAIN_FONT);
		numberValue = new JLabel();
		numberValue.setFont(Const.BOLD_FONT);
		
		lastVisitDateLabel = new JLabel(Const.MEDICAL_RECORD_INFO_LAST_VISIT_DATE);
		lastVisitDateLabel.setFont(Const.PLAIN_FONT);
		lastVisitDateValue = new JLabel();
		lastVisitDateValue.setFont(Const.BOLD_FONT);
		
		add(typeLabel, new GridBagConstraints(0, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
		add(typeValue, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
		
		add(numberLabel, new GridBagConstraints(2, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
		add(numberValue, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 0), 0, 0));
		
		add(lastVisitDateLabel, new GridBagConstraints(0, 1, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		add(lastVisitDateValue, new GridBagConstraints(1, 1, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
	}
	
	
	
	public void setMedicalRecordInfo(MedicalRecordInfo medicalRecordInfo) {
		typeValue.setText(medicalRecordInfo.getType());
		numberValue.setText(medicalRecordInfo.getNumber());
		lastVisitDateValue.setText(DateConverter.dateToString(medicalRecordInfo.getLastVisitDate()));
	}
}