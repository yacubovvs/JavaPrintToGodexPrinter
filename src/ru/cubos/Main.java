package ru.cubos;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	    PrinterSocketSender printerSocketSender = new PrinterSocketSender("10.0.0.162", 9100, false);

        try {
            //Example of label for Godex printers
            printerSocketSender.sendToSocket("^W90\r^Q60,3\r^S4\r^H19\r" +
                    "^L\rDy2-me-dd\rTh:m:s\r" +
                    "^P1\r^AT\r^C1\r^R0\r~Q+0\r^O0\r^D0\r^E12\r~R200\r" +
                    "^XSET,ROTATION,0\r" +
                    "^XSET,CODEPAGE,16\r" +
                    "BU,64,146,6,6,200,0,0,00{sscc}\r" +
                    "AG,64,400,1,1,0,0,(00){sscc}\r" +
                    "AD,64,623,1,1,0,0,Вместимость: 192 уп.\r" +
                    "E\r");

        } catch (IOException e) {
            printerSocketSender.showError("Sending error");
            e.printStackTrace();
        }
    }
}
