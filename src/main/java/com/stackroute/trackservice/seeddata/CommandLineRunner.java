package com.stackroute.trackservice.seeddata;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private TrackRepository trackRepository;

    @Autowired
    public CommandLineRunner(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Track track1 = new Track(44, "rrrr", "bad");
        trackRepository.save(track1);
        Track track2 = new Track(55, "ssss", "good");
        trackRepository.save(track2);


    }
}
