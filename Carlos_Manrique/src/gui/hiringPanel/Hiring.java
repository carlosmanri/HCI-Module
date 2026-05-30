package gui.hiringPanel;

import javax.swing.JPanel;

import gui.MainWindow;
import gui.orderConfirmation.OrderConfirmation;
import logic.ApplicationException;
import logic.product.Product;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.FlowLayout;
import javax.swing.Box;

public class Hiring extends JPanel {

	private static final long serialVersionUID = -3789888974782726485L;
	private MainWindow mainWindow;
	private JPanel pnNorth;
	private JPanel pnFilter;
	private JLabel lblOrganizing;
	private JLabel lblPrice;
	private JLabel lblCart;
	private JLabel lblType;
	private JCheckBox chckbxDrinks;
	private JCheckBox chckbxFood;
	private JCheckBox chckbxDecoration;
	private JCheckBox chckbxSpaces;
	private JCheckBox chckbxOthers;
	private JScrollPane spProducts;
	private JPanel pnProducts;
	private JPanel pnSouth;
	private JButton btnLogin;
	private JButton btnCancel;
	private JButton btnContinue;
	private JPanel pnNavButtons;
	private JPanel pnNorthNorth;
	private Component horizontalStrut;
	private Component horizontalStrut_1;

	/**
	 * Create the panel.
	 */
	public Hiring(MainWindow mainWindow) {

		this.mainWindow = mainWindow;
		setLayout(new BorderLayout(0, 0));
		add(getPnNorth(), BorderLayout.NORTH);
		add(getSpProducts(), BorderLayout.CENTER);
		add(getPnSouth(), BorderLayout.SOUTH);
		updatePeople();
		updateTotalPrice();
		showProducts();
		
	}
	private boolean isCartEmpty() {
		return getMainWindow().getPartyManager().getBill().getCart().isEmpty();
	}

	private void showProducts() {

		pnProducts.removeAll();
		addProductPanels(chckbxDrinks.isSelected(), chckbxFood.isSelected(), chckbxDecoration.isSelected(), chckbxSpaces.isSelected(), chckbxOthers.isSelected());
	}
	
	public void updatePeople() {
		int people = mainWindow.getPartyManager().getBill().getNumberAttendants();
		lblOrganizing.setText(mainWindow.getResourceBundle().getString("organizingFor") +" "+people +" "+ mainWindow.getResourceBundle().getString("people"));
	}
	

	
	public void updateTotalPrice() {
		lblPrice.setText(Float.toString(mainWindow.getPartyManager().getBill().getTotalPrice())+" €");

	}
	

	
	private void addProductPanels(boolean drinks, boolean food, boolean decoration, boolean spaces, boolean others) {

		for (Product p : mainWindow.getPartyManager().getProducts()) {
			if(p.getCategory().equals(Product.DRINK) && drinks) {
				pnProducts.add(new ProductPanel(this, p));
			}
			if(p.getCategory().equals(Product.FOOD) && food) {
				pnProducts.add(new ProductPanel(this, p));
			}
			if(p.getCategory().equals(Product.DECORATION) && decoration) {
				pnProducts.add(new ProductPanel(this, p));
			}
			if(p.getCategory().equals(Product.PLACE) && spaces) {
				pnProducts.add(new ProductPanel(this, p));
			}
			if(p.getCategory().equals(Product.OTHERS) && others) {
				pnProducts.add(new ProductPanel(this, p));
			}
			
			pnProducts.validate();
			spProducts.getViewport().validate();

		}
	}
	

