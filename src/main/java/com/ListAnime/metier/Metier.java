/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.ListAnime.metier;

import com.ListAnime.Controleur;
import java.util.ArrayList;
import java.util.List;

public class Metier 
{
	private List<Oeuvre>   lstOeuvresSave ;

	private GestionFichier gestion    ;
	private List<Oeuvre>   lstOeuvres ;
	private OeuvreService  service	  ;
	private Controleur	   ctrl       ;

	public Metier(Controleur ctrl)
	{
		this.lstOeuvres = new ArrayList<Oeuvre>();
		this.service	= new OeuvreService    ();
		this.gestion	= new GestionFichier   ();
		this.ctrl		= ctrl;

		this.lstOeuvresSave = new ArrayList<Oeuvre>();

		this.lstOeuvres = this.gestion.loadOeuvres("/file.json");
	}

	public List<Oeuvre> getLstOeuvres	 () {return this.lstOeuvres       ;} 	//Renvoie la List Actuelle
	public List<Oeuvre> getLstOeuvresSave() {return this.lstOeuvresSave   ;}	//Renvoie la List Sauvegarder
	public int 			getNbSerie	 	 () {return this.lstOeuvres.size();}	//Revoie un entier Total des serie dans la List


	public void sauvegardOeuvres () {this.gestion.sauvegardOeuvres(this.lstOeuvresSave, "src\\main\\resources\\file.json");}

	//Ajoute une Oeuvre a la List Actuelle et Sauvegarder
	public void addOeuvre(String name , String type, Double note)
	{
		String url   = this.service.getUrlOeuvre(name, type);
		int    nbVol = this.service.getnbVol	(name, type); 

		this.lstOeuvres	   .add(Oeuvre.verifOeuvre(url, name, type, nbVol, note ));
		this.lstOeuvresSave.add(Oeuvre.verifOeuvre(url, name, type, nbVol, note ));
	}

	//Ajoute une Oeuvre a la List Sauvegarder
	public void addOeuvreSave(String name , String type, Double note)
	{
		String url   = this.service.getUrlOeuvre(name, type);
		int    nbVol = this.service.getnbVol	(name, type); 

		this.lstOeuvresSave.add(Oeuvre.verifOeuvre(url, name, type, nbVol, note ));
	}

	//Regarde si cette Oeuvre existe deja
	public boolean checkList(String name , String type)
	{
		for (int j = 0; j < this.lstOeuvresSave.size(); j++) 
		{
			if (this.lstOeuvresSave.get(j).getName().equals(name) && 
				this.lstOeuvresSave.get(j).getType().equals(type))
			{
				return false;
			} 
		}
		return true;
	}


	//Supprime l'Oeuvre selectionner 
	public void supprimer(String name , String type)
	{
		for (int i = 0; i < this.lstOeuvres.size(); i++) 
		{
			if (this.lstOeuvres.get(i).getName().equals(name) && this.lstOeuvres.get(i).getType().equals(type))
			{
				this.lstOeuvres.remove(i);
				i--;
			}
		}

		for (int j = 0; j < this.lstOeuvresSave.size(); j++) 
		{
			if (this.lstOeuvresSave.get(j).getName().equals(name) && 
				this.lstOeuvresSave.get(j).getType().equals(type))
			{
				this.lstOeuvresSave.remove(j);
				j--;
			} 
		}
	}

	//Modifier la note de l'Oeuvre passer en parramettre
	public void modifier(int ind, Double note) 
	{
		this.lstOeuvres.get(ind).setNote(note);
		for (int cpt = 0; cpt < this.lstOeuvresSave.size(); cpt++)
		{
			if (this.lstOeuvres.get(ind).getName().equals(this.lstOeuvresSave.get(cpt).getName()) &&
				this.lstOeuvres.get(ind).getType().equals(this.lstOeuvresSave.get(cpt).getType()))
			{
				this.lstOeuvresSave.get(cpt).setNote(note);
			}
		}
	}

	//cherche les Oeuvre avec le paramettre et supprimer les autre
	public void research(String name)
	{
		this.resetLstOevre();
			if (!this.ctrl.getSelectedItem().equals("all")) 
				this.changeLstChoix(this.ctrl.getSelectedItem());

		if (name.length() > 0) 
		{
			for (int i = 0; i < this.lstOeuvres.size(); i++) 
			{
				if (!this.lstOeuvres.get(i).getName().contains(name) && !this.lstOeuvres.get(i).getName().toLowerCase().contains(name) &&
					!this.lstOeuvres.get(i).getName().toUpperCase().contains(name))
				{
					this.lstOeuvres.remove(i);
					i--;
				}
			}
		}
	}

	//Renvoie un entier total des nombre de Volume de tout les Oeuvre
	public int totalNbVol()
	{
		int totalnbVol = 0;
		for (Oeuvre o : this.lstOeuvres) 
			totalnbVol += o.getNbVol();
		
		return totalnbVol;
	}

	//Renvoie un Double la note Moyenne de tout les Oeuvre
	public Double noteMoy()
	{
		Double noteMoy = 0.0;
		for (Oeuvre o : this.lstOeuvres) 
			noteMoy += o.getNote();
		
		return noteMoy / this.lstOeuvres.size();
	}

	//Garde les Oeuvre que du type en paramettre 
	public void changeLstChoix(String type)
	{
		for(int i =0; i< this.lstOeuvres.size() ; i++)
		{
			if (!this.lstOeuvres.get(i).getType().equals(type)) 
			{
				this.lstOeuvres.remove(i);	
				i--;
			}
		}
	}

	//Methode permet de sauvegarder la List
	public void saveLstOevre ()
	{
		for (Oeuvre oeuvre : lstOeuvres) 
			this.lstOeuvresSave.add(oeuvre);
	}

	//Methode permet de Reset la List grace a la ListSave
	public void resetLstOevre()
	{
		lstOeuvres.clear();
		for (Oeuvre oeuvre : lstOeuvresSave) 
			this.lstOeuvres.add(oeuvre);
	}
}
