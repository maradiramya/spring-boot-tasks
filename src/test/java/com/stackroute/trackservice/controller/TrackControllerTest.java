package com.stackroute.trackservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.GlobalException;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
//which helps in testing the controllers explicitly starting a Servlet container.
public class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    //it adds mock objects to Spring ApplicationContext
    private TrackService trackService;
    @InjectMocks
    //Inject the mocks as dependencies into TrackServiceController
    private TrackController trackController;

    private List<Track> list;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).setControllerAdvice(new GlobalException()).build();
        track = new Track();
        track.setId(26);
        track.setName("Jonny");
        track.setComments("track is best");
        list = new ArrayList();
        list.add(track);
    }
    @After
    public void tearDown() throws Exception {
        track=null;
        list=null;
    }
        

    @Test
    public void givenTrackDetailsShouldReturnSavedTrack() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
         //verify here verifies that trackService save method is only called once
        verify(trackService,times(1)).saveTrack(track);
    }

    @Test
    public void givenTrackDetailsShouldNotReturnSavedTrack() throws Exception {
        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        //verify here verifies that trackService save method is only called once
        verify(trackService,times(1)).saveTrack(track);
    }


    @Test
    public void givenTrackShouldReturnListOfSavedTrack() throws Exception {
        when(trackService.getAllTrack()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        //verify here verifies that trackService getAllTrack method is only called once
        verify(trackService,times(1)).getAllTrack();
    }

    @Test
    public void givenNameShouldReturnTrackFromListOfTrack() throws Exception {
        when(trackService.findByName("ramya")).thenReturn(List.of(track));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/name")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(list)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
        //verify here verifies that trackService findByName method is only called once
        verify(trackService,times(1)).findByName("name");

    }

    @Test
    public void givenTrackIdShouldReturnDeletedTrack() throws Exception {
        when(trackService.deleteTrackById(10)).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/10")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        //verify here verifies that trackService getById method is only called once
        verify(trackService,times(1)).getById(10);
    }

    @Test
    public void givenTrackIdShouldReturnUpdatedTrack() throws Exception {
            when(trackService.updateTrack(track)).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/10")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        //verify here verifies that trackService getById method is only called once
        verify(trackService,times(1)).getById(10);

    }

    @Test
    public void givenTrackIdShouldReturnTrackOfGivenId() throws Exception {
        when(trackService.getById(10)).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/10")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        //verify here verifies that trackService getById method is only called once
        verify(trackService,times(1)).getById(10);

    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
