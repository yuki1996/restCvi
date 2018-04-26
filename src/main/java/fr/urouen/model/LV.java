package fr.urouen.model;

public class LV {
	private String iso;
	private String cert;
	private String nivs;
	private String nivi;
	
	public LV (String i, String c, String ns, String ni) {
		iso = i;
		cert = c;
		if (ns == "") {	
			ns = null;
		}
		if (ni == "") {
			ni = null;	
		}
		nivs = ns;
		nivi = ni;
	}
	
	public String toString() {
		String rslt = "<lv ";
		rslt += "iso='"+iso+"' ";
		rslt += "cert='"+cert+"' ";
		if (nivs != null) {
			rslt += "nivs='"+nivs+"' ";
		}
		if (nivi != null) {
			rslt += "nivi='"+nivi+"' ";
		}
		rslt += "/>";
		return rslt; 
	}

	public String getIso() {
		return iso;
	}

	public String getCert() {
		return cert;
	}

	public String getNivs() {
		return nivs;
	}

	public String getNivi() {
		return nivi;
	}
}
