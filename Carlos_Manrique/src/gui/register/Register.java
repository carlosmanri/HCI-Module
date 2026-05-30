package gui.register;

import javax.swing.JPanel;

import gui.MainWindow;
import gui.newParty.NewParty;
import logic.ApplicationException;
import logic.user.UserAccount;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

public class Register extends JPanel {

	private static final long serialVersionUID = -7929969096590628186L;

	MainWindow mainWindow;
	private JLabel lblUsername;
	private JTextField txtUserName;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JButton btnRegister;
	private JButton btnGoBack;
	private JPanel pnForm;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;

	/**
	 * Create the panel.
	 */
	public Register(MainWindow mainWindow) {
		setBackground(new Color(11,16,27));
		this.mainWindow = mainWindow;
		setLayout(new GridLayout(0, 3, 0, 0));
		add(getLblNewLabel());
		add(getLblNewLabel_1());
		add(getLblNewLabel_2());
		add(getLblNewLabel_3());
		add(getPnForm());
		add(getLblNewLabel_4());
		add(getLblNewLabel_5());
		add(getLblNewLabel_6());
		add(getLblNewLabel_7());
	}

	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel(mainWindow.getResourceBundle().getString("user"));
			lblUsername.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_user").toCharArray()[0]);
			lblUsername.setLabelFor(getTxtUserName());
			lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblUsername;
	}

	private JTextField getTxtUserName() {
		if (txtUserName == null) {
			txtUserName = new JTextField();
			txtUserName.setMinimumSize(new Dimension(100, 25));
			txtUserName.setMaximumSize(new Dimension(2147483647, 25));
			txtUserName.setColumns(10);
		}
		return txtUserName;
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel(mainWindow.getResourceBundle().getString("password"));
			lblPassword.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_password").toCharArray()[0]);
			lblPassword.setLabelFor(getPasswordField());
		}
		return lblPassword;
	}

	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField("");
			passwordField.setMinimumSize(new Dimension(150, 25));
			passwordField.setMaximumSize(new Dimension(2147483647, 25));
		}
		return passwordField;
	}

	private JButton getBtnRegister() {
		if (btnRegister == null) {
			btnRegister = new JButton(mainWindow.getResourceBundle().getString("register"));
			btnRegister.setMnemonic(mainWindow.getResourceBundle().getString("mnc_register").toCharArray()[0]);

			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					registerUser();
				}
			});
		}
		return btnRegister;

	}

	private void registerUser() {
		if(txtUserName.getText() == null || txtUserName.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, mainWindow.getResourceBundle().getString("reg_user_err"), mainWindow.getResourceBundle().getString("invalidForm"), JOptionPane.ERROR_MESSAGE);
			return;			
		}
		if(passwordField.getPassword() == null || passwordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, mainWindow.getResourceBundle().getString("reg_pass_err"), mainWindow.getResourceBundle().getString("invalidForm"), JOptionPane.ERROR_MESSAGE);
			return;
		} 
		
		try {
			mainWindow.getPartyManager().registerNewUser(new UserAccount(txtUserName.getText(),  new String(passwordField.getPassword())));
			NewParty newParty = (NewParty) mainWindow.getNewParty();
			newParty.updateWelcomeLabel();
			mainWindow.logIn();
		}
		catch(ApplicationException e) {
			JOptionPane.showMessageDialog(this, mainWindow.getResourceBundle().getString("reg_userExists_err"), mainWindow.getResourceBundle().getString("userAlreadyExists"), JOptionPane.ERROR_MESSAGE);
			return;	
		}

	}

	public void localize() {
		lblUsername.setText(mainWindow.getResourceBundle().getString("user"));
		lblPassword.setText(mainWindow.getResourceBundle().getString("password"));
		btnRegister.setText(mainWindow.getResourceBundle().getString("register"));
		btnGoBack.setText(mainWindow.getResourceBundle().getString("goBack"));
		
		lblUsername.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_user").toCharArray()[0]);
		lblPassword.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_password").toCharArray()[0]);
		btnRegister.setMnemonic(mainWindow.getResourceBundle().getString("mnc_register").toCharArray()[0]);
		btnGoBack.setMnemonic(mainWindow.getResourceBundle().getString("mnc_goBack").toCharArray()[0]);

	}

	private JButton getBtnGoBack() {
		if (btnGoBack == null) {
			btnGoBack = new JButton(mainWindow.getResourceBundle().getString("goBack"));
			btnGoBack.setMnemonic(mainWindow.getResourceBundle().getString("mnc_goBack").toCharArray()[0]);
			btnGoBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainWindow.showLogin();
				}
			});
		}
		return btnGoBack;
	}

	public void restart() {
		txtUserName.setText("");
		passwordField.setText("");

	}
	private JPanel getPnForm() {
		if (pnForm == null) {
			pnForm = new JPanel();
			pnForm.setMinimumSize(new Dimension(170, 150));
			pnForm.setBorder(new EmptyBorder(5, 5, 5, 5));
			pnForm.setLayout(new GridLayout(0, 1, 0, 0));
			pnForm.add(getLblUsername());
			pnForm.add(getTxtUserName());
			pnForm.add(getLblPassword());
			pnForm.add(getPasswordField());
			pnForm.add(getBtnRegister());
			pnForm.add(getBtnGoBack());
		}
		return pnForm;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
		}
		return lblNewLabel_4;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("");
		}
		return lblNewLabel_5;
	}
	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("");
		}
		return lblNewLabel_6;
	}
	private JLabel getLblNewLabel_7() {
		if (lblNewLabel_7 == null) {
			lblNewLabel_7 = new JLabel("");
		}
		return lblNewLabel_7;
	}
}
