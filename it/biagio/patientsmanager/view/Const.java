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



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;



public class Const
{
	static {
		IconFontSwing.register(FontAwesome.getIconFont());
	}
	
	
	
	public static final String PROGRAM_NAME = "Patients Manager v.2.0";
	
	
	
	/*
	 * DIMENSIONS
	 */
	
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static final int FRAME_WIDTH = SCREEN_SIZE.width * 19 / 20;
	
	public static final int FRAME_HEIGHT = SCREEN_SIZE.height * 19 / 20;
	
	
	
	public static final int UPPER_BANNER_HEIGHT = 50;
	
	public static final int LOWER_BANNER_HEIGHT = 30;
	
	public static final int LEFT_PANEL_WIDTH = SCREEN_SIZE.width / 4;
	
	public static final int BUTTONS_PANEL_HEIGHT = 55;
	
	
	
	/*
	 * FONTS
	 */
	
	public static final Font PLAIN_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	
	public static final Font BOLD_FONT = PLAIN_FONT.deriveFont(Font.BOLD);
	
	public static final Font TITLE_FONT = new Font(Font.SERIF, Font.BOLD, 12);
	
	
	
	/*
	 * COLORS
	 */
	
	public static final Color FRAME_BACKGROUND_COLOR = new Color(242, 248, 240);// #F2F8F0
	
	
	
	public static final Color BANNER_DARK_BACKGROUND_COLOR = new Color(55, 150, 32);// #379620
	
	public static final Color BANNER_LIGHT_BACKGROUND_COLOR = new Color(70, 192, 41);// #46C029
	
	
	
	public static final Color PANEL_BACKGROUND_COLOR = Color.WHITE;
	
	public static final Color PANEL_BORDER_COLOR = new Color(204, 204, 204);// #CCCCCC
	
	
	
	public static final Color MALE_BACKGROUND_COLOR = new Color(217, 247, 247);// #D9F7F7
	
	public static final Color MALE_FOREGROUND_COLOR = new Color(49, 112, 143);// #31708F
	
	public static final Color MALE_BORDER_COLOR = new Color(191, 229, 229);// #BFE5E5
	
	public static final Color FEMALE_BACKGROUND_COLOR = new Color(255, 221, 240);// #FFDDF0
	
	public static final Color FEMALE_FOREGROUND_COLOR = new Color(168, 76, 109);// #A84C6D
	
	public static final Color FEMALE_BORDER_COLOR = new Color(233, 194, 216);// #E9C2D8
	
	
	
	public static final Color BUTTON_FOREGROUND_COLOR = Color.WHITE;
	
	public static final Color PRIMARY_BUTTON_BACKGROUND_COLOR = new Color(66, 139, 202);// #428bca
	
	public static final Color PRIMARY_BUTTON_BORDER_COLOR = new Color(53, 126, 189);// #357ebd
	
	public static final Color SUCCESS_BUTTON_BACKGROUND_COLOR = new Color(92, 184, 92);// #5cb85c
	
	public static final Color SUCCESS_BUTTON_BORDER_COLOR = new Color(76, 174, 76);// #4cae4c
	
	public static final Color WARNING_BUTTON_BACKGROUND_COLOR = new Color(236, 151, 31);// #ec971f
	
	public static final Color WARNING_BUTTON_BORDER_COLOR = new Color(213, 133, 18);// #d58512
	
	public static final Color DANGER_BUTTON_BACKGROUND_COLOR = new Color(201, 48, 44);// #c9302c
	
	public static final Color DANGER_BUTTON_BORDER_COLOR = new Color(172, 41, 37);// #ac2925
	
	
	
	public static final Color INFO_PANELS_REQUIRED_FIELD_FOREGROUND_COLOR = Color.RED;
	
	
	
	/*
	 * STRINGS
	 */
	
	public static final String PATIENTS_TAB_TITLE = "Pazienti";
	
	public static final String DOCTORS_TAB_TITLE = "Medici";
	
	public static final String SEARCH_LABEL = "Cerca:";
	
	public static final String FILTERS_LABEL = "Filtri:";
	
	public static final String CURRENT_YEAR_FILTER_TEXT = "Ultima visita nell'anno corrente";
	
	
	
	public static final String ADD_PATIENT_PANEL_TITLE = "Nuovo Paziente";
	
	public static final String ADD_DOCTOR_PANEL_TITLE = "Nuovo Medico";
	
	
	
	public static final String ACCOUNT_INFO_TITLE = "Account";
	
	public static final String ACCOUNT_INFO_CREATION_DATE = "Data di creazione:";
	
	public static final String ACCOUNT_INFO_CREATION_TIME = "Orario di creazione:";
	
	public static final String PERSONAL_INFO_TITLE = "Informazioni personali";
	
	public static final String PERSONAL_INFO_SURNAME = "Cognome:";
	
