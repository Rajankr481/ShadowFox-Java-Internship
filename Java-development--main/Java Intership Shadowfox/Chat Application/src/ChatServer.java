import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static final ConcurrentHashMap<String, Set<ClientHandler>> chatRooms = new ConcurrentHashMap<>();
    private static final ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        System.out.println("Chat Server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection from: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                threadPool.execute(clientHandler);
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        } finally {
            threadPool.shutdown();
        }
    }

    public static void joinChatRoom(String roomName, ClientHandler client) {
        chatRooms.computeIfAbsent(roomName, k -> ConcurrentHashMap.newKeySet()).add(client);
        System.out.println(client.getClientName() + " joined room: " + roomName);
    }

    public static void leaveChatRoom(String roomName, ClientHandler client) {
        if (chatRooms.containsKey(roomName)) {
            chatRooms.get(roomName).remove(client);
            System.out.println(client.getClientName() + " left room: " + roomName);
        }
    }

    public static void broadcastMessage(String roomName, String message, ClientHandler sender) {
        if (chatRooms.containsKey(roomName)) {
            for (ClientHandler client : chatRooms.get(roomName)) {
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String clientName;
    private String currentRoom;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Get client name
            out.println("Enter your name:");
            clientName = in.readLine();
            out.println("Welcome, " + clientName + "! Type /help for commands.");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("/join ")) {
                    String roomName = inputLine.substring(6);
                    if (currentRoom != null) {
                        ChatServer.leaveChatRoom(currentRoom, this);
                    }
                    currentRoom = roomName;
                    ChatServer.joinChatRoom(roomName, this);
                    out.println("Joined room: " + roomName);
                }
                else if (inputLine.equals("/leave")) {
                    if (currentRoom != null) {
                        ChatServer.leaveChatRoom(currentRoom, this);
                        out.println("Left room: " + currentRoom);
                        currentRoom = null;
                    }
                }
                else if (inputLine.equals("/help")) {
                    out.println("Commands:\n/join [room] - Join a chat room\n/leave - Leave current room\n/help - Show this help");
                }
                else if (currentRoom != null) {
                    String formattedMessage = "[" + currentRoom + "] " + clientName + ": " + inputLine;
                    ChatServer.broadcastMessage(currentRoom, formattedMessage, this);
                }
                else {
                    out.println("You must join a room first. Use /join [roomname]");
                }
            }
        } catch (IOException e) {
            System.err.println("Client handler exception: " + e.getMessage());
        } finally {
            try {
                if (currentRoom != null) {
                    ChatServer.leaveChatRoom(currentRoom, this);
                }
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String getClientName() {
        return clientName;
    }
}