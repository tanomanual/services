package org.inneo.services.recursos;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Utilitarios {
	
	public static String dateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm dd-MM-yyyy");
		String publicado =format.format(date);
		return publicado;
	}
	
	public static String abreviarNome(String nome) {  
        if(nome == null) {
            return null;
        }
        String[] nomeCompleto = nome.split(" ");
        String primeiro = nomeCompleto[0];
        String meio = " ";
        for (int i = 1; i < nomeCompleto.length - 1; i++) 
        {
            if (!nomeCompleto[i].toLowerCase().equals("de") && !nomeCompleto[i].toLowerCase().equals("da") && !nomeCompleto[i].toLowerCase().equals("do") && !nomeCompleto[i].toLowerCase().equals("das") && !nomeCompleto[i].toLowerCase().equals("dos"))
            {
                 meio += nomeCompleto[i].substring(0, 1);
                 meio += ". ";
            }
        }

        String ultimo = nomeCompleto[nomeCompleto.length-1];        
        String nomeAbreviado = primeiro + meio + ultimo;        
        return nomeAbreviado.toString();
    }	
	
}
