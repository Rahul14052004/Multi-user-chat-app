import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;

public class client {
    Socket socket;
    BufferedReader br;
    PrintWriter pw;
    JFrame main_frame;
    JPanel chat_panel;
    JLabel label1,label2;
    JTextField text_box;
    public client(){
        try{
            main_frame=new JFrame("Chat");
            chat_panel= new JPanel();
            chat_panel.setBounds(0,100,100,100);
            chat_panel.setBackground(Color.gray);
            label1= new JLabel("Request sent to server");
            label1.setBounds(20,20,100,30);
            socket = new Socket("127.0.0.1",7777);
            label2= new JLabel("Connected");
            label2.setBounds(20,40,100,30);
            main_frame.add(label1);main_frame.add(label2);

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            pw=new PrintWriter(socket.getOutputStream());
            
            begin_reading();
            begin_writing();






            main_frame.add(chat_panel,BorderLayout.CENTER);
            main_frame.setSize(100,200);
            main_frame.setVisible(true);
            main_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


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
