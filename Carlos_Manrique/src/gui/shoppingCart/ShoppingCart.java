package gui.shoppingCart;

import javax.swing.JPanel;

import gui.MainWindow;
import gui.hiringPanel.Hiring;
import gui.orderConfirmation.OrderConfirmation;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class ShoppingCart extends JPanel {

	private static final long serialVersionUID = 6531920321235666441L;
	MainWindow mainWindow;
	private JPanel pnNorth;
	private JLabel lblShoppingCart;
	private JLabel lblPeople;
	private JSpinner spPeople;
	private JLabel lblItems;
	private JLabel lblTotal;
	private JScrollPane spProducts;
	private JPanel pnProducts;
	private JPanel pnSouth;
	private JPanel pnCartButtons;
	private JPanel pnNavButtons;
	private JButton btnRemoveAll;
	private JButton btnUndo;
	private JButton btnContinueHiring;
	private JButton btnLogin;
	private JButton btnNext;
	private JPanel pnTableKeys;
	private JLabel lblRemove;
	private JLabel lblProductDetails;
	private JLabel lblQuantity;
	private JLabel lblPriceUnit;
	private JLabel lblTotalPrice;
	private JPanel pnBillInfo;
	private JPanel pnWrapper;
	private JPanel pnPeople;
	
	/**
	 * Create the panel.
	 */
	public ShoppingCart(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		setLayout(new BorderLayout(0, 0));
		add(getPnNorth(), BorderLayout.NORTH);
		add(getSpProducts(), BorderLayout.CENTER);
		add(getPnSouth(), BorderLayout.SOUTH);
		showProducts();
	}
	
	public void showProducts() {

		pnProducts.removeAll();
		pnProducts.repaint();
		if(!mainWindow.getPartyManager().getBill().getCart().isEmpty()) {
			mainWindow.getPartyManager().getBill().getCart().forEach(
					(product, quantity) -> pnProducts.add(new CartProductPanel(this, product, quantity)));	
		}

	}
	
	public MainWindow getMainWindow() {
		return mainWindow;
	}
	
	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setLayout(new GridLayout(0, 1, 0, 0));
			pnNorth.add(getPnWrapper());
			pnNorth.add(getPnTableKeys());
		}
		return pnNorth;
	}
	private JLabel getLblShoppingCart() {
		if (lblShoppingCart == null) {
			lblShoppingCart = new JLabel(mainWindow.getResourceBundle().getString("shoppingCart"));
		}
		return lblShoppingCart;
	}
	private JLabel getLblPeople() {
		if (lblPeople == null) {
			lblPeople = new JLabel(mainWindow.getResourceBundle().getString("peopleCart"));
		}
		return lblPeople;
	}
	private JSpinner getSpPeople() {
		if (spPeople == null) {
			spPeople =  new JSpinner();
			spPeople.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					mainWindow.getPartyManager().getBill().setNumberAttendants((Integer)spPeople.getValue());
					Hiring hiring = (Hiring)mainWindow.getHiring();
					hiring.updatePeople();
					hiring.updateTotalPrice();
					updateTotalPrice();
				}
			});
			int people = mainWindow.getPartyManager().getBill().getNumberAttendants();
			spPeople.setModel(new SpinnerNumberModel(new Integer(people), new Integer(1), null, new Integer(1)));
		}
		return spPeople;
	}
	private JLabel getLblItems() {
		if (lblItems == null) {
			lblItems = new JLabel(mainWindow.getPartyManager().getBill().getNumberOfItems()+" "+mainWindow.getResourceBundle().getString("products"));
		}
		return lblItems;
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel(mainWindow.getResourceBundle().getString("total")+": "+mainWindow.getPartyManager().getBill().getTotalPrice());
		}
		return lblTotal;
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
	private JPanel getPnSouth() {
		if (pnSouth == null) {
			pnSouth = new JPanel();
			pnSouth.setLayout(new GridLayout(0, 1, 0, 0));
			pnSouth.add(getPnCartButtons());
			pnSouth.add(getPnNavButtons());
		}
		return pnSouth;
	}
	private JPanel getPnCartButtons() {
		if (pnCartButtons == null) {
			pnCartButtons = new JPanel();
			pnCartButtons.add(getBtnRemoveAll());
			pnCartButtons.add(getBtnUndo());
		}
		return pnCartButtons;
	}
	private JPanel getPnNavButtons() {
		if (pnNavButtons == null) {
			pnNavButtons = new JPanel();
			pnNavButtons.add(getBtnContinueHiring());
			pnNavButtons.add(getBtnLogin());
			pnNavButtons.add(getBtnNext());
		}
		return pnNavButtons;
	}
	private JButton getBtnRemoveAll() {
		if (btnRemoveAll == null) {
			btnRemoveAll = new JButton(mainWindow.getResourceBundle().getString("removeAll"));
			btnRemoveAll.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainWindow.getPartyManager().getBill().removeAllProducts();
					
					pnProducts.removeAll();
					pnProducts.repaint();
					setNextButtonEnabled(false);
					updateItemsLabel();
					updateTotalPrice();
					
					Hiring hiring = (Hiring)mainWindow.getHiring();
					hiring.updateTotalPrice();
					hiring.setNextButtonEnabled(false);
				}
			});
		}
		return btnRemoveAll;
	}
	
	
	
	private JButton getBtnUndo() {
		if (btnUndo == null) {
			btnUndo = new JButton(mainWindow.getResourceBundle().getString("undo"));
		}
		return btnUndo;
	}
	private JButton getBtnContinueHiring() {
		if (btnContinueHiring == null) {
			btnContinueHiring = new JButton(mainWindow.getResourceBundle().getString("continueHiring"));
			btnContinueHiring.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainWindow.showHiringPanel();
				}
			});
		}
		return btnContinueHiring;
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
		if(mainWindow.getPartyManager().getBill().isLoged()) {
			btnLogin.setVisible(false);
		}
		return btnLogin;
	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton(mainWindow.getResourceBundle().getString("next"));
			if(getMainWindow().getPartyManager().getBill().getCart().isEmpty())
				btnNext.setEnabled(false);
				
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OrderConfirmation oc = (OrderConfirmation)mainWindow.getOrderConfirmation();
					oc.updatePriceLabel();
					mainWindow.showOrderConfirmation();
				}
			});
		}
		return btnNext;
	}
	private JPanel getPnTableKeys() {
		if (pnTableKeys == null) {
			pnTableKeys = new JPanel();
			pnTableKeys.setLayout(new GridLayout(0, 5, 0, 0));
			pnTableKeys.add(getLblRemove());
			pnTableKeys.add(getLblProductDetails());
			pnTableKeys.add(getLblQuantity());
			pnTableKeys.add(getLblPriceUnit());
			pnTableKeys.add(getLblTotalPrice());
		}
		return pnTableKeys;
	}
	private JLabel getLblRemove() {
		if (lblRemove == null) {
			lblRemove = new JLabel(mainWindow.getResourceBundle().getString("remove"));
		}
		return lblRemove;
	}
	private JLabel getLblProductDetails() {
		if (lblProductDetails == null) {
			lblProductDetails = new JLabel(mainWindow.getResourceBundle().getString("productDetails"));
		}
		return lblProductDetails;
	}
	private JLabel getLblQuantity() {
		if (lblQuantity == null) {
			lblQuantity = new JLabel(mainWindow.getResourceBundle().getString("quantity"));
		}
		return lblQuantity;
	}
	private JLabel getLblPriceUnit() {
		if (lblPriceUnit == null) {
			lblPriceUnit = new JLabel(mainWindow.getResourceBundle().getString("priceUnit"));
		}
		return lblPriceUnit;
	}
	private JLabel getLblTotalPrice() {
		if (lblTotalPrice == null) {
			lblTotalPrice = new JLabel(mainWindow.getResourceBundle().getString("total"));
		}
		return lblTotalPrice;
	}
	private JPanel getPnBillInfo() {
		if (pnBillInfo == null) {
			pnBillInfo = new JPanel();
			pnBillInfo.add(getLblItems());
			pnBillInfo.add(getLblTotal());
		}
		return pnBillInfo;
	}

	public void updateTotalPrice() {
		lblTotal.setText(mainWindow.getResourceBundle().getString("total")+": "+mainWindow.getPartyManager().getBill().getTotalPrice()+"€");
		
	}

	public void restart() {
		showProducts();
		updateTotalPrice();
		updateItemsLabel();
		btnNext.setEnabled(false);
	}

	public void localize() {
		lblShoppingCart.setText(mainWindow.getResourceBundle().getString("shoppingCart"));
		lblPeople.setText(mainWindow.getResourceBundle().getString("peopleCart"));
		lblItems.setText(mainWindow.getPartyManager().getBill().getNumberOfItems()+" "+mainWindow.getResourceBundle().getString("products"));
		lblTotal.setText(mainWindow.getResourceBundle().getString("total")+": "+mainWindow.getPartyManager().getBill().getTotalPrice());
		lblRemove.setText(mainWindow.getResourceBundle().getString("remove"));
		lblProductDetails.setText(mainWindow.getResourceBundle().getString("productDetails"));
		lblQuantity.setText(mainWindow.getResourceBundle().getString("quantity"));
		lblPriceUnit.setText(mainWindow.getResourceBundle().getString("priceUnit"));
		lblTotalPrice.setText(mainWindow.getResourceBundle().getString("total"));
		btnRemoveAll.setText(mainWindow.getResourceBundle().getString("removeAll"));
		btnUndo.setText(mainWindow.getResourceBundle().getString("undo"));
		btnContinueHiring.setText(mainWindow.getResourceBundle().getString("continueHiring"));
		btnNext.setText(mainWindow.getResourceBundle().getString("next"));
		btnLogin.setText(mainWindow.getResourceBundle().getString("login"));

		if(mainWindow.getPartyManager().getBill().isLoged()) btnLogin.setVisible(false);
		
		for(Component c : pnProducts.getComponents()) {
			if(c instanceof CartProductPanel) {
				CartProductPanel p = (CartProductPanel)c;
				p.localize();
			}
		}
		

	}
	public void updatePeople() {
		spPeople.setValue(mainWindow.getPartyManager().getBill().getNumberAttendants());
	}
	

	public void setNextButtonEnabled(boolean state) {
		btnNext.setEnabled(state);
	}
	
	private JPanel getPnWrapper() {
		if (pnWrapper == null) {
			pnWrapper = new JPanel();
			pnWrapper.setLayout(new GridLayout(0, 3, 0, 0));
			pnWrapper.add(getLblShoppingCart());
			pnWrapper.add(getPnPeople());
			pnWrapper.add(getPnBillInfo());
		}
		return pnWrapper;
	}

	public void updateItemsLabel() {
		lblItems.setText(mainWindow.getPartyManager().getBill().getNumberOfItems()+" "+mainWindow.getResourceBundle().getString("products"));
	}
	
	
	
	private JPanel getPnPeople() {
		if (pnPeople == null) {
			pnPeople = new JPanel();
			pnPeople.add(getLblPeople());
			pnPeople.add(getSpPeople());
		}
		return pnPeople;
	}
}
