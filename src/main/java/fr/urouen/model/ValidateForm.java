package fr.urouen.model;

import java.util.ArrayList;

public class ValidateForm {
	public static CVS verifFormulaire(String lastname, String firstname, String objectif, 
			String objectifType, String[] dateDeb_exp, String[] descript_exp,
			String[] niveau_diplome, String[] date_diplome, String[] descript_diplome, String[] institut, 
			String[] dateDeb_certif, String[] dateFin_certif, String[] descript_certif, String[] iso, 
			String[] cert, String[] nivs, String[] nivi, String[] nom_lg, String[] niveau_lg) {
		boolean b = true;
		
		if (lastname == "" || firstname == "" || objectif == "" || (objectifType != "stage" && objectifType != "emploi")) {
			b = false;
		}
		
		for (String s : dateDeb_exp) {
			if (s == "yyyy-mm-dd") {
				b = false;
			}
		}
		
		for (String s : descript_exp) {
			if (s == "yyyy-mm-dd") {
				b = false;
			}
		}
		
		return b;
	}
	
	public CVS createCV() {
		ArrayList<Experience> exps = new ArrayList<Experience>();
		int cpt = 0;
		for (String dateDebExp : dateDeb_exp) {
			exps.add(new Experience (dateDebExp, dateFin_exp[cpt], descript_exp[cpt]));
			cpt++;
		}
		//final Experience e = new Experience(dateDeb_exp, dateFin_exp, descript_exp);
		
		cpt = 0;
		ArrayList<Diplome> diplomes = new ArrayList<Diplome>();
		for (String dateDiplome : date_diplome) {
			diplomes.add(new Diplome (niveau_diplome[cpt], dateDiplome, descript_diplome[cpt], institut[cpt]));
			cpt++;
		}
		//final Diplome d = new Diplome(niveau_diplome, date_diplome, descript_diplome, institut);
		
		cpt = 0;
		ArrayList<Certification> certifs = new ArrayList<Certification>();
		for (String dateCertif : dateDeb_certif) {
			certifs.add(new Certification (dateCertif, dateFin_certif[cpt], descript_certif[cpt]));
			cpt++;
		}
		//final Certification c = new Certification(dateDeb_certif, dateFin_certif, descript_certif);
		
		cpt = 0;
		ArrayList<LV> lvs = new ArrayList<LV>();
		for (String i : iso) {
			lvs.add(new LV (i, cert[cpt], nivs[cpt], nivi[cpt]));
			cpt++;
		}
		//final LV l = new LV(iso, cert, nivs, nivi);
		
		cpt = 0;
		ArrayList<Langage> lgs = new ArrayList<Langage>();
		for (String nomlg : nom_lg) {
			lgs.add(new Langage (nomlg, niveau_lg[cpt]));
			cpt++;
		}
		//final Langage lg = new Langage(nom_lg, niveau_lg);
		
		cpt = 0;
		ArrayList<String> lesdivs = new ArrayList<String>();
		for (String d : divers) {
			lesdivs.add(d);
			cpt++;
		}
		
		CVS cv = new CVS (lastname, firstname, objectifType, objectif, exps, diplomes, certifs, lvs, lgs, lesdivs);
		return cv;
	}
}
