package pl.com.bottega.photostock.sales.misc;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ChatClient {


    private static final String IP_ADDRESS = "192.168.1.32";
    private static final int PORT = 6661;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj na naszym chacie ");
        System.out.println("Podaj swoje imię: ");
        String name = scanner.nextLine();

        Socket socket = new Socket(IP_ADDRESS, PORT);

        //pisanie wiadomości
        new Thread(() -> {
            try {
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println(name);
                printWriter.flush();
                while (true) {
                    printWriter.println(scanner.nextLine());
                    printWriter.println("wysłano:" + LocalDateTime.now());
                    printWriter.flush();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }).start();

        //czytanie wiadomości innych
        new Thread(() -> {
            try {
                InputStream inputStream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String msgIn;
                while ((msgIn = bufferedReader.readLine()) != null) {
                    System.out.println(msgIn);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }).start();

    }


}