	public static final String PERSONAL_INFO_NAME = "Nome:";
	
	public static final String PERSONAL_INFO_BIRTHDATE = "Data di nascita:";
	
	public static final String PERSONAL_INFO_GENDER = "Sesso:";
	
	public static final String PERSONAL_INFO_MALE = "Maschio";
	
	public static final String PERSONAL_INFO_FEMALE = "Femmina";
	
	public static final String PERSONAL_INFO_TAXCODE = "Codice fiscale:";
	
	public static final String PERSONAL_INFO_PROFESSION = "Professione:";
	
	public static final String PERSONAL_INFO_SPECIALIZATION = "Specializzazione:";
	
	public static final String MEDICAL_RECORD_INFO_TITLE = "Cartella clinica";
	
	public static final String MEDICAL_RECORD_INFO_TYPE = "Tipo:";
	
	public static final String MEDICAL_RECORD_INFO_NUMBER = "Numero:";
	
	public static final String MEDICAL_RECORD_INFO_LAST_VISIT_DATE = "Data ultima visita:";
	
	public static final String ADDRESS_INFO_TITLE = "Indirizzo";
	
	public static final String ADDRESS_INFO_ADDRESS = "Via:";
	
	public static final String ADDRESS_INFO_CIVIC_NUMBER = "Numero civico:";
	
	public static final String ADDRESS_INFO_CITY = "Città:";
	
	public static final String ADDRESS_INFO_ZIP_CODE = "Cap:";
	
	public static final String ADDRESS_INFO_PROVINCE = "Provincia:";
	
	public static final String CONTACTS_INFO_TITLE = "Contatti";
	
	public static final String CONTACTS_INFO_TELEPHONE_NUMBER = "Numero di telefono:";
	
	public static final String CONTACTS_INFO_MOBILE_NUMBER = "Numero di cellulare:";
	
	public static final String REFERRING_PHYSICIAN_INFO_TITLE = "Medico di riferimento";
	
	public static final String PROBLEMS_INFO_TITLE = "Problemi";
	
	public static final String PROBLEMS_INFO_HEART_PROBLEMS = "Problemi cardiaci:";
	
	
	
	public static final String ADD_PATIENT_BUTTON_TOOL_TIP_TEXT = "Addiungi paziente";
	
	public static final String ADD_DOCTOR_BUTTON_TOOL_TIP_TEXT = "Aggiungi medico";
	
	public static final String EDIT_BUTTON_TOOL_TIP_TEXT = "Modifica dati";
	
	public static final String DELETE_BUTTON_TOOL_TIP_TEXT = "Elimina";
	
	public static final String EXIT_BUTTON_TOOL_TIP_TEXT = "Esci";
	
	public static final String CONFIRM_BUTTON_TOOL_TIP_TEXT = "Conferma";
	
	public static final String DISCARD_BUTTON_TOOL_TIP_TEXT = "Annulla";
	
	
	
	/*
	 * ICONS
	 */
	
	private static final String IMGS_PATH = "/it/biagio/patientsmanager/view/imgs/";
	
	public static final Icon SEARCH_ICON = new ImageIcon(Const.class.getResource(IMGS_PATH + "search_icon.png"));
	
	public static final Icon FILTERS_ICON = new ImageIcon(Const.class.getResource(IMGS_PATH + "filter_icon.png"));
	
	
	
	public static final Icon ADD_PATIENT_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.USER, 28, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon ADD_DOCTOR_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.USER_MD, 28, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon EDIT_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.PENCIL_SQUARE_O, 28, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon DELETE_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.TRASH_O, 28, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon EXIT_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.SIGN_OUT, 28, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon CONFIRM_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.CHECK, 28, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon DISCARD_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.ARROW_LEFT, 28, BUTTON_FOREGROUND_COLOR);
	
	
	
	public static final Icon EMPTY_PANEL_TITLE_ICON = IconFontSwing.buildIcon(FontAwesome.HEARTBEAT, 22, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon ADD_PANEL_TITLE_ICON = IconFontSwing.buildIcon(FontAwesome.USER_PLUS, 22, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon SHOW_MALE_PANEL_TITLE_ICON = IconFontSwing.buildIcon(FontAwesome.MALE, 22, MALE_FOREGROUND_COLOR);
	
	public static final Icon SHOW_FEMALE_PANEL_TITLE_ICON = IconFontSwing.buildIcon(FontAwesome.FEMALE, 22, FEMALE_FOREGROUND_COLOR);
	
	public static final Icon EDIT_PANEL_TITLE_ICON = IconFontSwing.buildIcon(FontAwesome.PENCIL, 22, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon DELETE_PANEL_TITLE_ICON = IconFontSwing.buildIcon(FontAwesome.USER_TIMES, 22, BUTTON_FOREGROUND_COLOR);
}