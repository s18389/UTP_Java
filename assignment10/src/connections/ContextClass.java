package connections;

import repositiors.GroupRepository;
import repositiors.UserRepository;
import repositories.IGroupRepository;
import repositories.IUserRepository;

import java.sql.Connection;

public class ContextClass {
    private final ConnectionConnect connectionConnect;
    private final ConnectionObject connectionObject;
    private IGroupRepository groupRepository;
    private IUserRepository userRepository;
    private Connection connection;


    public ContextClass(ConnectionConnect connectionManager, ConnectionObject connectionObject) {
        this.connectionConnect = connectionManager;
        this.connectionObject = connectionObject;
    }

    public Connection getConnection(){
        if (connection == null){
            connection = connectionConnect.getConnection(connectionObject);
        }
        return connection;
    }

    public IGroupRepository getGroupRepository(){
        if(groupRepository == null){
            groupRepository = new GroupRepository(this);
        }
        return groupRepository;
    }

    public IUserRepository getUserRepository() {
        if(userRepository == null){
            userRepository = new UserRepository(this);
        }
        return userRepository;
    }
}
