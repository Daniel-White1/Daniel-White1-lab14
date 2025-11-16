import java.io.BufferedWriter;
import java.io.PrintWriter;
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
        //Accepts all incoming  connections
        while (true) {
            try {
                //Accepts the connection 
                Socket clientSocket = this.sock.accept();

                (new ClientHandler(clientSocket)).start();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    // creates a class to handle each client in a different thread once the handshake is complete
    private class ClientHandler extends Thread{
        Socket sock;
        public ClientHandler(Socket sock){
            this.sock = sock;
        }

        public void run(){
            PrintWriter out = null; 
            BufferedWriter in = null;
            try {
                out = new PrintWriter(sock.getOutputStream());
                in = new BufferedWriter(new InputStreamReader(sock.getInputStream()));

                //read and echo back to eachother
                while (true) {
                    String msg = in.readLine();
                    //if the mesage is null then close the remote
                    if (msg == null) {
                        break;
                        out.println(msg);
                        out.flush();
                    }
                }

                out.close();
                in.close();
                sock.close();
            } catch (Exception e) {

            }
        }
    }
    
    public void disconnect(){

    }

    public void getConnectedTimes(){

    }
}
