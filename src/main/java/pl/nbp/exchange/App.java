package pl.nbp.exchange;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;
import pl.nbp.exchange.dataProcess.CurrencyExchange;
import pl.nbp.exchange.dataProcess.GoldPrice;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        showMainMenu();


    }
    static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Select: ");
        System.out.println("1. Currency exchange (USD) for the last 5 buisness days");
        System.out.println("2. Average gold price for the las 14 buisness days");
        System.out.println("3. Exit");
        System.out.println();

        switch(scanner.nextInt()) {
            case 1:
                CurrencyExchange currencyExchange = new CurrencyExchange();
                try {
                    currencyExchange.showCurrencyExchange();
                    showMainMenu();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                GoldPrice goldPrice = new GoldPrice();
                try {
                    goldPrice.averageGoldPrice();
                    showMainMenu();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Select a number");
                showMainMenu();
        }
    }
}
