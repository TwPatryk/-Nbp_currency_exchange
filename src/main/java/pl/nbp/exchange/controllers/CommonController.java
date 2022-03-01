package pl.nbp.exchange.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {

    @RequestMapping(value="http://api.nbp.pl/api/exchangerates/rates/a/usd/2022-02-24/2022-03-01/", method = RequestMethod.GET)
    public String currencyExchangeRate() {
        return "redirect:/main";
    }

    @RequestMapping(value="http://api.nbp.pl/api/cenyzlota/2022-02-15/2022-03-01/", method = RequestMethod.GET)
    public String averageGoldPrice() {
        return "redirect:/main";
    }
}

//TODO automatyczne ustawianie dzisiejszej daty i podawanie danych z ostatnich 5 i 14 dni
//TODO algorytm obliczający średnią cenę złota z 14 dni
