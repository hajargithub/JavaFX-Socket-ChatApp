import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
 Socket s=new Socket("127.0.0.1",9090);
 OutputStream os=s.getOutputStream();
 InputStream is=s.getInputStream();
 BufferedReader br=new BufferedReader(new InputStreamReader(is));
 PrintWriter pw=new PrintWriter(new OutputStreamWriter(os),true);
 Scanner sc= new Scanner(System.in);
 String msg;
 do{
     System.out.println("Client:  ");
     msg=sc.nextLine();
     pw.println(msg);
     // Si le message envoyé est "bye", on arrête la communication
     if (msg.equals("bye")) {
         break;
     }
     msg=br.readLine();
     System.out.println("Serveur: " + msg);
 }while(!msg.equals("bye"));
        is.close();
        os.close();

    }
}
