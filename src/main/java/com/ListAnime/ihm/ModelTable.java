/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.ListAnime.ihm;

import com.ListAnime.Controleur;
import com.ListAnime.metier.Oeuvre;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import java.net.URL;
import java.awt.Image;

public class ModelTable extends AbstractTableModel
{
	private Controleur ctrl     ;
	private String[]   tabEntete;
	private Object[][] taObjects;
	
	public ModelTable(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.tabEntete = new String[] {"Image", "Name", "Type", "NbVol", "Note"};
		this.taObjects = new Object[this.ctrl.totalSerie()][5];

		List<Oeuvre> lstOeuvres = this.ctrl.getLstOeuvres();

		for (int i = 0; i<lstOeuvres.size(); i++) 
		{
			try {
                URL url = new URL(lstOeuvres.get(i).getUrl());
                Image image = ImageIO.read(url);
                this.taObjects[i][0] = new ImageIcon(image);
            } catch (IOException e) {
                e.printStackTrace();
                this.taObjects[i][0] = new ImageIcon(); // Image par défaut ou null en cas d'échec
            }
			this.taObjects[i][1] = lstOeuvres.get(i).getName ();
			this.taObjects[i][2] = lstOeuvres.get(i).getType ();
			this.taObjects[i][3] = lstOeuvres.get(i).getNbVol();
			this.taObjects[i][4] = lstOeuvres.get(i).getNote ();
		}
	}

	public String[]   getTabEntetes()                  {return this.tabEntete;                  } //Retourne le tableau String des nom des Colonne
	public Object[][] getTabDonnees ()                 {return this.taObjects;                  } //Retourne un tableau de String (info des villes)
	public int        getColumnCount()                 {return this.taObjects.length;           } //Retourne le nombre de colonne
	public int        getRowCount   ()                 {return this.taObjects.length;           } //Retourne le nombre de ligne
	public String     getColumnName (int col)          {return this.tabEntete[col];             } //Retourne le non de la colonne
	public Object     getValueAt    (int row, int col) {return this.taObjects[row][col];        } //Retourne l'objet dans le tableau a ligne et collone passer en paramettre
	public Class      getColumnClass(int c)            {return getValueAt(0, c).getClass(); } 
}
