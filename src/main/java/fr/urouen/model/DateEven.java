package fr.urouen.model;

public class DateEven {
	private int jour;
	private int mois;
	private int annee;
	
	public DateEven (int j, int m, int a) {
		jour = j;
		mois = m;
		annee = a;
	}
	
	public DateEven (String s) {
		String[] els = s.split("-");
		jour = Integer.parseInt(els[2]);
		mois = Integer.parseInt(els[1]);
		annee = Integer.parseInt(els[0]);
	}

	public boolean isBefore(String s){
		DateEven d = new DateEven(s);
		int j = d.getJour();
		int m = d.getMois();
		int a = d.getAnnee();
		if (annee <= a){
			if (mois <= m){
				if (jour < j){
					return true;
				}
			}
		}
		return false;
	}
	
	public String toString() {
		String date = annee+"-";
		if (mois < 10) {
			date += "0"+mois+"-";
		} else {
			date += mois+"-";
		}
		if (jour < 10){
			date += "0"+jour;
		} else {
			date += jour;
		}
		return date;
	}
	
	public int getJour() {
		return jour;
	}
	public int getMois() {
		return mois;
	}
	public int getAnnee() {
		return annee;
	}
}
