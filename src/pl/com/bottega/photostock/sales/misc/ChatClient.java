package pl.com.bottega.photostock.sales.misc;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 6661;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Imię: ");
        String name = scanner.nextLine();
        Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.println(name);
        pw.flush();
        new Thread(() -> {
            try {
                while (true) {
                    String msg = br.readLine();
                    if (msg == null)
                        return;
                    System.out.println(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }).start();
        while (true) {
            String msg = scanner.nextLine();
            pw.println(msg);
            pw.flush();
        }
    }

}
