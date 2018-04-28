package fr.urouen.controller;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import fr.urouen.model.CVS;
import fr.urouen.model.Certification;
import fr.urouen.model.DateEven;
import fr.urouen.model.Diplome;
import fr.urouen.model.Experience;
import fr.urouen.model.LV;
import fr.urouen.model.Langage;
import fr.urouen.model.LesCVS;
import fr.urouen.model.ValidateXML;
import fr.urouen.model.WriteXML;

@RestController
public class CVIController {
	public LesCVS lescvs = new LesCVS();

	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public @ResponseBody String getHome() {
		HelpPage help = new HelpPage();
		return help.toString();
	}

	@RequestMapping(value = "/resume",
            method=RequestMethod.GET,
            produces=MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody String getResume() {
		if (lescvs.getNbCV() == 0) {
			return "<error>Aucun cv disponible</error>";
		}
		return "<lescvs>"+lescvs.toString()+"</lescvs>";
	}

	@RequestMapping(value = "/resume/{id}",
            method=RequestMethod.GET,
            produces=MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public String getResumeId(@PathVariable("id") int id) {
		if (lescvs.getNbCV() == 0) {
			return "<error>Aucun cv disponible</error>";
		}
		if (lescvs.getCV(id) == null) {
			return "<error>Identifiant erroné</error>";
		}
		return lescvs.getCV(id);
	}

	@RequestMapping(value = "/insert",
            method=RequestMethod.POST,
            produces=MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public String insert(
			@RequestParam("lastName") String lastname,
			@RequestParam("firstName") String firstname,
			@RequestParam("descript") String objectif,
			@RequestParam("obj") String objectifType,
            // une experience
			@RequestParam("dateDebExp") String[] dateDeb_exp,
			@RequestParam("dateFinExp") String[] dateFin_exp,
			@RequestParam("decribeExp") String[] descript_exp,
            // un diplome
			@RequestParam("nvDip") String[] niveau_diplome,
			@RequestParam("dateDip") String[] date_diplome,
			@RequestParam("describeDip") String[] descript_diplome,
			@RequestParam("institutDip") String[] institut,
            // une certif
			@RequestParam("dateDebCert") String[] dateDeb_certif,
			@RequestParam("dateFinCert") String[] dateFin_certif,
			@RequestParam("describeCert") String[] descript_certif,
            // une lv
			@RequestParam("iso") String[] iso,
			@RequestParam("cert") String[] cert,
			@RequestParam("nivs") String[] nivs,
			@RequestParam("nivi") String[] nivi,
            // un langage
			@RequestParam("nameLi") String[] nom_lg,
			@RequestParam("nvLi") String[] niveau_lg,
            // un divers
            		@RequestParam("describeDiv") String[] divers
			) throws ParserConfigurationException, SAXException, IOException {
		ArrayList<Experience> exps = new ArrayList<Experience>();
		int cpt = 0;
		for (String dateDebExp : dateDeb_exp) {
			if (cpt != 0) {
				DateEven d = new DateEven(dateDebExp);
				if (!dateFin_exp[cpt].equals("YYYY-MM-DD")){
					if (d.isBefore(dateFin_exp[cpt]) == false) {
						return "<error>Erreur dans le renseignement des dates des expériences.</error>";
					}
				} 
				exps.add(new Experience (dateDebExp, dateFin_exp[cpt], descript_exp[cpt]));			
			}
			cpt++;
		}

		cpt = 0;
		ArrayList<Diplome> diplomes = new ArrayList<Diplome>();
		for (String dateDiplome : date_diplome) {
			if (cpt != 0) {			
				diplomes.add(new Diplome (niveau_diplome[cpt], dateDiplome, descript_diplome[cpt], institut[cpt]));
			}
			cpt++;
		}

		cpt = 0;
		ArrayList<Certification> certifs = new ArrayList<Certification>();
		for (String dateCertif : dateDeb_certif) {
			if (cpt != 0) {				
				DateEven d = new DateEven(dateCertif);				
				if (!dateFin_certif[cpt].equals("YYYY-MM-DD")){
					if (d.isBefore(dateFin_certif[cpt]) == false) {
						return "<error>Erreur dans le renseignement des dates des certifications.</error>";
					}
				} 
				certifs.add(new Certification (dateCertif, dateFin_certif[cpt], descript_certif[cpt]));
			}
			cpt++;
		}

		cpt = 0;
		ArrayList<LV> lvs = new ArrayList<LV>();
		for (String i : iso) {
			if (cpt != 0) {
				lvs.add(new LV (i, cert[cpt], nivs[cpt], nivi[cpt]));
			}
			cpt++;
		}

		cpt = 0;
		ArrayList<Langage> lgs = new ArrayList<Langage>();
		for (String nomlg : nom_lg) {
			if (cpt != 0) {
				lgs.add(new Langage (nomlg, niveau_lg[cpt]));
			}
			cpt++;
		}

		cpt = 0;
		ArrayList<String> lesdivs = new ArrayList<String>();
		for (String d : divers) {
			if (cpt != 0) {
				lesdivs.add(d);
			}
			cpt++;
		}

		CVS cv = new CVS (lastname, firstname, objectifType, objectif, exps, diplomes, certifs, lvs, lgs, lesdivs);
		
		ValidateXML vxml = new ValidateXML();
		
		if (!vxml.validate_xsd("<lescvs>"+cv.toStringValid()+"</lescvs>")) {
			return "<error>Erreur lors de l'insertion</error>";
		}

		int id = lescvs.insert(cv);
		boolean b = WriteXML.writeXML(lescvs);
		if (b == false){
			return "<error>Erreur ecriture</error>";
		}
		if (lescvs.getNbCV() == 0) {
			return "<error>Aucun cv disponible</error>";
		}
		return "<id>"+id+"</id>";
	}

	@RequestMapping(value = "/delete/{id}",
            method=RequestMethod.GET,
            produces=MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public String getDeleteId(@PathVariable("id") int id) {
		if (lescvs.getNbCV() == 0) {
			return "<error>Aucun cv disponible</error>";
		}
		if (lescvs.getCV(id) == null) {
			return "<error>Identifiant errone</error>";
		}
		lescvs.delete(id);
		return "<lescv>"+lescvs.toString()+"</lescv>";
	}

	@RequestMapping(value = "/update/{id}",
            method=RequestMethod.GET,
            produces=MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public String getUpdateId(@PathVariable("id") int id,
			@RequestParam("lastName") String lastname,
			@RequestParam("firstName") String firstname,
			@RequestParam("descript") String objectif,
			@RequestParam("obj") String objectifType,
            // une experience
			@RequestParam("dateDebExp") String[] dateDeb_exp,
			@RequestParam("dateFinExp") String[] dateFin_exp,
			@RequestParam("decribeExp") String[] descript_exp,
            // un diplome
			@RequestParam("nvDip") String[] niveau_diplome,
			@RequestParam("dateDip") String[] date_diplome,
			@RequestParam("describeDip") String[] descript_diplome,
			@RequestParam("institutDip") String[] institut,
            // une certif
			@RequestParam("dateDebCert") String[] dateDeb_certif,
			@RequestParam("dateFinCert") String[] dateFin_certif,
			@RequestParam("describeCert") String[] descript_certif,
            // une lv
			@RequestParam("iso") String[] iso,
			@RequestParam("cert") String[] cert,
			@RequestParam("nivs") String[] nivs,
			@RequestParam("nivi") String[] nivi,
            // un langage
			@RequestParam("nameLi") String[] nom_lg,
			@RequestParam("nvLi") String[] niveau_lg,
            // un divers
            		@RequestParam("describeDiv") String[] divers
			) throws ParserConfigurationException, SAXException, IOException, URISyntaxException {

		if (lescvs.getNbCV() == 0) {
			return "<error>Aucun cv disponible</error>";
		}
		if (lescvs.getCV(id) == null) {
			return "<error>Identifiant erroné</error>";
		}
		ArrayList<Experience> exps = new ArrayList<Experience>();
		int cpt = 0;
		for (String dateDebExp : dateDeb_exp) {
			if (cpt != 0) {
				exps.add(new Experience (dateDebExp, dateFin_exp[cpt], descript_exp[cpt]));
				
			}cpt++;
		}

		cpt = 0;
		ArrayList<Diplome> diplomes = new ArrayList<Diplome>();
		for (String dateDiplome : date_diplome) {
			if (cpt != 0) {
			diplomes.add(new Diplome (niveau_diplome[cpt], dateDiplome, descript_diplome[cpt], institut[cpt]));
			
			}cpt++;
		}

		cpt = 0;
		ArrayList<Certification> certifs = new ArrayList<Certification>();
		for (String dateCertif : dateDeb_certif) {
			if (cpt != 0) {
			certifs.add(new Certification (dateCertif, dateFin_certif[cpt], descript_certif[cpt]));
			
			}cpt++;
		}

		cpt = 0;
		ArrayList<LV> lvs = new ArrayList<LV>();
		for (String i : iso) {
if (cpt != 0) {
			lvs.add(new LV (i, cert[cpt], nivs[cpt], nivi[cpt]));
			
}cpt++;
		}

		cpt = 0;
		ArrayList<Langage> lgs = new ArrayList<Langage>();
		for (String nomlg : nom_lg) {
if (cpt != 0) {
			lgs.add(new Langage (nomlg, niveau_lg[cpt]));
			}cpt++;
		}

		cpt = 0;
		ArrayList<String> lesdivs = new ArrayList<String>();
		for (String d : divers) {
if (cpt != 0) {
			lesdivs.add(d);
			
}cpt++;
		}

		CVS nvCVS = new CVS (lastname, firstname, objectifType, objectif, exps, diplomes, certifs, lvs, lgs, lesdivs);

		ValidateXML vxml = new ValidateXML();
		if (!vxml.validate_xsd("<lescvs>"+nvCVS.toStringValid()+"</lescvs>")) {
			return "<error>Erreur lors de l'insertion</error>";
		}
		lescvs.update(id, nvCVS);
		return "<lescv>"+lescvs.toString()+"</lescv>";
	}
}
