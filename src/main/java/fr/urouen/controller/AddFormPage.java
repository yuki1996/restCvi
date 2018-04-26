package fr.urouen.controller;

public class AddFormPage {

	public String toString() {
		return 
			"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" + 
			"<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"fr\" lang=\"fr\">\n" + 
				"<head>\n" + 
					"<title>RestCvi</title>\n" + 
					"<meta http-equiv=\"Content-Type\" content=\"text/html; UTF-8\" />\n" + 
					"<meta http-equiv=\"Content-Language\" content=\"fr\" />\n" + 
					"<link href=\"/restcvi/resources/CSS/form.css\" rel=\"stylesheet\" />\n" + 
				"</head>" +
				"<body>" + 
					"<h1>Ajout d'un CV</h1>\n" +
					"<form action=\"insert2\" method=\"post\" name=\"addForm\">\n" +
						"<fieldset>\n" +
							"<legend>Comment souhaitez-vous ajouter votre CV ?</legend>\n" +
							"<input type=\"radio\" name=\"keying\" value=\"file\" onchange=\"changeForm();\"/>\n" +
							"<label>par fichier CV</label>\n" +
							"<input type=\"radio\"name=\"keying\" value=\"manual\" onchange=\"changeForm();\"/>\n" +
							"<label>saisie manuelle</label>\n" +
						"</fieldset>\n" +
						"<script> \n" +
							"function changeForm() {\n" +
								"var form = document.getElementById('form');\n" +
								"var file = document.getElementById('file');\n" +
								"if (document.forms.addForm.keying[1].checked == true) { \n" +
								"	form.style.display = '';\n" +
								"	file.style.display = 'none';\n" +
								"} else {\n" +
								"	form.style.display = 'none';\n" +
								"	file.style.display = '';\n" +
								"}\n" +
							"}\n"+
							"var expe = 0;\n" + 
							"function moreExpe() {\n" + 
								"expe++;\n" + 
								"var newExpe = document.getElementById('expe').cloneNode(true);\n" + 
								"newExpe.id = 'expe'+expe;\n" + 
								"newExpe.style.display = 'block';\n" + 
								"for (var i = 0; i < newExpe.length; i++) {\n" + 
									"var theName = newExpe[i].name;\n" + 
									"if (theName) {\n" + 
										"newExpe[i].name = theName + expe;\n" + 
									"}\n" + 
								"}\n" + 
								"var insertHere = document.getElementById('expe').parentNode;\n" + 
								"insertHere.insertBefore(newExpe, null);\n" + 
							"}\n" + 
							"var dipl = 0;\n" + 
							"function moreDipl() {\n" + 
								"dipl++;\n" + 
								"var newDipl = document.getElementById('dipl').cloneNode(true);\n" + 
								"newDipl.id = 'dipl'+dipl;\n" + 
								"newDipl.style.display = 'block';\n" + 
								"for (var i = 0; i < newDipl.length; i++) {\n" + 
									"var theName = newDipl[i].name;\n" + 
									"if (theName) {\n" + 
										"newDipl[i].name = theName + dipl;\n" + 
									"}\n" + 
								"}\n" + 
								"var insertHere = document.getElementById('dipl').parentNode;\n" + 
								"insertHere.insertBefore(newDipl, null);\n" + 
							"}\n" +
							"var cert = 0;\n" + 
							"function moreCert() {\n" + 
								"cert++;\n" + 
								"var newCert = document.getElementById('cert').cloneNode(true);\n" + 
								"newCert.id = 'cert'+cert;\n" + 
								"newCert.style.display = 'block';\n" + 
								"for (var i = 0; i < newCert.length; i++) {\n" + 
									"var theName = newCert[i].name;\n" + 
									"if (theName) {\n" + 
										"newCert[i].name = theName + dipl;\n" + 
									"}\n" + 
								"}\n" + 
								"var insertHere = document.getElementById('cert').parentNode;\n" + 
								"insertHere.insertBefore(newCert, null);\n" + 
							"}\n" +
							"var lv = 0;\n" + 
							"function moreLv() {\n" + 
								"lv++;\n" + 
								"var newLv = document.getElementById('lv').cloneNode(true);\n" + 
								"newLv.id = 'lv'+lv;\n" + 
								"newLv.style.display = 'block';\n" + 
								"for (var i = 0; i < newLv.length; i++) {\n" + 
									"var theName = newLv[i].name;\n" + 
									"if (theName) {\n" + 
										"newLv[i].name = theName + lv;\n" + 
									"}\n" + 
								"}\n" + 
								"var insertHere = document.getElementById('lv').parentNode;\n" + 
								"insertHere.insertBefore(newLv, null);\n" + 
							"}\n" +
							"var li = 0;\n" + 
							"function moreLi() {\n" + 
								"li++;\n" + 
								"var newLi = document.getElementById('li').cloneNode(true);\n" + 
								"newLi.id = 'li'+li;\n" + 
								"newLi.style.display = 'block';\n" + 
								"for (var i = 0; i < newLi.length; i++) {\n" + 
									"var theName = newLi[i].name;\n" + 
									"if (theName) {\n" + 
										"newLi[i].name = theName + li;\n" + 
									"}\n" + 
								"}\n" + 
								"var insertHere = document.getElementById('li').parentNode;\n" + 
								"insertHere.insertBefore(newLi, null);\n" + 
							"}\n" +
							"var div = 0;\n" + 
							"function moreDiv() {\n" + 
								"div++;\n" + 
								"var newDiv = document.getElementById('div').cloneNode(true);\n" + 
								"newDiv.id = 'div'+div;\n" + 
								"newDiv.style.display = 'block';\n" + 
								"for (var i = 0; i < newDiv.length; i++) {\n" + 
									"var theName = newDiv[i].name;\n" + 
									"if (theName) {\n" + 
										"newDiv[i].name = theName + Div;\n" + 
									"}\n" + 
								"}\n" + 
								"var insertHere = document.getElementById('div').parentNode;\n" + 
								"insertHere.insertBefore(newDiv, null);\n" + 
							"}\n" +
							"function verifDate(champ) {\n" + 
								"if (champ.value == \"\") {\n" +
									"champ.value = \"yyyy-mm-jj\";\n"+
								"}\n" +
							"}\n" +
							"function verifDescrip(champ) {\n" + 
								"if (champ.value == \"\") {\n" +
									"champ.value = \"yyyy-mm-jj\";\n"+
								"}\n" +
							"}\n" +
						"</script>\n" +
						"<div id=\"form\" style=\"display:none\" >\n" +
							"<h2>Identité</h2>\n" +
							"<p>\n" +
								"<label>Nom : </label><input type=\"text\" name=\"lastName\" size=\"35\"/>\n" +
								"<label>Prénom : </label><input type=\"text\" name=\"firstName\" size=\"35\"/>\n" +
							"</p>\n" +
							"<h2>Objectif</h2>\n" +
							"<p>\n" +
								"<select name=\"obj\">\n" +
									"<option value=\"stage\">stage</option>\n" +
									"<option value=\"emploi\">emploi</option>\n" +
								"</select>\n" +
								"<input type=\"text\" name=\"descript\" value=\"intitulé du stage / désignation du poste demandé\" size=\"100\"/>\n" +
							"</p>\n" +
							"<div name=\"prof\" id=\"prof\">\n" +
								"<h2>Expérience(s) professionelle(s)<input type=\"button\" class=\"buttonAdd\" value=\"+\" onclick=\"moreExpe();\"></h2>\n" +
								"<div name=\"expe\" id=\"expe\" style=\"display:none\" class=\"expe\">\n" +
									"<input type=\"button\" class=\"buttonLess\" value=\"-\" onclick=\"this.parentNode.parentNode.removeChild(this.parentNode);\" />" +
									"<p>\n" +
										"<label>Date début : </label><input type=\"text\" name=\"dateDebExp\" value=\"yyyy-mm-dd\" onblur=\"verifDate(this)\"/>\n" +
										"<label>Date fin : </label><input type=\"text\" name=\"dateFinExp\" value=\"yyyy-mm-dd\" onblur=\"verifDate(this)\"/>\n" +
									"</p>\n" +
									"<p>\n" +
										"<textarea rows=\"4\" value=\"description de l'expérience...\" name=\"decribeExp\" >\n" +
										"</textarea>\n" +
									"</p> "+
								"</div>\n" +
							"</div>\n" +
							"<h2>Compétences</h2>\n" +
							"<fieldset>\n" +
								"<legend>Diplôme(s)<input type=\"button\" class=\"buttonAdd\" value=\"+\" onclick=\"moreDipl();\"></legend>\n" +
								"<div name=\"comp1\" id=\"comp1\">\n" +
									"<div name=\"dipl\" id=\"dipl\" style=\"display:none\" class=\"dipl\">\n" +
										"<input type=\"button\" class=\"buttonLess\" value=\"-\" onclick=\"this.parentNode.parentNode.removeChild(this.parentNode);\" />" +
										"<p>\n" +
											"<label>Niveau : </label>\n" +
											"<select name=\"nvDip\">\n" +
												"<option value=\"V\">CAP, BEP, BEPC</option>\n" +
												"<option value=\"IV\">Bac, Brevet professionnel</option>\n" +
												"<option value=\"III\">BTS, DUT</option>\n" +
												"<option value=\"II\">Bac+3 (licence), Bac+4 (Master)</option>\n" +
												"<option value=\"I\">Master, Diplôme ingénieur, Doctorat</option>\n" +
												
											"</select>\n" +
											"<label>Date d'obtention : </label><input type=\"text\" name=\"dateDip\" value=\"yyyy-mm-dd\" onblur=\"verifDate(this)\"/>\n" +
										"</p> "+
										"<p>\n" +
											"<label>Intitulé du diplôme : </label><input type=\"text\" name=\"describeDip\" size=\"100\"/>\n" +
										"<p>\n" +
											"<label>Etablissement : </label><input type=\"text\" name=\"institutDip\" size=\"100\"/>\n" +
										"</p>\n" +
									"</div>\n" +
								"</div>\n" +
							"</fieldset>\n" +
							"<fieldset>\n" +
								"<legend>Certification(s)<input type=\"button\" class=\"buttonAdd\" value=\"+\" onclick=\"moreCert();\"/></legend>\n" +
								"<div name=\"comp1\" id=\"comp2\">\n" +
									"<div name=\"cert\" id=\"cert\" style=\"display:none\" class=\"cert\">\n" +
										"<input type=\"button\" class=\"buttonLess\" value=\"-\" onclick=\"this.parentNode.parentNode.removeChild(this.parentNode);\" />" +
										"<p>\n" +
											"<label>Date début : </label><input type=\"text\" name=\"dateDebCert\" value=\"yyyy-mm-dd\" onblur=\"verifDate(this)\"/>\n" +
											"<label>Date fin : </label><input type=\"text\" name=\"dateFinCert\" value=\"yyyy-mm-dd\" onblur=\"verifDate(this)\"/>\n" +
										"</p>\n" +
										"<p>\n" +
											"<textarea rows=\"4\" placeholder=\"description de la formation...\" name=\"describeCert\" >\n" +
											"</textarea>\n" +
										"</p> " +
									"</div>\n" +
								"</div>\n" +
							"</fieldset>\n" +
							"<fieldset>\n" +
								"<legend>Langue(s) vivante(s)<input type=\"button\" class=\"buttonAdd\" value=\"+\" onclick=\"moreLv();\"/></legend>\n" +
								"<div name=\"comp3\" id=\"comp3\">\n" +
									"<div name=\"lv\" id=\"lv\" style=\"display:none\" class=\"lv\">\n" +
										"<input type=\"button\" class=\"buttonLess\" value=\"-\" onclick=\"this.parentNode.parentNode.removeChild(this.parentNode);\" />" +						
										"<p>\n" +
											"<label>Code iso : </label><input type=\"text\" name=\"iso\" size=\"5\"/>\n" +
											"<label>Certification : </label><input type=\"text\" name=\"cert\" placeholde=\"CLES\"/>\n" +
										"</p>\n" +
										"<p>\n" +
											"<label>Niveau : </label>\n" +
											"<select name=\"nivs\">\n" +
												"<option value=\"A1\">A1</option>\n" +
												"<option value=\"A2\">A2</option>\n" +
												"<option value=\"B1\">B1</option>\n" +
												"<option value=\"B2\">B2</option>\n" +
												"<option value=\"C1\">C1</option>\n" +
												"<option value=\"C2\">C2</option>\n" +
												
											"</select>\n" +
											"<label> ou </label>\n" +
											"<input type=\"number\" name=\"nivi\" />\n" +
										"</p>" +	
									"</div>\n" +
								"</div>\n" +
							"</fieldset>\n" +
							"<fieldset>\n" +
								"<legend>Langage(s) informatique(s)<input type=\"button\" class=\"buttonAdd\" value=\"+\" onclick=\"moreLi();\"/></legend>\n" +
								"<div name=\"comp4\" id=\"comp4\">\n" +
									"<div name=\"li\" id=\"li\" style=\"display:none\" class=\"li\">\n" +
										"<input type=\"button\" class=\"buttonLess\" value=\"-\" onclick=\"this.parentNode.parentNode.removeChild(this.parentNode);\" />" +						
										"<p>\n" +
											"<label>Nom : </label><input type=\"text\" name=\"nameLi\" size=\"100\"/>\n" +
											"<label>Niveau : </label><input type=\"number\" name=\"nvLi\" />\n" +
										"</p>\n" +	
									"</div>\n" +
								"</div>\n" +
							"</fieldset>\n" +
							"<div name=\"divs\" id=\"divs\">\n" +
								"<h2>Divers<input type=\"button\" value=\"+\" class=\"buttonAdd\" onclick=\"moreDiv();\"/></h2>\n" +
									"<div name=\"div\" id=\"div\" style=\"display:none\" class=\"div\">\n" +
										"<input type=\"button\" class=\"buttonLess\" value=\"-\" onclick=\"this.parentNode.parentNode.removeChild(this.parentNode);\" />" +						
										"<p>\n" +
											"<textarea rows=\"2\" placeholder=\"informations complémentaires...\" name=\"describeDiv\" ></textarea>\n" +
										"</p>\n" +
									"</div>\n" +
								"</div>\n" +
							"</div>\n" +
						"<div id=\"file\" style=\"display:none\">\n" +
							"<br/>\n" +
							"<label>Sélectionner le fichier à envoyer</label>\n" +
							"<input type=\"file\" name=\"file\">\n" +
							"<br/>\n" +
						"</div>\n" +
						"<br/>\n" +
					"<input type=\"submit\" value=\"Ajouter le CV\" />\n" +
					
				"</form>" +
			"</body>" +
		"</html>";
	} 
}
