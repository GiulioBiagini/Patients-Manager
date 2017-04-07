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



package it.biagio.patientsmanager.view.panel.info.problems;



import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.biagio.patientsmanager.model.entities.info.problems.ProblemsInfo;
import it.biagio.patientsmanager.view.Const;
import it.biagio.patientsmanager.view.panel.info.AInfo;



@SuppressWarnings("serial")
public class UneditableProblemsInfo extends AInfo
{
	protected JLabel heartProblemsLabel;
	
	private JScrollPane heartProblemsScrollPane;
	
	protected JTextArea heartProblemsValue;
	
	
	
	public UneditableProblemsInfo() {
		super(Const.PROBLEMS_INFO_TITLE);
		
		heartProblemsLabel = new JLabel(Const.PROBLEMS_INFO_HEART_PROBLEMS);
		heartProblemsLabel.setFont(Const.PLAIN_FONT);
		heartProblemsValue = new JTextArea();
		heartProblemsValue.setFont(Const.BOLD_FONT);
		heartProblemsValue.setEditable(false);
		heartProblemsValue.setBackground(Const.PANEL_BACKGROUND_COLOR);
		heartProblemsScrollPane = new JScrollPane(heartProblemsValue);
		heartProblemsScrollPane.getVerticalScrollBar().setUnitIncrement(8);
		heartProblemsScrollPane.setPreferredSize(new Dimension(heartProblemsScrollPane.getWidth(), 120));
		heartProblemsScrollPane.setBorder(BorderFactory.createLineBorder(Const.PANEL_BORDER_COLOR, 1, true));
		
		add(heartProblemsLabel, new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
		add(heartProblemsScrollPane, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
	}
	
	
	
	public void setProblemsInfo(ProblemsInfo problemsInfo) {
		if (problemsInfo != null)
			heartProblemsValue.setText(problemsInfo.getHeartProblems());
	}
}