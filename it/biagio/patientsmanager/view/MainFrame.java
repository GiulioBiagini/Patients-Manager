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



package it.biagio.patientsmanager.view;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JFrame;

import it.biagio.patientsmanager.model.entities.Doctor;
import it.biagio.patientsmanager.model.entities.Patient;
import it.biagio.patientsmanager.utils.DateConverter;
import it.biagio.patientsmanager.utils.MessageFactory;
import it.biagio.patientsmanager.utils.TransparentPanel;
import it.biagio.patientsmanager.view.banners.LowerBanner;
import it.biagio.patientsmanager.view.banners.UpperBanner;
import it.biagio.patientsmanager.view.lists.ListsPanel;
import it.biagio.patientsmanager.view.lists.ListsPanelObserver;
import it.biagio.patientsmanager.view.operations.OperationsPanel;
import it.biagio.patientsmanager.view.operations.OperationsPanelObserver;
import it.biagio.patientsmanager.view.panel.AddDoctorPanel;
import it.biagio.patientsmanager.view.panel.AddPatientPanel;
import it.biagio.patientsmanager.view.panel.DeleteDoctorPanel;
import it.biagio.patientsmanager.view.panel.DeletePatientPanel;
import it.biagio.patientsmanager.view.panel.EditDoctorPanel;
import it.biagio.patientsmanager.view.panel.EditPatientPanel;
import it.biagio.patientsmanager.view.panel.EmptyPanel;
import it.biagio.patientsmanager.view.panel.ShowFemaleDoctorPanel;
import it.biagio.patientsmanager.view.panel.ShowFemalePatientPanel;
import it.biagio.patientsmanager.view.panel.ShowMaleDoctorPanel;
import it.biagio.patientsmanager.view.panel.ShowMalePatientPanel;



