package fr.urouen.model;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {

	private boolean errorOccured;
	
	public SimpleErrorHandler() {
		this.errorOccured = false;
	}
	
	public boolean hasError() {
		return this.errorOccured;
	}
	
	public void warning(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
	}

	public void error(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
		this.errorOccured = true;
	}

	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
		this.errorOccured = true;
	}

}
