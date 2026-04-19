package za.co.codes.bhungane;

import org.json.JSONObject;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException{

        // 1. Connect to the server
        Socket socket = new Socket("localhost", 5050);
        System.out.println("Connected to server!");

        // 2. Set up reading and writing streams
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // 3. Build JSON packet with player information
        JSONObject playerData = new JSONObject();
        playerData.put("name", "Thami");
        playerData.put("x", 32);
        playerData.put("y", 9);

        // 4. Send the packet (must be one line!)
        out.println(playerData.toString());
        System.out.println("Sent > " + playerData.toString());

        // 5. Wait for the server's response
        String rawResponse = in.readLine();
        JSONObject response = new JSONObject(rawResponse);
        System.out.println("Server >> " + response.getString("message"));
        System.out.println("Server >> " + "Your status is [" + response.getString("status") + "]");

        // 6. Clean up
        socket.close();

    }
}
