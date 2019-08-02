package com.stackroute.trackservice.seeddata;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerImpl implements ApplicationListener
{
    private TrackRepository trackRepository;
@Autowired
    public ApplicationListenerImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event)
    {
        Track track1=new Track(33,"ramya","track is good");
        trackRepository.save(track1);
        Track track2=new Track(34,"sunona","track is bad");
        trackRepository.save(track2);
    }
}
