import java.io.*;
import java.net.*;

public class client {
    Socket socket;
    BufferedReader br;
    PrintWriter pw;
    public client(){
        try{
            System.out.println("Sending request to server");
            socket = new Socket("127.0.0.1",7777);
            System.out.println("Connected");

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            pw=new PrintWriter(socket.getOutputStream());

            begin_reading();
            begin_writing();
        }catch(Exception e){
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
        System.out.println("Client...");

        new client();
    }

}

