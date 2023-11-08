import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) throws InterruptedException, IOException {

        try(Socket socket = new Socket("127.0.0.1",6666)) {

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage;
            String clientMessage;


            while (true) {
                System.out.print("Client: ");
                clientMessage = consoleReader.readLine();
                out.writeUTF(clientMessage);
                out.flush();

                serverMessage = in.readUTF();
                System.out.println("Server: " + serverMessage);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Connection was closed");
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}