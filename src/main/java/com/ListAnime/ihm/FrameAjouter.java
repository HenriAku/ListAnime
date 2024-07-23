/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.ListAnime.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.ListAnime.Controleur;

public class FrameAjouter extends JFrame implements ActionListener
{
	private Controleur ctrl;

	private JTextField txtName;
	private JComboBox  cbType ;
	private JTextField txtNote;

	private JButton	   btnCancel;
	private JButton	   btnValide;

	private JLabel lblName;
	private JLabel lblType;
	private JLabel lblNote;

	public FrameAjouter(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Ajouter");
		this.setSize (400,300  );
		this.setLayout(null);

		/**************************/
		/* Creation des Composant */
		/**************************/

		String[] tabType = new String[] {"anime","manga","manhwa"};

		this.txtName = new JTextField();
		this.txtName.setBounds(150, 20, 200, 30);

		this.cbType  = new JComboBox<>(tabType);
		this.cbType.setBounds(150, 70, 200, 30);

		this.txtNote = new JTextField();
		this.txtNote.setBounds(150, 130, 200, 30);

		this.btnValide = new JButton("Valider");
		this.btnValide.setBounds(50, 220, 120, 30);

		this.btnCancel = new JButton("Cancel" );
		this.btnCancel.setBounds(200, 220, 120, 30);

		this.lblName = new JLabel("Name Anime : ");
		this.lblName.setBounds(20, 20, 200, 30);

		this.lblType = new JLabel("Type Anime : ");
		this.lblType.setBounds(20, 70, 200, 30);

		this.lblNote = new JLabel("Note Anime : ");
		this.lblNote.setBounds(20, 130, 200, 30);


		/**************************/
		/* Position des Composant */
		/**************************/

		this.add(this.lblName);
		this.add(this.txtName);

		this.add(this.lblType);
		this.add(this.cbType );

		this.add(this.lblNote);
		this.add(this.txtNote);

		this.add(this.btnValide);
		this.add(this.btnCancel);

		/****************************/
		/* Activation des Composant */
		/****************************/

		this.btnValide.addActionListener(this);
		this.btnCancel.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnValide) 
		{
			String name = this.txtName.getText();
			String type = (String) this.cbType.getSelectedItem();
			if (name.length() == 0)
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir le Champs Name", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else if (this.txtNote.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir le Champs Note", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else if (!this.ctrl.checkList(name, type)) 
			{
				JOptionPane.showMessageDialog(this, "Cette Oeuvre et deja dans votre list", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else if (this.ctrl.getSelectedItem().equals(type) || this.ctrl.getSelectedItem().equals("all")) 
			{
				Double note = Double.parseDouble(this.txtNote.getText());
				this.ctrl.addOeuvre(name, type, note);
				this.ctrl.majIHMInfo();
				this.ctrl.majIHM();
				this.ctrl.majTable();
			}
			else
			{
				Double note = Double.parseDouble(this.txtNote.getText());
				this.ctrl.addOeuvreSave(name, type, note);
			}
			this.ctrl.sauvegardOeuvres();
		}

		if (e.getSource() == this.btnCancel) 
			this.dispose();
	}
}
