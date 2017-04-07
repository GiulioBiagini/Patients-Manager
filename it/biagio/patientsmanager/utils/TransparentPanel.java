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



import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;



/**
 * Class for a transparent panel.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
@SuppressWarnings("serial")
public class TransparentPanel extends JPanel
{
	/**
	 * Create a transparent panel
	 */
	public TransparentPanel() {
		super();
		setOpaque(false);
	}
	
	/**
	 * Create a transparent panel
	 * 
	 * @param layoutManager - the layout of this panel
	 */
	public TransparentPanel(LayoutManager layoutManager) {
		super(layoutManager);
		setOpaque(false);
	}
	
	/**
	 * Create a transparent panel
	 * 
	 * @param top - the empty top border
	 * @param left - the empty left border
	 * @param bottom - the empty bottom border
	 * @param right - the empty right border
	 */
	public TransparentPanel(int top, int left, int bottom, int right) {
		super();
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
	}
	
	/**
	 * Create a transparent panel
	 * 
	 * @param layoutManager - the layout of this panel
	 * @param top - the empty top border
	 * @param left - the empty left border
	 * @param bottom - the empty bottom border
	 * @param right - the empty right border
	 */
	public TransparentPanel(LayoutManager layoutManager, int top, int left, int bottom, int right) {
		super(layoutManager);
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
	}
}