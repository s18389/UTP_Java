package connections;

import java.sql.Connection;
import java.util.Map;
import java.util.TreeMap;

public final class ConnectionCollection {

    private final Map<String, Connection> connectionsMap;


    public ConnectionCollection() {
        connectionsMap = new TreeMap<String, Connection>();
    }

    public void add(String username, Connection connection){
        if(!connectionsMap.containsKey(username)){
            connectionsMap.put(username, connection);
        }
    }

    public Connection getConnection(String username){
        Connection connection = null;
        if(!connectionsMap.containsKey(username)){
            connection = connectionsMap.get(username);
        }
        return connection;
    }
}
