import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    private ArrayList<LocalDateTime> connectedTimes = new ArrayList<>();

    public ArrayList<LocalDateTime> getConnectedTimes(){
        return connectedTimes;
    }

    public void serve(int a){
        //Accepts all incoming  connections
        int i = 0;
        while (i < a) {
            try {
                //Accepts the connection 
                Socket clientSocket = this.sock.accept();

                //Add the time to the array of times
                connectedTimes.add(LocalDateTime.now());

                (new ClientHandler(clientSocket)).start();
            } catch (Exception e) {
                // TODO: handle exception
            }
            i++;
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
            BufferedReader in = null;
            try{
                out = new PrintWriter(sock.getOutputStream());
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

                //read and echo back forever!
                while(true){
                    String msg = in.readLine();
                    if(msg == null) break; //read null, remote closed
                    out.println(msg);
                    out.flush();
                }

                out.close();
                in.close();
                sock.close();
            } catch (Exception e){

            }
        }
    }
    public void disconnect(){
        try {
            sock.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
