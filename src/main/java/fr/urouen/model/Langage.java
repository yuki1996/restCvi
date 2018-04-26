package fr.urouen.model;

public class Langage {
	private String nom;
	private String niveau;
	
	public Langage (String no, String ni) {
		nom = no;
		niveau = ni;
	}
	
	public String toString() {
		String rslt = "<langage>";
		rslt += "<nom>"+nom+"</nom>";
		rslt += "<niveau>"+niveau+"</niveau>";
		rslt += "</langage>";
		return rslt;
	}

	public String getNom() {
		return nom;
	}

	public String getNiveau() {
		return niveau;
	}

}
