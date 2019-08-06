package com.stackroute.trackservice.repository;

import com.stackroute.trackservice.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@RunWith, JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit
//The SpringRunner provides support for loading a Spring ApplicationContext and having beans @Autowired into your test instance

//@DataJpaTest

@SpringBootTest
//Spring Boot provides a convenient way to set up an environment with an embedded database to test our database queries against.
public class TrackRepositoryTest {
    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp() throws Exception {
        track = new Track();
        track.setId(10);
        track.setName("ramya");
        track.setComments("aaaa");

    }

    @After
    public void tearDown() throws Exception {
        trackRepository.deleteAll();
    }

    @Test
    public void findById() {
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId());
        Assert.assertEquals(10, fetchTrack.getId());
    }

    @Test
    public void testTrackFailure() {
        Track testTrack = new Track(34, "darshan", "track is nice");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId());
        Assert.assertNotSame(testTrack, track);
    }

    @Test
    public void findByName() {
        Track testTrack = new Track(20, "raj", "track is better");
        trackRepository.save(testTrack);
        List<Track> fetchTrack = trackRepository.findAll();
        Assert.assertEquals("raj", fetchTrack.get(0).getName());
    }
}