import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public ServerSocket sock;
    public Server(int port){
        try{
            this.sock = new ServerSocket(port);
        }catch (Exception e){
            System.err.println("Server Socket Creation Failed");
            System.exit(1);
        }
    }

    public void serve(){

    }

    public void run(){

    }

    public void disconnect(){

    }

    public void getConnectedTimes(){

    }
}
