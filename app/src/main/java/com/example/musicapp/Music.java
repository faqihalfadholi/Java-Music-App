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

    // Parcelable implementation
    protected Music(Parcel in) {
        title = in.readString();
        artist = in.readString();
        imageMusic = in.readInt();
        musicResource = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeInt(imageMusic);
        dest.writeInt(musicResource);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Music music = (Music) obj;
        return musicResource == music.musicResource && title.equals(music.title) && artist.equals(music.artist);
    }

}
