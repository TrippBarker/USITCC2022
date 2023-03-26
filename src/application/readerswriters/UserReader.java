package application.readerswriters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import application.*;

public class UserReader { 
	
	public ArrayList<User> readXML() {
		ArrayList<User> usersAL = new ArrayList<User>();
		File xmlFile = new File("src/application/data/Users.xml");
		Document doc = null;
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(xmlFile);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		NodeList list = doc.getElementsByTagName("user");
		User user;
		for (int i = 0; i < list.getLength(); i++) {
			Element userElm = (Element)list.item(i);
			switch (userElm.getAttribute("type")) {
			case "Admin":
				user = new Admin();
				break;
			case "Player":
				user = new Player();
				break;
			default:
				user = new User();
			}
			user.setUsername(getTextFromElement(userElm, "username"));
			user.setPassword(getTextFromElement(userElm, "password"));
			if (user instanceof Admin) {
				((Admin) user).setAdminPassword(getTextFromElement(userElm, "adminPassword"));
			} else if (user instanceof Player) {
				((Player) user).setPlayerID(getTextFromElement(userElm, "playerID"));
				((Player) user).setFName(getTextFromElement(userElm, "fName"));
				((Player) user).setLName(getTextFromElement(userElm, "lName"));
			}
			usersAL.add(user);
		}
		return usersAL;
	}

	private String getTextFromElement(Element slamElm, String elementName) {
		Element node = (Element) slamElm.getElementsByTagName(elementName).item(0);
		String textContent = node.getTextContent();
		return textContent;
	}
}
