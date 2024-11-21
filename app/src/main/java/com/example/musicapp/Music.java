package com.example.musicapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable {

    private String title;
    private String artist;
    private int imageMusic;
    private int musicResource;

    public Music(String title, String artist, int imageMusic, int musicResource) {
        this.title = title;
        this.artist = artist;
        this.imageMusic = imageMusic;
        this.musicResource = musicResource;
    }

    public int getMusicResource() {
        return musicResource;
    }

    public void setMusicResource(int musicResource) {
        this.musicResource = musicResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getImageMusic() {
        return imageMusic;
    }

    public void setImageMusic(int imageMusic) {
        this.imageMusic = imageMusic;
    }

    protected Music(Parcel in) {
        // Read data from parcel
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Write data to parcel
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };
}
