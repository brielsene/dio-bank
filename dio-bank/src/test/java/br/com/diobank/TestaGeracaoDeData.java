package br.com.diobank;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Random;

public class TestaGeracaoDeData {

    @Test
    void teste(){
        Random random = new Random();
        LocalDate hoje = LocalDate.now();

        int anoMin = hoje.getYear()+3;

        int anoMax = hoje.getYear()+8;

        int anoAleatorio = random.nextInt(anoMax - anoMin + 1) + anoMin;

        int mesAleatorio = random.nextInt(12)+1;

        LocalDate localDate = LocalDate.of(anoAleatorio, mesAleatorio, 1);
        String data = localDate.toString();
        String [] dataFormat = data.split("-");
//        for (int i = 0; i < 3; i++){
//
//        }

        StringBuilder dataFinish = new StringBuilder();

        String anoFinish = dataFormat[0].substring(2);

        dataFinish.append(dataFormat[1]+"/"+anoFinish);


    }
}
