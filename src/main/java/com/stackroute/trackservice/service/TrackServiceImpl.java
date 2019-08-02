package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public TrackServiceImpl() {
    }

    @Override
    public Track saveTrack(Track track) {
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public Track getById(int id) {
        Track retrivedTrack = trackRepository.findById(id).get();
        return retrivedTrack;
    }

    @Override
    public List<Track> getAllTrack() {
        List<Track> retriveAllTrack = trackRepository.findAll();
        return retriveAllTrack;
    }

    @Override
    public Track deleteTrackById(int id) {
        Optional<Track> deletedTrack = trackRepository.findById(id);
        trackRepository.deleteById(id);
        return deletedTrack.get();
    }

    @Override
    public Track updateTrack(Track track) {
        Track updateTrack = trackRepository.save(track);

        return updateTrack;
    }
}
