package ru.cubos;

import java.io.*;
import java.net.Socket;

public class PrinterSocketSender {
    String host;
    int port;
    boolean waitingForAnswer;

    public PrinterSocketSender(String host, int port, boolean waitingForAnswer){
        this.host               = host;
        this.port               = port;
        this.waitingForAnswer   = waitingForAnswer;
    }

    public String sendToSocket(String String) throws IOException {
        Socket socket = null;
        String serverAnswer = "";

        try{
            socket = new Socket(host, port);
            socket.setSoTimeout(3000);
            OutputStream output = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output, "cp1251");
            PrintWriter writer = new PrintWriter(outputStreamWriter, true);
            writer.println(String);

            if(waitingForAnswer) {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line;

                while ((line = reader.readLine()) != null) {
                    serverAnswer += line + "\n";
                    if(line.equals("")) break;
                }
            }


        } catch (IOException ex) {
            System.out.println("Server not found: " + ex.getMessage());
            showError(ex.getMessage());
            showError("\n");
        } finally {
            if(socket!=null) socket.close();
        }

        if(socket!=null) socket.close();

        return serverAnswer;
    }


    public void showError(String string){
        System.out.println(string);
    }
}
