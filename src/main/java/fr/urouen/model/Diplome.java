package fr.urouen.model;

public class Diplome {
	private String niveau;
	private DateEven date;
	private String descript;
	private String institut;
	
	public Diplome (String n, String da, String de, String i) {
		niveau = n;
		date = new DateEven(da);
		descript = de;
		institut = i;
	}
	
	public String toString() {
		String rslt = "<diplome";
		if (niveau != null) {
			rslt += " niveau ='"+niveau+"'";
		}
		rslt += ">";
		rslt += "<date>"+date+"</date>";
		rslt += "<descript>"+descript+"</descript>";
		rslt += "<institut>"+institut+"</institut>";
		rslt += "</diplome>";
		return rslt;
	}

	public String getNiveau() {
		return niveau;
	}

	public String getDate() {
		return date.toString();
	}

	public String getDescript() {
		return descript;
	}

	public String getInstitut() {
		return institut;
	}
}
