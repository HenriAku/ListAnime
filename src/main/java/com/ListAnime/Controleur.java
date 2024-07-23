/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/

package com.ListAnime;

import java.util.List;
import com.ListAnime.ihm.*;
import com.ListAnime.metier.*;

public class Controleur 
{
	private Metier 		   metier;
	private FramePrincipal frame ;
	private String		   onglet;

	public Controleur()
	{
		this.onglet = "all";
		this.metier = new Metier		(this);
		this.frame  = new FramePrincipal(this);
		this.metier.saveLstOevre();
	}


	//Methodes pour Sauvegarder L'onglet Selectionner
	public String getSelectedItem()				 {return this.onglet            ;}
	public void   setSelectedItem(String onglet) {this.onglet = onglet          ;}
	public int    getRow         ()              {return this.frame.getRow    ();}
	public int    getCol         ()              {return this.frame.getCol    ();}
	public Double getVal 		 () 			 {return this.frame.getVal    ();}
	public String getTypeCol     ()              {return this.frame.getTypeCol();}
	public String getNameCol     ()              {return this.frame.getNameCol();}


	//Methodes Pour les List d'Oeuvre
	public void 		addOeuvreSave     (String name , String type, Double note) {this.metier.addOeuvreSave(name, type, note) ;}	 //Ajoute une Oeuvre a la List Actuelle
	public void         addOeuvre         (String name , String type, Double note) {this.metier.addOeuvre(name, type, note)     ;}	 //Ajoute une Oeuvre a la List Sauvegarder
	public void         changeLstChoix    (String type)							   {this.metier.changeLstChoix(type)            ;}	 //Garde les Oeuvre que du type en paramettre
	public void         resetLstOevre     ()									   {this.metier.resetLstOevre()			        ;}	 //Reset la List Actuelle grace a la List Sauvegarder
	public List<Oeuvre> getLstOeuvres     () 									   {return this.metier.getLstOeuvres()	        ;}	 //Renvoie la List Actuelle
	public List<Oeuvre> getLstOeuvresSave () 									   {return this.metier.getLstOeuvresSave()	    ;}	 //Renvoie la List Sauvegarder
	public void 		modifier		  (int ind, Double note) 				   {this.metier.modifier(ind, note)			    ;}   //Modifier la note de l'Oeuvre passer en parramettre
	public void 		supprimer		  (String name , String type) 			   {this.metier.supprimer(name, type)		 	;}  
	public void         sauvegardOeuvres  ()  									   {this.metier.sauvegardOeuvres() 	            ;}
	public Boolean      checkList  		  (String name , String type)  			   {return this.metier.checkList(name, type)	;}
	public void     	research  		  (String name )  			   			   {this.metier.research(name)					;}



	//Methodes pour Actualiser IHM
	public void majIHM  	() {this.frame.majIHM  	 ();}		//Actualise la frame Principale
	public void majIHMInfo  () {this.frame.majIHMInfo();}		//Actualise la Panel Info
	public void majTable	() {this.frame.majTable	 ();}		//Actualise le tableau des Oeuvres
	public void activModif	() {this.frame.activModif();}		//Actualise le tableau des Oeuvres


	//Renvoie un Double la note Moyenne de tout les Oeuvre
	public Double noteMoy()
	{
		if (this.metier != null) 
			return this.metier.noteMoy();
		else 
			return 0.0; 
	}


	//Renvoie un entier total des serie dans la List
	public int totalSerie() 
	{
        if (this.metier != null) 
            return this.metier.getNbSerie();
    	else 
            return 0;
    }


	//Renvoie un entier total des nombre de Volume de tout les Oeuvre
	public int totalNbVol() 
	{
		if (this.metier != null) 
            return this.metier.totalNbVol();
    	else 
            return 0;
	}
	
	public static void main(String[] args) {new Controleur();}
}
