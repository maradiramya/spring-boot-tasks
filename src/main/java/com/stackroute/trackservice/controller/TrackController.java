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
    private ResponseEntity responseEntity;

    public TrackController() {
    }

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> savedTrack(@RequestBody Track track) throws TrackAlreadyExistsException,Exception {
        Track savedTrack = trackService.saveTrack(track);
        responseEntity = new ResponseEntity<>(savedTrack, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("tracks/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable String name) throws TrackNotFoundException,Exception {
        List<Track> trackByName = trackService.getByName(name);
        responseEntity = new ResponseEntity(trackByName, HttpStatus.FOUND);
        return responseEntity;
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException,Exception {
        Track track = trackService.getById(id);
        responseEntity = new ResponseEntity(track, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("tracks") throws Exception
    public ResponseEntity<?> getAllTracks() throws Exception {
        List<Track> allTrack = trackService.getAllTrack();
        responseEntity = new ResponseEntity(allTrack, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id) throws TrackNotFoundException,Exception {
        Track track = trackService.getById(id);
        responseEntity = new ResponseEntity(allTrack, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrack(@PathVariable int id, @RequestBody Track track) throws TrackNotFoundException,Exception {
        Track updateTrack = trackService.updateTrackById(id, track);
        responseEntity = new ResponseEntity(allTrack, HttpStatus.OK);
        return responseEntity;
    }
}
