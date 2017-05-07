package tda367.myapplication;

import java.io.*;
import java.net.*;


/**
 * Created by sarakitzing on 2017-04-27.
 */

public class Server {

    private String userCode;
    private File compiledCode;
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
            // while(true) {
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
            // }
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    //wait for connection, then display connaction information
    private void waitForConnection() throws IOException {
        System.out.println("Waiting for connection ... ");
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

    private void whileCompile() throws IOException {
        System.out.println("You are now connected");

        try {
            output.writeObject(userCode);
            output.flush();
            compiledCode = (File) input.readObject();
            System.out.println(compiledCode);
        } catch (IOException io) {

        } catch (ClassNotFoundException classNotFound) {
            System.out.println("Somethings wrong");
        }
    }

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

   /* public File getCompiledCode(){
        return this.compiledCode;
    }
*/
}
