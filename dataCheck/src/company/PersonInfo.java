package company;

public class PersonInfo implements IPerson{
    private String surname;
    private static int nextID = 0;
    private final int id;
    private double xCord;
    private double yCord;
    private PersonGeneralInfo personGeneralInfo;

    public PersonInfo(String surname, double x, double y, PersonGeneralInfo personGeneralInfo) {
        this.surname = surname;
        this.xCord = x;
        this.yCord = y;
        this.personGeneralInfo = personGeneralInfo;
        this.id = nextID;
        nextID++;

    }


    public String getName() {
        return personGeneralInfo.getName();
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public void setName(String name) {
        personGeneralInfo.setName(name);
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public double getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public int getId() {
        return id;
    }

    @Override
    public void changeLetters() {
        this.setName(this.getName().substring(0,1).toUpperCase() + this.getName().substring(1).toLowerCase());
        this.setSurname(this.getSurname().substring(0,1).toUpperCase() + this.getSurname().substring(1).toLowerCase());
    }
}
