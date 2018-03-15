package pl.com.bottega.photostock.sales.misc;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    InputStream is = socket.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    while (!(line = br.readLine()).trim().equals("")) {
                        System.out.println(line);
                    }
                    OutputStream os = socket.getOutputStream();
                    PrintWriter pw = new PrintWriter(os);
                    pw.println("HTTP/1.1 200 OK");
                    pw.println("Content-Type: text/html; charset=utf-8");
                    pw.print("\r\n");
                    pw.println("<p>Cześć Maciek!!!</p>");
                    pw.println("<p>" + Thread.currentThread().getName() + "</p>");
                    pw.flush();
                    socket.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        }
    }

}
