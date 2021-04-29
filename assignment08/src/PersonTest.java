import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class PersonTest {

	private final File inputPerson = new File("inputPerson.txt");
	private final File outputPerson = new File("outputPerson.odt");

	@Test
	public void serializeAndDeserialize() throws Throwable{

		Person person = InputParser.parsePerson(inputPerson);

		//serialization
		OutputStream output = new FileOutputStream(outputPerson);
		DataOutputStream outputContent = new DataOutputStream(output);
		person.serialize(outputContent);
		outputContent.close();
		System.out.println(person.toString());

		//deserialize
		InputStream input = new FileInputStream(outputPerson);
		DataInputStream inputContent = new DataInputStream(input);
		Person deseializedPerson = Person.deserialize(inputContent);
		inputContent.close();
		System.out.println(deseializedPerson.toString());

		Assert.assertNotNull(deseializedPerson);
		Assert.assertEquals(person, deseializedPerson);
	}
}