package za.co.codes.bhungane;

import org.json.JSONObject;
import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {

        // Open the "door" on port 5050
        ServerSocket serverSocket = new ServerSocket(5050);
        System.out.println("Server waiting for connection...");

        // Generate the client Socket
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        // Setup reading and writing streams
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        // Read json string sent by client
        String rawJSON = in.readLine();
        System.out.println("Received: " + rawJSON);

        // Parse the raw json
        JSONObject playerData = new JSONObject(rawJSON);
        String playerName = playerData.getString("name");
        int playerX = playerData.getInt("x");
        int playerY = playerData.getInt("y");

        System.out.println(playerName + " is at (" + playerX + ", " + playerY + ")");

        // Send a JSON reponse back
        JSONObject response = new JSONObject();
        response.put("status", "ok");
        response.put("message", "Welcome, " + playerName);

        out.println(response.toString());

        // Close the sockets
        clientSocket.close();
        serverSocket.close();

    }
}
