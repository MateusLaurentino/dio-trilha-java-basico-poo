package domain.models;

public class playMedia {
    private String name;
    private Integer identifierMusic;
    private boolean playing;

    public playMedia(Music music) {
        this.name = music.getName();
        this.identifierMusic = music.getIdentifier();
        this.playing = true;
    }

    public String getName() {
        return name;
    }

    public int getIdentifierMusic() {
        return  identifierMusic;
    };

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying() {
        this.playing = !this.playing;
    }
}
