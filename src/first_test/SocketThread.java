package first_test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketThread extends Thread {
    private Socket socket;
    private int nb;

    public SocketThread(Socket socket, int nb) {
        this.socket = socket;
        this.nb = nb ;                       ;
    }
@Override
    public void run() {
    try {
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(os), true);
        Scanner sc= new Scanner(System.in);
        String msg;
        int mystery_nb;
        pw.println("Welcome dear first_test.Client. Guess the mystery number!!!");

        do{
            mystery_nb= Integer.parseInt(br.readLine());
            if(mystery_nb > nb){
                pw.println("Enter a smaller number");
            } else if (mystery_nb < nb) {
                pw.println("Enter a larger number");
            }else {
                pw.println("Bravo, you found the mystery number:  " + mystery_nb);
            }

        }while(mystery_nb !=nb);

    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}
}
