package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    public Track saveTrack(Track track);

    public Track getById(int id);

    public List<Track> getAllTrack();

    public Track deleteTrackById(int id);

    public Track updateTrack(Track track);
}
