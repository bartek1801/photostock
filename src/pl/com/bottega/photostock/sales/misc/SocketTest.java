package pl.com.bottega.photostock.sales.misc;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.Socket;

public class SocketTest {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("www.pollub.pl", 80);
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.println("GET /pl/uczelnia/o-politechnice HTTP/1.1");
        pw.println("Host: www.pollub.pl");
        pw.print("\r\n");
        pw.flush();
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

}