@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ListsPanelObserver, OperationsPanelObserver
{
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	
	
	private TransparentPanel northPanel;
	
	private UpperBanner upperBanner;
	
	
	
	private TransparentPanel leftPanel;
	
	private ListsPanel listsPanel;
	
	
	
	private TransparentPanel middlePanel;
	
	private EmptyPanel emptyPanel;
	
	private AddPatientPanel addPatientPanel;
	
	private AddDoctorPanel addDoctorPanel;
	
	private ShowMalePatientPanel showMalePatientPanel;
	
	private ShowMaleDoctorPanel showMaleDoctorPanel;
	
	private ShowFemalePatientPanel showFemalePatientPanel;
	
	private ShowFemaleDoctorPanel showFemaleDoctorPanel;
	
	private EditPatientPanel editPatientPanel;
	
	private EditDoctorPanel editDoctorPanel;
	
	private DeletePatientPanel deletePatientPanel;
	
	private DeleteDoctorPanel deleteDoctorPanel;
	
	private OperationsPanel operationsPanel;
	
	
	
	private TransparentPanel southPanel;
	
	private LowerBanner lowerBanner;
	
	
	
	private MainFrameObserver observer;
	
	
	
	public MainFrame(MainFrameObserver observer) {
		super(Const.PROGRAM_NAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Const.FRAME_WIDTH, Const.FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		setBackground(Const.FRAME_BACKGROUND_COLOR);
		
		/* model */
		
		this.observer = observer;
		
		upperBanner = new UpperBanner();
		
		listsPanel = new ListsPanel(this);
		
		emptyPanel = new EmptyPanel();
		addPatientPanel = new AddPatientPanel();
		addDoctorPanel = new AddDoctorPanel();
		showMalePatientPanel = new ShowMalePatientPanel();
		showMaleDoctorPanel = new ShowMaleDoctorPanel();
		showFemalePatientPanel = new ShowFemalePatientPanel();
		showFemaleDoctorPanel = new ShowFemaleDoctorPanel();
		editPatientPanel = new EditPatientPanel();
		editDoctorPanel = new EditDoctorPanel();
		deletePatientPanel = new DeletePatientPanel();
		deleteDoctorPanel = new DeleteDoctorPanel();
		
		operationsPanel = new OperationsPanel(this);
		operationsPanel.setEditButtonEnabled(false);
		operationsPanel.setDeleteButtonEnabled(false);
		
		lowerBanner = new LowerBanner();
		
		/* structure */
		
		northPanel = new TransparentPanel(new BorderLayout(), 0, 0, 10, 0);
		northPanel.setPreferredSize(new Dimension(northPanel.getWidth(), Const.UPPER_BANNER_HEIGHT));
		northPanel.add(upperBanner, BorderLayout.CENTER);
		
		leftPanel = new TransparentPanel(new BorderLayout(0, 20), 10, 20, 10, 10);
		leftPanel.setPreferredSize(new Dimension(Const.LEFT_PANEL_WIDTH, leftPanel.getHeight()));
		leftPanel.add(listsPanel, BorderLayout.CENTER);
		
		middlePanel = new TransparentPanel(new BorderLayout(0, 20), 10, 10, 10, 20);
		middlePanel.add(operationsPanel, BorderLayout.SOUTH);
		middlePanel.add(emptyPanel, BorderLayout.CENTER);
		
		southPanel = new TransparentPanel(new BorderLayout(), 10, 0, 0, 0);
		southPanel.setPreferredSize(new Dimension(southPanel.getWidth(), Const.LOWER_BANNER_HEIGHT));
		southPanel.add(lowerBanner, BorderLayout.CENTER);
		
		add(northPanel, BorderLayout.NORTH);
		add(leftPanel, BorderLayout.WEST);
		add(middlePanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}
	
	
	
	/*
	 * Lists observers
	 */
	
	@Override
	public void onPatientSearch(String text, boolean lastVisitCurrentYear) {
		if (observer != null)
			observer.onFilterPatients(text, lastVisitCurrentYear);
	}
	
	@Override
	public void onDoctorSearch(String text) {
		if (observer != null)
			observer.onFilterDoctors(text);
	}
	
	@Override
	public void onPatientSelection(Patient patient) {
		middlePanel.remove(1);
		if (patient == null) {
			operationsPanel.setEditButtonEnabled(false);
			operationsPanel.setDeleteButtonEnabled(false);
			middlePanel.add(emptyPanel, BorderLayout.CENTER);
		} else {
			operationsPanel.setEditButtonEnabled(true);
			operationsPanel.setDeleteButtonEnabled(true);
			if (patient.getPersonalInfo().isMale()) {
				showMalePatientPanel.setPatient(patient);
				middlePanel.add(showMalePatientPanel, BorderLayout.CENTER);
			} else {
				showFemalePatientPanel.setPatient(patient);
				middlePanel.add(showFemalePatientPanel, BorderLayout.CENTER);
			}
		}
		operationsPanel.showMainOperations();
		middlePanel.revalidate();
		middlePanel.repaint();
	}
	
	@Override
	public void onDoctorSelection(Doctor doctor) {
		middlePanel.remove(1);
		if (doctor == null) {
			operationsPanel.setEditButtonEnabled(false);
			operationsPanel.setDeleteButtonEnabled(false);
			middlePanel.add(emptyPanel, BorderLayout.CENTER);
		} else {
			operationsPanel.setEditButtonEnabled(true);
			operationsPanel.setDeleteButtonEnabled(true);
			if (doctor.getPersonalInfo().isMale()) {
			showMaleDoctorPanel.setDoctor(doctor);
			middlePanel.add(showMaleDoctorPanel, BorderLayout.CENTER);
			} else {
				showFemaleDoctorPanel.setDoctor(doctor);
				middlePanel.add(showFemaleDoctorPanel, BorderLayout.CENTER);
			}
		}
		operationsPanel.showMainOperations();
		middlePanel.revalidate();
		middlePanel.repaint();
	}
	
	
	
	/*
	 * Operations observers
	 */
	
	@Override
	public void onAddPatient() {
		middlePanel.remove(1);
		operationsPanel.setEditButtonEnabled(false);
		operationsPanel.setDeleteButtonEnabled(false);
		addPatientPanel.reset();
		middlePanel.add(addPatientPanel);
		operationsPanel.showConfirmationSuccessOperations();
		middlePanel.revalidate();
		middlePanel.repaint();
	}
	
	@Override
	public void onAddDoctor() {
		middlePanel.remove(1);
		operationsPanel.setEditButtonEnabled(false);
		operationsPanel.setDeleteButtonEnabled(false);
		addDoctorPanel.reset();
		middlePanel.add(addDoctorPanel);
		operationsPanel.showConfirmationSuccessOperations();
		middlePanel.revalidate();
		middlePanel.repaint();
	}
	
	@Override
	public void onEdit() {
		middlePanel.remove(1);
		if (listsPanel.isPatientsTabSelected()) {
			editPatientPanel.setPatient(listsPanel.getSelectedPatient());
			middlePanel.add(editPatientPanel);
		} else {
			editDoctorPanel.setDoctor(listsPanel.getSelectedDoctor());
			middlePanel.add(editDoctorPanel);
		}
		operationsPanel.showConfirmationWarningOperations();
		middlePanel.revalidate();
		middlePanel.repaint();
	}
	
	@Override
	public void onDelete() {
		middlePanel.remove(1);
		if (listsPanel.isPatientsTabSelected()) {
			deletePatientPanel.setPatient(listsPanel.getSelectedPatient());
			middlePanel.add(deletePatientPanel);
		} else {
			deleteDoctorPanel.setDoctor(listsPanel.getSelectedDoctor());
			middlePanel.add(deleteDoctorPanel);
		}
		operationsPanel.showConfirmationDangerOperations();
		middlePanel.revalidate();
		middlePanel.repaint();
	}
	
	@Override
	public void onExit() {
		if (observer != null)
			observer.onExit();
	}
	
	@Override
	public void onConfirm() {
		try {
			Component displayedComponent = middlePanel.getComponent(1);
			if (displayedComponent.equals(addPatientPanel)) {
				if (observer != null)
					observer.onAddPatient(addPatientPanel.getPatient());
			} else if (displayedComponent.equals(addDoctorPanel)) {
				if (observer != null)
					observer.onAddDoctor(addDoctorPanel.getDoctor());
			} else if (displayedComponent.equals(editPatientPanel)) {
				if (observer != null)
					observer.onEditPatient(listsPanel.getSelectedPatient(), editPatientPanel.getPatient());
			} else if (displayedComponent.equals(editDoctorPanel)) {
				if (observer != null)
					observer.onEditDoctor(listsPanel.getSelectedDoctor(), editDoctorPanel.getDoctor());
			} else if (displayedComponent.equals(deletePatientPanel)) {
				if (observer != null)
					observer.onDeletePatient(listsPanel.getSelectedPatient());
			} else if (
				displayedComponent.equals(deleteDoctorPanel) &&
				MessageFactory.questionDialog(this, "L'eliminazione di questo medico comporterà l'aggiornamento del campo" + LINE_SEPARATOR +
					"\"Medico di riferimento\" dei pazienti che lo usavano come riferimento." + LINE_SEPARATOR +
					"Il nuovo valore sarà impostato a \"nessun medico di riferimento\"." + LINE_SEPARATOR +
					"Eliminare il medico?") &&
				observer != null
			)
				observer.onDeleteDoctor(listsPanel.getSelectedDoctor());
		} catch (ParseException ex) {
			MessageFactory.errorDialog(this, "Impossibile completare l'operazione:" + LINE_SEPARATOR + "formato date non corretto (" + DateConverter.DATE_FORMAT + ")");
		} catch (IllegalArgumentException ex) {
			MessageFactory.errorDialog(this, "Impossibile completare l'operazione:" + LINE_SEPARATOR + ex.getMessage());
		}
	}
	
	@Override
	public void onDiscard() {
		if (listsPanel.isPatientsTabSelected())
			onPatientSelection(listsPanel.getSelectedPatient());
		else
			onDoctorSelection(listsPanel.getSelectedDoctor());
	}
	
	
	
	public void setPatients(Patient[] patients) {
		listsPanel.setPatients(patients);
	}
	
	public void setDoctors(Doctor[] doctors) {
		listsPanel.setDoctors(doctors);
		addPatientPanel.setReferringPhysicians(doctors);
		editPatientPanel.setReferringPhysicians(doctors);
	}
	
	public String getPatientsTabSearchText() {
		return listsPanel.getPatientsTabSearchText();
	}
	
	public String getDoctorsTabSearchText() {
		return listsPanel.getDoctorsTabSearchText();
	}
	
	public boolean isPatientsTabLastVisitCurrentYearSelected() {
		return listsPanel.isPatientsTabLastVisitCurrentYearSelected();
	}
}