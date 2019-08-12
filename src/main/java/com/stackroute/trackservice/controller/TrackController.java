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
    public ResponseEntity<?> savedTrack(@RequestBody Track track) {
        
        try {
            Track saveTrack = trackService.saveTrack(track);
            responseEntity = new ResponseEntity(saveTrack, HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        catch(Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("tracks/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable String name) {
   
      try{
         List <Track> trackByName=trackService.getByName(name);
          responseEntity=new ResponseEntity(trackByName,HttpStatus.CREATED);
      }
      catch (TrackNotFoundException e)
      {
          responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
      }
        catch(Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        }

        return responseEntity;
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) {
       
        try {

            Track track = trackService.getById(id);
            responseEntity = new ResponseEntity<>(track, HttpStatus.CREATED);
        } catch (TrackNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        catch(Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks() {
        
        try{
        List  <Track> allTrack= trackService.getAllTrack();
            responseEntity=new ResponseEntity(allTrack,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e)
        {
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id) {
        
        try {
            Track track = trackService.getById(id);
            responseEntity = new ResponseEntity<>(track, HttpStatus.CREATED);
        } catch (TrackNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        catch(Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrack(@PathVariable int id,@RequestBody Track track) {
      
        try {
            Track updateTrack = trackService.updateTrackById(id,track);
            responseEntity = new ResponseEntity<>(updateTrack, HttpStatus.UPGRADE_REQUIRED);
        } catch (TrackNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        catch(Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        }
        return responseEntity;
    }


}
