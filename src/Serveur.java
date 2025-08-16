import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
  BufferedReader br = new BufferedReader(new InputStreamReader(is));
  PrintWriter pw = new PrintWriter(new OutputStreamWriter(os), true);
  Scanner sc = new Scanner(System.in);
  String msg ;
        do{
            msg = br.readLine();
            System.out.println("lui: "+msg);
            System.out.println("moi : ");
            msg= sc.nextLine();
            pw.println(msg);

        }while(!msg.equals("bye"));
        is.close();
        os.close();
    }
}
