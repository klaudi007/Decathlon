package com.swedbank.decathlon;

import com.swedbank.decathlon.exceptions.WriteFileException;
import com.swedbank.decathlon.model.Athlete;
import com.swedbank.decathlon.staticData.Event;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

/**
 * Created by Edvis on 2016-12-30.
 */
public class Writer {

    private static final String DTD_TEMPLATE = "classpath:templates/Results.dtd";

    private static final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
    private static final DecimalFormat decimalFormat = new DecimalFormat("#0.00", decimalFormatSymbols);
    private static final DecimalFormat fullDecimalFormat = new DecimalFormat("00.00", decimalFormatSymbols);

    private static volatile Writer writer = null;
    private static final Object LOCK = new Object();
    private static String fileLocation;

    private Writer() {
    }

    /**
     * Initialization of a singleton.
     *
     * @return Writer instance.
     */
    public static Writer getWriter() {
        if (writer == null) {
            synchronized (LOCK) {
                if (writer == null) {
                    writer = new Writer();
                }
            }
        }
        return writer;
    }

    /**
     * Them method exports list of athletes to an output file using XML format.
     *
     * @param athletes calculated and sorted list of athletes.
     * @throws WriteFileException throws an exception if write operation has failed.
     */
    public void writeFileAsXML(List<Athlete> athletes) throws WriteFileException {
        PrintStream printStream = newPrintStream();

        try {
            File dtdFile = new File(DTD_TEMPLATE);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtdFile.getPath());
            transformer.transform(generateXmlDom(athletes), new StreamResult(printStream));
        } catch (TransformerException e) {
            throw new WriteFileException("XML transformer error: " + e.getMessage());
        }
        printStream.close();
    }

    /**
     * The method generates a DOM source that is used to export data to XML format.
     *
     * @param athletes calculated and sorted list of athletes.
     * @return source document.
     * @throws WriteFileException throws an exception if write operation has failed.
     */
    private DOMSource generateXmlDom(List<Athlete> athletes) throws WriteFileException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element rootElement = doc.createElement("decathlon");
            doc.appendChild(rootElement);
            doc.setNodeValue("decathlon");

            for (Athlete a : athletes) {
                Element athleteElement = doc.createElement("athlete");
                rootElement.appendChild(athleteElement);

                //Set attributes
                Attr scoreAttr = doc.createAttribute("score");
                scoreAttr.setValue(new Integer(a.getScore()).toString());
                athleteElement.setAttributeNode(scoreAttr);

                Attr placeAttr = doc.createAttribute("place");
                placeAttr.setValue(a.getPlace());
                athleteElement.setAttributeNode(placeAttr);

                Attr nameAttr = doc.createAttribute("name");
                nameAttr.setValue(a.getName());
                athleteElement.setAttributeNode(nameAttr);

                //Set elements
                Element placeElement = doc.createElement("place");
                placeElement.appendChild(doc.createTextNode(a.getPlace()));
                athleteElement.appendChild(placeElement);

                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(a.getName()));
                athleteElement.appendChild(nameElement);

                Element scoreElement = doc.createElement("score");
                scoreElement.appendChild(doc.createTextNode(new Integer(a.getScore()).toString()));
                athleteElement.appendChild(scoreElement);

                Element resultsElement = doc.createElement("results");

                for (Event event : Event.values()) {
                    Element resultElement = doc.createElement(event.toString().toLowerCase());
                    resultElement.appendChild(doc.createTextNode(getEventsAsString(a, event)));
                    resultsElement.appendChild(resultElement);
                }
                athleteElement.appendChild(resultsElement);
            }
            return new DOMSource(doc);
        } catch (ParserConfigurationException e) {
            throw new WriteFileException(e.getMessage());
        }
    }

    /**
     * The method returns formatted results as String.
     *
     * @param athlete Athlete object with calculated score and place.
     * @param event   a particular event of decathlon.
     * @return event result as String.
     */
    private String getEventsAsString(Athlete athlete, Event event) {
        switch (event) {
            case SPRINT_100M:
            case SPRINT_400M:
            case HURDLES_110M:
            case RACE_1500M:
                String minutes = "";
                if (athlete.getResults().get(event) >= 60) {
                    minutes += (int) (athlete.getResults().get(event) / 60) + ":";
                }
                return minutes + fullDecimalFormat.format(athlete.getResults().get(event) % 60.0);
            case DISCUS_THROW:
            case JAVELIN_THROW:
            case SHOT_PUT:
                return decimalFormat.format(athlete.getResults().get(event));
            case LONG_JUMP:
            case HIGH_JUMP:
            case POLE_VAULT:
                return decimalFormat.format(athlete.getResults().get(event) / (double) 100);
        }
        return null;
    }

    /**
     * The method creates new print stream.
     *
     * @return return new print stream, where to write generated xml dom.
     * @throws WriteFileException throws an exception if write operation has failed.
     */
    private PrintStream newPrintStream() throws WriteFileException {
        try {
            return new PrintStream(new FileOutputStream(fileLocation), true, "UTF-8");
        } catch (FileNotFoundException e) {
            throw new WriteFileException("The file " + "'" + fileLocation + "'" + " was not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new WriteFileException(e.getMessage());
        }
    }

    /**
     * Setter method encapsulates fileLocation field.
     *
     * @param fileLocation string path of output file.
     */
    public void setOutputFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

}
