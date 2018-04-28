package fr.urouen.model;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXML {
	private static URL url = ReadXML.class.getClassLoader().getResource("../../resources/xml/fichier.xml");
	private static File file = new File(url.getFile());	
	public static ArrayList<CVS> readXML() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		//LesCVS cvs = new LesCVS();
		ArrayList<CVS> cvs = new ArrayList<CVS>();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            if (file.exists()) {
            	Document document= builder.parse(file);
                Element racine = document.getDocumentElement();

    	        NodeList racineNoeuds = racine.getChildNodes();	
    	        int nbRacineNoeuds = racineNoeuds.getLength();

    	        for (int i = 0; i<nbRacineNoeuds; i++) {
    	            if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
    	                Element cv = (Element) racineNoeuds.item(i);
    	                String nom = cv.getElementsByTagName("nom").item(0).getTextContent();
    	                String prenom = cv.getElementsByTagName("prenom").item(0).getTextContent();
    	                Element obj = (Element)cv.getElementsByTagName("objectif").item(0);
    	                Element objType = (Element)obj.getChildNodes().item(0);
    	                String objectif, objectifType;
    	                if (objType.getNodeName() == "stage") {
    	                	objectifType = "stage";
    	                	objectif = obj.getElementsByTagName("stage").item(0).getTextContent();
    	                } else {
    	                	objectifType = "emploi";
    	                	objectif = obj.getElementsByTagName("emploi").item(0).getTextContent();	
    	                }

    	                Element prof = (Element)cv.getElementsByTagName("prof").item(0);
    	                NodeList exps = prof.getElementsByTagName("expe");
    	                int nbExps = exps.getLength();
    	                ArrayList<Experience> lesExps = new ArrayList<Experience>();
    	                for(int j = 0; j<nbExps; j++) {
    	                	Element exp = (Element) exps.item(j);
    	                	String dateDeb = exp.getElementsByTagName("datedeb").item(0).getTextContent();
    	                	String dateFin = exp.getElementsByTagName("datefin").item(0).getTextContent();
    	                	String descript = exp.getElementsByTagName("descript").item(0).getTextContent();	
    		                lesExps.add(new Experience(dateDeb, dateFin, descript));
    	                }
    	                
    	                Element skills = (Element)cv.getElementsByTagName("competences").item(0);
    	                NodeList diplomes = skills.getElementsByTagName("diplome");
    	                int nbDiplomes = diplomes.getLength();
    	                ArrayList<Diplome> lesDiplomes = new ArrayList<Diplome>();
    	                for(int j = 0; j<nbDiplomes; j++) {
    	                	Element diplome = (Element) diplomes.item(j);
    	                	String date = diplome.getElementsByTagName("date").item(0).getTextContent();
    	                	String descript = diplome.getElementsByTagName("descript").item(0).getTextContent();	
    	                	String institut = diplome.getElementsByTagName("institut").item(0).getTextContent();	
    		                String niveau = diplome.getAttribute("niveau");
    	                	lesDiplomes.add(new Diplome(niveau, date, descript, institut));
    	                }
    	                
    	                NodeList certifs = skills.getElementsByTagName("certif");
    	                int nbCertifs = certifs.getLength();
    	                ArrayList<Certification> lesCertifs = new ArrayList<Certification>();
    	                for(int j = 0; j<nbCertifs; j++) {
    	                	Element certif = (Element) certifs.item(j);
    	                	String dateDebut = certif.getElementsByTagName("datedeb").item(0).getTextContent();
    	                	String dateFin = certif.getElementsByTagName("datefin").item(0).getTextContent();
    	                	String descript = certif.getElementsByTagName("descript").item(0).getTextContent();	
    	                	lesCertifs.add(new Certification(dateDebut, dateFin, descript));
    	                }
    	                
    	                NodeList lvs = skills.getElementsByTagName("lv");
    	                int nbLvs = lvs.getLength();
    	                ArrayList<LV> lesLVS = new ArrayList<LV>();
    	                for(int j = 0; j<nbLvs; j++) {
    	                	Element certif = (Element) certifs.item(j);
    	                	String iso = certif.getAttribute("iso");
    	                	String cert = certif.getAttribute("cert");
    	                	String nivi = certif.getAttribute("nivi");	
    	                	String nivs = certif.getAttribute("nivs");	
    	                	lesLVS.add(new LV(iso, cert, nivi, nivs));
    	                }
    	                
    	                Element info = (Element) skills.getElementsByTagName("info").item(0);
    	                NodeList lgs = info.getElementsByTagName("langage");
    	                int nbLgs = lgs.getLength();
    	                ArrayList<Langage> lesLangages = new ArrayList<Langage>();
    	                for(int j = 0; j<nbLgs; j++) {
    	                	Element lg = (Element) lgs.item(j);
    	                	String nomLg = lg.getElementsByTagName("nom").item(0).getTextContent();
    	                	String niveau = lg.getElementsByTagName("niveau").item(0).getTextContent();
    	                	lesLangages.add(new Langage(nomLg, niveau));
    	                }
    	                
    	                NodeList divers = cv.getElementsByTagName("divers");
    	                int nbDivers = divers.getLength();
    	                ArrayList<String> lesDivers = new ArrayList<String>();
    	                for(int j = 0; j<nbDivers; j++) {
    	                	String d = divers.item(j).getTextContent();
    	                	lesDivers.add(d);
    	                }
    	                
    	                CVS cv_final = new CVS (nom, prenom, objectifType, objectif, lesExps, lesDiplomes, 
    	                		lesCertifs, lesLVS, lesLangages, lesDivers);
    	                cvs.add(cv_final);
    	            }               
    	        }           
            }
        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return cvs;
    }
}
