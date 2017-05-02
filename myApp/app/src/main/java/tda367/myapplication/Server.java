package tda367.myapplication;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by sarakitzing on 2017-04-27.
 */

public class Server {

    private String userCode;
    private String compiledCode;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;

    public Server(String userCode){
        this.userCode = userCode;
    }

    //set up and run server
    public void startRunning(){
        try {
            server = new ServerSocket(6789, 100);
            while(true) {
                try {
                    //connect and compile code
                    waitForConnection();
                    setupStreams();
                    whileCompile();

                } catch (EOFException eofException) {
                    System.out.println("Server ended connection");
                } finally {
                    shutDown();
                }
            }
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    //wait for connection, then display connaction information
    private void waitForConnection() throws IOException {
        System.out.println("Waiting for connection... ");
        connection = server.accept();
        System.out.println("Now connected to " + connection.getInetAddress().getHostName());
    }

    //get stream to send and receive data
    private void setupStreams() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        System.out.println("Streams are now setup");
    }

    //TODO send code-input to server, compile and send back
    private void whileCompile() throws IOException{
        System.out.println("You are now connected");

        try{
            output.writeObject(userCode);
            System.out.println("write object");
            //output.flush();
            compiledCode = (String) input.readObject();
        }catch (IOException io){

        }catch (ClassNotFoundException classNotFound){
            System.out.println("Somethings wrong");
        }
     /*
         showMessage(message);
        ableToType(true);
        do {
            try {
                message = (String) input.readObject();
                showMessage("\n" + message);
            } catch (ClassNotFoundException classNotFoundException) {
                showMessage("\n I don't know what that user sent ");
            }
        }while(!message.equals("CLIENT - END"));
         */
    }


    //sends back the result of the compile
/*    private String result() throws IOException{
        String result = "hej";
        try {
            result = (String) input.readObject();
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println("Don't know what the user sent");
        }

        return result;
    }
*/

    //close streams and sockets after its done compiling
    private void shutDown(){
        System.out.println("Closing connections ... ");
        try{
            output.close();
            input.close();
            connection.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    public String getCompiledCode(){
        return this.compiledCode;
    }

}
