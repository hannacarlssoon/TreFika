package tda367.myapplication.service;

import java.io.*;
import java.net.*;


/**
 * Created by sarakitzing on 2017-04-27.
 */

//Handles setting up, running, shutting down and connecting with the server which compiles the user input.
public class Server {

    private String userCode;
    private String compiledCode;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String serverIP;
    private Socket connection;

    public Server(String host, String userCode){
        serverIP = host;
        this.userCode = userCode;
    }

    //set up and run server
    public void startRunning(){
        try{
            connectToServer();
            setupStreams();
            whileCompile();

        }catch(EOFException eofException){
            System.out.println("Client terminated connection");

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            shutDown();
        }
    }

    //connect to server
    private void connectToServer() throws IOException{
        System.out.println("Try to connect ...");
        connection = new Socket(InetAddress.getByName(serverIP), 6789);
        System.out.println("Connected to: " + connection.getInetAddress().getHostName());
    }


    //get stream to send and receive data
    private void setupStreams() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        System.out.println("Streams are good to go");
    }

    private void whileCompile() throws IOException {
        System.out.println("You are now connected");

        try {
            output.writeObject(userCode);
            output.flush();
            compiledCode = (String) input.readObject();
            System.out.println("Compiled code: " + compiledCode);
        } catch (IOException io) {

        } catch (ClassNotFoundException classNotFound) {
            System.out.println("Somethings wrong");
        }
    }

    //close streams and sockets
    private void shutDown(){
        System.out.println("Closing down ... ");
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
