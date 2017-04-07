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



package it.biagio.patientsmanager.view.banners;



import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public abstract class ABanner extends JPanel
{
	private Color upperBackgroundColor;
	
	private Color lowerBackgroundColor;
	
	
	
	public ABanner(Color upperBackgroundColor, Color lowerBackgroundColor) {
		super();
		
		this.upperBackgroundColor = upperBackgroundColor;
		this.lowerBackgroundColor = lowerBackgroundColor;
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(
			RenderingHints.KEY_RENDERING,
			RenderingHints.VALUE_RENDER_QUALITY
		);
		
		GradientPaint gradientPaint = new GradientPaint(
			0, 0, upperBackgroundColor,
			0, getHeight(), lowerBackgroundColor
		);
		
		g2.setPaint(gradientPaint);
		g2.fillRect(0, 0, getWidth(), getHeight());
	}
}