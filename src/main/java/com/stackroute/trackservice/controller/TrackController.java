package com.stackroute.trackservice.controller;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackNotFoundException;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TrackController {

    private TrackService trackService;

    public TrackController() {
    }

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> savedTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        Track savedTrack = trackService.saveTrack(track);
        return new ResponseEntity<>(savedTrack, HttpStatus.CREATED);
    }

    @GetMapping("tracks/{name}")
    public ResponseEntity<?> findTrackByName(@PathVariable String name) throws TrackNotFoundException {
        List<Track> trackByName = trackService.findByName(name);
        return new ResponseEntity(trackByName, HttpStatus.FOUND);
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException {
        Track track = trackService.getById(id);
        return new ResponseEntity(track, HttpStatus.OK);
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks() throws Exception {
        List<Track> allTrack = trackService.getAllTrack();
        return new ResponseEntity(allTrack, HttpStatus.OK);
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id) throws TrackNotFoundException {
        Track track = trackService.deleteTrackById(id);
        return new ResponseEntity(track, HttpStatus.OK);
    }

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrack(@PathVariable int id, @RequestBody Track track) throws TrackNotFoundException {
        Track updateTrack = trackService.updateTrackById(id, track);
        return new ResponseEntity<>(updateTrack, HttpStatus.OK);
    }
}
