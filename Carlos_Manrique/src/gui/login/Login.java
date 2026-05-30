package gui.login;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.MainWindow;
import gui.newParty.NewParty;
import logic.ApplicationException;
import logic.user.UserAccount;

import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;

public class Login extends JPanel {

	private static final long serialVersionUID = 3273745577049613544L;
	private JPanel pnLogin;
	private JPanel pnBackground;
	private JLabel lblUser;
	private JTextField txtUser;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JPanel pnForm;
	private JButton btnLogin;
	private JButton btnLoginUnregistered;
	private JButton btnSignIn;
	private JPanel pnSignIn;
	private JLabel lblBackgroud;
	private MainWindow mainWindow;
	private JLabel lblError;
	private JLabel lblNewLabel;
	private JLabel lblWords;
	private JPanel pnTitle;

	/**
	 * Create the panel.
	 */
	public Login(MainWindow mainWindow) {
		setBackground(new Color(11,16,27));
		this.mainWindow = mainWindow;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_pnBackground = new GridBagConstraints();
		gbc_pnBackground.gridwidth = 3;
		gbc_pnBackground.insets = new Insets(0, 0, 0, 5);
		gbc_pnBackground.fill = GridBagConstraints.BOTH;
		gbc_pnBackground.gridx = 0;
		gbc_pnBackground.gridy = 0;
		add(getPnBackground(), gbc_pnBackground);
		GridBagConstraints gbc_pnLogin = new GridBagConstraints();
		gbc_pnLogin.fill = GridBagConstraints.BOTH;
		gbc_pnLogin.gridx = 3;
		gbc_pnLogin.gridy = 0;
		add(getPnLogin(), gbc_pnLogin);

	}

	public void localize() {
		lblUser.setText(mainWindow.getResourceBundle().getString("user"));
		lblPassword.setText(mainWindow.getResourceBundle().getString("password"));
		btnLogin.setText(mainWindow.getResourceBundle().getString("login"));
		btnLoginUnregistered.setText(mainWindow.getResourceBundle().getString("unregistered"));
		btnSignIn.setText(mainWindow.getResourceBundle().getString("signIn"));
		lblError.setText(mainWindow.getResourceBundle().getString("loginError"));
		
		lblUser.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_user").toCharArray()[0]);
		lblPassword.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_password").toCharArray()[0]);
		btnLogin.setMnemonic(mainWindow.getResourceBundle().getString("mnc_login").toCharArray()[0]);
		btnLoginUnregistered.setMnemonic(mainWindow.getResourceBundle().getString("mnc_unregistered").toCharArray()[0]);
		btnSignIn.setMnemonic(mainWindow.getResourceBundle().getString("mnc_signIn").toCharArray()[0]);

	}
	
