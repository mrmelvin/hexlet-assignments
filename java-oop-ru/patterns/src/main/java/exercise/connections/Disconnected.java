package exercise.connections;


import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {

    private TcpConnection conn;
    private String buffer;

    public Disconnected() {
        this.conn = conn;
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
        System.out.println("Connection established!");
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void write(String inputData) {
        System.out.println("Error writing because disconnected");
    }
}
// END
