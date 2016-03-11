package edu.sjsu.cmpe295.resonance.models.Song;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by harkirat singh on 3/8/2016.
 */

@Entity
@Table(name = "Songs")
public class Song {



    @Id
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "songid")
    private long songId;


    @NotNull
    @JsonProperty
    @Column(name = "title")
    private String songTitle;

    @NotNull
    @JsonProperty
    @Column(name = "songkey")
    private String key;

    @NotNull
    @JsonProperty
    @Column(name = "path")
    private String songPath;

    @NotNull
    @JsonProperty
    @Column(name = "userid")
    private String uploadedByUserId;

    public Song() {
    }

    public Song(long songId) {
        this.songId=songId;
    }

    public Song(@JsonProperty String songTitle, @JsonProperty String key, @JsonProperty String songPath, @JsonProperty String uploadedByUserId) {
        this.songTitle = songTitle;
        this.key = key;
        this.songPath = songPath;
        this.uploadedByUserId=uploadedByUserId;
    }

    public long getSongId() {
        return songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getKey() {
        return key;
    }

    public String getSongPath() {
        return songPath;
    }

    public String getUploadedByUserId() {
        return uploadedByUserId;
    }

    @Id
    @Column(name = "songid", unique = true, nullable = false)
    public void setSongId(long songId) {
        this.songId = songId;
    }

    @Column(name = "title", unique = false, nullable = false)
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    @Column(name = "songkey", unique = false, nullable = false)
    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "path", unique = false, nullable = false)
    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    @Column(name = "userid", unique = false, nullable = false)
    public void setUploadedByUserId(String uploadedByUserId) {
        this.uploadedByUserId = uploadedByUserId;
    }
}
