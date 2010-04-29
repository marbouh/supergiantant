package InterfaceGraphique;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import Algorithme.Graphe.Graphe;
import Algorithme.Graphe.GrapheList;
import Algorithme.Graphe.NoeudList;

public class ParserGraphe
{
	private static ArrayList<NoeudList> listeNoeuds;

	public static Graphe chargerFichier(String nomFichier)
	{
		File fd = new File(nomFichier);
		String sGraphe = recupererGraphe(fd);
		Graphe graphe = creerGraphe(sGraphe);
		return graphe;		
	}
	
	
	private static String recupererGraphe(File aFile) 
	{
		StringBuilder contents = new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null;
				while (( line = input.readLine()) != null)
				{
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			}
			finally {
				input.close();
			}
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		return contents.toString();
	}
	
	
	private static Graphe creerGraphe(String sGraphe)
	{
		GrapheList graphe = null;
		String nom="";
		String noeuds="";
		boolean noeud = false;
		
		listeNoeuds = new ArrayList<NoeudList>();
		
		
		String[] texte = sGraphe.split("\r\n");
		
		for (int i=0; i<texte.length; i++)
		{
			if(i==0)//on a le nom du graphe
			{
				nom = texte[i];
			}
			else if(i >= 1 && noeud == false)//on a l'ensemble des noeuds
			{
				if(texte[i].compareTo("")!=0)
				{
					listeNoeuds.add(creerNoeud(texte[i]));
				}else
					noeud = true;
			}else if(i >= 2 && noeud == true)//on a les ingrédients
			{
				creerArrete(texte[i]);
			}
		}
		if(listeNoeuds.size() >= 1)
		{
			graphe = new GrapheList(nom,listeNoeuds.size());
			for(int j = 0; j < listeNoeuds.size(); j++)
			{
				graphe.ajouterNoeud(listeNoeuds.get(j));
			}
		}
		return graphe;
	}
	
	
	private static NoeudList creerNoeud(String sNoeud)
	{
		String[] infos = sNoeud.split(" ");
		NoeudList noeud = null;
		int id = -1;
		for(int i = 0; i< infos.length; i++)
		{
			if(i == 0)
			{
				id = Integer.parseInt(infos[i]);
			}
		}
		noeud = new NoeudList(id);
		return noeud;
	}
	
	private static void creerArrete(String sArrete)
	{
		String[] infos = sArrete.split(" ");
		NoeudList nDepart = null;
		NoeudList nArrivee = null;
		double poids = -1;
		for(int i = 0; i< infos.length; i++)
		{
			if(i == 0)//on a le premier noeud de l'arrete
			{
				nDepart = NoeudList.trouverNoeud(listeNoeuds, Integer.parseInt(infos[i]));					
			}
			else if(i == 1)//on a le deuxième noeud de l'arrete
			{
				nArrivee = NoeudList.trouverNoeud(listeNoeuds, Integer.parseInt(infos[i]));
			}
			else//on a le poids 
			{
				poids = Double.parseDouble(infos[i]);
				if(nDepart != null && nArrivee != null && poids != -1)
					nDepart.addDestination(nArrivee, poids);
			}
		}
	}
}



	
