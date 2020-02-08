package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) throws
            IOException,
            SAXException,
            TransformerException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input phone number to replace: ");
        String newNumber = scanner.nextLine();
        System.out.println("Input file path: ");
        String path = scanner.nextLine();
        System.out.println("Input file name (xml): ");
        String fileName = scanner.nextLine();
        try {
        File sourceXml = new File(path + "\\" + fileName + ".xml");
        File newXmlFile = new File(path + "\\newFile.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(sourceXml);
        document.getDocumentElement().normalize();
        System.out.println("Root element :" + document.getDocumentElement().getNodeName());
        //TASK AREA
            // create a list of xml nodes
        NodeList nTask = document.getElementsByTagName("cdm:Attribute");
        for (int i = 0; i < nTask.getLength(); i++) {
            //iterate through each node from the nodelist
            Node nNTask = nTask.item(i);
            //System.out.println("\nCurrent Element: " + nNTask.getNodeName());
            //checking if the nodeType is an element node (the actual status)
            if (nNTask.getNodeType() == Node.ELEMENT_NODE) {
                Element eElementTask = (Element) nNTask;
                //if matches any number
                String content = eElementTask.getElementsByTagName("cdm:value").item(0).getTextContent();
//                String regex = "[0-9]+";
//                or
//                String regex = "\\d+";
                if (content.matches("[0-9]+")){
                    System.out.println("Numarul vechi care trebuie inlocuit: " + content);
                    //set the status from E to I (initiate)
                    eElementTask.getElementsByTagName("cdm:value").item(0).setTextContent(newNumber);
//                    eElementTask.getElementsByTagName("ns2:status").item(0).setNodeValue("I");
                    System.out.println("Numarul schimbat este: " +
                            eElementTask.getElementsByTagName("cdm:value")
                                    .item(0)
                                    .getTextContent());
                    // writing the content into xml file
                    TransformerFactory factory = TransformerFactory.newInstance();
                    Transformer transformer = factory.newTransformer();
                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(new File(String.valueOf(newXmlFile)));
                    transformer.transform(source, result);
                    System.out.println("Done");
                    }
                }
            }
        }
        catch (ParserConfigurationException e) {
        e.printStackTrace(); }
        }
    }
