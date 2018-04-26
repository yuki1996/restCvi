package validXML;

import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

public class TransformXML {
	public static void main(String[] args) throws TransformerConfigurationException, TransformerException, SAXException, IOException {
		transform_XSL();
	}
	public static void transform_XSL() throws TransformerException, TransformerConfigurationException,SAXException, IOException {
		String file = "/home/m1info/bouchlae/eclipse-workspace/cvi/src/main/resources/tp3.cvi-html.01.xsl";
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer(new StreamSource(file));
		transformer.transform(new StreamSource(file), new StreamResult("/home/m1info/bouchlae/eclipse-workspace/cvi/src/main/resources/test2.html"));
	}
}
