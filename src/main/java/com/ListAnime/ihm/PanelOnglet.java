/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.ListAnime.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.ListAnime.Controleur;

public class PanelOnglet extends JMenuBar implements ActionListener
{
	private Controleur ctrl;

	private JMenuItem menuIAnime  ; 
	private JMenuItem menuIManga  ;  
	private JMenuItem menuIManhwa ;
	private JMenuItem menuIAll    ;

	public PanelOnglet(Controleur ctrl)
	{
		this.ctrl = ctrl;

		/**************************/
		/* Creation des Composant */
		/**************************/

		this.menuIAnime  = new JMenuItem("Anime" );
		this.menuIManga  = new JMenuItem("Manga" );
		this.menuIManhwa = new JMenuItem("Manhwa");
		this.menuIAll    = new JMenuItem("All"   );


		JMenu menuType = new JMenu("Changer Type");

		/**************************/
		/* Position des Composant */
		/**************************/

		menuType.add(this.menuIAnime );
		menuType.add(this.menuIManga );
		menuType.add(this.menuIManhwa);
		menuType.add(this.menuIAll   );


		this.add(menuType);

		/****************************/
		/* Activation des Composant */
		/****************************/

		this.menuIAnime .addActionListener(this);
		this.menuIManga .addActionListener(this);
		this.menuIManhwa.addActionListener(this);
		this.menuIAll	.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.menuIAnime) 
		{
			this.ctrl.resetLstOevre();
			this.ctrl.changeLstChoix(this.menuIAnime.getText().toLowerCase());
			this.ctrl.majTable  ();
			this.ctrl.majIHMInfo();
			this.ctrl.setSelectedItem(this.menuIAnime.getText().toLowerCase());
		}

		if (e.getSource() == this.menuIManga) 
		{
			this.ctrl.resetLstOevre();
			this.ctrl.changeLstChoix(this.menuIManga.getText().toLowerCase());
			this.ctrl.majTable();
			this.ctrl.majIHMInfo();
			this.ctrl.setSelectedItem(this.menuIManga.getText().toLowerCase());
		}

		if (e.getSource() == this.menuIManhwa) 
		{
			this.ctrl.resetLstOevre();
			this.ctrl.changeLstChoix(this.menuIManhwa.getText().toLowerCase());
			this.ctrl.majTable();
			this.ctrl.majIHMInfo();
			this.ctrl.setSelectedItem(this.menuIManhwa.getText().toLowerCase());
		}

		if (e.getSource() == this.menuIAll) 
		{
			this.ctrl.resetLstOevre();
			this.ctrl.majTable();
			this.ctrl.majIHMInfo();
			this.ctrl.setSelectedItem("all");
		}
	}
}
