package tests;

import dtos.GroupDTO;
import dtos.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import repositiors.UserRepository;
import repositories.IUserRepository;

import java.util.ArrayList;
import java.util.List;


public class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {


	@Test
	public void add() {
		System.out.println();
		System.out.println("METHOD: add");

		IUserRepository userRepository = getRepository();
		UserDTO user1 = new UserDTO( 12, "user12", "a moze podam");
		userRepository.add(user1);
		int count = userRepository.getCount();

		UserDTO user2 = new UserDTO( 13, "user13", "a moze podam");
		userRepository.add(user2);
		int expectedCount = count + 1;
		int actualCount = userRepository.getCount();
		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void update() {
		System.out.println();
		System.out.println("METHOD: update");


		IUserRepository userRepository = getRepository();
		String passBefore = "passBEFOREUpdate";
		UserDTO userDTOBefore = new UserDTO(14, "user14" , passBefore);
		userRepository.add(userDTOBefore);
		List<UserDTO> beforeUpdate = userRepository.findByName("user14");

		String passAfter = "passAFTERUpdate";
		UserDTO userAfter = new UserDTO(14, "user14", passAfter);
		userRepository.update(userAfter);
		List<UserDTO> afrerUpdate = userRepository.findByName("user14");

		Assert.assertNotEquals(beforeUpdate.toString(),afrerUpdate.toString());
	}
	
	@Test
	public void addOrUpdate() {
		System.out.println();
		System.out.println("METHOD: addOrUpdate");

		IUserRepository userRepository = getRepository();
		String login = "user1";
		String pass1 = "pass BEFORE update";
		String pass2 = "pass AFTER update";

		int counterBeforeAdd = userRepository.getCount();

		UserDTO user1 = new UserDTO(1, login, pass1);
		UserDTO user2 = new UserDTO(1, login, pass2);

		userRepository.addOrUpdate(user1);

		int counterAfterAdd = userRepository.getCount();

		Assert.assertEquals(counterBeforeAdd, counterAfterAdd - 1 );

		userRepository.addOrUpdate(user2);

		ArrayList<UserDTO> usersList = (ArrayList<UserDTO>) userRepository.findByName(user1.getLogin());
		UserDTO userFounded = usersList.get(0);

		String passwordAfterUpdate = userFounded.getPassword();
		counterAfterAdd = userRepository.getCount();

		Assert.assertEquals(counterBeforeAdd, counterAfterAdd - 1 );
		Assert.assertEquals(pass2, passwordAfterUpdate);
	}

	@Test
	public void delete() {
		System.out.println();
		System.out.println("METHOD: delete");

		IUserRepository userRepository = getRepository();
		UserDTO user15 = new UserDTO(15, "user15", "pass15");
		userRepository.add(user15);

		int counterBeforeDelete = userRepository.getCount();
		userRepository.delete(user15);

		int counterAfterDeletePlusOne  = userRepository.getCount() + 1;

		Assert.assertEquals(counterBeforeDelete, counterAfterDeletePlusOne);
	}

	@Test
	public void findById() {
	}
	
	@Test
	public void findByName() {
		System.out.println();
		System.out.println("METHOD: findByName");

		IUserRepository userRepository = getRepository();
		String name = "user16";
		UserDTO userNew1 = new UserDTO(16, name, "pass");
		userRepository.add(userNew1);

		List<UserDTO> newUsersList = new ArrayList<>();
		newUsersList.add(userNew1);

		List<UserDTO> listUsersFounded =  userRepository.findByName(name);

		Assert.assertEquals(newUsersList.toString(),listUsersFounded.toString());
	}

	@Test
	public void addUserToGroup(){
		IUserRepository userRepository = getRepository();
		UserDTO user = new UserDTO(99, "user99", "password");
		userRepository.addToGroup(user, 666);
	}

	protected IUserRepository getRepositoryFromContextClass() {
		IUserRepository userRepository = getContext().getUserRepository();
		return userRepository;
	}

	@Override
	protected IUserRepository Create() {

		//throw new UnimplementedException();
		return null;
	}
}