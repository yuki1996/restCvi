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

	public String toString() {
		if (jour == 0 && mois == 0 && annee == 0) {
			return null;
		}
		return jour+"/"+mois+"/"+annee;
	}

	public int getJour() {
		return jour;
	}
}
