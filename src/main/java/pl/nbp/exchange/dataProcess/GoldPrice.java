package pl.nbp.exchange.dataProcess;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoldPrice {

    public void averageGoldPrice() throws ParserConfigurationException, IOException, SAXException {

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = subtractDaysSkippingWeekends(startDate, 13);
        System.out.println("Today's date: " + startDate);
        System.out.println("Indicated date: " +endDate);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://api.nbp.pl/api/cenyzlota/")
                .append(endDate).append("/")
                .append(startDate)
                .append("?format=xml");
        System.out.println("Link to the page: " + stringBuilder);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new URL(stringBuilder.toString()).openStream());
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement();
        //System.out.println(root.getNodeName());


        NodeList nList = document.getElementsByTagName("CenaZlota");
        System.out.println("=============Gold Price===============");
        List<Double> list = new ArrayList<>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            //System.out.println("");
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                System.out.print(" Price : "   + eElement.getElementsByTagName("Cena").item(0).getTextContent());
                System.out.println("  || Date : "   + eElement.getElementsByTagName("Data").item(0).getTextContent());
                list.add(Double.valueOf(eElement.getElementsByTagName("Cena").item(0).getTextContent()));


            }
        }
        //System.out.println(list);
        Double srednia = 0.0;
        for(Double liczba : list) {
            srednia += liczba;
        }
        srednia = srednia / list.size();
        System.out.println();
        System.out.printf("Average Price of gold is : " + "%.2f", srednia);

    }

    public static LocalDate subtractDaysSkippingWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int subtractedDays = 0;
        while (subtractedDays < days) {
            result = result.minusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++subtractedDays;
            }
        }
        return result;
    }


}
