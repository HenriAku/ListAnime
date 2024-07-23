/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.ListAnime.ihm;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.ListAnime.Controleur;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class PanelList extends JPanel
{
	private FramePrincipal 	  frame;
	private Controleur		  ctrl ;

	private JTable 			  tblList;
	private JScrollPane 	  spGrilleDonnees;
	private DefaultTableModel dfTable;

	private int col;
    private int row;

	public PanelList( FramePrincipal frame, Controleur ctrl)
	{
		this.frame = frame;
		this.ctrl  = ctrl ;

		this.col = 0;
		this.row = 0;

		this.setLayout(new BorderLayout());

        this.dfTable = new DefaultTableModel(this.frame.getTabDonnees(), this.frame.getTabEntetes()) {
            public boolean isCellEditable(int row, int column) {return column == 4;}
        };		

		this.tblList         = new JTable	   (dfTable);
		this.spGrilleDonnees = new JScrollPane (this.tblList);

		this.tblList.setFillsViewportHeight(true);
		this.tblList.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
		this.tblList.setRowHeight(50);

		this.add(spGrilleDonnees, BorderLayout.CENTER);

		this.tblList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
				row = PanelList.this.tblList.rowAtPoint(e.getPoint());
				col = PanelList.this.tblList.columnAtPoint(e.getPoint());
				ctrl.activModif();
            }
        });
	}

	public int    getRow     () {return this.row;}
	public int    getCol     () {return this.col;}
	public Double getVal     () {return Double.parseDouble((String) this.tblList.getValueAt(this.row, this.col)) ;}
	public String getTypeCol () {return (String) this.tblList.getValueAt(this.row, 2 )      					 ;}
	public String getNameCol () {return (String) this.tblList.getValueAt(this.row, 1 )      					 ;}


	public void  majTable () 
	{ 
		ModelTable mt = new ModelTable(this.ctrl);
		this.dfTable  = new DefaultTableModel (mt.getTabDonnees(), mt.getTabEntetes());
		this.tblList.setModel(dfTable);
		this.tblList.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
		this.tblList.setRowHeight(50);	
	}

	class ImageRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (value instanceof ImageIcon) {
				ImageIcon originalIcon = (ImageIcon) value;
				ImageIcon resizedIcon = resizeImageIcon(originalIcon, 50, 50);
				setIcon(resizedIcon);
				setText(""); // Supprime le texte pour afficher uniquement l'image
			} else {
				setIcon(null);
				setText((value == null) ? "" : value.toString());
			}
			return this;
		}

		private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
			// Obtenir l'image originale à partir de l'ImageIcon
			java.awt.Image img = icon.getImage();

			// Créer une image redimensionnée
			java.awt.Image resizedImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

			// Créer un BufferedImage pour une meilleure manipulation
			BufferedImage bufferedResizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = bufferedResizedImg.createGraphics();
			g2d.drawImage(resizedImg, 0, 0, width, height, null);
			g2d.dispose();

			// Retourner l'image redimensionnée sous forme d'ImageIcon
			return new ImageIcon(bufferedResizedImg);
		}
	}
}