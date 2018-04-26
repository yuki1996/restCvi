package validXML;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ValidateXML {
	@Test
	public void should_validate_with_DOM() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);

		SchemaFactory schemaFactory = 
		    SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

		factory.setSchema(schemaFactory.newSchema(
		    new Source[] {new StreamSource("/home/m1info/bouchlae/eclipse-workspace/restcvi/src/test/java/validXML/v_laetitia.xsd")}
		    )
		);
		
		DocumentBuilder builder = factory.newDocumentBuilder();
		SimpleErrorHandler seh = new SimpleErrorHandler();
		builder.setErrorHandler(seh);
		Document document = builder.parse(new InputSource("/home/m1info/bouchlae/eclipse-workspace/cvi/src/main/resources/tp1.good2.xml"));
		
		assert seh.hasError() == false;
		System.out.println("Tout c'est bien passée DOM");
	}
	
	@Test
	public void should_validate_with_SAX() throws SAXException, ParserConfigurationException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);

		SchemaFactory schemaFactory = 
		    SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

		factory.setSchema(schemaFactory.newSchema(
		    new Source[] {new StreamSource("/home/m1info/bouchlae/eclipse-workspace/cvi/src/main/resources/tp1.cv1.02.xsd")}));

		SAXParser parser = factory.newSAXParser();

		XMLReader reader = parser.getXMLReader();
		SimpleErrorHandler seh = new SimpleErrorHandler();
		
		reader.setErrorHandler(seh);
		reader.parse(new InputSource("/home/m1info/bouchlae/eclipse-workspace/cvi/src/main/resources/tp1.bad1.xml"));
		assert seh.hasError() == false;
		System.out.println("Tout c'est bien passée SAX");
	}
}
