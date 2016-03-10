package edu.sjsu.cmpe295.resonance.services.Song;

import edu.sjsu.cmpe295.resonance.models.Song.Song;
import edu.sjsu.cmpe295.resonance.models.Song.SongDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by harkirat singh on 3/9/2016.
 */
public class SongServiceImpl implements SongService{

    @Autowired
    SongDao songDao;

    @Override
    public Song create(Song song) {

        songDao.save(song);
        return song;
    }

}
