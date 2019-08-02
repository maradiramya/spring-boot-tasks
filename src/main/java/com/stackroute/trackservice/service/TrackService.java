package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;

import java.util.List;
import java.util.Optional;
//service interface
public interface TrackService {
    //abstract method to save track
    public Track saveTrack(Track track);
    //abstract method to get a track using name
    public List<Track> getByName(String name);
    //abstract method to get a track by using id
    public Track getById(int id);
    //abstract method to get a all tracks
    public List<Track> getAllTrack();
    //abstract method to delete track using id
    public Track deleteTrackById(int id);
    //abstract method to update the track
    public Track updateTrack(Track track);
}
