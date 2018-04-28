package fr.urouen.model;

import java.io.Serializable;
import java.util.List;

public class LesCVS implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<CVS> lesCVS;
	
	public LesCVS() {
		lesCVS = ReadXML.readXML();
	}
	
	public int insert(CVS cv) {
		cv.setId(lesCVS.size()+1);
		lesCVS.add(cv);
		return cv.getId();
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
