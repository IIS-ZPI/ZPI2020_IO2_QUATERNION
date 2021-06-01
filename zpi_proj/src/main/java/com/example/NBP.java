package com.example;
import java.util.Arrays;
import java.util.Date;

public class NBP{
    String table;
    String no;
    Date effectiveDate;
    NationalRate[] rates;

    @Override
    public String toString() {
        
        return table +' '+ no +' '+ effectiveDate +' ' + Arrays.toString(rates);
    }
}