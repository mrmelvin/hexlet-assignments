package exercise.connections;

import exercise.*;

// BEGIN
public class Connected implements Connection {

    private TcpConnection conn;
    private String buffer;

    public Connected() {
        this.conn = conn;
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already connected");
    }

    @Override
    public void disconnect() {
        System.out.println("Connection disabled!");
    }

    @Override
    public void write(String inputData) {
        buffer += inputData;
    }
}
// END
