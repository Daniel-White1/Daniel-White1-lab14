import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    //variables to store the socket of a client
    Socket sock;
    public Client (String host, int port){
        //Inputs in the strings and ports into a socket stream
        try{
            this.sock = new Socket(host, port);
        } catch (Exception e){
            System.err.println("Couldn't connect");
            System.exit(1);
        }
    }

    //Simple method to get the Socket
    public Socket getSocket(){
        return this.sock;
    }

    //This sends in the totally secure password for the serversocket to accept
    public void handshake() {
        try{
            //Creates a print writer and inputs in the code
            PrintWriter pw = new PrintWriter(sock.getOutputStream());
            pw.println("12345");
        } catch(Exception e){
            System.err.println("IOException");
            System.exit(1);
        }
    }

    public void request(){
    
    }

    public void disconnect(){

    }
}
