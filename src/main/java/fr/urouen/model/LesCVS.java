package fr.urouen.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LesCVS implements Serializable {
	private List<CVS> lesCVS;
	
	public LesCVS() {
		//lesCVS = new LinkedList<CVS>();
		/*insert(new CVS("Pierre", "Paul", "stage", "Staaage", 
				new ArrayList<Experience>(){{add(new Experience("2014-02-02", "2017-02-02", "description"));}},
				new ArrayList<Diplome>(){{add(new Diplome("BAC", "2012-02-02", "description", "Lycée"));}}, 
				new ArrayList<Certification>(){{add(new Certification("2015-03-03", "2016-05-02", "description"));}}, 
				new ArrayList<LV>(){{add(new LV("er", "ert", "A1", null));}}, 
				new ArrayList<Langage>(){{add(new Langage("Nom", "2"));}}, 
				new ArrayList<String>(){{add("Divers");}}
		));*/
		lesCVS = ReadXML.readXML("fichier");
	}
	
	public void insert(CVS cv) {
		cv.setId(lesCVS.size()+1);
		lesCVS.add(cv);
	}
	
	public void delete(int id) {
		for (CVS cv : lesCVS) {
			if (cv.getId() == id) {
				lesCVS.remove(cv);
			}
		}
	}
	
	public void update(int id, CVS nvCVS) {
		for (CVS cv : lesCVS) {
			if (cv.getId() == id) {
				cv.setNom(nvCVS.getNom());
				cv.setPrenom(nvCVS.getPrenom());
				cv.setCertifs(nvCVS.getCertifs());
				cv.setDiplomes(nvCVS.getDiplomes());
				cv.setDivers(nvCVS.getDivers());
				cv.setExps(nvCVS.getExps());
				cv.setLangages(nvCVS.getLangages());
				cv.setLvs(nvCVS.getLvs());
				cv.setObjectif(nvCVS.getObjectif());
				cv.setObjectifType(nvCVS.getObjectifType());
			}
		}
	}
	
	public String toString() {
		String lescvs = "";
		for (CVS cv : lesCVS) {
			lescvs += cv.toStringXML();
		}
		return lescvs;
	}
	
	public String getCV(int id) {
		for (CVS cv : lesCVS) {
			if (cv.getId() == id) {
				return cv.toStringXML();
			}
		}
		return null;
	}
	
	public int getNbCV() {
		return lesCVS.size();
	}

	public List<CVS> getLesCVS() {
		return lesCVS;
	}
}
