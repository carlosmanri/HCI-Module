package gui.hiringPanel;

import javax.swing.JPanel;

import gui.MainWindow;
import gui.shoppingCart.ShoppingCart;
import logic.ApplicationException;
import logic.product.Product;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class ProductPanel extends JPanel {

	private static final long serialVersionUID = -2338333579473902759L;
	private Product product;
	private Hiring hiringPanel;
	private JLabel lblImage;
	private JLabel lblName;
	private JLabel lblPrice;
	private JScrollPane spDescription;
	private JTextArea taDescription;
	private JPanel pnAdd;
	private JSpinner spQuantity;
	private JButton btnAdd;
	private JLabel lblType;
	private Adapt adapt;


	/**
	 * Create the panel.
	 */
	public ProductPanel(Hiring hiringPanel, Product product) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		adapt = new Adapt();
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectPanel(hiringPanel, e);

			}
		});

		this.hiringPanel = hiringPanel;
		this.product = product;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 0, 0, 0, 0, 400, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.gridheight = 4;
		gbc_lblImage.insets = new Insets(0, 0, 5, 5);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 0;
		add(getLblImage(), gbc_lblImage);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 0;
		add(getLblName(), gbc_lblName);
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrice.gridx = 3;
		gbc_lblPrice.gridy = 0;
		add(getLblPrice(), gbc_lblPrice);
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.WEST;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 7;
		gbc_lblType.gridy = 0;
		add(getLblType(), gbc_lblType);
		GridBagConstraints gbc_spDescription = new GridBagConstraints();
		gbc_spDescription.gridheight = 3;
		gbc_spDescription.gridwidth = 5;
		gbc_spDescription.insets = new Insets(0, 0, 5, 5);
		gbc_spDescription.fill = GridBagConstraints.BOTH;
		gbc_spDescription.gridx = 2;
		gbc_spDescription.gridy = 1;
		add(getSpDescription(), gbc_spDescription);
		GridBagConstraints gbc_pnAdd = new GridBagConstraints();
		gbc_pnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_pnAdd.fill = GridBagConstraints.BOTH;
		gbc_pnAdd.gridx = 7;
		gbc_pnAdd.gridy = 3;
		add(getPnAdd(), gbc_pnAdd);

	}
	
	private void selectPanel(Hiring hiring, MouseEvent e) {
		int index = 0;
		// First, we set GRAY as background colour for every panel.
		for (int i=0;i<hiring.getPnProducts().getComponentCount();i++) {
			Color bgColor = new Color(240, 240 ,240 );
			hiring.getPnProducts().getComponent(i).setBackground(bgColor);
			 // Also the panel of the Visit button.
			 ((JPanel)hiring.getPnProducts().getComponent(i)).getComponent(5).setBackground(bgColor);
			 if(this.equals(hiring.getPnProducts().getComponent(i))) {
				index = i;
			 }
			}

		// We set the background of the selected panel to Orange
		this.setBackground(Color.orange);
		pnAdd.setBackground(Color.orange);
	}
	
	class Adapt extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent e) {
			adaptPictureLabel(lblImage, product.getImageFileName()); 
		}
	}
	
	private void adaptPictureLabel(JLabel label, String path){

		  Image imgOriginal = new ImageIcon(getClass().getResource("/gui/img/products/"+path)).getImage();
		 // Image imgScaled = imgOriginal.getScaledInstance((int)(label.getWidth()),(int)(label.getHeight()), Image.SCALE_FAST);
		  Image imgScaled = imgOriginal.getScaledInstance(100,100, Image.SCALE_FAST);

		  label.setIcon(new ImageIcon(imgScaled));
		}

	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setIcon(new ImageIcon(ProductPanel.class.getResource("/gui/img/icons/cart.png")));
			lblImage.setMinimumSize(new Dimension(0, 0));
			lblImage.setMaximumSize(new Dimension(0, 0));
			lblImage.addComponentListener(adapt);
			adaptPictureLabel(lblImage, product.getImageFileName());

		}
		return lblImage;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel(product.getName());
		}
		return lblName;
	}
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel(Float.toString(product.getUnitPrice()));
			if(product.getGroupPrice()==0) {
				lblPrice.setText(Float.toString(product.getUnitPrice())+"€");
			}else {
				lblPrice.setText(Float.toString(product.getGroupPrice())+"€");

			}
		}
		return lblPrice;
	}
	private JScrollPane getSpDescription() {
		if (spDescription == null) {
			spDescription = new JScrollPane();
			spDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			spDescription.setViewportView(getTaDescription());
		}
		return spDescription;
	}
	private JTextArea getTaDescription() {
		if (taDescription == null) {
			taDescription = new JTextArea();
			taDescription.setWrapStyleWord(true);
			taDescription.setMaximumSize(new Dimension(150, 2147483647));
			taDescription.setEditable(false);
			taDescription.setText(product.getDescription());

		}

		return taDescription;
	}
	private JPanel getPnAdd() {
		if (pnAdd == null) {
			pnAdd = new JPanel();
			pnAdd.add(getSpQuantity());
			pnAdd.add(getBtnAdd());

		}
		return pnAdd;
	}
	private JSpinner getSpQuantity() {
		if (spQuantity == null) {
			spQuantity = new JSpinner();
			spQuantity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		}
		if(product.getUnitPrice()==0) {
			spQuantity.setVisible(false);
		}
		return spQuantity;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton(hiringPanel.getMainWindow().getResourceBundle().getString("add"));
			btnAdd.setMnemonic(hiringPanel.getMainWindow().getResourceBundle().getString("mnc_add").toCharArray()[0]);
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					addToCart();				
					
					hiringPanel.setNextButtonEnabled(true);
					ShoppingCart sc = (ShoppingCart)hiringPanel.getMainWindow().getShoppingCart();
					sc.setNextButtonEnabled(true);
					sc.updateItemsLabel();

				}
			});
		}
		return btnAdd;
	}
	
	private void addToCart() {
		int quantity = (Integer) spQuantity.getValue();
		try {
			hiringPanel.getMainWindow().getPartyManager().getBill().addProduct(product, quantity);
			hiringPanel.updateTotalPrice();
			
			ShoppingCart sc = (ShoppingCart) hiringPanel.getMainWindow().getShoppingCart();
			sc.showProducts();

		} catch (ApplicationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error adding product to cart", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	private JLabel getLblType() {
		if (lblType == null) {
			lblType = new JLabel(product.getCategory());
		}
		return lblType;
	}

	public void localize() {
		btnAdd.setText(hiringPanel.getMainWindow().getResourceBundle().getString("add"));
		btnAdd.setMnemonic(hiringPanel.getMainWindow().getResourceBundle().getString("mnc_add").toCharArray()[0]);		
	}
}
