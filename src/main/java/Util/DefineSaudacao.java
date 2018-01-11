package Util;

import java.util.Calendar;

public class DefineSaudacao {
    public static String saudacao() {
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);

        if(hora >= 0 && hora < 12){
            return "Bom dia, ";
        } else if (hora >= 12 && hora < 18){
            return "Boa tarde";
        } else {
            return "Boa noite";
        }
    }
}
