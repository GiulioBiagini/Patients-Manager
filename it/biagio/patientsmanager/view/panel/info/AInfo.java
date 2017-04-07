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



package it.biagio.patientsmanager.view.panel.info;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import it.biagio.patientsmanager.Const;
import it.biagio.patientsmanager.utils.TransparentPanel;



@SuppressWarnings("serial")
public abstract class AInfo extends TransparentPanel
{
	private TransparentPanel middlePanel;
	
	
	
	public AInfo(String title) {
		super(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Const.PANEL_BORDER_COLOR, 1, true),
			title,
			TitledBorder.DEFAULT_JUSTIFICATION,
			TitledBorder.DEFAULT_POSITION,
			Const.BOLD_FONT
		));
		
		middlePanel = new TransparentPanel(new GridBagLayout());
		middlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		super.add(middlePanel, BorderLayout.CENTER);
	}
	
	
	
	@Override
	public void add(Component component, Object constraints) {
		middlePanel.add(component, constraints);
	}
}