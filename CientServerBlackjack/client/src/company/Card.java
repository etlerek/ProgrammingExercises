package company;

public class Card implements ICard{

    private String name;
    private int value;
    private String color;

    public Card(String name, int value, String color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    public String getColor() {
        return color;
    }

    @Override
    public void printCard() {
        System.out.println(name + " of " + color);
    }
}
