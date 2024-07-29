package it.prometeo.Socket.Service;

import it.prometeo.Configuration.HL7Config;
import it.prometeo.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
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

    public static String sendHL7Message(String hl7Message) throws SocketTimeoutException, IOException {
        try (Socket socket = new Socket(hl7Config.getSocketClientHost(), Integer.parseInt(hl7Config.getSocketClientPortPS()))) {
            socket.setSoTimeout(hl7Config.getSocketClientTimeout());
            util.insertLogRow("Socket Client Pronto Soccorso attiva su porta " +hl7Config.getSocketClientPortPS());

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
        }
        return hl7Message;
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
