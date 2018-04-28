package fr.urouen.model;

import java.io.File;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXML {
	private static URL url = WriteXML.class.getClassLoader().getResource("../../resources/xml/fichier.xml");
	private static File file = new File(url.getFile());	
	
	public static boolean writeXML(LesCVS cvs) {
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    try {
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document document= builder.newDocument();

	        Element racine = document.createElement("lescvs");
	        document.appendChild(racine);           

	        for (CVS cv : cvs.getLesCVS()) {
		        Element cvNode = document.createElement("cv");
		      
		        //identite
		        Element identite = document.createElement("identite");
		        Element nom = document.createElement("nom");
		        nom.appendChild(document.createTextNode(cv.getNom()));
		        identite.appendChild(nom);
		        Element prenom = document.createElement("prenom");
		        prenom.appendChild(document.createTextNode(cv.getPrenom()));
		        identite.appendChild(prenom);
		        cvNode.appendChild(identite);
		        
		        //objectif
		        Element objectifType = document.createElement("objectif");
		        Element objectif;
		        if (cv.getObjectifType() == "stage") {
		        	objectif = document.createElement("stage");
		        } else {
		        	objectif = document.createElement("emploi");
		        }
		    	objectif.appendChild(document.createTextNode(cv.getObjectif()));
		        objectifType.appendChild(objectif);
		        cvNode.appendChild(objectifType);
		        
		        //experiences
		        Element prof = document.createElement("prof");
		        for (Experience exp : cv.getExps()) {
		        	Element expNode = document.createElement("expe");
		        	Element datedeb = document.createElement("datedeb");
		        	datedeb.appendChild(document.createTextNode(exp.getDateDeb()));
			        expNode.appendChild(datedeb);
			        Element datefin = document.createElement("datefin");
		        	datefin.appendChild(document.createTextNode(exp.getDateFin()));
			        expNode.appendChild(datefin);
			        Element descript = document.createElement("descript");
			        descript.appendChild(document.createTextNode(exp.getDescript()));
			        expNode.appendChild(descript);
			        prof.appendChild(expNode);
		        }
		        cvNode.appendChild(prof);
		        
		        //competence
		        Element skills = document.createElement("competences");
		        
		        //diplome
		        for (Diplome diplome : cv.getDiplomes()) {
		        	Element diplomeNode = document.createElement("diplome");
		        	diplomeNode.setAttribute("niveau", diplome.getNiveau());
		        	Element date = document.createElement("date");
		        	date.appendChild(document.createTextNode(diplome.getDate()));
			        diplomeNode.appendChild(date);
			        Element descript = document.createElement("descript");
			        descript.appendChild(document.createTextNode(diplome.getDescript()));
			        diplomeNode.appendChild(descript);
			        Element institut = document.createElement("institut");
			        institut.appendChild(document.createTextNode(diplome.getInstitut()));
			        diplomeNode.appendChild(institut);
			        skills.appendChild(diplomeNode);
		        }
		        
		        //certification
		        for (Certification certif : cv.getCertifs()) {
		        	Element certifNode = document.createElement("certif");
		        	Element datedeb = document.createElement("datedeb");
		        	datedeb.appendChild(document.createTextNode(certif.getDatedeb()));
		        	certifNode.appendChild(datedeb);
		        	Element datefin = document.createElement("datefin");
		        	datefin.appendChild(document.createTextNode(certif.getDatefin()));
		        	certifNode.appendChild(datefin);
			        Element descript = document.createElement("descript");
			        descript.appendChild(document.createTextNode(certif.getDescript()));
			        certifNode.appendChild(descript);
			        skills.appendChild(certifNode);
		        }
		        
		        //lv
		        for (LV lv : cv.getLvs()) {
		        	Element lvNode = document.createElement("lv");
		        	lvNode.setAttribute("iso", lv.getIso());
		        	lvNode.setAttribute("cert", lv.getCert());
		        	if (lv.getNivs() != null) 
		        		lvNode.setAttribute("nivs", lv.getNivs());
		        	if (lv.getNivi() != null)
		        		lvNode.setAttribute("nivi", lv.getNivi());
		        	
			        skills.appendChild(lvNode);
		        }
		        
		        //langage
		        Element info = document.createElement("info");
		        for (Langage lg : cv.getLangages()) {
		        	Element lgNode = document.createElement("langage");
		        	Element nomLg = document.createElement("nom");
		        	nomLg.appendChild(document.createTextNode(lg.getNom()));
		        	lgNode.appendChild(nomLg);
		        	Element niveau = document.createElement("niveau");
		        	niveau.appendChild(document.createTextNode(lg.getNiveau()));
		        	lgNode.appendChild(niveau);
			        info.appendChild(lgNode);
		        }
		        skills.appendChild(info);
		        cvNode.appendChild(skills);
		        
		        //divers
		        for (String d : cv.getDivers()) {
		        	Element diversNode = document.createElement("divers");
		        	diversNode.appendChild(document.createTextNode(d));
		        	cvNode.appendChild(diversNode);
		        }
		        racine.appendChild(cvNode);
		        
		        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		        Transformer transformer;
				try {
					transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(document);
			        StreamResult resultat = new StreamResult(file);
			        try {
						transformer.transform(source, resultat);
						return true;
					} catch (TransformerException e) {
						e.printStackTrace();
					}
				} catch (TransformerConfigurationException e1) {
					e1.printStackTrace();
				}
	        }
	    } catch (final ParserConfigurationException e) {
	        e.printStackTrace();
	    }   
	    return false;
	}
}
