import java.net.*;
import java.io.*;


public class server{
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter pw;
    public server(){
        try{
            server =new ServerSocket(7777);
            System.out.println("Ready to accept connection!");
            System.out.println(".......");
            socket=server.accept();
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            pw=new PrintWriter(socket.getOutputStream());

            begin_reading();
            begin_writing();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void begin_reading(){
        Runnable r1=()->{
            System.out.println("Reading...");
            while(true){
                try{
                String message=br.readLine();
                if(message.equals("finish.")){
                    System.out.println("Client terminated.");
                    break;
                }
                System.out.println("User2:"+message);
            }
            catch(Exception e){
                e.printStackTrace();
            }

        
        }
    };
    new Thread(r1).start();
}
    public void begin_writing(){
        Runnable r2=()->{
            while(true){
                try{
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    String content= br1.readLine();
                    pw.println(content);
                    pw.flush();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        new Thread(r2).start();
    }

    public static void main(String args[]){
        System.out.println("Server...");

        new server();

    } 
}

