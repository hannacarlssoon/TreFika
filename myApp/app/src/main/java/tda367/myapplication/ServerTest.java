package tda367.myapplication;

import java.io.File;

/**
 * Created by sarakitzing on 2017-05-02.
 */

public class ServerTest {

    public static void main (String[] args){
        StringBuilder sb = new StringBuilder();
                sb.append("for (int i=0; i<10; i++ ) {\n" + "print(\"Hejsan svejsan\");\n" + "}");


        Server s1 = new Server(sb.toString());
        s1.startRunning();
    }
}
