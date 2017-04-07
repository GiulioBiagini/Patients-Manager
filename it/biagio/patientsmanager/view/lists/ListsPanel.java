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



package it.biagio.patientsmanager.view.lists;



import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.biagio.patientsmanager.model.entities.AEntity;
import it.biagio.patientsmanager.model.entities.Doctor;
import it.biagio.patientsmanager.model.entities.Patient;
import it.biagio.patientsmanager.view.Const;
import it.biagio.patientsmanager.view.lists.tab.DoctorsTab;
import it.biagio.patientsmanager.view.lists.tab.DoctorsTabObserver;
import it.biagio.patientsmanager.view.lists.tab.PatientsTab;
import it.biagio.patientsmanager.view.lists.tab.PatientsTabObserver;



@SuppressWarnings("serial")
public class ListsPanel extends JTabbedPane implements PatientsTabObserver, DoctorsTabObserver
{
	private PatientsTab patientsTab;
	
	private DoctorsTab doctorsTab;
	
	
	
	private ListsPanelObserver observer;
	
	
	
	public ListsPanel(ListsPanelObserver observer) {
		super();
		setBackground(Const.PANEL_BACKGROUND_COLOR);
		setFont(Const.BOLD_FONT);
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				actionChange();
			}
		});
		
		this.observer = observer;
		
		patientsTab = new PatientsTab(this);
		
		doctorsTab = new DoctorsTab(this);
		
		addTab(Const.PATIENTS_TAB_TITLE, patientsTab);
		addTab(Const.DOCTORS_TAB_TITLE, doctorsTab);
	}
	
	
	
	private void actionChange() {
		try {
			if (observer != null) {
				if (getSelectedComponent().equals(patientsTab))
					observer.onPatientSelection((Patient) patientsTab.getSelectedValue());
				else
					observer.onDoctorSelection((Doctor) doctorsTab.getSelectedValue());
			}
		// during initalization a change is fired but objects managed by the observer are still not initialized
		} catch (NullPointerException ex) {
			
		}
	}
	
	
	
	@Override
	public void onSearch(String text) {
		if (observer != null) {
			if (getSelectedComponent().equals(patientsTab))
				observer.onPatientSearch(text, patientsTab.isLastVisitCurrentYearSelected());
			else
				observer.onDoctorSearch(text);
		}
	}
	
	@Override
	public void onSelection(AEntity entity) {
		if (observer != null) {
			if (getSelectedComponent().equals(patientsTab))
				observer.onPatientSelection((Patient) entity);
			else
				observer.onDoctorSelection((Doctor) entity);
		}
	}
	
	@Override
	public void onFiltersSelection(boolean lastVisitCurrentYear) {
		if (observer != null)
			observer.onPatientSearch(patientsTab.getSearchText(), lastVisitCurrentYear);
	}
	
	
	
	public boolean isPatientsTabSelected() {
		return getSelectedComponent().equals(patientsTab);
	}
	
	public boolean isDoctorsTabSelected() {
		return getSelectedComponent().equals(doctorsTab);
	}
	
	public String getPatientsTabSearchText() {
		return patientsTab.getSearchText();
	}
	
	public String getDoctorsTabSearchText() {
		return doctorsTab.getSearchText();
	}
	
	public void setPatients(Patient[] patients) {
		patientsTab.setListData(patients);
	}
	
	public void setDoctors(Doctor[] doctors) {
		doctorsTab.setListData(doctors);
	}
	
	public Patient getSelectedPatient() {
		return (Patient) patientsTab.getSelectedValue();
	}
	
	public Doctor getSelectedDoctor() {
		return (Doctor) doctorsTab.getSelectedValue();
	}
	
	public boolean isPatientsTabLastVisitCurrentYearSelected() {
		return patientsTab.isLastVisitCurrentYearSelected();
	}
}