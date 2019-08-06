package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackNotFoundException;
import com.stackroute.trackservice.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    Track track;
    //Create a mock for TrackRepository
    @Mock
    TrackRepository trackRepository;
    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list = null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(10);
        track.setName("jonny");
        track.setComments("good");
        list = new ArrayList<>();
        list.add(track);
    }


    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track, savedTrack);
        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository, times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        trackRepository.save(track);
        when(trackRepository.existsById((track.getId()))).thenReturn(true);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertNotEquals(track, savedTrack);

    }

    @Test
    public void getAllTrack() throws Exception {
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTrack();
        Assert.assertEquals(list, tracklist);
    }

    @Test
    public void findByName() throws TrackNotFoundException {
        when(trackRepository.findByName(any())).thenReturn((list));
        List<Track> fetchTrack = trackService.findByName("ramya");
        Assert.assertEquals(list, fetchTrack);

    }

    @Test
    public void getById() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.existsById(10)).thenReturn(true);
        when(trackRepository.findById(10)).thenReturn((track));
        Track fetchTrackById = trackService.getById(10);
        Assert.assertEquals(track, fetchTrackById);
    }

    @Test
    public void deleteTrackById() throws TrackNotFoundException {

        when(trackRepository.existsById(10)).thenReturn(true);
        when(trackRepository.findById(10)).thenReturn(track);
        Track deleteTrackById = trackService.getById(10);
        Assert.assertEquals(track, deleteTrackById);
    }

    @Test
    public void updateTrackById() throws TrackNotFoundException, TrackAlreadyExistsException {
        when(trackRepository.existsById(101)).thenReturn(true);
        when(trackRepository.save(track)).thenReturn(track);
        Track updateTrackById = trackService.saveTrack(track);
        Assert.assertEquals(track, updateTrackById);
    }

}