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



package it.biagio.patientsmanager.view.operations.button;



import java.awt.event.ActionListener;

import javax.swing.Icon;

import it.biagio.patientsmanager.Const;



@SuppressWarnings("serial")
public class DangerButton extends AButton
{
	public DangerButton(Icon icon, String toolTipText, ActionListener actionListener) {
		super(
			icon,
			toolTipText,
			Const.BUTTON_FOREGROUND_COLOR,
			Const.DANGER_BUTTON_BACKGROUND_COLOR,
			Const.DANGER_BUTTON_BORDER_COLOR,
			actionListener
		);
	}
}