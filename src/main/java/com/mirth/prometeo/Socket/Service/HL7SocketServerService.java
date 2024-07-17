package com.mirth.prometeo.Socket.Service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class HL7SocketServerService {

    public void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server in ascolto sulla porta " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Connessione accettata da " + clientSocket.getInetAddress());

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                    String hl7Message = readMessage(in);
                    System.out.println("Ricevuto: " + hl7Message);

                    String responseMessage = processHL7Message(hl7Message);

                    writeMessage(out, responseMessage);
                } catch (Exception e) {
                    System.err.println("Errore di comunicazione: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Errore di avvio del server: " + e.getMessage());
        }
    }

    private String processHL7Message(String hl7Message) {
        return "ACK^" + hl7Message;
    }

    private String readMessage(BufferedReader in) throws Exception {
        StringBuilder responseBuilder = new StringBuilder();
        int character;
        boolean endOfMessage = false;

        do {
            character = in.read();
            if (character != -1) {
                responseBuilder.append((char) character);
                if (responseBuilder.toString().endsWith("\u001c\r")) {
                    endOfMessage = true;
                }
            }
        } while (!endOfMessage && character != -1);

        if (character == -1) {
            System.out.println("End of stream reached.");
        }

        String response = responseBuilder.toString();
        if (response.startsWith("\u000b") && response.endsWith("\u001c\r")) {
            response = response.substring(1, response.length() - 2);
        }
        return response;
    }

    private void writeMessage(PrintWriter out, String hl7Message) {
        String messageWithDelimiters = '\u000b' + hl7Message + '\u001c' + '\r';
        out.print(messageWithDelimiters);
        out.flush();
    }

}
