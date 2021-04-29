package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.TreeMap;

public abstract class ConnectionConnect {

    private final Map<String, ConnectionCollection> connections;

    protected ConnectionConnect(){
        connections = new TreeMap<String , ConnectionCollection>();
    }

    public final Connection getConnection(ConnectionObject connectionObject){

        String url = connectionObject.getUrl();
        String username = connectionObject.getUsername();
        String password = connectionObject.getPassword();

        Connection connection = null;
        ConnectionCollection connections = getConnectionCollection(url);
        if(connections != null){
            connection = connections.getConnection(username);
            if (connection == null){
                connection = createConnection(url, username, password);
                connections.add(username, connection);
            }
        } else{
            connection = createConnection(url, username, password);
            connections = new ConnectionCollection();
            connections.add(username, connection);
        }
        return connection;
    }

    private Connection createConnection(String uri, String username, String password){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(uri, username,password);
            connection.setAutoCommit(false);
            return connection;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private ConnectionCollection getConnectionCollection(String url){ return connections.get(url); }

}
