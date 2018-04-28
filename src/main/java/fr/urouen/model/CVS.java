package fr.urouen.model;
import java.io.Serializable;
import java.util.ArrayList;

public class CVS implements Serializable {
	private static final long serialVersionUID = 2L;
	private int id;
	private String nom;
	private String prenom;
	private String objectifType;
	private String objectif;
	private ArrayList<Experience> exps;
	private ArrayList<Diplome> diplomes;
	private ArrayList<Certification> certifs;
	private ArrayList<LV> lvs;
	private ArrayList<Langage> langages;
	private ArrayList<String> divers;
	
	public CVS(String n, String p, String ot, String o, ArrayList<Experience> e, ArrayList<Diplome> d, 
			ArrayList<Certification> c, ArrayList<LV> l, ArrayList<Langage> la, ArrayList<String> ds) {
		nom = n;
		prenom = p;
		objectifType = ot;
		objectif = o;
		exps = e;
		diplomes = d;
		certifs = c;
		lvs = l;
		langages = la;
		divers = ds;
	}
	
	public CVS() {
	}
	
	public String toStringIdentite() {
		String rslt = "<identite>";
		rslt += "<nom>"+nom+"</nom>";
		rslt += "<prenom>"+prenom+"</prenom>";
		rslt += "</identite>";
		return rslt;
	}
	
	public String toStringObjectif() {
		String rslt = "<objectif>";
		if (objectifType == "stage") {
			rslt += "<stage>";
			rslt += objectif;
			rslt += "</stage>";
		} else {
			rslt += "<emploi>";
			rslt += objectif;
			rslt += "</emploi>";
		}
		rslt += "</objectif>";
		return rslt;
	}
	
	public String toStringProf() {
		String rslt = "<prof>";
		for (Experience exp : exps) {
			rslt += exp.toString();	
		}
		rslt += "</prof>";
		return rslt;
	}
	
	public String toStringCompetence() {
		String rslt = "<competences>";
		for (Diplome diplome : diplomes) {
			rslt += diplome.toString();
		}
		for (Certification certif : certifs) {
			rslt += certif.toString();
		}
		for (LV lv : lvs) {
			rslt += lv.toString();
		}
		rslt += "<info>";
		for (Langage langage : langages) {
			rslt += langage.toString();
		}
		rslt += "</info>";
		rslt += "</competences>";
		return rslt;
	}

	public String toStringValid(){
		String rslt = "<cv>";
		rslt += toStringIdentite();
		rslt += toStringObjectif();
		rslt += toStringProf();
		rslt += toStringCompetence();
		for (String d : divers) {
			rslt += "<divers>"+d+"</divers>";
		}
		rslt += "</cv>";
		
		return rslt;
	}
	
	public String toStringXML() {
		String rslt = "<cv>";
		rslt += "<id>"+id+"</id>";
		rslt += toStringIdentite();
		rslt += toStringObjectif();
		rslt += toStringProf();
		rslt += toStringCompetence();
		for (String d : divers) {
			rslt += "<divers>"+d+"</divers>";
		}
		rslt += "</cv>";
		
		return rslt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getObjectifType() {
		return objectifType;
	}

	public void setObjectifType(String objectifType) {
		this.objectifType = objectifType;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public ArrayList<Experience> getExps() {
		return exps;
	}

	public void setExps(ArrayList<Experience> exps) {
		this.exps = exps;
	}

	public ArrayList<Diplome> getDiplomes() {
		return diplomes;
	}

	public void setDiplomes(ArrayList<Diplome> diplomes) {
		this.diplomes = diplomes;
	}

	public ArrayList<Certification> getCertifs() {
		return certifs;
	}

	public void setCertifs(ArrayList<Certification> certifs) {
		this.certifs = certifs;
	}

	public ArrayList<LV> getLvs() {
		return lvs;
	}

	public void setLvs(ArrayList<LV> lvs) {
		this.lvs = lvs;
	}

	public ArrayList<Langage> getLangages() {
		return langages;
	}

	public void setLangages(ArrayList<Langage> langages) {
		this.langages = langages;
	}

	public ArrayList<String> getDivers() {
		return divers;
	}

	public void setDivers(ArrayList<String> divers) {
		this.divers = divers;
	}
}
