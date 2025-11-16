import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    //variables to store the socket of a client
    Socket sock;
    private PrintWriter out;
    private BufferedReader in;
    public Client (String host, int port){
        //Inputs in the strings and ports into a socket stream
        try{
            this.sock = new Socket(host, port);
            this.out = new PrintWriter(sock.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
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
            out.println("12345");
        } catch(Exception e){
            System.err.println("IOException");
            System.exit(1);
        }
    }

    //requests takes in a integer and
    public String request(String msg){
        String reply = "";
        try {
            out.println(msg);
            out.flush();

            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            reply = in.readLine();
        } catch (Exception e) {
            System.err.println(e);
        }
        return reply;
    }

    public void disconnect(){
        try{
            //Try catch block to close each input output and socket
            if (out != null) {
                out.close();
            }

            if (in != null) {
                in.close();
            }

            if (sock != null) {
                sock.close();
            }
        } catch (Exception e){
            System.err.println(e);
        }
    }
}
