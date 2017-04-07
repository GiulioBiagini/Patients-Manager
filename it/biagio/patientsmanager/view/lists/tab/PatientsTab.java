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



package it.biagio.patientsmanager.view.lists.tab;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;

import it.biagio.patientsmanager.Const;
import it.biagio.patientsmanager.utils.TransparentPanel;



@SuppressWarnings("serial")
public class PatientsTab extends ATab
{
	private TransparentPanel southPanel;
	
	private TransparentPanel filtersPanel;
	
	private JCheckBox lastVisitCurrentYearCheckBox;
	
	
	
	private PatientsTabObserver observer;
	
	
	
	public PatientsTab(PatientsTabObserver observer) {
		super(observer);
		
		this.observer = observer;
		
		lastVisitCurrentYearCheckBox = new JCheckBox(Const.CURRENT_YEAR_FILTER_TEXT);
		lastVisitCurrentYearCheckBox.setOpaque(false);
		lastVisitCurrentYearCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionFilters();
			}
		});
		lastVisitCurrentYearCheckBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		filtersPanel = new TransparentPanel(new GridLayout(1, 1));
		filtersPanel.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Const.PANEL_BORDER_COLOR, 1, true),
			Const.FILTERS_LABEL
		));
		filtersPanel.add(lastVisitCurrentYearCheckBox);
		
		southPanel = new TransparentPanel(new BorderLayout(), 0, 10, 10, 10);
		southPanel.add(filtersPanel);
		
		add(southPanel, BorderLayout.SOUTH);
	}
	
	
	
	private void actionFilters() {
		if (observer != null)
			observer.onFiltersSelection(lastVisitCurrentYearCheckBox.isSelected());
	}
	
	
	
	public boolean isLastVisitCurrentYearSelected() {
		return lastVisitCurrentYearCheckBox.isSelected();
	}
}