/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.ListAnime.ihm;

import com.ListAnime.Controleur;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;

public class PanelInfo extends JPanel
{
	private Controleur ctrl;

	private JLabel totalSerie;
	private JLabel totalvol  ;
	private JLabel moyNote 	 ;

	public PanelInfo(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new GridLayout(1,3,5,5));
		this.setPreferredSize(new Dimension(600, 30));

		/***************************/
		/* Création des Composants */
		/***************************/
		String s = "";
		if (this.ctrl.getLstOeuvres().size() == 0) 
			s = "Note moyenne : " + 0.00;
		else
			s = "Note moyenne : " + String.format("%-10.2f", this.ctrl.noteMoy());

		this.totalSerie = new JLabel ("Total de serie : "      + this.ctrl.totalSerie());
		this.totalvol   = new JLabel ("Total de d'episodes : " + this.ctrl.totalNbVol());
		this.moyNote    = new JLabel (s);

		/***************************/
		/* Position des Composants */
		/***************************/

		this.add(this.totalSerie);
		this.add(this.totalvol  );
		this.add(this.moyNote   );
	}

	public void majIHMInfo()
	{
		String s = "";
		if (this.ctrl.getLstOeuvres().size() == 0) 
			s = "Note moyenne : " + 0.00;
		else
			s = "Note moyenne : " + String.format("%-10.2f", this.ctrl.noteMoy());

		this.totalSerie.setText("Total de serie : "      + this.ctrl.totalSerie());
		this.totalvol  .setText("Total de d'episodes : " + this.ctrl.totalNbVol()); 
		this.moyNote   .setText(s);
		this.repaint();
	}
}
