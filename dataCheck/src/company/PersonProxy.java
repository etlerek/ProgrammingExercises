package company;

public class PersonProxy implements IPerson{

    private PersonInfo personInfo;

    public PersonProxy(PersonInfo personInfo){
        this.personInfo = personInfo;
        changeLetters();
    }

    @Override
    public void setName(String name) {
        personInfo.setName(name);
        changeLetters();
    }

    @Override
    public void setSurname(String surname) {
        personInfo.setSurname(surname);
        changeLetters();
    }

    @Override
    public void changeLetters() {
        personInfo.changeLetters();
    }
}
