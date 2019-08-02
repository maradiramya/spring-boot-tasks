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
    //implementation method to save track
    public Track saveTrack(Track track) {
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    //implementation method to get track using name
    public List<Track> getByName(String name) {
        List<Track> retrivedTrack = trackRepository.findByName(name);
        return retrivedTrack;
    }

    @Override
    //implementation method to get track using id
    public Track getById(int id) {
        Track retrivedTrackById = trackRepository.findById(id).get();
        return retrivedTrackById;
    }

    @Override
    //implementation method to get all tracks
    public List<Track> getAllTrack() {
        List<Track> retriveAllTrack = trackRepository.findAll();
        return retriveAllTrack;
    }

    @Override
    //implementation method to delete track using id
    public Track deleteTrackById(int id) {
        Optional<Track> deletedTrack = trackRepository.findById(id);
        trackRepository.deleteById(id);
        return deletedTrack.get();
    }

    @Override
    //implementation method to update track
    public Track updateTrack(Track track) {
        Track updateTrack = trackRepository.save(track);
        return updateTrack;
    }
}
