/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.ListAnime.metier;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OeuvreService 
{
	private String urlSite;

	public OeuvreService()
	{
		this.urlSite = "https://api.jikan.moe/v4/";
	}

	public String getUrlOeuvre(String name, String type)
	{
        if (type.equals("manhwa")) 
            type = "manga";  

		String url ="";
		OkHttpClient client = new OkHttpClient();	

        Request request = new Request.Builder()
                .url(this.urlSite + type + "?q=" + name)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur d'entrée/sortie lors de la récupération des données : ttt " + response);
            }

            String responseBody = response.body().string();
            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonObject firstResult = json.getAsJsonArray("data").get(0).getAsJsonObject();

            // Récupérer l'image de l'anime
            url = firstResult.getAsJsonObject("images").getAsJsonObject("jpg").get("image_url").getAsString();
        } catch (IOException e) {
            System.out.println("Erreur d'entrée/sortie lors de la récupération des données : " + e.getMessage());
        }
		
		return url;
	}

	public int getnbVol(String name, String type)
	{
        if (type.equals("manhwa")) 
            type = "manga";            

		int nbVol = 0;

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(this.urlSite + type + "?q=" + name)
				.build();

		try (Response response = client.newCall(request).execute()){
			if (!response.isSuccessful()) {
                throw new IOException("Erreur d'entrée/sortie lors de la récupération des données : " + response);
            }

			String responseBody = response.body().string();
            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonObject firstResult = json.getAsJsonArray("data").get(0).getAsJsonObject();

            // Récupérer le nombre de volumes
			switch (type.toLowerCase()) {
                case "manga":
						if (firstResult.has("volumes") && !firstResult.get("volumes").isJsonNull()) {
							nbVol = firstResult.get("volumes").getAsInt();
							
						} else {
							System.out.println("Le champ 'volumes' est manquant ou nul pour le type " );
						}
                    break;

                case "anime":
                    if (firstResult.has("episodes") && !firstResult.get("episodes").isJsonNull()) {
                        nbVol = firstResult.get("episodes").getAsInt();
                    } else {
                        nbVol = 0;
                    }
                    break;

                default:
                    System.out.println("Type non reconnu : " + type);
                    break;
            }
        } catch (IOException e) {
            System.out.println("Erreur d'entrée/sortie lors de la récupération des données : " + e.getMessage());
        }

		return nbVol;
	}
}