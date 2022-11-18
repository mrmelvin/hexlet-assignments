package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;
import exercise.connections.Connected;


// BEGIN
public class TcpConnection {
    private String currentStatusConnection;
    private String ipAddress;
    private Integer port;

    TcpConnection(String initialIpAddrress, Integer initialPort) {
        ipAddress = initialIpAddrress;
        port = initialPort;
    }

    public void connect() {
        Connection c = new Connected();
        c.connect();
        currentStatusConnection = c.getCurrentState();
    }

    public void disconnect() {
        Connection c = new Disconnected();
        c.disconnect();
        currentStatusConnection = c.getCurrentState();
    }

    public void write(String inputData) {
        if (currentStatusConnection.equals("connected")) {
            System.out.println("You data will writing");
        } else {
            System.out.println("Error! You disconnect!");
        }
    }

    public String getCurrentState() {
        return currentStatusConnection;
    }

}
// END
