/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.ListAnime.ihm;

import com.ListAnime.Controleur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelBtn extends JPanel implements ActionListener 
{
	private Controleur ctrl;

	private JTextField txtResearch;

	private JButton btnResearch;
	private JButton btnAdd;
	private JButton btnDel;
	private JButton btnMod;

	public PanelBtn(Controleur ctrl) 
	{
		this.ctrl = ctrl;

		this.setLayout(new GridLayout(1,6,5,5));
		this.setPreferredSize(new Dimension(600, 30));

		/***************************/
		/* Création des Composants */
		/***************************/

		this.txtResearch = new JTextField();
		this.txtResearch.setMaximumSize(new Dimension(400, 30)); // Pour BoxLayout

		this.btnResearch = new JButton(new ImageIcon("src/main/java/com/ListAnime/images/Search.png"));
		this.btnResearch.setMaximumSize(new Dimension(30,30));
		this.btnResearch.setBorderPainted(false);
		this.btnResearch.setContentAreaFilled(true);
		this.btnResearch.setBackground(Color.WHITE);

		this.btnAdd = new JButton(new ImageIcon("src/main/java/com/ListAnime/images/Ajouter.jpg"));
		this.btnAdd.setMaximumSize(new Dimension(30,30));
		this.btnAdd.setBorderPainted(false);
		this.btnAdd.setContentAreaFilled(true);
		this.btnAdd.setBackground(Color.WHITE);

		this.btnDel = new JButton(new ImageIcon("src/main/java/com/ListAnime/images/Delete.jpg"));
		this.btnDel.setMaximumSize(new Dimension(30,30));
		this.btnDel.setBorderPainted(false);
		this.btnDel.setContentAreaFilled(true);
		this.btnDel.setBackground(Color.WHITE);
		this.btnDel.setEnabled(false);

		this.btnMod = new JButton(new ImageIcon("src/main/java/com/ListAnime/images/Modifier.png"));
		this.btnMod.setMaximumSize(new Dimension(30,30));
		this.btnMod.setBorderPainted(false);
		this.btnMod.setContentAreaFilled(true);
		this.btnMod.setBackground(Color.WHITE);
		this.btnMod.setEnabled(false);

		/***************************/
		/* Position des Composants */
		/***************************/

		this.add(new JLabel("Recherche :"));
		this.add(this.txtResearch);

		this.add(this.btnResearch);
		this.add(this.btnAdd);
		this.add(this.btnDel);
		this.add(this.btnMod);

		/*****************************/
		/* Activation des Composants */
		/*****************************/

		this.btnResearch.addActionListener(this);
		this.btnAdd     .addActionListener(this);
		this.btnDel     .addActionListener(this);
		this.btnMod     .addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnResearch) 
		{
			this.ctrl.research(this.txtResearch.getText());
			this.ctrl.majIHMInfo();
			this.ctrl.majIHM();
			this.ctrl.majTable();
		}

		if (e.getSource() == this.btnAdd) 
		{
			new FrameAjouter(this.ctrl);
		}

		if (e.getSource() == this.btnDel) 
		{
			this.ctrl.supprimer(this.ctrl.getNameCol(), this.ctrl.getTypeCol());
			this.ctrl.sauvegardOeuvres();
			this.ctrl.majIHMInfo();
			this.ctrl.majTable();
			this.ctrl.majIHM();		
		}

		if (e.getSource() == this.btnMod) 
		{
			this.ctrl.modifier(this.ctrl.getRow(), this.ctrl.getVal());
			this.ctrl.sauvegardOeuvres();
			this.ctrl.majIHMInfo();
			this.ctrl.majIHM();
		}
	}

	public void activModif() 
	{
		this.btnDel.setEnabled(true);
		this.btnMod.setEnabled(true);
	}

}
