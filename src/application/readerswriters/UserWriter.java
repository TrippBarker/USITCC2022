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

import application.User;
import javafx.collections.ObservableList;
import application.Player;
import application.Admin;

public class UserWriter {
	
	public void buildDocument(ObservableList<User> users) 
			throws ParserConfigurationException, TransformerException {
		Document doc = createXMLDoc(users);
		outputAsFile(doc, "src/application/data/Users.xml");
		
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

	public Document createXMLDoc(ObservableList<User> users) 
			throws ParserConfigurationException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		
		Element root = doc.createElement("USERS");
		doc.appendChild(root);

		for(User user : users) {
			Element userElm = addElement(root, "user", "", doc);
			if (user instanceof Admin) {
				userElm.setAttribute("type", "Admin");
			} else if (user instanceof Player) {
				userElm.setAttribute("type", "Player");
			} else {
				userElm.setAttribute("type", "User");
			}
			
			addElement(userElm, "username", user.getUsername(), doc);
			addElement(userElm, "password", user.getPassword(), doc);
			if (user instanceof Player) {
				Player player = (Player) user;
				addElement(userElm, "playerID", player.getPlayerID(), doc);
				addElement(userElm, "fName", player.getFName(), doc);
				addElement(userElm, "lName", player.getLName(), doc);
			} else if (user instanceof Admin) {
				Admin admin = (Admin) user;
				addElement(userElm, "adminPassword", admin.getAdminPassword(), doc);
			}
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
