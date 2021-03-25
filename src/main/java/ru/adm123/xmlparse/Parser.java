package ru.adm123.xmlparse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Тестовое задание по парсингу xml
 *
 * @author Dmitry Ushakov on 24.03.21
 */
public class Parser {

    private static final Logger logger;
    private static final Color color;
    private static final XPath xpath;
    private static final DocumentBuilder documentBuilder;
    private static final Document document;
    private static final Transformer transformer;

    static {
        logger = LogManager.getLogger("LOGGER_FILE");
        color = new Color(255, 0, 0);
        xpath = XPathFactory.newInstance().newXPath();
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse("./src/main/resources/source_file.xml");
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (FileNotFoundException e) {
            logger.error("input file not found");
            throw new RuntimeException();
        } catch (ParserConfigurationException e) {
            logger.error("ParserConfigurationException");
            throw new RuntimeException();
        } catch (SAXException e) {
            logger.error("SAXException");
            throw new RuntimeException();
        } catch (IOException e) {
            logger.error("IOException");
            throw new RuntimeException();
        } catch (TransformerConfigurationException e) {
            logger.error("TransformerConfigurationException");
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        try {
            String xPathExpression = ".//Neutral[.//Origin]";
            NodeList nodes = (NodeList) xpath.evaluate(xPathExpression, document, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                setNodeColor(nodes.item(i));
            }
            transformer.transform(new DOMSource(document), new StreamResult(new File("./results/target_file.xml")));
        }  catch (XPathExpressionException e) {
            logger.error("wrong xPath expression");
        } catch (TransformerException e) {
            logger.error("TransformerException");
        }
    }

    private static void setNodeColor(Node neutral) throws XPathExpressionException {
        if (neutral != null) {
            String xPathExpression = "Actor.Color";
            Element oldColorNode = (Element) xpath.evaluate(xPathExpression, neutral, XPathConstants.NODE);
            Element colorNode = oldColorNode == null ? document.createElement("Actor.Color") : oldColorNode;
            color.setToElement(colorNode);
            if (oldColorNode != null) {
                neutral.replaceChild(colorNode, oldColorNode);
                logger.info("replace color for node");
            } else {
                neutral.appendChild(colorNode);
                logger.info("add color node");
            }
        }
    }

}
