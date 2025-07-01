package domain.services;

import domain.models.playMedia;

import java.util.Optional;

public interface music {
    void musicPlaying();
    void findArtist();
    void playPause();
    void stop();
    void find();
    void next();
    void previous();
    void select(Optional<Integer> identifier);
    void run();
}
