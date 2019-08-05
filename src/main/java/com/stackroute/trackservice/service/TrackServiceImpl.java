package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackNotFoundException;
import com.stackroute.trackservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary

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
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("track already exists");
        }
        Track savedUser = trackRepository.save(track);
        return savedUser;
    }

    @Override
    //implementation method to get track using name
    public List<Track> getByName(String name) throws TrackNotFoundException {
        if (trackRepository.findByName(name).isEmpty()) {
            throw new TrackNotFoundException("Track not found");
        } else {
            List<Track> retriveByName = trackRepository.findByName(name);
            return retriveByName;

        }
    }

    @Override
    //implementation method to get track using id
    public Track getById(int id) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            Track retrivedTrack = trackRepository.findById(id).get();
            return retrivedTrack;
        } else {
            throw new TrackNotFoundException("Track not found");
        }

    }

    @Override
    //implementation method to get all tracks
    public List<Track> getAllTrack() throws Exception {
        if (trackRepository.findAll().isEmpty()) {
            throw new Exception("internal server error");
        } else {
            List<Track> retriveAllTrack = trackRepository.findAll();
            return getAllTrack();
        }

    }

    @Override
    //implementation method to delete track using id
    public Track deleteTrackById(int id) throws TrackNotFoundException {

        if (trackRepository.existsById(id)) {
            Track retrivedTrack = trackRepository.findById(id).get();
            trackRepository.deleteById(id);
            return retrivedTrack;
        } else {
            throw new TrackNotFoundException("Track not found");
        }

    }

    @Override
    //implementation method to update track
    public Track updateTrackById(int id, Track track) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            Track retrivedTrack = trackRepository.save(track);
            return retrivedTrack;
        } else {
            throw new TrackNotFoundException("Track not found");
        }
    }
}

