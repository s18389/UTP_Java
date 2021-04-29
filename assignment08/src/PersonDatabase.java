import javax.jws.Oneway;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class PersonDatabase {

	private static final Comparator<Person> SortByFirstNameComparator = new FirstNameComparator();
	private static final Comparator<Person> SortByBirthDateComparator = new BirthdateComparator();
	private static final Comparator<Person> SortBySurnameFirstNameBirthDateComparator = Comparator.naturalOrder();

	private final List<Person> SurnameFirstNameBirthDate;
	private final List<Person> BirthDate;
	private final List<Person> FirstName;
	private final long size;
	private List<Person> listToPrint;

    private final Map<Date, List<Person>> SearchForBirthDate;

	public PersonDatabase(File file) throws IOException {
		this(InputParser.parse(file));
	}

	public PersonDatabase(List<Person> list){
		SurnameFirstNameBirthDate = list;
		SurnameFirstNameBirthDate.sort(SortBySurnameFirstNameBirthDateComparator);

		FirstName = new ArrayList<>(list);
		FirstName.sort(SortByFirstNameComparator);

		BirthDate = new ArrayList<>(list);
		BirthDate.sort(SortByBirthDateComparator);

		SearchForBirthDate = list.stream().collect(Collectors.groupingBy(Person::get_birthdate));
		size = list.size();

		this.listToPrint = list;
	}

	public List<Person> sortedByFirstName() { return FirstName; }
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() { return SurnameFirstNameBirthDate; }
	
	public List<Person> sortedByBirthdate() { return BirthDate; }

    public long size() { return size; }
	
	public List<Person> bornOnDay(Date date) {
	//	List<Person> PersonListByDate = new ArrayList<Person>();
			if (SearchForBirthDate != null){
				if (SearchForBirthDate.containsKey(date)){
				//	System.out.println(SearchForBirthDate.get(date));
					return new ArrayList<Person>(SearchForBirthDate.get(date));
				}
				else System.out.println("There is no people born at this date!");
			}
			else System.out.println("The list is empty!");
			//System.out.println(PersonListByDate);
	//	if(SearchForBirthDate.containsKey(date)) PersonListByDate.add(SearchForBirthDate.get(Person));
	//	return PersonListByDate;
		return null;
	}

	// assignment 8 - factory method based on deserialization
	public static PersonDatabase deserialize(DataInputStream input) throws Assignment08Exception {
	    try{
	        int size = input.readInt();
	        List<Person> personList = new ArrayList<>();
	        for (int i = 0; input.available() > 0 && i < size; i++){
	            Person person = Person.deserialize(input);
                personList.add(person);
            }
	        return new PersonDatabase(personList);
        } catch (Throwable e){ throw new Assignment08Exception(e); }
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {
	    try {
	        output.writeInt(sortedBySurnameFirstNameAndBirthdate().size());
	        sortedBySurnameFirstNameAndBirthdate().forEach(person -> { try { person.serialize(output); } catch (Assignment08Exception e) { e.printStackTrace(); } });
        }catch (Throwable e){ throw new Assignment08Exception(e); }
	}

    @Override
    public String toString() {
        return listToPrint.toString();
    }

}