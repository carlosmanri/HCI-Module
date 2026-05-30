package gui.shoppingCart;

import javax.swing.JPanel;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;

import gui.hiringPanel.Hiring;

import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;

public class CartProductPanel extends JPanel {
	

	private static final long serialVersionUID = 8707261133930971788L;
	private Product product;
	private int quantity;
	private ShoppingCart shoppingPanel;
	private JLabel lblImage;
	private JLabel lblName;
	private JLabel lblPrice;
	private JScrollPane spDescription;
	private JTextArea taDescription;
	private JLabel lblType;
	private Adapt adapt;
	private JButton btnRemove;
	private JSpinner spQuantity;
	private JLabel lblPriceUnit;
	private JLabel lblPriceTotal;


	/**
	 * Create the panel.
	 */
	public CartProductPanel(ShoppingCart shoppingPanel, Product product, int quantity) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		adapt = new Adapt();
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectPanel(shoppingPanel, e);

			}
		});

		this.shoppingPanel = shoppingPanel;
		this.product = product;
		this.quantity = quantity;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 180, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.gridheight = 4;
		gbc_lblImage.insets = new Insets(0, 0, 5, 5);
		gbc_lblImage.gridx = 1;
		gbc_lblImage.gridy = 0;
		add(getLblImage(), gbc_lblImage);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 0;
		add(getLblName(), gbc_lblName);
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 3;
		gbc_lblPrice.gridy = 0;
		add(getLblPrice(), gbc_lblPrice);
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.WEST;
		gbc_lblType.insets = new Insets(0, 0, 5, 0);
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
		GridBagConstraints gbc_spQuantity = new GridBagConstraints();
		gbc_spQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_spQuantity.gridx = 7;
		gbc_spQuantity.gridy = 1;
		add(getSpQuantity(), gbc_spQuantity);
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.gridheight = 2;
		gbc_btnRemove.anchor = GridBagConstraints.NORTH;
		gbc_btnRemove.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemove.gridx = 0;
		gbc_btnRemove.gridy = 2;
		add(getBtnRemove(), gbc_btnRemove);
		GridBagConstraints gbc_lblPriceUnit = new GridBagConstraints();
		gbc_lblPriceUnit.insets = new Insets(0, 0, 5, 0);
		gbc_lblPriceUnit.gridx = 8;
		gbc_lblPriceUnit.gridy = 3;
		add(getLblPriceUnit(), gbc_lblPriceUnit);
		GridBagConstraints gbc_lblPriceTotal = new GridBagConstraints();
		gbc_lblPriceTotal.insets = new Insets(0, 0, 5, 0);
		gbc_lblPriceTotal.gridx = 10;
		gbc_lblPriceTotal.gridy = 3;
		add(getLblPriceTotal(), gbc_lblPriceTotal);
		updateUnitPrice();
	}
	
	private void updateUnitPrice(){
		if(product.getGroupPrice()==0) {
			lblPrice.setText(Float.toString(product.getUnitPrice())+"€");
		}else {
			lblPrice.setText(Float.toString(product.getGroupPrice())+"€");

		}
	}
	
	private void selectPanel(ShoppingCart shoppingPanel, MouseEvent e) {
		int index = 0;
		Color bgColor = new Color(240, 240 ,240 );

		// First, we set GRAY as background colour for every panel.
		for (int i=0;i<shoppingPanel.getPnProducts().getComponentCount();i++) {
			shoppingPanel.getPnProducts().getComponent(i).setBackground(bgColor);
			 // Also the panel of the Visit button.
			 ((JPanel)shoppingPanel.getPnProducts().getComponent(i)).getComponent(6).setBackground(bgColor);
			 if(this.equals(shoppingPanel.getPnProducts().getComponent(i))) {
				index = i;
			 }
			}

		// We set the background of the selected panel to Orange
		this.setBackground(Color.orange);
	}
	
	class Adapt extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent e) {
			adaptPictureLabel(lblImage, product.getImageFileName()); 
		}
	}
	
	private void adaptPictureLabel(JLabel label, String path){
		  Image imgOriginal = new ImageIcon(getClass().getResource("/gui/img/products/"+path)).getImage();
		  Image imgScaled = imgOriginal.getScaledInstance(100,100, Image.SCALE_FAST);
		  label.setIcon(new ImageIcon(imgScaled));
		}

	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel(" ");
			lblImage.addComponentListener(adapt);

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
			lblPrice = new JLabel(Float.toString(product.getUnitPrice())+"€");

		}
		return lblPrice;
	}
	private JScrollPane getSpDescription() {
		if (spDescription == null) {
			spDescription =  new JScrollPane();
			spDescription.setViewportView(getTaDescription());
		}
		return spDescription;
	}
	private JTextArea getTaDescription() {
		if (taDescription == null) {
			taDescription = new JTextArea();
		}
		taDescription.setText(product.getDescription());
		return taDescription;
	}
	private JLabel getLblType() {
		if (lblType == null) {
			lblType = new JLabel("New label");
		}
		lblType.setText(product.getCategory().toString());
		return lblType;
	}
	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton(shoppingPanel.getMainWindow().getResourceBundle().getString("remove"));
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removeProduct();
				}
			});
		}
		return btnRemove;
	}
	
	
	private void removeProduct() {
		try {
			shoppingPanel.getMainWindow().getPartyManager().getBill().removeProduct(product);
			shoppingPanel.updateTotalPrice();
			shoppingPanel.updateItemsLabel();
			shoppingPanel.showProducts();
			
			Hiring hiring = (Hiring)shoppingPanel.getMainWindow().getHiring();

			if(shoppingPanel.getMainWindow().getPartyManager().getBill().getCart().isEmpty()) {
				shoppingPanel.setNextButtonEnabled(false);
				hiring.setNextButtonEnabled(false);
			}
			
			hiring.updateTotalPrice();
			
		} catch (ApplicationException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Error removing a product", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private JSpinner getSpQuantity() {
		if (spQuantity == null) {
			spQuantity = new JSpinner();
			spQuantity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spQuantity.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					changeQuantity();
					shoppingPanel.updateItemsLabel();
				}
			});
			if(product.getUnitPrice()==0) {
				spQuantity.setVisible(false);
			}
			spQuantity.setValue(quantity);

		}
		return spQuantity;
	}
	
	private void changeQuantity() {
		try {
			shoppingPanel.getMainWindow().getPartyManager().getBill().changeItemQuantity(product, (Integer)spQuantity.getValue());
			shoppingPanel.updateTotalPrice();
			Hiring h = (Hiring)shoppingPanel.getMainWindow().getHiring();
			h.updateTotalPrice();

		} catch (ApplicationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error changing the quantity", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	private JLabel getLblPriceUnit() {
		if (lblPriceUnit == null) {
			lblPriceUnit = new JLabel();
		}
		if(product.getUnitPrice()==0) {
			lblPriceUnit.setText(product.getGroupPrice()+"€");
		}
		else {
			lblPriceUnit.setText(product.getUnitPrice()+"€");

		}
		return lblPriceUnit;
	}
	private JLabel getLblPriceTotal() {
		if (lblPriceTotal == null) {
			lblPriceTotal = new JLabel();
			if(product.getUnitPrice()==0) {
				lblPriceUnit.setText(product.getGroupPrice()*(shoppingPanel.getMainWindow().getPartyManager().getBill().getNumberAttendants())+"€");
			}
			else {
				lblPriceUnit.setText(product.getUnitPrice()+"€");

			}
		}
		return lblPriceTotal;
	}

	public void localize() {
		btnRemove.setText(shoppingPanel.getMainWindow().getResourceBundle().getString("remove"));
		
	}
}
