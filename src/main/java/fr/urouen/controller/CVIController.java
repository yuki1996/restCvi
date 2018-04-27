package fr.urouen.controller;
import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import fr.urouen.model.CVS;
import fr.urouen.model.Certification;
import fr.urouen.model.Diplome;
import fr.urouen.model.Experience;
import fr.urouen.model.LV;
import fr.urouen.model.Langage;
import fr.urouen.model.LesCVS;
import fr.urouen.model.ValidateForm;
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
		return "<lescvs>"+lescvs.toStringResume()+"</lescvs>";
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
            @FormParam("describeDiv") String[] divers
			) throws ParserConfigurationException, SAXException, IOException {

		if (!ValidateForm.verifFormulaire(lastname, firstname, objectif, objectifType, dateDeb_exp, descript_exp,
				niveau_diplome, date_diplome, descript_diplome, institut, dateDeb_certif, dateFin_certif, descript_certif,
				iso, cert, nivs, nivi, nom_lg, niveau_lg)) {
			return "<error>Erreur lors de l'insertion</error>";
		}
		ArrayList<Experience> exps = new ArrayList<Experience>();
		int cpt = 0;
		for (String dateDebExp : dateDeb_exp) {
			exps.add(new Experience (dateDebExp, dateFin_exp[cpt], descript_exp[cpt]));
			cpt++;
		}
		//final Experience e = new Experience(dateDeb_exp, dateFin_exp, descript_exp);

		cpt = 0;
		ArrayList<Diplome> diplomes = new ArrayList<Diplome>();
		for (String dateDiplome : date_diplome) {
			diplomes.add(new Diplome (niveau_diplome[cpt], dateDiplome, descript_diplome[cpt], institut[cpt]));
			cpt++;
		}
		//final Diplome d = new Diplome(niveau_diplome, date_diplome, descript_diplome, institut);

		cpt = 0;
		ArrayList<Certification> certifs = new ArrayList<Certification>();
		for (String dateCertif : dateDeb_certif) {
			certifs.add(new Certification (dateCertif, dateFin_certif[cpt], descript_certif[cpt]));
			cpt++;
		}
		//final Certification c = new Certification(dateDeb_certif, dateFin_certif, descript_certif);

		cpt = 0;
		ArrayList<LV> lvs = new ArrayList<LV>();
		for (String i : iso) {
			lvs.add(new LV (i, cert[cpt], nivs[cpt], nivi[cpt]));
			cpt++;
		}
		//final LV l = new LV(iso, cert, nivs, nivi);

		cpt = 0;
		ArrayList<Langage> lgs = new ArrayList<Langage>();
		for (String nomlg : nom_lg) {
			lgs.add(new Langage (nomlg, niveau_lg[cpt]));
			cpt++;
		}
		//final Langage lg = new Langage(nom_lg, niveau_lg);

		cpt = 0;
		ArrayList<String> lesdivs = new ArrayList<String>();
		for (String d : divers) {
			lesdivs.add(d);
			cpt++;
		}

		/*CVS cv = new CVS(lastname, firstname, objectifType, objectif, new ArrayList<Experience>(){{add(e);}},
				new ArrayList<Diplome>(){{add(d);}}, new ArrayList<Certification>(){{add(c);}},
				new ArrayList<LV>(){{add(l);}}, new ArrayList<Langage>(){{add(lg);}},
				new ArrayList<String>(){{add(divers);}});*/

		CVS cv = new CVS (lastname, firstname, objectifType, objectif, exps, diplomes, certifs, lvs, lgs, lesdivs);

		//return "<error>"+ValidateXML.validate_xsd(cv)+"</error>";
		/*if (!ValidateXML.validate_xsd(cv)) {
			return "<error>Erreur lors de l'insertion</error>";
		}*/

		lescvs.insert(cv);
		WriteXML.WriteXML("fichier", lescvs);
		if (lescvs.getNbCV() == 0) {
			return "<error>Aucun cv disponible</error>";
		}
		return "<lescv>"+lescvs.toString()+"</lescv>";
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
			return "<error>Identifiant erroné</error>";
		}
		lescvs.delete(id);
		return "<id>"+id+"</id>";
	}

	@RequestMapping(value = "/update/{id}",
            method=RequestMethod.GET,
            produces=MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public String getUpdateId(@PathVariable("id") int id,
			@RequestParam("lastname") String lastname,
			@RequestParam("firstname") String firstname,
			@RequestParam("objectif") String objectif,
			@RequestParam("objectifType") String objectifType,
            // une experience
			@RequestParam("dateDeb_exp") String dateDeb_exp,
			@RequestParam("dateFin_exp") String dateFin_exp,
			@RequestParam("descript_exp") String descript_exp,
            // un diplome
			@RequestParam("niveau_diplome") String niveau_diplome,
			@RequestParam("date_diplome") String date_diplome,
			@RequestParam("descript_diplome") String descript_diplome,
			@RequestParam("institut") String institut,
            // une certif
			@RequestParam("dateDeb_certif") String dateDeb_certif,
			@RequestParam("dateFin_certif") String dateFin_certif,
			@RequestParam("descript_certif") String descript_certif,
            // une lv
			@RequestParam("iso") String iso,
			@RequestParam("cert") String cert,
			@RequestParam("nivs") String nivs,
			@RequestParam("nivi") String nivi,
            // un langage
			@RequestParam("nom_lg") String nom_lg,
			@RequestParam("niveau_lg") String niveau_lg,
            // un divers
			@RequestParam("divers") final String divers) throws ParserConfigurationException, SAXException, IOException {

		if (lescvs.getNbCV() == 0) {
			return "<error>Aucun cv disponible</error>";
		}
		if (lescvs.getCV(id) == null) {
			return "<error>Identifiant erroné</error>";
		}
		final Experience e = new Experience(dateDeb_exp, dateFin_exp, descript_exp);
		final Diplome d = new Diplome(niveau_diplome, date_diplome, descript_diplome, institut);
		final Certification c = new Certification(dateDeb_certif, dateFin_certif, descript_certif);
		final LV l = new LV(iso, cert, nivs, nivi);
		final Langage lg = new Langage(nom_lg, niveau_lg);
		CVS nvCVS = new CVS(lastname, firstname, objectifType, objectif, new ArrayList<Experience>(){{add(e);}},
				new ArrayList<Diplome>(){{add(d);}}, new ArrayList<Certification>(){{add(c);}},
				new ArrayList<LV>(){{add(l);}}, new ArrayList<Langage>(){{add(lg);}},
				new ArrayList<String>(){{add(divers);}});
		if (!ValidateXML.validate_xsd(nvCVS)) {
			return "<error>Erreur lors de l'insertion</error>";
		}
		lescvs.update(id, nvCVS);
		return "<id>"+id+"</id>";
	}
}
