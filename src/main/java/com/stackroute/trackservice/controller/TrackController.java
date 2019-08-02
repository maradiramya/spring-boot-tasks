package com.stackroute.trackservice.controller;

import com.stackroute.trackservice.domain.Track;
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
    public ResponseEntity<?> savedTrack(@RequestBody Track track) {
        Track savedTrack = trackService.saveTrack(track);
        return new ResponseEntity<>(savedTrack, HttpStatus.OK);
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) {
        Track retriveTrack = trackService.getById(id);
        return new ResponseEntity<>(retriveTrack, HttpStatus.OK);
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks() {
        return new ResponseEntity<List<Track>>(trackService.getAllTrack(), HttpStatus.OK);
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id) {
        Track deletedTrack = trackService.deleteTrackById(id);
        return new ResponseEntity<>(deletedTrack, HttpStatus.OK);
    }

    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        Track updatedTrack = trackService.updateTrack(track);
        responseEntity = new ResponseEntity("successfully updated", HttpStatus.CREATED);
        return responseEntity;
    }


}
