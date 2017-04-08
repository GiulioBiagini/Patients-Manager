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



package it.biagio.patientsmanager.view.operations;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import it.biagio.patientsmanager.utils.TransparentPanel;
import it.biagio.patientsmanager.view.Const;
import it.biagio.patientsmanager.view.operations.button.DangerButton;
import it.biagio.patientsmanager.view.operations.button.PrimaryButton;
import it.biagio.patientsmanager.view.operations.button.SuccessButton;
import it.biagio.patientsmanager.view.operations.button.WarningButton;



@SuppressWarnings("serial")
public class OperationsPanel extends JPanel
{
	private TransparentPanel mainMiddlePanel;
	
	private SuccessButton addPatientButton;
	
	private SuccessButton addDoctorButton;
	
	private WarningButton editButton;
	
	private DangerButton deleteButton;
	
	private PrimaryButton exitButton;
	
	
	
	private TransparentPanel confirmationMiddlePanel;
	
	private PrimaryButton discardButton;
	
	private SuccessButton confirmSuccessButton;
	
	private WarningButton confirmWarningButton;
	
	private DangerButton confirmDangerButton;
	
	
	
	private OperationsPanelObserver observer;
	
	
	
	public OperationsPanel(OperationsPanelObserver observer) {
		super(new BorderLayout());
		setBackground(Const.PANEL_BACKGROUND_COLOR);
		setBorder(BorderFactory.createLineBorder(Const.PANEL_BORDER_COLOR, 1, true));
		
		this.observer = observer;
		
		addPatientButton = new SuccessButton(
			Const.ADD_PATIENT_BUTTON_ICON,
			Const.ADD_PATIENT_BUTTON_TOOL_TIP_TEXT,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actionAddPatient();
				}
			}
		);
		
		addDoctorButton = new SuccessButton(
			Const.ADD_DOCTOR_BUTTON_ICON,
			Const.ADD_DOCTOR_BUTTON_TOOL_TIP_TEXT,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actionAddDoctor();
				}
			}
		);
		
		editButton = new WarningButton(
			Const.EDIT_BUTTON_ICON,
			Const.EDIT_BUTTON_TOOL_TIP_TEXT,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actionEdit();
				}
			}
		);
		
		deleteButton = new DangerButton(
			Const.DELETE_BUTTON_ICON,
			Const.DELETE_BUTTON_TOOL_TIP_TEXT,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actionDelete();
				}
			}
		);
		
		exitButton = new PrimaryButton(
			Const.EXIT_BUTTON_ICON,
			Const.EXIT_BUTTON_TOOL_TIP_TEXT,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actionExit();
				}
			}
		);
		
		
		
		discardButton = new PrimaryButton(
				Const.DISCARD_BUTTON_ICON,
				Const.DISCARD_BUTTON_TOOL_TIP_TEXT,
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						actionDiscard();
					}
				}
			);
		
		confirmSuccessButton = new SuccessButton(
			Const.CONFIRM_BUTTON_ICON,
			Const.CONFIRM_BUTTON_TOOL_TIP_TEXT,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actionConfirm();
				}
			}
		);
		
		confirmWarningButton = new WarningButton(
			Const.CONFIRM_BUTTON_ICON,
			Const.CONFIRM_BUTTON_TOOL_TIP_TEXT,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actionConfirm();
				}
			}
		);
		
		confirmDangerButton = new DangerButton(
			Const.CONFIRM_BUTTON_ICON,
			Const.CONFIRM_BUTTON_TOOL_TIP_TEXT,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actionConfirm();
				}
			}
		);
		
		
		
		mainMiddlePanel = new TransparentPanel(new GridLayout(1, 5, 10, 0));
		mainMiddlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainMiddlePanel.add(addPatientButton);
		mainMiddlePanel.add(addDoctorButton);
		mainMiddlePanel.add(editButton);
		mainMiddlePanel.add(deleteButton);
		mainMiddlePanel.add(exitButton);
		
		confirmationMiddlePanel = new TransparentPanel(new GridLayout(1, 2, 10, 0));
		confirmationMiddlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		confirmationMiddlePanel.add(discardButton);
		confirmationMiddlePanel.add(confirmSuccessButton);
		
		add(mainMiddlePanel, BorderLayout.CENTER);
	}
	
	
	
	private void actionAddPatient() {
		if (observer != null)
			observer.onAddPatient();
	}
	
	private void actionAddDoctor() {
		if (observer != null)
			observer.onAddDoctor();
	}
	
	private void actionEdit() {
		if (observer != null)
			observer.onEdit();
	}
	
	private void actionDelete() {
		if (observer != null)
			observer.onDelete();
	}
	
	private void actionExit() {
		if (observer != null)
			observer.onExit();
	}
	
	private void actionConfirm() {
		if (observer != null)
			observer.onConfirm();
	}
	
	private void actionDiscard() {
		if (observer != null)
			observer.onDiscard();
	}
	
	
	
	public void showMainOperations() {
		removeAll();
		add(mainMiddlePanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public void showConfirmationSuccessOperations() {
		removeAll();
		if (!confirmationMiddlePanel.getComponent(1).equals(confirmSuccessButton)) {
			confirmationMiddlePanel.remove(1);
			confirmationMiddlePanel.add(confirmSuccessButton);
			confirmationMiddlePanel.revalidate();
			confirmationMiddlePanel.repaint();
		}
		add(confirmationMiddlePanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public void showConfirmationWarningOperations() {
		removeAll();
		if (!confirmationMiddlePanel.getComponent(1).equals(confirmWarningButton)) {
			confirmationMiddlePanel.remove(1);
			confirmationMiddlePanel.add(confirmWarningButton);
			confirmationMiddlePanel.revalidate();
			confirmationMiddlePanel.repaint();
		}
		add(confirmationMiddlePanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public void showConfirmationDangerOperations() {
		removeAll();
		if (!confirmationMiddlePanel.getComponent(1).equals(confirmDangerButton)) {
			confirmationMiddlePanel.remove(1);
			confirmationMiddlePanel.add(confirmDangerButton);
			confirmationMiddlePanel.revalidate();
			confirmationMiddlePanel.repaint();
		}
		add(confirmationMiddlePanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public void setEditButtonEnabled(boolean enabled) {
		editButton.setEnabled(enabled);
	}
	
	public void setDeleteButtonEnabled(boolean enabled) {
		deleteButton.setEnabled(enabled);
	}
}