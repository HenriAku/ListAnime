/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.ListAnime.metier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class GestionFichier 
{
    //Methode pour Recuperer Les Oeuvres dans le fichier .json
    public List<Oeuvre> loadOeuvres(String filename) 
    {
        Gson gson = new Gson();
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream(filename))) 
        {
            return gson.fromJson(reader, new TypeToken<List<Oeuvre>>(){}.getType()); //lis le fichier 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Methode pour ecrire Les Oeuvres dans le fichier .json
    public void sauvegardOeuvres(List<Oeuvre> oeuvres, String filename)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Utilisation de GsonBuilder pour une meilleure lisibilité

        try (Writer writer = new FileWriter(filename)) 
        {
            gson.toJson(oeuvres, writer);   //ecrit les oeuvre grace au get et attrribut
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la sauvegarde des oeuvres : " + e.getMessage());
        } catch (JsonIOException e) {
            e.printStackTrace();
            System.err.println("Erreur JSON lors de la sauvegarde des oeuvres : " + e.getMessage());
        }
    }

}
