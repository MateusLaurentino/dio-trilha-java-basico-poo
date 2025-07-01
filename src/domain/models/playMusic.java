package domain.models;

public class playMusic {
    private Integer identifier;
    private String name;
    private String artist;
    private double time;

    public playMusic(Integer identifier, String name, String artist, double time) {
        this.identifier = identifier;
        this.name = name;
        this.artist = artist;
        this.time = time;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public double getTime() {
        return this.time;
    }
}
