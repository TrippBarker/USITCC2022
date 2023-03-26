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

public class GrandSlamReader {
	
	public ArrayList<GrandSlam> readXML() {
		ArrayList<GrandSlam> gSlams = new ArrayList<GrandSlam>();
		File xmlFile = new File("src/application/data/GrandSlamInformation.xml");
		Document doc = null;
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(xmlFile);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		NodeList list = doc.getElementsByTagName("slam");
		GrandSlam gSlam;
		for (int i = 0; i < list.getLength(); i++) {
			gSlam = new GrandSlam();
			gSlams.add(gSlam);
			Element slamElm = (Element)list.item(i);
			String slamID = slamElm.getAttribute("slamID");
			gSlam.setSlamID(slamID);
			gSlam.setSlamName(getTextFromElement(slamElm, "slamName"));
			gSlam.setPlayerID(getTextFromElement(slamElm, "playerID"));
			gSlam.setPlayerName(getTextFromElement(slamElm, "playerName"));
			gSlam.setPlayerWinnings(getTextFromElement(slamElm, "playerWinnings"));
		}
		return gSlams;
	}

	private String getTextFromElement(Element slamElm, String elementName) {
		Element node = (Element) slamElm.getElementsByTagName(elementName).item(0);
		String textContent = node.getTextContent();
		return textContent;
	}
}
