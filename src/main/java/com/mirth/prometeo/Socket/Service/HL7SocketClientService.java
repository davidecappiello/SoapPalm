package com.mirth.prometeo.Socket.Service;

import ca.uhn.hl7v2.model.v25.message.ACK;
import com.mirth.prometeo.HL7Config;
import com.mirth.prometeo.ServiceORUR01.Event.MessageEventServiceORUR01;
import com.mirth.prometeo.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

@Service
public class HL7SocketClientService {

    private static HL7Config hl7Config = null;
    private static final Util util = new Util();

    @Autowired
    public HL7SocketClientService(HL7Config hl7Config) {
        HL7SocketClientService.hl7Config = hl7Config;
    }

    public static String sendHL7Message(String hl7Message) throws Exception {
        try (Socket socket = new Socket(hl7Config.getSocketClientHost(), Integer.parseInt(hl7Config.getSocketClientPort()))) {
            socket.setSoTimeout(hl7Config.getSocketClientTimeout());

            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                writeMessage(out, hl7Message);

                return readMessage(in);
            }
        } catch (SocketTimeoutException e) {
            util.insertLogRow("Read timeout occurred.");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static void writeMessage(PrintWriter out, String hl7Message) {
        String messageWithDelimiters = '\u000b' + hl7Message + '\u001c' + '\r';
        out.print(messageWithDelimiters);
        out.flush();
    }

    private static String readMessage(BufferedReader in) throws Exception {
        StringBuilder responseBuilder = new StringBuilder();
        int character = -1;
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
            util.insertLogRow("End of stream reached.");
        }

        String response = responseBuilder.toString();
        if (response.startsWith("\u000b") && response.endsWith("\u001c\r")) {
            response = response.substring(1, response.length() - 2);
        }
        return response;
    }
}
