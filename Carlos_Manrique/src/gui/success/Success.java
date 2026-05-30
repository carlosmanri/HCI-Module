package gui.success;

import javax.swing.JPanel;

import gui.MainWindow;
import logic.ApplicationException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Success extends JPanel {

	private static final long serialVersionUID = 7209529532735628659L;
	private MainWindow mainWindow;
	private JLabel lblSuccess;
	private JLabel lblSuccessIcon;
	private JLabel lblOrderProcessed;
	private JButton btnPrint;
	private JButton btnNewParty;
	
	/**
	 * Create the panel.
	 */
	public Success(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{60, 60, 60, 60, 60, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_lblSuccess = new GridBagConstraints();
		gbc_lblSuccess.fill = GridBagConstraints.VERTICAL;
		gbc_lblSuccess.insets = new Insets(0, 0, 5, 0);
		gbc_lblSuccess.gridx = 0;
		gbc_lblSuccess.gridy = 0;
		add(getLblSuccess(), gbc_lblSuccess);
		GridBagConstraints gbc_lblSuccessIcon = new GridBagConstraints();
		gbc_lblSuccessIcon.fill = GridBagConstraints.VERTICAL;
		gbc_lblSuccessIcon.insets = new Insets(0, 0, 5, 0);
		gbc_lblSuccessIcon.gridx = 0;
		gbc_lblSuccessIcon.gridy = 1;
		add(getLblSuccessIcon(), gbc_lblSuccessIcon);
		GridBagConstraints gbc_lblOrderProcessed = new GridBagConstraints();
		gbc_lblOrderProcessed.fill = GridBagConstraints.VERTICAL;
		gbc_lblOrderProcessed.insets = new Insets(0, 0, 5, 0);
		gbc_lblOrderProcessed.gridx = 0;
		gbc_lblOrderProcessed.gridy = 2;
		add(getLblOrderProcessed(), gbc_lblOrderProcessed);
		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.fill = GridBagConstraints.VERTICAL;
		gbc_btnPrint.insets = new Insets(0, 0, 5, 0);
		gbc_btnPrint.gridx = 0;
		gbc_btnPrint.gridy = 3;
		add(getBtnPrint(), gbc_btnPrint);
		GridBagConstraints gbc_btnNewParty = new GridBagConstraints();
		gbc_btnNewParty.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewParty.gridx = 0;
		gbc_btnNewParty.gridy = 4;
		add(getBtnNewParty(), gbc_btnNewParty);

	}

	private JLabel getLblSuccess() {
		if (lblSuccess == null) {
			lblSuccess = new JLabel(mainWindow.getResourceBundle().getString("success"));
		}
		return lblSuccess;
	}
	
	private JLabel getLblSuccessIcon() {
		if (lblSuccessIcon == null) {
			lblSuccessIcon = new JLabel("");
			lblSuccessIcon.setHorizontalAlignment(SwingConstants.CENTER);
			lblSuccessIcon.setIcon(new ImageIcon(Success.class.getResource("/gui/img/icons/success.png")));
		}
		return lblSuccessIcon;
	}
	private JLabel getLblOrderProcessed() {
		if (lblOrderProcessed == null) {
			lblOrderProcessed = new JLabel(mainWindow.getResourceBundle().getString("orderProcesed"));
		}
		return lblOrderProcessed;
	}
	private JButton getBtnPrint() {
		if (btnPrint == null) {
			btnPrint = new JButton(mainWindow.getResourceBundle().getString("printReceipt"));
			btnPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(getRootPane(), mainWindow.getResourceBundle().getString("printing"), mainWindow.getResourceBundle().getString("printingTitle"), JOptionPane.INFORMATION_MESSAGE);
					mainWindow.getPartyManager().printReceipt();
				}
			});
		}
		return btnPrint;
	}
	private JButton getBtnNewParty() {
		if (btnNewParty == null) {
			btnNewParty = new JButton(mainWindow.getResourceBundle().getString("newParty"));
			btnNewParty.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainWindow.restart();		
					mainWindow.showLogin();
				}
			});
		}
		return btnNewParty;
	}

	public void localize() {
		btnNewParty.setText(mainWindow.getResourceBundle().getString("newParty"));
		btnPrint.setText(mainWindow.getResourceBundle().getString("printReceipt"));
		lblOrderProcessed.setText(mainWindow.getResourceBundle().getString("orderProcesed"));
		lblSuccess.setText(mainWindow.getResourceBundle().getString("success"));

	}

}
