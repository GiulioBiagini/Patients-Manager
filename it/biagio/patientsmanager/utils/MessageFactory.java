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



package it.biagio.patientsmanager.utils;



import java.awt.Component;

import javax.swing.JOptionPane;

import it.biagio.patientsmanager.Const;



/**
 * Class for the messages panels.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class MessageFactory
{
	/**
	 * Display an information message
	 * 
	 * @param parent - the parent component of this panel
	 * @param message - the message to show
	 */
	public static void informationDialog(Component parent, String message) {
		JOptionPane.showMessageDialog(parent, message, Const.PROGRAM_NAME, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Display an error message
	 * 
	 * @param parent - the parent component of this panel
	 * @param message - the message to show
	 */
	public static void errorDialog(Component parent, String message) {
		JOptionPane.showMessageDialog(parent, message, Const.PROGRAM_NAME, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Display a yes/no question message
	 * 
	 * @param parent - the parent component of this panel
	 * @param message - the message to show
	 * @return true if the user answered "yes" to this question
	 */
	public static boolean questionDialog(Component parent, String message) {
		return JOptionPane.showConfirmDialog(parent, message, Const.PROGRAM_NAME, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
	}
}