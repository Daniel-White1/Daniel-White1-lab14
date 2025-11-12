import java.net.Socket;

public class Client {
    //variables to store the socket of a client
    Socket sock;
    public Client (String host, int port){
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

    public void handshake() {

    }

    public void request(){
    
    }

    public void disconnect(){
        
    }
}
