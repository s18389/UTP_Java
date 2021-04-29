package tests;

import dtos.GroupDTO;
import org.junit.Assert;
import org.junit.Test;
import repositories.IGroupRepository;

import java.util.ArrayList;
import java.util.List;


public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

	@Test
	public void add() {
		System.out.println();
		System.out.println("METHOD: add");

		IGroupRepository groupRepository = getRepository();
		GroupDTO group1 = new GroupDTO(3, "Group 1B", "I am short description");
		groupRepository.add(group1);
		int count = groupRepository.getCount();

		GroupDTO group = new GroupDTO(4, "Group 1C", "I am short description");
		groupRepository.add(group);
		int expectedCount = count +1;
		int actualCount = groupRepository.getCount();

		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void update() {
		System.out.println();
		System.out.println("METHOD: update");

		IGroupRepository groupRepository = getRepository();
		String nameBefore = "NameBEFOREUpdate";
		GroupDTO groupBefore = new GroupDTO(7, nameBefore , "I am short description");
		groupRepository.add(groupBefore);
		String nameBeforeUpdate = groupRepository.findById(7).getName();
		System.out.println(nameBeforeUpdate);

		String nameAfter = "NameAFTERUpdate";
		GroupDTO groupAfter = new GroupDTO(7, nameAfter, "I am very short description");
		groupRepository.update(groupAfter);

		String nameAfterUpdate = groupRepository.findById(7).getName();
		System.out.println(nameAfterUpdate);

		Assert.assertNotEquals(nameBefore, nameAfter);
	}
	
	@Test
	public void addOrUpdate() {
		System.out.println();
		System.out.println("METHOD: addOrUpdate");

		IGroupRepository groupRepository = getRepository();
		String nameOfGroup = "Group 1A";
		String descriptionGroupFirst = "Description BEFORE update";
		String descriptionGroupSecond = "Description AFTER update";

		int counterBeforeAdd = groupRepository.getCount();

		GroupDTO group1 = new GroupDTO(1, nameOfGroup, descriptionGroupFirst);
		GroupDTO group2 = new GroupDTO(1, nameOfGroup, descriptionGroupSecond);

		groupRepository.addOrUpdate(group1);

		int counterAfterAdd = groupRepository.getCount();
		Assert.assertEquals(counterBeforeAdd, counterAfterAdd - 1 );

		groupRepository.addOrUpdate(group2);

		GroupDTO groupFounded = getRepository().findById(1);
		String descriptionGroupFounded = groupFounded.getDescription();
		counterAfterAdd = groupRepository.getCount();

		Assert.assertEquals(counterBeforeAdd, counterAfterAdd - 1 );
		Assert.assertEquals(descriptionGroupSecond, descriptionGroupFounded);
	}

	@Test
	public void delete() {
		System.out.println();
		System.out.println("METHOD: delete");

		IGroupRepository groupRepository = getRepository();
		GroupDTO group1 = new GroupDTO(29, "Group 1A", "I am short description");
		groupRepository.add(group1);

		int counterDelete = groupRepository.getCount();
		groupRepository.delete(group1);

		int counterAfterDeletePlusOne  = groupRepository.getCount() + 1;

		Assert.assertEquals(counterDelete, counterAfterDeletePlusOne);
	}

	@Test
	public void findById() {
		System.out.println();
		System.out.println("METHOD: findById");

		IGroupRepository groupRepository = getRepository();
		int idToFind = 10;
		GroupDTO groupNew = new GroupDTO(idToFind, "Group 1A", "I am short description");
		groupRepository.add(groupNew);

		GroupDTO groupFounded = groupRepository.findById(idToFind);
//		System.out.println(groupFounded);

		Assert.assertEquals(groupNew.getId(), groupFounded.getId());
		Assert.assertEquals(groupNew.getName(), groupFounded.getName());
		Assert.assertEquals(groupNew.getDescription(), groupFounded.getDescription());

	}
	
	@Test
	public void findByName() {
		System.out.println();
		System.out.println("METHOD: findByName");

		IGroupRepository groupRepository = getRepository();
		String nameGroup = "Group 1E";
		GroupDTO groupNew1 = new GroupDTO(28, nameGroup, "I am short description");
		GroupDTO groupNew2 = new GroupDTO(29, nameGroup, "I am short description");
		groupRepository.add(groupNew1);
		groupRepository.add(groupNew2);
		List<GroupDTO> newGroupsList = new ArrayList<>();
		newGroupsList.add(groupNew1);
		newGroupsList.add(groupNew2);

		List<GroupDTO> listGroupsFounded =  groupRepository.findByName(nameGroup);
		System.out.println(newGroupsList);
		System.out.println(listGroupsFounded);

		Assert.assertEquals(newGroupsList.toString(),listGroupsFounded.toString());
	}

	protected IGroupRepository getRepositoryFromContextClass(){
		IGroupRepository groupRepository = getContext().getGroupRepository();
		return groupRepository;
	}

	@Override
	protected IGroupRepository Create() {
		//throw new UnimplementedException();
		return null;
	}
}