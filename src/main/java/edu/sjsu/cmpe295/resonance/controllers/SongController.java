package edu.sjsu.cmpe295.resonance.controllers;

import edu.sjsu.cmpe295.resonance.Utils.S3Connector;
import edu.sjsu.cmpe295.resonance.exceptions.BadRequestException;
import edu.sjsu.cmpe295.resonance.models.Song.Song;
import edu.sjsu.cmpe295.resonance.services.Song.SongService;
import edu.sjsu.cmpe295.resonance.services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by harkirat singh on 3/9/2016.
 */

@RestController
@EnableAutoConfiguration
@ComponentScan
@Component("SongController")
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @Autowired
    private S3Connector s3Connector;
    //=================================================
    //          Upload a new song
    //=================================================
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Song> createUser(@Valid @RequestBody Song song, BindingResult result, HttpServletResponse response) {

        System.out.println("UserID : "+ song.getUploadedByUserId()+song.getKey());
        if (song.getSongTitle() == null )
            throw new BadRequestException("Song title required.");
        if (song.getSongPath() == null || song.getSongPath().trim().equals(""))
            throw new BadRequestException("Song path required.");
        if (song.getKey() == null || song.getKey().trim().equals(""))
            throw new BadRequestException("Key required.");
        if (song.getUploadedByUserId()==null)
            throw new BadRequestException("User Id is required.");


        if(userService.getUserById(Long.parseLong(song.getUploadedByUserId()))==null){
            throw new BadRequestException("Invalid user");
        }
        Song songObj = null;

        try {
            songObj = new Song(song.getSongTitle(), song.getKey(),song.getSongPath(),song.getUploadedByUserId());
            System.out.println("Harkirat : "+song.getSongId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        System.out.println("Harkirat : "+songObj.getSongPath());
        songService.create(songObj);
        s3Connector.uploadFile(songObj.getKey(),songObj.getSongPath());
        return new ResponseEntity<Song>(songObj, HttpStatus.CREATED);
    }
}
