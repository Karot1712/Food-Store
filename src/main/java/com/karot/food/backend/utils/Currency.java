package com.karot.food.backend.utils;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class Currency {
    public String formatCurrency(BigDecimal price){
        Locale locale = Locale.of("vi","VN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        return currencyFormat.format(price);
    }

}
