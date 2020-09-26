package sample;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//import static java.net.InetAddress.getLocalHost;

public class IPAddressChecker {
    public static String getCurrentIPAddress(){
        /*
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            return "false";
        }
        */
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("google.com", 80));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println();
//        System.out.println("System IP Address : " + (localhost.getHostAddress()));
//        System.out.println("System IP Address : " + socket.getLocalAddress().getHostAddress());
//        return localhost.getHostAddress().trim();
        return socket.getLocalAddress().getHostAddress();
    }
}
