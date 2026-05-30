package gui.newParty;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import gui.MainWindow;
import gui.hiringPanel.Hiring;
import gui.shoppingCart.ShoppingCart;
import logic.ApplicationException;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.HierarchyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NewParty extends JPanel {

	private static final long serialVersionUID = -8915450270163887528L;
	private MainWindow mainWindow;
	private JLabel lblWelcome;
	private JPanel panel;
	private JLabel lblNumberPeople;
	private JSpinner spPeople;
	private JPanel pnButtons;
	private JButton btnNext;
	private JButton btnCancel;
	private JPanel pnWrapper;


	/**
	 * Create the panel.
	 */
	public NewParty(MainWindow mainWindow) {


		setBackground(new Color(11,16,27));
		this.mainWindow = mainWindow;
		setLayout(new BorderLayout(0, 0));

		add(getLblWelcome(), BorderLayout.NORTH);
		add(getPanel(), BorderLayout.CENTER);
		add(getPnButtons(), BorderLayout.SOUTH);
	

	}
	
	public void updateWelcomeLabel() {
		lblWelcome.setText("");
		if(mainWindow.getPartyManager().getBill().isLoged()) {
			lblWelcome.setText(mainWindow.getResourceBundle().getString("welcome")+ " "+mainWindow.getPartyManager().getBill().getUsername()+"!");
		}
		else {
			lblWelcome.setText(mainWindow.getResourceBundle().getString("welcome")+ " "+mainWindow.getResourceBundle().getString("guest")+"!");
		}
	}

	public void localize() {
		lblWelcome.setText(mainWindow.getResourceBundle().getString("welcome"));
		updateWelcomeLabel();
		lblNumberPeople.setText(mainWindow.getResourceBundle().getString("howManyPeople"));
		btnCancel.setText(mainWindow.getResourceBundle().getString("cancel"));
		btnNext.setText(mainWindow.getResourceBundle().getString("next"));

	}
	
	private JLabel getLblWelcome() {
		if (lblWelcome == null) {
			lblWelcome = new JLabel(mainWindow.getResourceBundle().getString("welcome"));
			lblWelcome.setForeground(new Color(45, 196, 182));

		}
		if(mainWindow.getPartyManager().getBill().isLoged()) {
			lblWelcome.setText(lblWelcome.getText()+ " "+mainWindow.getPartyManager().getBill().getUsername()+"!");
		}
		else {
			lblWelcome.setText(lblWelcome.getText()+ " "+mainWindow.getResourceBundle().getString("guest")+"!");
		}
		return lblWelcome;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(new Color(11,16,27));
			GridBagLayout gbl_panel = new GridBagLayout();
//			gbl_panel.columnWidths = new int[]{450, 0};
//			gbl_panel.rowHeights = new int[]{267, 0};
			gbl_panel.columnWeights = new double[]{0.0};
			gbl_panel.rowWeights = new double[]{0.0};
			panel.setLayout(gbl_panel);
//			GridBagConstraints gbc_pnWrapper = new GridBagConstraints();
//			gbc_pnWrapper.fill = GridBagConstraints.BOTH;
//			gbc_pnWrapper.gridx = 0;
//			gbc_pnWrapper.gridy = 0;
//			panel.add(getPnWrapper(), gbc_pnWrapper);
			panel.add(getPnWrapper());

		}
		return panel;
	}
	private JLabel getLblNumberPeople() {
		if (lblNumberPeople == null) {
			lblNumberPeople = new JLabel(mainWindow.getResourceBundle().getString("howManyPeople"));
			lblNumberPeople.setForeground(new Color(45, 196, 182));
			File font_file = new File("Fonts/Open_Sans/OpenSans-SemiBold.ttf");
		
				Font font;
				try {
					font = Font.createFont(Font.TRUETYPE_FONT, font_file);
					font = font.deriveFont(Font.PLAIN, 30);
					lblNumberPeople.setFont(font);
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			lblNumberPeople.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_howManyPeople").toCharArray()[0]);
			lblNumberPeople.setLabelFor(getSpPeople());
		}
		return lblNumberPeople;
	}
	private JSpinner getSpPeople() {
		if (spPeople == null) {
			spPeople = new JSpinner();
			spPeople.setForeground(new Color(11,16,27));
			spPeople.setBackground(new Color(11,16,27));
			spPeople.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 45));
			spPeople.setMinimumSize(new Dimension(65, 20));
			spPeople.setMaximumSize(new Dimension(80, 30));
			spPeople.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		}
		return spPeople;
	}
	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.add(getBtnNext());

			pnButtons.add(getBtnCancel());
		}
		return pnButtons;
	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton(mainWindow.getResourceBundle().getString("next"));
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int value = (Integer) spPeople.getValue();
					mainWindow.getPartyManager().getBill().setNumberAttendants(value);
					Hiring hr = (Hiring)mainWindow.getHiring();
					hr.updatePeople();
					ShoppingCart sc = (ShoppingCart)mainWindow.getShoppingCart();
					sc.updatePeople();
					mainWindow.showHiringPanel();
				}
			});
		}

		return btnNext;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton(mainWindow.getResourceBundle().getString("cancel"));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancel();
				}
			});
		}
		return btnCancel;
	}
	
	private void cancel() {
		try {
			mainWindow.getPartyManager().restart();
			mainWindow.restart();

		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}
		mainWindow.showLogin();		
	}

	public void restart() {
		spPeople.setValue(1);
		updateWelcomeLabel();
	}
	

	private JPanel getPnWrapper() {
		if (pnWrapper == null) {
			pnWrapper = new JPanel();
			pnWrapper.setBackground(new Color(11,16,27));
			pnWrapper.add(getLblNumberPeople());
			pnWrapper.add(getSpPeople());
		}
		return pnWrapper;
	}
}
