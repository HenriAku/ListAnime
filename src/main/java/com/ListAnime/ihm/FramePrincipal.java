/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.ListAnime.ihm;

import com.ListAnime.Controleur;
import javax.swing.*;

public class FramePrincipal extends JFrame
{
	private Controleur ctrl;
	private ModelTable table;

	private PanelOnglet panelOnglet;
	private PanelBtn    panelBtn   ;
	private PanelInfo   panelInfo  ;
	private PanelList   panelList  ;

	public FramePrincipal(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("ListAnime");
		this.setSize(1000,1050);
		this.setLayout(null);

		/**************************/
		/* Creation des Composant */
		/**************************/

		this.table = new ModelTable(this.ctrl);

		this.panelOnglet  = new PanelOnglet(this.ctrl);

		JLabel image   = new JLabel(new ImageIcon("src/main/java/com/ListAnime/images/Fond1.jpg"));
		image.setBounds(50, 5, 900, 300);

		this.panelBtn  = new PanelBtn(this.ctrl);
		this.panelBtn.setBounds(200, 315, 600, 30);

		this.panelList = new PanelList(this, this.ctrl);
		this.panelList.setBounds(50, 355, 900, 600);

		this.panelInfo = new PanelInfo(this.ctrl);
		this.panelInfo.setBounds(250, 950, 600, 30);

		/**************************/
		/* Position des Composant */
		/**************************/

		this.setJMenuBar(this.panelOnglet);

		this.add(image         );
		this.add(this.panelBtn );
		this.add(this.panelList);
		this.add(this.panelInfo);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void majIHM     () {this.repaint		   		();}
	public void majIHMInfo () {this.panelInfo.majIHMInfo();}
	public void majTable   () {this.panelList.majTable	();}
	public void activModif () {this.panelBtn.activModif ();}


	public String[]   getTabEntetes () {return this.table.getTabEntetes(); }
	public Object[][] getTabDonnees () {return this.table.getTabDonnees(); } 

	public int    getRow     () {return this.panelList.getRow    ();}
	public int    getCol     () {return this.panelList.getCol    ();}
	public Double getVal     () {return this.panelList.getVal    ();}
	public String getTypeCol () {return this.panelList.getTypeCol();}
	public String getNameCol () {return this.panelList.getNameCol();}
}
