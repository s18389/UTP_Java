/*
import connections.ConnectionConfiguration;
import dtos.GroupDTO;
import dtos.UserDTO;
import org.junit.Before;
import org.junit.Test;
import repositiors.GroupRepository;
import repositiors.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TEST {

    UserRepository userRepository = new UserRepository();
    GroupRepository groupRepository = new GroupRepository();

        @Test
        public void addUser(){
            UserDTO user1 = new UserDTO( 1, "user1", "a moze podam");
            userRepository.add(user1);
        }

         @Test
         public void addGroup() {
             GroupDTO group1 = new GroupDTO(2, "Group 1B", "I am short description");
             groupRepository.add(group1);
         }

         @Test //NOT WORKING
         public void deleteUser(){
        UserDTO user4 = new UserDTO( 1, "user1", "a moze podam");
        userRepository.delete(user4);
        }

    @Test
    public void deleteGroup() {
        GroupDTO group1 = new GroupDTO(1, "Group 1A", "I am short description");
        groupRepository.delete(group1);
    }

    @Test
    public void updateUser() {
        UserDTO user1 = new UserDTO( 1, "user1", "a jednak podam");
        userRepository.update(user1);
    }

    @Test
    public void updateGroup() {
        GroupDTO group1 = new GroupDTO(1, "Group 1B", "Update");
        groupRepository.update(group1);
    }

    @Test
    public void countUser() {
            System.out.println(userRepository.getCount());
    }

    @Test
    public void countGroup() {
        System.out.println(groupRepository.getCount());
    }

    @Test
    public void findByNameGroup() {
        System.out.println(groupRepository.findByName("Group 1B"));
    }

    @Test
    public void findByNameUser() {
        System.out.println(userRepository.findByName("user_"));
    }

    @Test
    public void findByIdGroup() {
        System.out.println(groupRepository.findById(1));
    }

}


 */