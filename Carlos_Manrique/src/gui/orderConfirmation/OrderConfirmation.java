package gui.orderConfirmation;

import javax.swing.JPanel;

import gui.MainWindow;
import logic.ApplicationException;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;

public class OrderConfirmation extends JPanel {

	private static final long serialVersionUID = 2377670602585492522L;
	
	private MainWindow mainWindow;
	private JPanel pnForm;
	private JPanel pnSummary;
	private JScrollPane scrollPane;
	private JTextArea taSummary;
	private JLabel lblOrderConfirmation;
	private JPanel pnTitle;
	private JLabel lblPrice;
	private JButton btnLogin;
	private JPanel pnCustomerInfo;
	private JLabel lblName;
	private JLabel lblSurname;
	private JTextField txtName;
	private JTextField txtSurname;
	private JLabel lblDni;
	private JLabel lblDate;
	private JTextField txtDni;
	private JPanel pnDate;
	private JPanel pnComments;
	private JScrollPane spComments;
	private JTextArea taComments;
	private JPanel pnButtons;
	private JButton btnBack;
	private JButton btnCancel;
	private JButton btnConfirm;
	private JButton btnPay;
	private JPanel pnBtnWrapper;
	private JPanel pnBackLoginBtn;
	private JPanel pnBtnNav;
	private JXDatePicker datePicker;
	/**
	 * Create the panel.
	 */
	public OrderConfirmation(MainWindow mainWindow) {

		this.mainWindow = mainWindow;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 300, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_pnForm = new GridBagConstraints();
		gbc_pnForm.insets = new Insets(0, 0, 5, 0);
		gbc_pnForm.fill = GridBagConstraints.BOTH;
		gbc_pnForm.gridx = 0;
		gbc_pnForm.gridy = 0;
		add(getPnForm(), gbc_pnForm);
		GridBagConstraints gbc_pnSummary = new GridBagConstraints();
		gbc_pnSummary.fill = GridBagConstraints.BOTH;
		gbc_pnSummary.gridx = 1;
		gbc_pnSummary.gridy = 0;
		add(getPnSummary(), gbc_pnSummary);
		
	}
	
	public void updatePriceLabel() {
		lblPrice.setText(mainWindow.getResourceBundle().getString("total")+" " + mainWindow.getPartyManager().getBill().getTotalPrice()+" €");

	}

