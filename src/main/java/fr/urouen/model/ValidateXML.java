package fr.urouen.model;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import java.net.URL;
import java.io.File;
import org.xml.sax.SAXException;

import javax.xml.validation.SchemaFactory;


public class ValidateXML {
	public ValidateXML(){
	}
	
	public boolean validate_xsd(String cv) {
		URL url = getClass().getClassLoader().getResource("../../resources/xml/v_laetitia.xsd");
		File schemaFile = new File(url.getFile());
		Source xmlFile = new StreamSource(new java.io.StringReader(cv));
		SchemaFactory schemaFactory = SchemaFactory
		    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
		  Schema schema = schemaFactory.newSchema(schemaFile);
		  Validator validator = schema.newValidator();
		  validator.validate(xmlFile);
		} catch (SAXException e) {
			return false;
		} catch (IOException e) {}		
		//return false;	
		return true;	
	}

}
