package domain.models;

public class contact {
    private String name;
    private long number;

    public contact(String name, long number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public long getNumber() {
        return number;
    }
}
