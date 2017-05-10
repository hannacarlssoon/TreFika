package tda367.myapplication.service;

/**
 * Created by sarakitzing on 2017-05-02.
 */

public class ServerTest {

    public static void main (String[] args){
        StringBuilder sb = new StringBuilder();
                sb.append("for int i=0; i<10; i++ ){ \n" + "print(\"Tjo\");\n" + "}");


        Server server = new Server("127.0.0.1", sb.toString());
        server.startRunning();
    }
}
