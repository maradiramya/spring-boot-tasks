package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

//service interface
public interface TrackService {
    //abstract method to save track
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    //abstract method to get a track using name
    public List<Track> findByName(String name) throws TrackNotFoundException;

    //abstract method to get a track by using id
    public Track getById(int id) throws TrackNotFoundException;

    //abstract method to get a all tracks
    public List<Track> getAllTrack() throws Exception;

    //abstract method to delete track using id
    public Track deleteTrackById(int id) throws TrackNotFoundException;

    //abstract method to update the track
    public Track updateTrackById(int id, Track track) throws TrackNotFoundException;
}
