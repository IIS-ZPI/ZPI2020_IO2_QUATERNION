package com.example;

public class NationalRate{
    String currency;
    String code;
    Double mid;

    @Override
    public String toString() {
        return currency +' '+ code +' '+ mid;
    }
}

