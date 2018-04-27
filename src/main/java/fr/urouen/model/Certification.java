package fr.urouen.model;

public class Certification {
	private DateEven datedeb;
	private DateEven datefin;
	private String descript;
	
	public Certification(String dd, String df, String d) {
		datedeb = new DateEven(dd);
		if (df == "yyyy-MM-dd") {
			datefin = null;
		} else {
			datefin = new DateEven(df);
		}
		descript = d;
	}
	
	public String toString() {
		String rslt = "<certif>";
		rslt += "<datedeb>"+datedeb+"</datedeb>";
		rslt += "<datefin>"+datefin+"</datefin>";
		rslt += "<descript>"+descript+"</descript>";
		rslt += "</certif>";
		return rslt;
	}

	public String getDatedeb() {
		return datedeb.toString();
	}

	public String getDatefin() {
		return datefin.toString();
	}

	public String getDescript() {
		return descript;
	}
}
