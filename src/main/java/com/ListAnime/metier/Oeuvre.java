/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.ListAnime.metier;

public class Oeuvre 
{
	public static final Oeuvre verifOeuvre = null;
	
	private String url  ;
	private String name ;
	private String type ;
	private int    nbVol;
	private Double note ;

	private Oeuvre(String url, String name, String type, int nbVol ,double note )
	{
		this.url   = url  ;
		this.name  = name ;
		this.type  = type ;
		this.nbVol = nbVol;
		this.note  = note ;
	}

	public static Oeuvre verifOeuvre(String url, String name, String type, int nbVol ,double note)
	{
		if (nbVol >= 0 && url != null && name != null) 
			return new Oeuvre( url,  name,  type,  nbVol , note);
		return null;
	}

	/********************/
	/* 		  GET		*/
	/********************/

	public String getUrl   () {return this.url   ;}
	public String getName  () {return this.name  ;}
	public String getType  () {return this.type  ;}
	public int 	  getNbVol () {return this.nbVol ;}
	public Double getNote  () {return this.note  ;}
	
	/********************/
	/* 		  SET		*/
	/********************/

	public void setUrl   (String url  ) {this.url   = url  ; }
	public void setName  (String name ) {this.name  = name ; }
	public void setType  (String type ) {this.type  = type ; }
	public void setNbVol (int    nbVol) {this.nbVol = nbVol; }
	public void setNote  (Double note ) {this.note  = note ; }

	public String toString()
	{
		return 	"URL  :  " + this.url   + " \n" +
				"Name :  " + this.name  + " \n" +
			   	"Type :  " + this.type  + " \n" +
			   	"nbVol:  " + this.nbVol + " \n" +
			   	"Note :  " + this.note  + " \n" ;
			   
	}
}
