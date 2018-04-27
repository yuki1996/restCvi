package fr.urouen.model;

public class Experience {
	private DateEven dateDeb;
	private DateEven dateFin;
	private String descript;
	
	public Experience (String dd, String df, String d) {
		dateDeb = new DateEven(dd);
		if (df == "yyyy-MM-dd") {
			dateFin = null;
		} else {
			dateFin = new DateEven(df);
		}
		descript = d;
	}
	
	public String toString() {
		String rslt = "<expe>";
		rslt += "<datedeb>"+dateDeb+"</datedeb>";
		rslt += "<datefin>"+dateFin+"</datefin>";
		rslt += "<descript>"+descript+"</descript>";
		rslt += "</expe>";
		return rslt;
	}

	public String getDateDeb() {
		return dateDeb.toString();
	}

	public String getDateFin() {
		return dateFin.toString();
	}

	public String getDescript() {
		return descript;
	}
	
}
