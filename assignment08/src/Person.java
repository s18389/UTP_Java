import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;
import java.util.Objects;

public class Person implements Comparable<Person> {

	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;
	private static int _id = 0;
	private final int person_id;
	
	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
		person_id = _id++;
	}

	public Date get_birthdate() { return _birthdate; }
	public String get_firstName() { return _firstName; }
	public String get_surname() { return _surname; }
	public static int getId(){
		return _id;
	}

	public static void set_id(int _id){
		Person._id = _id;
	}

	@Override
	public int compareTo(Person otherPerson) {
		int a = this._surname.compareTo(otherPerson.get_surname());
		if(a!=0) return a;
		int b = this._firstName.compareTo(otherPerson.get_firstName());
		if(b!=0) return b;
		return this._birthdate.compareTo(otherPerson._birthdate);
	}

	@Override
	public String toString() {
		return  _firstName + " " + _surname + " " + _birthdate + "\n";
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		else if(o== null || getClass()!=o.getClass()) return false;
		Person p1 = (Person) o;
		return 	_id == p1._id &&
				Objects.equals(_firstName, p1._firstName) &&
				Objects.equals(_surname, p1._surname) &&
				Objects.equals(_birthdate, p1._birthdate);
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {
		// serialize birth date with getTime() method  ??????
        try {
            output.writeUTF(_firstName);
            output.writeUTF(_surname);
            InputParser.serialize(_birthdate, output);
        } catch (Throwable e){ throw new Assignment08Exception(e); }
	}

	public static Person deserialize(DataInputStream input) throws Assignment08Exception {
	    try{
	        String firstName = input.readUTF();
	        String surname = input.readUTF();
	        Date birthDate = InputParser.deserialize(input);
	        return new Person(firstName, surname, birthDate);
        }catch (Throwable e){ throw new Assignment08Exception(e); }
	}

}