	private JPanel getPnForm() {
		if (pnForm == null) {
			pnForm = new JPanel();
			pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.Y_AXIS));
			pnForm.add(getPnTitle());
			pnForm.add(getPnCustomerInfo());
			pnForm.add(getPnComments());
			pnForm.add(getPnButtons());
			pnForm.add(getPnBtnWrapper());
		}
		return pnForm;
	}
	private JPanel getPnSummary() {
		if (pnSummary == null) {
			pnSummary = new JPanel();
			pnSummary.setLayout(new BoxLayout(pnSummary, BoxLayout.Y_AXIS));
			pnSummary.add(getScrollPane());
			pnSummary.add(getBtnPay());
		}
		return pnSummary;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTaSummary());
		}
		return scrollPane;
	}
	private JTextArea getTaSummary() {
		if (taSummary == null) {
			taSummary = new JTextArea();
			taSummary.setEditable(false);
		}
		return taSummary;
	}
	private JLabel getLblOrderConfirmation() {
		if (lblOrderConfirmation == null) {
			lblOrderConfirmation = new JLabel(mainWindow.getResourceBundle().getString("orderConfirmation"));
		}
		return lblOrderConfirmation;
	}
	private JPanel getPnTitle() {
		if (pnTitle == null) {
			pnTitle = new JPanel();
			pnTitle.add(getLblOrderConfirmation());
			pnTitle.add(getLblPrice());
		}
		return pnTitle;
	}
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel(mainWindow.getResourceBundle().getString("total") + mainWindow.getPartyManager().getBill().getTotalPrice()+"€");
		}
		return lblPrice;
	}
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton(mainWindow.getResourceBundle().getString("login"));
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainWindow.showLogin();
				}
			});
		}
		return btnLogin;
	}
	private JPanel getPnCustomerInfo() {
		if (pnCustomerInfo == null) {
			pnCustomerInfo = new JPanel();
			pnCustomerInfo.setBorder(new TitledBorder(null, mainWindow.getResourceBundle().getString("addYourData"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCustomerInfo.setLayout(new GridLayout(0, 2, 0, 0));
			pnCustomerInfo.add(getLblName());
			pnCustomerInfo.add(getLblSurname());
			pnCustomerInfo.add(getTxtName());
			pnCustomerInfo.add(getTxtSurname());
			pnCustomerInfo.add(getLblDni());
			pnCustomerInfo.add(getLblDate());
			pnCustomerInfo.add(getTxtDni());
			pnCustomerInfo.add(getPnDate());
		}
		return pnCustomerInfo;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel(mainWindow.getResourceBundle().getString("name"));
			lblName.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_name").toCharArray()[0]);
			lblName.setLabelFor(getTxtName());
		}
		return lblName;
	}
	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel(mainWindow.getResourceBundle().getString("surname"));
			lblSurname.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_surname").toCharArray()[0]);
			lblSurname.setLabelFor(getTxtSurname());
		}
		return lblSurname;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setMaximumSize(new Dimension(2147483647, 25));
			txtName.setColumns(10);
		}
		return txtName;
	}
	private JTextField getTxtSurname() {
		if (txtSurname == null) {
			txtSurname = new JTextField();
			txtSurname.setMaximumSize(new Dimension(2147483647, 25));
			txtSurname.setColumns(10);
		}
		return txtSurname;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel(mainWindow.getResourceBundle().getString("dni"));
			lblDni.setLabelFor(getTxtDni());
		}
		return lblDni;
	}
	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel(mainWindow.getResourceBundle().getString("date"));
			lblDate.setLabelFor(getDatePicker());
		}
		return lblDate;
	}
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setMaximumSize(new Dimension(2147483647, 25));
			txtDni.setColumns(10);
		}
		return txtDni;
	}
	private JPanel getPnDate() {
		if (pnDate == null) {
			pnDate = new JPanel();
			pnDate.add(getDatePicker());
		}
		return pnDate;
	}
	
	private JXDatePicker getDatePicker() {
		if(datePicker == null) {
			datePicker = new JXDatePicker();
			datePicker.setDate(Calendar.getInstance().getTime());
			datePicker.setFormats(new SimpleDateFormat("dd-MM-yy"));
			datePicker.getMonthView().setLowerBound(new Date());
		}
		return datePicker;
	}
	private JPanel getPnComments() {
		if (pnComments == null) {
			pnComments = new JPanel();
			pnComments.setBorder(new TitledBorder(null, mainWindow.getResourceBundle().getString("addComment"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnComments.add(getSpComments());
		}
		return pnComments;
	}
	private JScrollPane getSpComments() {
		if (spComments == null) {
			spComments = new JScrollPane();
			spComments.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			spComments.setMinimumSize(new Dimension(200, 40));
			spComments.setViewportView(getTaComments());
		}
		return spComments;
	}
	private JTextArea getTaComments() {
		if (taComments == null) {
			taComments = new JTextArea();
			taComments.setPreferredSize(new Dimension(100, 40));
			taComments.setMinimumSize(new Dimension(100, 40));
		}
		return taComments;
	}
	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
		}
		return pnButtons;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton(mainWindow.getResourceBundle().getString("goBack"));
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainWindow.showHiringPanel();
				}
			});
		}
		return btnBack;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton(mainWindow.getResourceBundle().getString("cancel"));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						mainWindow.getPartyManager().restart();
						mainWindow.restart();

					} catch (ApplicationException e1) {
						e1.printStackTrace();
					}
					mainWindow.showLogin();					

				}
			});
		}
		return btnCancel;
	}
	private JButton getBtnConfirm() {
		if (btnConfirm == null) {
			btnConfirm = new JButton(mainWindow.getResourceBundle().getString("confirmOrder"));
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(txtName.getText().length()==0 || txtSurname.getText().length()==0 || txtDni.getText().length() == 0 || datePicker.getDate() == null) {
						JOptionPane.showMessageDialog(getParent(), mainWindow.getResourceBundle().getString("infoMissing"), mainWindow.getResourceBundle().getString("infoMissingTitle"), JOptionPane.ERROR_MESSAGE);
						return;
					}
					mainWindow.getPartyManager().getBill().setName(txtName.getText());
					mainWindow.getPartyManager().getBill().setSurname(txtSurname.getText());
					mainWindow.getPartyManager().getBill().setNif(txtDni.getText());
					mainWindow.getPartyManager().getBill().setDate(datePicker.getDate());  
					mainWindow.getPartyManager().getBill().setComments(taComments.getText());
					taSummary.setText(mainWindow.getPartyManager().getBill().toString());
					btnPay.setVisible(true);
				}
			});
		}
		return btnConfirm;
	}
	private JButton getBtnPay() {
		if (btnPay == null) {
			btnPay = new JButton(mainWindow.getResourceBundle().getString("pay"));
			btnPay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainWindow.showSuccess();
				}
			});
			btnPay.setVisible(false);
		}
		return btnPay;
	}

	public void restart() {
		txtName.setText("");
		txtSurname.setText("");
        datePicker.setDate(Calendar.getInstance().getTime());
		txtDni.setText("");
		taComments.setText("");
		taSummary.setText("");
	}
	private JPanel getPnBtnWrapper() {
		if (pnBtnWrapper == null) {
			pnBtnWrapper = new JPanel();
			pnBtnWrapper.setLayout(new BorderLayout(0, 0));
			pnBtnWrapper.add(getPnBackLoginBtn(), BorderLayout.WEST);
			pnBtnWrapper.add(getPnBtnNav(), BorderLayout.EAST);
		}
		return pnBtnWrapper;
	}
	private JPanel getPnBackLoginBtn() {
		if (pnBackLoginBtn == null) {
			pnBackLoginBtn = new JPanel();
			pnBackLoginBtn.add(getBtnBack());
			pnBackLoginBtn.add(getBtnLogin());
		}
		return pnBackLoginBtn;
	}
	private JPanel getPnBtnNav() {
		if (pnBtnNav == null) {
			pnBtnNav = new JPanel();
			pnBtnNav.add(getBtnConfirm());
			pnBtnNav.add(getBtnCancel());
		}
		return pnBtnNav;
	}

	public void localize() {

		btnBack.setText(mainWindow.getResourceBundle().getString("goBack"));
		btnCancel.setText(mainWindow.getResourceBundle().getString("cancel"));
		btnConfirm.setText(mainWindow.getResourceBundle().getString("confirmOrder"));
		btnLogin.setText(mainWindow.getResourceBundle().getString("login"));
		btnPay.setText(mainWindow.getResourceBundle().getString("pay"));
		
		btnBack.setMnemonic(mainWindow.getResourceBundle().getString("mnc_goBack").toCharArray()[0]);
		btnConfirm.setMnemonic(mainWindow.getResourceBundle().getString("mnc_confirmOrder").toCharArray()[0]);
		btnLogin.setMnemonic(mainWindow.getResourceBundle().getString("mnc_login").toCharArray()[0]);
		btnPay.setMnemonic(mainWindow.getResourceBundle().getString("mnc_pay").toCharArray()[0]);
		
		lblDate.setText(mainWindow.getResourceBundle().getString("date"));
		lblDni.setText(mainWindow.getResourceBundle().getString("dni"));
		lblName.setText(mainWindow.getResourceBundle().getString("name"));
		lblOrderConfirmation.setText(mainWindow.getResourceBundle().getString("orderConfirmation"));
		lblSurname.setText(mainWindow.getResourceBundle().getString("surname"));
		
		lblDate.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_date").toCharArray()[0]);
		lblDni.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_dni").toCharArray()[0]);
		lblName.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_name").toCharArray()[0]);
		lblSurname.setDisplayedMnemonic(mainWindow.getResourceBundle().getString("mnc_surname").toCharArray()[0]);

		
		pnComments.setBorder(new TitledBorder(null, mainWindow.getResourceBundle().getString("addComment"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnCustomerInfo.setBorder(new TitledBorder(null, mainWindow.getResourceBundle().getString("addYourData"), TitledBorder.LEADING, TitledBorder.TOP, null, null));

	}
	
	
	
	
	
	
}
