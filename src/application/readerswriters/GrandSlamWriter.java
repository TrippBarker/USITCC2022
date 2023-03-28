package application.readerswriters;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import application.*;
import javafx.collections.ObservableList;



public class GrandSlamWriter {
	public void buildDocument(ObservableList<GrandSlam> gSlams) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		Document doc = createXMLDoc(gSlams);
		outputAsFile(doc, "src/application/data/GrandSlamInformation.xml");
	}
	
	
	private void outputAsFile(Document doc, String filename) 
			throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filename));
		getTransformer().transform(source, result);
	}
	
	
	private Transformer getTransformer()
			throws TransformerFactoryConfigurationError, TransformerConfigurationException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xsltindent-amount}", "2");
		return transformer;
	}
	
	
	public Document createXMLDoc(ObservableList<GrandSlam> gSlams) 
			throws ParserConfigurationException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		
		Element root = doc.createElement("SLAMS");
		doc.appendChild(root);

		for(GrandSlam gSlam : gSlams) {
			Element userElm = addElement(root, "slam", "", doc);
			userElm.setAttribute("slamID", gSlam.getSlamID());
			addElement(userElm, "slamName", gSlam.getSlamName(), doc);
			addElement(userElm, "playerID", gSlam.getPlayerID(), doc);
			addElement(userElm, "playerName", gSlam.getPlayerName(), doc);
			addElement(userElm, "playerWinnings", gSlam.getPlayerWinnings(), doc);
		}
		return doc;
	}
	
	public Element addElement(Element parent, String elementName, String textVal, Document doc) {
		Element childElm = doc.createElement(elementName);
		childElm.setTextContent(textVal);
		parent.appendChild(childElm);
		return childElm;
	}
}
