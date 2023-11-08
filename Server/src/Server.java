import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws InterruptedException {

        try( ServerSocket serverSocket = new ServerSocket(6666)) {

            System.out.println("Server started");

            System.out.println("Server waiting for client...");
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client accepted");

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));


            String clientMessage;
            String serverMessage;

            while (true) {
                clientMessage = in.readUTF();
                if(clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client exited");
                    break;
                }
                System.out.println("Client:" + clientMessage);
                System.out.print("Server:");
                serverMessage = consoleReader.readLine();
                out.writeUTF(serverMessage);
                out.flush();

            }

        }
        catch (IOException e) {
        e.printStackTrace();
        }


    }
}