	public void localize() {
		btnCancel.setText(mainWindow.getResourceBundle().getString("cancel"));;
		btnContinue.setText(mainWindow.getResourceBundle().getString("next"));
		btnLogin.setText(mainWindow.getResourceBundle().getString("login"));
		btnLogin.setMnemonic(mainWindow.getResourceBundle().getString("mnc_login").toCharArray()[0]);
		
		chckbxDecoration.setText(mainWindow.getResourceBundle().getString("decoration"));
		chckbxDrinks.setText(mainWindow.getResourceBundle().getString("drinks"));
		chckbxFood.setText(mainWindow.getResourceBundle().getString("food"));
		chckbxOthers.setText(mainWindow.getResourceBundle().getString("others"));
		chckbxSpaces.setText(mainWindow.getResourceBundle().getString("spaces"));
		chckbxDecoration.setMnemonic(mainWindow.getResourceBundle().getString("mnc_decoration").toCharArray()[0]);
		chckbxDrinks.setMnemonic(mainWindow.getResourceBundle().getString("mnc_drinks").toCharArray()[0]);
		chckbxFood.setMnemonic(mainWindow.getResourceBundle().getString("mnc_food").toCharArray()[0]);
		chckbxOthers.setMnemonic(mainWindow.getResourceBundle().getString("mnc_others").toCharArray()[0]);
		chckbxSpaces.setMnemonic(mainWindow.getResourceBundle().getString("mnc_spaces").toCharArray()[0]);

		lblOrganizing.setText(mainWindow.getResourceBundle().getString("organizingFor") +" "+mainWindow.getPartyManager().getBill().getNumberAttendants() +" "+ mainWindow.getResourceBundle().getString("people"));
		
		pnFilter.setBorder(new TitledBorder(null, mainWindow.getResourceBundle().getString("filterBy"), TitledBorder.LEADING, TitledBorder.TOP, null, null));

		lblType.setText(mainWindow.getResourceBundle().getString("selectType"));
		
		lblOrganizing.setText(mainWindow.getResourceBundle().getString("organizingFor") +" "+mainWindow.getPartyManager().getBill().getNumberAttendants() +" "+ mainWindow.getResourceBundle().getString("people"));
		
		lblCart.setToolTipText(mainWindow.getResourceBundle().getString("shoppingCart"));

		for(Component c : pnProducts.getComponents()) {
			if(c instanceof ProductPanel ) {
				ProductPanel p = (ProductPanel)c;
				p.localize();
			}
		}
		
	}
	
	
	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setLayout(new GridLayout(2, 0, 0, 0));
			pnNorth.add(getPnNorthNorth());
			pnNorth.add(getPnFilter());
		}
		return pnNorth;
	}
	private JPanel getPnFilter() {
		if (pnFilter == null) {
			pnFilter = new JPanel();
			pnFilter.setBorder(new TitledBorder(null, mainWindow.getResourceBundle().getString("filterBy"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnFilter.add(getLblType());
			pnFilter.add(getChckbxDrinks());
			pnFilter.add(getChckbxFood());
			pnFilter.add(getChckbxDecoration());
			pnFilter.add(getChckbxSpaces());
			pnFilter.add(getChckbxOthers());
		}
		return pnFilter;
	}
	private JLabel getLblOrganizing() {
		if (lblOrganizing == null) {
			lblOrganizing = new JLabel(mainWindow.getResourceBundle().getString("organizingFor") +" "+mainWindow.getPartyManager().getBill().getNumberAttendants() +" "+ mainWindow.getResourceBundle().getString("people"));

		}

		return lblOrganizing;
	}
	private JLabel getLblPrice() {
		if (lblPrice == null) {

			lblPrice = new JLabel(Float.toString(mainWindow.getPartyManager().getBill().getTotalPrice())+" €");
		}
		return lblPrice;
	}
	private JLabel getLblCart() {
		if (lblCart == null) {
			lblCart = new JLabel("");
			lblCart.setToolTipText(mainWindow.getResourceBundle().getString("shoppingCart"));
			lblCart.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					mainWindow.showShoppingCart();
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
					lblCart.setIcon(new ImageIcon(Hiring.class.getResource("/gui/img/icons/cartHover.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblCart.setIcon(new ImageIcon(Hiring.class.getResource("/gui/img/icons/cart.png")));
				}
				
			});
			lblCart.setIcon(new ImageIcon(Hiring.class.getResource("/gui/img/icons/cart.png")));

		}
		return lblCart;
	}
	private JLabel getLblType() {
		if (lblType == null) {
			lblType = new JLabel(mainWindow.getResourceBundle().getString("selectType") );
		}
		return lblType;
	}
	private JCheckBox getChckbxDrinks() {
		if (chckbxDrinks == null) {
			chckbxDrinks = new JCheckBox(mainWindow.getResourceBundle().getString("drinks"));
			chckbxDrinks.setMnemonic(mainWindow.getResourceBundle().getString("mnc_drinks").toCharArray()[0]);
			chckbxDrinks.setSelected(true);
			chckbxDrinks.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showProducts();
				}
			});
		}
		return chckbxDrinks;
	}
	private JCheckBox getChckbxFood() {
		if (chckbxFood == null) {
			chckbxFood = new JCheckBox(mainWindow.getResourceBundle().getString("food"));
			chckbxFood.setMnemonic(mainWindow.getResourceBundle().getString("mnc_food").toCharArray()[0]);
			chckbxFood.setSelected(true);

			chckbxFood.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showProducts();

				}
			});


		}
		return chckbxFood;
	}
	private JCheckBox getChckbxDecoration() {
		if (chckbxDecoration == null) {
			chckbxDecoration = new JCheckBox(mainWindow.getResourceBundle().getString("decoration"));
			chckbxDecoration.setMnemonic(mainWindow.getResourceBundle().getString("mnc_decoration").toCharArray()[0]);
			chckbxDecoration.setSelected(true);

			chckbxDecoration.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showProducts();

				}
			});
		}
		return chckbxDecoration;
	}
	private JCheckBox getChckbxSpaces() {
		if (chckbxSpaces == null) {
			chckbxSpaces = new JCheckBox(mainWindow.getResourceBundle().getString("spaces"));
			chckbxSpaces.setMnemonic(mainWindow.getResourceBundle().getString("mnc_spaces").toCharArray()[0]);
			chckbxSpaces.setSelected(true);

			chckbxSpaces.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showProducts();

				}
			});
		}
		return chckbxSpaces;
	}
	private JCheckBox getChckbxOthers() {
		if (chckbxOthers == null) {
			chckbxOthers = new JCheckBox(mainWindow.getResourceBundle().getString("others"));
			chckbxOthers.setMnemonic(mainWindow.getResourceBundle().getString("mnc_others").toCharArray()[0]);
			chckbxOthers.setSelected(true);

			chckbxOthers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showProducts();

				}
			});
		}
		return chckbxOthers;
	}
	private JScrollPane getSpProducts() {
		if (spProducts == null) {
			spProducts = new JScrollPane();
			spProducts.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			spProducts.setViewportView(getPnProducts());
			spProducts.getVerticalScrollBar().setUnitIncrement(10); //Speed up the bar
		}
		return spProducts;
	}
	public JPanel getPnProducts() {
		if (pnProducts == null) {
			pnProducts = new JPanel();
			pnProducts.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnProducts;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}
	private JPanel getPnSouth() {
		if (pnSouth == null) {
			pnSouth = new JPanel();
			pnSouth.setLayout(new BorderLayout(0, 0));
			pnSouth.add(getBtnLogin(), BorderLayout.WEST);
			pnSouth.add(getPnNavButtons(), BorderLayout.EAST);
		}
		return pnSouth;
	}
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton(mainWindow.getResourceBundle().getString("login"));
			btnLogin.setMnemonic(mainWindow.getResourceBundle().getString("mnc_login").toCharArray()[0]);
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mainWindow.showLogin();
				}
			});
		}
		if(mainWindow.getPartyManager().getBill().isLoged()) {
			btnLogin.setVisible(false);
		}
		return btnLogin;
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
	private JButton getBtnContinue() {
		if (btnContinue == null) {
			btnContinue = new JButton(mainWindow.getResourceBundle().getString("continue"));
			btnContinue.setEnabled(false);
			btnContinue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OrderConfirmation oc = (OrderConfirmation)mainWindow.getOrderConfirmation();
					oc.updatePriceLabel();
					mainWindow.showOrderConfirmation();
				}
			});
		}
		return btnContinue;
	}

	public void restart() {
		updateTotalPrice();
		updatePeople();
		chckbxDecoration.setSelected(true);
		chckbxDrinks.setSelected(true);
		chckbxFood.setSelected(true);
		chckbxOthers.setSelected(true);
		chckbxSpaces.setSelected(true);
		showProducts();
		btnContinue.setEnabled(false);
	}
	private JPanel getPnNavButtons() {
		if (pnNavButtons == null) {
			pnNavButtons = new JPanel();
			pnNavButtons.add(getBtnContinue());
			pnNavButtons.add(getBtnCancel());
		}
		return pnNavButtons;
	}
	
	
	public void setNextButtonEnabled(boolean state) {
		btnContinue.setEnabled(state);
	}

	private JPanel getPnNorthNorth() {
		if (pnNorthNorth == null) {
			pnNorthNorth = new JPanel();
			pnNorthNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnNorthNorth.add(getLblOrganizing());
			pnNorthNorth.add(getHorizontalStrut());
			pnNorthNorth.add(getLblPrice());
			pnNorthNorth.add(getHorizontalStrut_1());
			pnNorthNorth.add(getLblCart());
		}
		return pnNorthNorth;
	}
	private Component getHorizontalStrut() {
		if (horizontalStrut == null) {
			horizontalStrut = Box.createHorizontalStrut(50);
		}
		return horizontalStrut;
	}
	private Component getHorizontalStrut_1() {
		if (horizontalStrut_1 == null) {
			horizontalStrut_1 = Box.createHorizontalStrut(50);
		}
		return horizontalStrut_1;
	}
}