	private JPanel getPnLogin() {
		if (pnLogin == null) {
			pnLogin = new JPanel();
			pnLogin.setLayout(new BorderLayout(0, 0));
			pnLogin.add(getPnForm());
		}
		return pnLogin;
	}
	private JPanel getPnBackground() {
		if (pnBackground == null) {
			pnBackground = new JPanel();
			pnBackground.setBackground(new Color(11, 16, 27));
			pnBackground.setLayout(new BorderLayout(0, 0));
			pnBackground.add(getPnTitle(), BorderLayout.CENTER);
		}
		return pnBackground;
	}
	private JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel(mainWindow.getResourceBundle().getString("user"));
			lblUser.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_user").toCharArray()[0]);
			lblUser.setLabelFor(getTxtUser());
			
		}
		return lblUser;
	}
	private JTextField getTxtUser() {
		if (txtUser == null) {
			txtUser = new JTextField();
			txtUser.setMaximumSize(new Dimension(2147483647, 15));
			txtUser.setColumns(10);
		}
		return txtUser;
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
			passwordField = new JPasswordField();
			passwordField.setMaximumSize(new Dimension(2147483647, 15));
		}
		return passwordField;
	}
	private JPanel getPnForm() {
		if (pnForm == null) {
			pnForm = new JPanel();
			pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.Y_AXIS));
			pnForm.add(getLblUser());
			pnForm.add(getTxtUser());
			pnForm.add(getLblPassword());
			pnForm.add(getPasswordField());
			pnForm.add(getBtnLogin());
			pnForm.add(getBtnLoginUnregistered());
			pnForm.add(getBtnSignIn());
			pnForm.add(getLblError());
			pnForm.add(getPnSignIn());
		}
		return pnForm;
	}
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton(mainWindow.getResourceBundle().getString("login"));
			btnLogin.setMnemonic(mainWindow.getResourceBundle().getString("mnc_login").toCharArray()[0]);
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					logIn();
				}
			});
		}
		return btnLogin;
	}
	
	private void logIn() {
		if(txtUser.getText() == null || txtUser.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, mainWindow.getResourceBundle().getString("reg_user_err"), mainWindow.getResourceBundle().getString("invalidForm"), JOptionPane.ERROR_MESSAGE);
			return;			
		}
		if(passwordField.getPassword() == null || passwordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, mainWindow.getResourceBundle().getString("reg_pass_err"), mainWindow.getResourceBundle().getString("invalidForm"), JOptionPane.ERROR_MESSAGE);
			return;
		} 
		String passText = new String(passwordField.getPassword());
		UserAccount user;
		try {
			user = new UserAccount(txtUser.getText(), passText);
			if(mainWindow.getPartyManager().validateUser(user)) {
				mainWindow.getPartyManager().getBill().setLoged(true);
				mainWindow.getPartyManager().getBill().setUsername(txtUser.getText());
				
				txtUser.setText("");
				passwordField.setText("");

				
				NewParty newParty = (NewParty)mainWindow.getNewParty();
				newParty.updateWelcomeLabel();
				
				mainWindow.logIn();
			}
			else {
				JOptionPane.showMessageDialog(this, mainWindow.getResourceBundle().getString("login_auth_error"), mainWindow.getResourceBundle().getString("login_auth_error_title"), JOptionPane.ERROR_MESSAGE);
			}
		} catch (ApplicationException e) {
			lblError.setVisible(true);
			return;
		}

	}
	
	
	private JButton getBtnLoginUnregistered() {
		if (btnLoginUnregistered == null) {
			btnLoginUnregistered = new JButton(mainWindow.getResourceBundle().getString("unregistered"));
			btnLoginUnregistered.setMnemonic(mainWindow.getResourceBundle().getString("mnc_unregistered").toCharArray()[0]);
			btnLoginUnregistered.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainWindow.getPartyManager().getBill().setLoged(false);
					mainWindow.logIn();
				}
			});
		}
		return btnLoginUnregistered;
	}
	private JButton getBtnSignIn() {
		if (btnSignIn == null) {
			btnSignIn = new JButton(mainWindow.getResourceBundle().getString("signIn"));
			btnSignIn.setMnemonic(mainWindow.getResourceBundle().getString("mnc_signIn").toCharArray()[0]);
			btnSignIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mainWindow.register();
				}
			});
		}
		return btnSignIn;
	}
	private JPanel getPnSignIn() {
		if (pnSignIn == null) {
			pnSignIn = new JPanel();
		}
		return pnSignIn;
	}
	private JLabel getLblBackgroud() {
		if (lblBackgroud == null) {
			lblBackgroud = new JLabel("Party Manager");
			
			File font_file = new File("Fonts/Helvetica-Bold.ttf");
			try {
				Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
				
				lblBackgroud.setFont(font.deriveFont(Font.PLAIN, 66));
			} catch (FontFormatException | IOException e) {
				lblBackgroud.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 46));
				//e.printStackTrace();
			}
			
			lblBackgroud.setForeground(mainWindow.getBlueColor());
			lblBackgroud.setHorizontalAlignment(SwingConstants.CENTER);
			//ImageIcon imageIcon = new ImageIcon(Login.class.getResource("/gui/img/photos/Influencer-Marketing-Twitter.jpg")); 
			//Image image = imageIcon.getImage(); 
			//Image newimg = image.getScaledInstance(lblBackgroud.getWidth(), lblBackgroud.getHeight(),  java.awt.Image.SCALE_SMOOTH);  
			//imageIcon = new ImageIcon(newimg); 
			//lblBackgroud.setIcon(imageIcon);
		}
		return lblBackgroud;
	}
	private JLabel getLblError() {
		if (lblError == null) {
			lblError = new JLabel(mainWindow.getResourceBundle().getString("loginError"));
			lblError.setVisible(false);;
		}
		return lblError;
	}

	public void restart() {
		txtUser.setText("");
		passwordField.setText("");
		lblError.setVisible(false);
	}

	private JLabel getLblWords() {
		if (lblWords == null) {
			lblWords = new JLabel("Success \u25AA Influence \u25AA Engaging \u25AA Communication");
			
			File font_file = new File("Fonts/Helvetica.ttf");
			try {
				Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
				
				lblWords.setFont(font.deriveFont(Font.PLAIN, 15));
			} catch (FontFormatException | IOException e) {
				//lblWords.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 46));
				//e.printStackTrace();
			}
			
			lblWords.setForeground(mainWindow.getBlueColor());
		
		}
		return lblWords;
	}
	private JPanel getPnTitle() {
		if (pnTitle == null) {
			pnTitle = new JPanel();
			pnTitle.add(getLblBackgroud());
			pnTitle.add(getLblWords());
			pnTitle.setBackground(new Color(11,16,27));
		}
		return pnTitle;
	}
}
