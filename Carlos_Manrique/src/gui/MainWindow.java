package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.hiringPanel.Hiring;
import gui.login.Login;
import gui.newParty.NewParty;
import gui.orderConfirmation.OrderConfirmation;
import gui.register.Register;
import gui.shoppingCart.ShoppingCart;
import gui.success.Success;
import logic.ApplicationException;
import logic.PartyManager;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.help.CSH;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ButtonGroup;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1101856805333404940L;

	private PartyManager partyManager = new PartyManager();

	private Login login;
	private NewParty newParty;
	private Hiring hiring;
	private Register register;
	private ShoppingCart shoppingCart;
	private OrderConfirmation orderConfirmation;
	private Success success;

	private JPanel pnBase;
	private JMenuBar menuBar;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JMenu mnLanguage;
	private JRadioButtonMenuItem rdbtnmntmEnglish;
	private JRadioButtonMenuItem rdbtnmntmSpanish;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	Locale locale = Locale.getDefault(Locale.Category.FORMAT);
	ResourceBundle strings = ResourceBundle.getBundle("resources/res", locale);
	private JMenuItem mntmHelp;
	private JSeparator separator;
	private JMenu mnParty;
	private JMenuItem mntmExit;
	private JMenuItem mntmCancel;
	private JSeparator separator_1;
	private JMenuItem mntmNew;

	
	private Color backgroundColor = new Color(11,16,27);
	private Color whiteColor = new Color(253,255,252);
	private Color blueColor = new Color(46,196,182);
	private Color redColor = new Color(231, 29, 54);
	private Color orangeColor = new Color(255, 159, 28);
	
	
	
	private void loadHelp() {

		URL hsURL;
		HelpSet hs;

		try {
			File file = new File("help/Help.hs");
			hsURL = file.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		}

		catch (Exception e) {
			System.out.println("Help not found");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();

		hb.enableHelpKey(getRootPane(), "introduction", hs);
		
		ActionListener helper = new CSH.DisplayHelpFromSource(hb);
		mntmHelp.addActionListener(helper);

	}
	
	public void restart() {

		login.restart();
		register.restart();
		newParty.restart();
		hiring.restart();
		shoppingCart.restart();
		orderConfirmation.restart();
		
	}
	
	private void localize(Locale locale) {

		strings = ResourceBundle.getBundle("resources/res", locale);

		mnHelp.setText(strings.getString("help"));
		mntmHelp.setText(strings.getString("help"));
		mntmAbout.setText(strings.getString("about"));
		mnHelp.setMnemonic(strings.getString("mnc_help").toCharArray()[0]);
		mntmHelp.setMnemonic(strings.getString("mnc_help").toCharArray()[0]);
		mntmAbout.setMnemonic(strings.getString("mnc_about").toCharArray()[0]);

		mnLanguage.setText(strings.getString("languages"));
		rdbtnmntmEnglish.setText(strings.getString("english"));
		rdbtnmntmSpanish.setText(strings.getString("spanish"));
		mnLanguage.setMnemonic(strings.getString("mnc_languages").toCharArray()[0]);
		rdbtnmntmEnglish.setMnemonic(strings.getString("mnc_english").toCharArray()[0]);
		rdbtnmntmSpanish.setMnemonic(strings.getString("mnc_spanish").toCharArray()[0]);
		
		mnParty.setText(strings.getString("party"));
		mntmNew.setText(strings.getString("new"));
		mntmCancel.setText(strings.getString("cancel"));
		mntmExit.setText(strings.getString("exit"));
		mnParty.setMnemonic(strings.getString("mnc_party").toCharArray()[0]);
		mntmNew.setMnemonic(strings.getString("mnc_new").toCharArray()[0]);
		mntmCancel.setMnemonic(strings.getString("mnc_cancel").toCharArray()[0]);
		mntmExit.setMnemonic(strings.getString("mnc_exit").toCharArray()[0]);
		
		login.localize();
		register.localize();
		newParty.localize();
		hiring.localize();
		shoppingCart.localize();
		orderConfirmation.localize();
		success.localize();
	}

	public ResourceBundle getResourceBundle() {
		return strings;
	}

	public PartyManager getPartyManager() {
		return partyManager;
	}

	public void showLogin() {
		CardLayout cardLayout = (CardLayout) pnBase.getLayout();
		cardLayout.show(pnBase, "pnLogin");
	}
	
	public void logIn() {
		CardLayout cardLayout = (CardLayout) pnBase.getLayout();
		cardLayout.show(pnBase, "pnNewParty");
	}
	public void register() {
		CardLayout cardLayout = (CardLayout) pnBase.getLayout();
		cardLayout.show(pnBase, "pnRegister");
	}
	
	public void showHiringPanel() {
		CardLayout cardLayout = (CardLayout) pnBase.getLayout();
		cardLayout.show(pnBase, "pnHiring");		
	}
	
	public void showSuccess() {
		CardLayout cardLayout = (CardLayout) pnBase.getLayout();
		cardLayout.show(pnBase, "pnSuccess");		
	}
	
	public void showOrderConfirmation() {
		CardLayout cardLayout = (CardLayout) pnBase.getLayout();
		cardLayout.show(pnBase, "pnOrderConfirmation");			
	}
	
	public void showShoppingCart() {
		CardLayout cardLayout = (CardLayout) pnBase.getLayout();
		cardLayout.show(pnBase, "pnShoppingCart");		
	}

	private JPanel getLogin() {
		if (login == null) {
			login = new Login(this);

		}
		return login;
	}
	
	public JPanel getShoppingCart() {
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart(this);

		}
		return shoppingCart;
	}
	private JPanel getRegister() {
		if (register == null) {
			register = new Register(this);

		}
		return register;
	}
	
	private JPanel getSuccess() {
		if (success == null) {
			success = new Success(this);

		}
		return success;
	}
	
	public JPanel getNewParty() {
		if (newParty == null) {
			newParty = new NewParty(this);

		}
		return newParty;
	}
	
	public JPanel getHiring() {
		if (hiring == null) {
			hiring = new Hiring(this);

		}
		return hiring;
	}
	
	public JPanel getOrderConfirmation() {
		if (orderConfirmation == null) {
			orderConfirmation = new OrderConfirmation(this);

		}
		return orderConfirmation;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
					File font_file = new File("Fonts/Open_Sans/OpenSans-SemiBold.ttf");
					try {
						Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
						font = font.deriveFont(Font.PLAIN, 12);
						UIManager.put("Button.font", font);
						UIManager.put("ToggleButton.font", font);
						UIManager.put("RadioButton.font", font);
						UIManager.put("CheckBox.font", font);
						UIManager.put("ColorChooser.font", font);
						UIManager.put("ComboBox.font", font);
						UIManager.put("Label.font", font);
						UIManager.put("List.font", font);
						UIManager.put("MenuBar.font", font);
						UIManager.put("MenuItem.font", font);
						UIManager.put("RadioButtonMenuItem.font", font);
						UIManager.put("CheckBoxMenuItem.font", font);
						UIManager.put("Menu.font", font);
						UIManager.put("PopupMenu.font", font);
						UIManager.put("OptionPane.font", font);
						UIManager.put("Panel.font", font);
						UIManager.put("ProgressBar.font", font);
						UIManager.put("ScrollPane.font", font);
						UIManager.put("Viewport.font", font);
						UIManager.put("TabbedPane.font", font);
						UIManager.put("Table.font", font);
						UIManager.put("TableHeader.font", font);
						UIManager.put("TextField.font", font);
						UIManager.put("PasswordField.font", font);
						UIManager.put("TextArea.font", font);
						UIManager.put("TextPane.font", font);
						UIManager.put("EditorPane.font", font);
						UIManager.put("TitledBorder.font", font);
						UIManager.put("ToolBar.font", font);
						UIManager.put("ToolTip.font", font);
						UIManager.put("Tree.font", font);	
					}
					catch (FontFormatException | IOException e) {
						e.printStackTrace();
					}
					
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Party Manager");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/gui/img/icons/cart.png")));
		
		try {
			partyManager.init();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 550);
		setJMenuBar(getMenuBar_1());
		pnBase = new JPanel();
		//pnBase.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnBase);
		pnBase.setLayout(new CardLayout(0, 0));
		pnBase.add(getLogin(), "pnLogin");
		pnBase.add(getNewParty(), "pnNewParty");
		pnBase.add(getHiring(), "pnHiring");
		pnBase.add(getRegister(), "pnRegister");
		pnBase.add(getShoppingCart(), "pnShoppingCart");
		pnBase.add(getOrderConfirmation(), "pnOrderConfirmation");
		pnBase.add(getSuccess(), "pnSuccess");

		setLocationRelativeTo(null);

		loadHelp();
		

	}


	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnParty());
			menuBar.add(getMnLanguage());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu(strings.getString("help"));
			mnHelp.setMnemonic(strings.getString("mnc_help").toCharArray()[0]);
			mnHelp.add(getMntmHelp());
			mnHelp.add(getSeparator());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem(strings.getString("about"));
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(getContentPane(), strings.getString("aboutMessage"),
							strings.getString("aboutTitle"), JOptionPane.PLAIN_MESSAGE);
				}
			});
			mntmAbout.setMnemonic(strings.getString("mnc_about").toCharArray()[0]);
		}
		return mntmAbout;
	}

	private JMenu getMnLanguage() {
		if (mnLanguage == null) {
			mnLanguage = new JMenu(strings.getString("languages"));
			mnLanguage.add(getRdbtnmntmEnglish());
			mnLanguage.add(getRdbtnmntmSpanish());
			mnLanguage.setMnemonic(strings.getString("mnc_languages").toCharArray()[0]);
		}
		return mnLanguage;
	}

	private JRadioButtonMenuItem getRdbtnmntmEnglish() {
		if (rdbtnmntmEnglish == null) {
			rdbtnmntmEnglish = new JRadioButtonMenuItem(strings.getString("english"));
			rdbtnmntmEnglish.setIcon(new ImageIcon(MainWindow.class.getResource("/gui/img/icons/uk16x.png")));
			if(strings.getLocale().equals(new Locale("en")))
				rdbtnmntmEnglish.setSelected(true);
			rdbtnmntmEnglish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					localize(new Locale("en"));
				}
			});
			buttonGroup.add(rdbtnmntmEnglish);
			rdbtnmntmEnglish.setMnemonic(strings.getString("mnc_english").toCharArray()[0]);
		}
		return rdbtnmntmEnglish;
	}

	private JRadioButtonMenuItem getRdbtnmntmSpanish() {
		if (rdbtnmntmSpanish == null) {
			rdbtnmntmSpanish = new JRadioButtonMenuItem(strings.getString("spanish"));
			rdbtnmntmSpanish.setIcon(new ImageIcon(MainWindow.class.getResource("/gui/img/icons/spain16x.png")));
			if(strings.getLocale().equals(new Locale("es")))
				rdbtnmntmSpanish.setSelected(true);
			rdbtnmntmSpanish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					localize(new Locale("es"));
				}
			});
			buttonGroup.add(rdbtnmntmSpanish);
			rdbtnmntmSpanish.setMnemonic(strings.getString("mnc_spanish").toCharArray()[0]);
		}
		return rdbtnmntmSpanish;
	}




	private JMenuItem getMntmHelp() {
		if (mntmHelp == null) {
			mntmHelp = new JMenuItem(strings.getString("help"));
			mntmHelp.setMnemonic(strings.getString("mnc_help").toCharArray()[0]);
			
		}
		return mntmHelp;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenu getMnParty() {
		if (mnParty == null) {
			mnParty = new JMenu(strings.getString("party"));
			mnParty.setMnemonic(strings.getString("mnc_party").toCharArray()[0]);
			mnParty.add(getMntmNew());
			mnParty.add(getMntmCancel());
			mnParty.add(getSeparator_1());
			mnParty.add(getMntmExit());
		}
		return mnParty;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem(strings.getString("exit"));
			mntmExit.setMnemonic(strings.getString("mnc_exit").toCharArray()[0]);
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showExitConfirmationDialog();
				}
			});
		}
		return mntmExit;
	}
	
	private void showExitConfirmationDialog() {
		int res = JOptionPane.showConfirmDialog(this, strings.getString("exitConfirmation"), "Party manager: "+strings.getString("exit"), JOptionPane.WARNING_MESSAGE);
		if(res == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	private JMenuItem getMntmCancel() {
		if (mntmCancel == null) {
			mntmCancel = new JMenuItem(strings.getString("cancel"));
			mntmCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						getPartyManager().restart();
						restart();
						showLogin();		
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			mntmCancel.setMnemonic(strings.getString("mnc_cancel").toCharArray()[0]);
		}
		return mntmCancel;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JMenuItem getMntmNew() {
		if (mntmNew == null) {
			mntmNew = new JMenuItem(strings.getString("new"));
			mntmNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					partyManager.newParty();
					restart();
					logIn();
				}
			});
			mntmNew.setMnemonic(strings.getString("mnc_new").toCharArray()[0]);
		}
		return mntmNew;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Color getWhiteColor() {
		return whiteColor;
	}

	public Color getBlueColor() {
		return blueColor;
	}

	public Color getRedColor() {
		return redColor;
	}

	public Color getOrangeColor() {
		return orangeColor;
	}
	
	
}
