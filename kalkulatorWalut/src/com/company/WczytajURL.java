package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class WczytajURL {

    //tworzenie singletona
    static WczytajURL wczytajURL = null;

    //konstruktor
    private WczytajURL() {

    }

    public ArrayList<Waluta> odswiez(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = Objects.requireNonNull(db).parse(new URL("https://www.nbp.pl/kursy/xml/lasta.xml").openStream());
        } catch (UnknownHostException e){
            System.out.println("Błąd pobierania adresu URL, sprawdź połączenie sieciowe");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        Objects.requireNonNull(doc).getDocumentElement().normalize();

        NodeList list = doc.getElementsByTagName("pozycja");
        Waluta[] waluty = new Waluta[list.getLength()+1];;

        for (int i = 0; i < list.getLength(); i++) {

            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                String nazwaWaluty = element.getElementsByTagName("nazwa_waluty").item(0).getTextContent();
                String przelicznik = element.getElementsByTagName("przelicznik").item(0).getTextContent();
                String kodWaluty = element.getElementsByTagName("kod_waluty").item(0).getTextContent();
                String kursSredni = element.getElementsByTagName("kurs_sredni").item(0).getTextContent();

                waluty[i] = new Waluta(nazwaWaluty, przelicznik, kodWaluty, kursSredni);
            }
        }
        waluty[waluty.length-1] = new Waluta("Złoty", "1", "PLN", "1.0");
        return new ArrayList<Waluta>(Arrays.asList(waluty));
    }

    public static WczytajURL getInstance(){
        if (wczytajURL == null){
            wczytajURL = new WczytajURL();
        }
        return wczytajURL;
    }
}
