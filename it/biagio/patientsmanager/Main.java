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



package it.biagio.patientsmanager;



import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.biagio.patientsmanager.model.database.Database;
import it.biagio.patientsmanager.model.database.DatabaseException;
import it.biagio.patientsmanager.model.database.IOException;
import it.biagio.patientsmanager.model.entities.Doctor;
import it.biagio.patientsmanager.model.entities.Patient;
import it.biagio.patientsmanager.utils.MessageFactory;
import it.biagio.patientsmanager.view.MainFrame;
import it.biagio.patientsmanager.view.MainFrameObserver;



public class Main implements MainFrameObserver
{
	
	private MainFrame mainFrame;
	
	
	
	private Main() {
		try {
			Database.init();
			
			final Patient[] patients = Database.getAllPatients();
			final Doctor[] doctors = Database.getAllDoctors();
			final MainFrameObserver observer = this;
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					mainFrame = new MainFrame(observer);
					mainFrame.setPatients(patients);
					mainFrame.setDoctors(doctors);
					mainFrame.setVisible(true);
				}
			});
		} catch (IOException ex) {
			MessageFactory.errorDialog(null, ex.getMessage());
		} catch (DatabaseException ex) {
			MessageFactory.errorDialog(null, ex.getMessage());
		}
	}
	
	
	
	@Override
	public void onFilterPatients(final String text, final boolean lastVisitCurrentYear) {
		new Thread(new Runnable() {
			public void run() {
				final Patient[] patients = Database.getFilteredPatients(
					text,
					lastVisitCurrentYear
				);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						mainFrame.setPatients(patients);
					}
				});
			}
		}).start();
	}
	
	@Override
	public void onFilterDoctors(final String text) {
		new Thread(new Runnable() {
			public void run() {
				final Doctor[] doctors = Database.getFilteredDoctors(
					text
				);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						mainFrame.setDoctors(doctors);
					}
				});
			}
		}).start();
	}
	
	
	
	@Override
	public void onAddPatient(final Patient patient) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Database.addPatient(patient);
					final Patient[] patients = Database.getFilteredPatients(
						mainFrame.getPatientsTabSearchText(),
						mainFrame.isPatientsTabLastVisitCurrentYearSelected()
					);
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							mainFrame.setPatients(patients);
						}
					});
				} catch (IOException | DatabaseException ex) {
					MessageFactory.errorDialog(null, ex.getMessage());
				}
			}
		}).start();
	}
	
	@Override
	public void onAddDoctor(final Doctor doctor) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Database.addDoctor(doctor);
					final Doctor[] doctors = Database.getFilteredDoctors(
						mainFrame.getDoctorsTabSearchText()
					);
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							mainFrame.setDoctors(doctors);
						}
					});
				} catch (IOException | DatabaseException ex) {
					MessageFactory.errorDialog(null, ex.getMessage());
				}
			}
		}).start();
	}
	
	@Override
	public void onEditPatient(final Patient oldPatient, final Patient newPatient) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Database.editPatient(oldPatient, newPatient);
					final Patient[] patients = Database.getFilteredPatients(
						mainFrame.getPatientsTabSearchText(),
						mainFrame.isPatientsTabLastVisitCurrentYearSelected()
					);
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							mainFrame.setPatients(patients);
						}
					});
				} catch (IOException | DatabaseException ex) {
					MessageFactory.errorDialog(null, ex.getMessage());
				}
			}
		}).start();
	}
	
	@Override
	public void onEditDoctor(final Doctor oldDoctor, final Doctor newDoctor) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Database.editDoctor(oldDoctor, newDoctor);
					final Doctor[] doctors = Database.getFilteredDoctors(
						mainFrame.getDoctorsTabSearchText()
					);
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							mainFrame.setDoctors(doctors);
						}
					});
				} catch (IOException | DatabaseException ex) {
					MessageFactory.errorDialog(null, ex.getMessage());
				}
			}
		}).start();
	}
	
	@Override
	public void onDeletePatient(final Patient patient) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Database.deletePatient(patient);
					final Patient[] patients = Database.getFilteredPatients(
						mainFrame.getPatientsTabSearchText(),
						mainFrame.isPatientsTabLastVisitCurrentYearSelected()
					);
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							mainFrame.setPatients(patients);
						}
					});
				} catch (IOException | DatabaseException ex) {
					MessageFactory.errorDialog(null, ex.getMessage());
				}
			}
		}).start();
	}
	
	@Override
	public void onDeleteDoctor(final Doctor doctor) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Database.deleteDoctor(doctor);
					final Doctor[] doctors = Database.getFilteredDoctors(
						mainFrame.getDoctorsTabSearchText()
					);
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							mainFrame.setDoctors(doctors);
						}
					});
				} catch (IOException | DatabaseException ex) {
					MessageFactory.errorDialog(null, ex.getMessage());
				}
			}
		}).start();
	}
	
	@Override
	public void onExit() {
		mainFrame.dispose();
	}
	
	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
			
		} catch (InstantiationException ex) {
			
		} catch (IllegalAccessException ex) {
			
		} catch (UnsupportedLookAndFeelException ex) {
			
		}
		
		new Main();
	}
}