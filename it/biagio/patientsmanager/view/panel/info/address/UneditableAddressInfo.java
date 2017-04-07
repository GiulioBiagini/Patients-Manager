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



package it.biagio.patientsmanager.view.panel.info.address;



import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

import it.biagio.patientsmanager.model.entities.info.address.AddressInfo;
import it.biagio.patientsmanager.view.Const;
import it.biagio.patientsmanager.view.panel.info.AInfo;



@SuppressWarnings("serial")
public class UneditableAddressInfo extends AInfo
{
	private JLabel addressLabel;
	
	private JLabel addressValue;
	
	private JLabel civicNumberLabel;
	
	private JLabel civicNumberValue;
	
	private JLabel cityLabel;
	
	private JLabel cityValue;
	
	private JLabel zipCodeLabel;
	
	private JLabel zipCodeValue;
	
	private JLabel provinceLabel;
	
	private JLabel provinceValue;
	
	
	
	public UneditableAddressInfo() {
		super(Const.ADDRESS_INFO_TITLE);
		
		addressLabel = new JLabel(Const.ADDRESS_INFO_ADDRESS);
		addressLabel.setFont(Const.PLAIN_FONT);
		addressValue = new JLabel();
		addressValue.setFont(Const.BOLD_FONT);
		
		civicNumberLabel = new JLabel(Const.ADDRESS_INFO_CIVIC_NUMBER);
		civicNumberLabel.setFont(Const.PLAIN_FONT);
		civicNumberValue = new JLabel();
		civicNumberValue.setFont(Const.BOLD_FONT);
		
		cityLabel = new JLabel(Const.ADDRESS_INFO_CITY);
		cityLabel.setFont(Const.PLAIN_FONT);
		cityValue = new JLabel();
		cityValue.setFont(Const.BOLD_FONT);
		
		zipCodeLabel = new JLabel(Const.ADDRESS_INFO_ZIP_CODE);
		zipCodeLabel.setFont(Const.PLAIN_FONT);
		zipCodeValue = new JLabel();
		zipCodeValue.setFont(Const.BOLD_FONT);
		
		provinceLabel = new JLabel(Const.ADDRESS_INFO_PROVINCE);
		provinceLabel.setFont(Const.PLAIN_FONT);
		provinceValue = new JLabel();
		provinceValue.setFont(Const.BOLD_FONT);
		
		add(addressLabel, new GridBagConstraints(0, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
		add(addressValue, new GridBagConstraints(1, 0, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
		
		add(civicNumberLabel, new GridBagConstraints(4, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
		add(civicNumberValue, new GridBagConstraints(5, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 0), 0, 0));
		
		add(cityLabel, new GridBagConstraints(0, 1, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		add(cityValue, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		
		add(zipCodeLabel, new GridBagConstraints(2, 1, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		add(zipCodeValue, new GridBagConstraints(3, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		
		add(provinceLabel, new GridBagConstraints(4, 1, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		add(provinceValue, new GridBagConstraints(5, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
	}
	
	
	
	public void setAddressInfo(AddressInfo addressInfo) {
		addressValue.setText(addressInfo.getAddress());
		civicNumberValue.setText(addressInfo.getCivicNumber());
		cityValue.setText(addressInfo.getCity());
		zipCodeValue.setText(addressInfo.getZipCode());
		provinceValue.setText(addressInfo.getProvince());
	}
}