package company;

import java.util.HashMap;
import java.util.Map;

public class PersonFactory {

    public static Map<String, PersonGeneralInfo> peopleByName = new HashMap<>();

    public static PersonGeneralInfo getPerson(String name){
        PersonGeneralInfo person = peopleByName.get(name);

        if(person == null){
            person = new PersonGeneralInfo(name);
            peopleByName.put(name, person);
        }

        return person;
    }

}
