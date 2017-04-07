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



package it.biagio.patientsmanager.view.panel;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.biagio.patientsmanager.view.Const;



@SuppressWarnings("serial")
public abstract class APanel extends JPanel
{
	private JPanel northPanel;
	
	private JLabel titleLabel;
	
	
	
	private JScrollPane middleScrollPanel;
	
	private JPanel middlePanel;
	
	
	
	public APanel(Icon titleIcon, Color titleForegroundColor, Color titleBackgroundColor, Color titleBorderColor) {
		super(new BorderLayout());
		
		titleLabel = new JLabel(titleIcon, JLabel.CENTER);
		titleLabel.setFont(Const.TITLE_FONT);
		titleLabel.setForeground(titleForegroundColor);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		northPanel = new JPanel(new BorderLayout());
		northPanel.setBackground(titleBackgroundColor);
		northPanel.setBorder(BorderFactory.createLineBorder(titleBorderColor, 1, true));
		northPanel.add(titleLabel, BorderLayout.CENTER);
		
		middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
		middlePanel.setBackground(Const.PANEL_BACKGROUND_COLOR);
		middlePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		middleScrollPanel = new JScrollPane(middlePanel);
		middleScrollPanel.getVerticalScrollBar().setUnitIncrement(8);
		middleScrollPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Const.PANEL_BORDER_COLOR));// TODO -> MAKE IT ROUNDED!
		
		super.add(northPanel, BorderLayout.NORTH);
		super.add(middleScrollPanel, BorderLayout.CENTER);
	}
	
	
	
	@Override
	public Component add(Component component) {
		middlePanel.add(Box.createRigidArea(new Dimension(middlePanel.getWidth(), 10)));
		return middlePanel.add(component);
	}
	
	
	
	public void setTitle(String title) {
		titleLabel.setText(" -  " + title);
	}
}