package company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Terminal {

    private ArrayList<PersonInfo> people = new ArrayList<>();

    public void addPerson(String name, String surname, double x, double y) {
        PersonGeneralInfo personGeneralInfo = PersonFactory.getPerson(name);
        PersonInfo personInfo = new PersonInfo(surname, x, y, personGeneralInfo);
        IPerson personproxy = new PersonProxy(personInfo);
        people.add(personInfo);
    }

    public void editPerson(int index, String name, String surname){
        try {
            IPerson personproxy = new PersonProxy(people.get(index));
            personproxy.setName(name);
            personproxy.setSurname(surname);
        } catch(Exception e) {
            System.out.println("Pod podanym indeksem nikt się nie znajude!");
        }
    }

    public void printToFile(){
        try {
            String path = "zapisane_osoby.txt";
            File file = new File(path);

            if(!file.exists())
                file.createNewFile();

            PrintWriter write = new PrintWriter(path);

            for (PersonInfo personInfo : people) {
                String tmp = personInfo.getId()+". " + personInfo.getName() + " " + personInfo.getSurname() + " [" + personInfo.getxCord() + ", " + personInfo.getyCord()+"]";
                write.println(tmp);
            }
            write.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printToConsole() {
        System.out.println("-------------------------------------------------");
        System.out.println("Lista osób: ");
        System.out.println();
        for (PersonInfo personInfo : people) {
            System.out.println(personInfo.getId()+". " + personInfo.getName() + " " + personInfo.getSurname() + " [" + personInfo.getxCord() + ", " + personInfo.getyCord() + "]");
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Aby kontynuować naciśnij przycisk ...");
        try {
            System.in.read();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

