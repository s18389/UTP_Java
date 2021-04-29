package repositiors;

import connections.ContextClass;
import dtos.UserDTO;
import repositories.IUserRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends RepositoryBase<UserDTO> implements IUserRepository {

    public UserRepository(ContextClass context){
        super(context);
    }

    @Override
    public List<UserDTO> findByName(String username) {
        ResultSet resultSet = null;
        UserDTO user = new UserDTO();
        ArrayList<UserDTO> usersList = new ArrayList<UserDTO>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT USER_LOGIN, USER_PASSWORD FROM USERS WHERE USER_LOGIN LIKE ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String user_login = resultSet.getString(1);
                String user_password = resultSet.getString(2);
              //  System.out.println(user_login + " " + user_password);
                user = new UserDTO(user_login, user_password);
                usersList.add(user);
            }
            return usersList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void add(UserDTO dto) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (user_login, user_password) VALUES ( ? , ?)");
            preparedStatement.setString(1, dto.getLogin());
            preparedStatement.setString(2, dto.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO users (user_login, user_password) VALUES ( ? , ?)");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addToGroup(UserDTO dto, int group_id) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO GROUP_USERS (GROUP_USERS_ID, USER_LOGIN, GROUP_ID) VALUES ( ? , ? , ?)");
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, dto.getLogin());
            preparedStatement.setInt(3, group_id);
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO GROUP_USERS (GROUP_USERS_ID, USER_LOGIN, GROUP_ID) VALUES ( ? , ? , ?)");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserDTO dto) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE USERS SET " +
                    "USER_LOGIN = ?, USER_PASSWORD = ? WHERE USER_LOGIN = ?");
            preparedStatement.setString(1, dto.getLogin());
            preparedStatement.setString(2, dto.getPassword());
            preparedStatement.setString(3, dto.getLogin());
            preparedStatement.executeUpdate();
            System.out.println("UPDATE USERS SET " +
                    "USER_LOGIN = ?, USER_PASSWORD = ? WHERE USER_LOGIN = ?");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(UserDTO dto) {
        try {

            List<UserDTO> usersList =  findByName(dto.getLogin());
          //  System.out.println(usersList);
            if(usersList.toString().contains(dto.getLogin()) == false){
                System.out.println("User not exist! Adding new one...");
                add(dto);
            }
            else {
                System.out.println("User already exist! Updating existing user...");
                update(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserDTO dto) {
        try{
            Connection connection = getConnection();
        //    String varchar = "'" + dto.getLogin() + "'";
        //    System.out.println(varchar);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE USER_LOGIN=?");
            preparedStatement.setString(1, dto.getLogin());
            preparedStatement.executeUpdate();
            System.out.println("DELETE FROM USERS WHERE USER_LOGIN=?");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int getCount() {
        int counter = 0;
        try{
            Connection connection = getConnection();
            Statement s = connection.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT COUNT(*) AS ROWCOUNT FROM USERS");
            resultSet.next();
            counter = resultSet.getInt("ROWCOUNT");
            resultSet.close();
            System.out.println("SELECT COUNT(*) FROM USERS = " + counter);
            return counter;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

}
