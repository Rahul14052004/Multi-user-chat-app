import java.net.*;


public class server{
    ServerSocket server;
    Socket socket;
    public server(){
        try{
            server =new ServerSocket();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        System.out.println("Server...");

        new server();

    }
}

