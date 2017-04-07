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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.biagio.patientsmanager.Const;
import it.biagio.patientsmanager.model.entities.AEntity;
import it.biagio.patientsmanager.utils.TransparentPanel;



@SuppressWarnings("serial")
public abstract class ATab extends JPanel
{
	private TransparentPanel northPanel;
	
	private JLabel searchLabel;
	
	private JTextField searchTextField;
	
	
	
	private TransparentPanel middlePanel;
	
	private JScrollPane listScrollPanel;
	
	private JList<AEntity> list;
	
	
	
	private ATabObserver observer;
	
	
	
	public ATab(ATabObserver observer) {
		super(new BorderLayout());
		setBackground(Const.PANEL_BACKGROUND_COLOR);
		setBorder(BorderFactory.createLineBorder(Const.PANEL_BORDER_COLOR, 1, true));
		
		this.observer = observer;
		
		searchLabel = new JLabel(Const.SEARCH_LABEL, Const.SEARCH_ICON, JLabel.LEFT);
		
		searchTextField = new JTextField();
		searchTextField.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				actionSearch();
			}
		});
		
		list = new JList<AEntity>();
		list.setBackground(Const.PANEL_BACKGROUND_COLOR);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!list.getValueIsAdjusting())
					actionSelection();
			}
		});
		
		listScrollPanel = new JScrollPane(list);
		listScrollPanel.getVerticalScrollBar().setUnitIncrement(8);
		listScrollPanel.setBorder(BorderFactory.createLineBorder(Const.PANEL_BORDER_COLOR, 1, true));
		
		northPanel = new TransparentPanel(new BorderLayout(10, 0), 10, 10, 5, 10);
		northPanel.add(searchLabel, BorderLayout.WEST);
		northPanel.add(searchTextField, BorderLayout.CENTER);
		
		middlePanel = new TransparentPanel(new BorderLayout(), 5, 10, 10, 10);
		middlePanel.add(listScrollPanel);
		
		add(northPanel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
	}
	
	
	
	private void actionSearch() {
		if (observer != null)
			observer.onSearch(searchTextField.getText());
	}
	
	private void actionSelection() {
		if (observer != null)
			observer.onSelection(list.getSelectedValue());
	}
	
	
	
	public String getSearchText() {
		return searchTextField.getText();
	}
	
	public void setListData(AEntity[] listData) {
		list.setListData(listData);
	}
	
	public AEntity getSelectedValue() {
		return list.getSelectedValue();
	}
	
	public void clearListSelection() {
		list.clearSelection();
	}
}