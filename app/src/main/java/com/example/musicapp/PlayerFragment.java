package com.example.musicapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

public class PlayerFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private ImageButton  nextButton, previousButton, randomButton, playButton;
    private SeekBar seekBar;
    private Handler handler = new Handler(Looper.getMainLooper());
    private int currentSongIndex = 0;
    private Music selectedMusic;

    private int[] songResources = {
            R.raw.cent,
            R.raw.theboyismine,
            R.raw.wecantbefriend,
            R.raw.dont,
            R.raw.collide,
            R.raw.youright,
            R.raw.ladykiller,
            R.raw.goodforyouxoneofthegirl,
            R.raw.onthefloor,
            R.raw.confident,
            R.raw.rodeo,
            R.raw.thelostsouldown,
            R.raw.obsessed,
            R.raw.kissitbetter,
            R.raw.espresso,
            R.raw.sayit,
            R.raw.mindgames,
            R.raw.nobodygetsme,
            R.raw.snooze,
            R.raw.twoon,
            R.raw.heydaddy,
            R.raw.yad
    };


    public PlayerFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);

        playButton = view.findViewById(R.id.button_play);
        nextButton = view.findViewById(R.id.button_next);
        previousButton = view.findViewById(R.id.button_previous);
        seekBar = view.findViewById(R.id.seek_bar);
        randomButton = view.findViewById(R.id.button_random);

        if (getArguments() != null) {
            selectedMusic = getArguments().getParcelable("selected_music");
        }


        mediaPlayer = MediaPlayer.create(requireContext(), songResources[currentSongIndex]);
        seekBar.setMax(mediaPlayer.getDuration());

        if (selectedMusic != null) {
            playSelectedMusic(selectedMusic);
            playButton.setImageResource(R.drawable.ic_pause);
        }

        playButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                playButton.setImageResource(R.drawable.ic_play);

            } else {
                mediaPlayer.start();
                updateSeekBar();
                playButton.setImageResource(R.drawable.ic_pause);

            }

        });


        nextButton.setOnClickListener(v -> {
            currentSongIndex = (currentSongIndex + 1) % songResources.length;
            playSong();
        });

        previousButton.setOnClickListener(v -> {
            currentSongIndex = (currentSongIndex - 1 + songResources.length) % songResources.length;
            playSong();
        });

        randomButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                playRandomSong();
                playButton.setImageResource(R.drawable.ic_pause);
            } else {
                playRandomSong();
                playButton.setImageResource(R.drawable.ic_pause);
            }
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer.setOnCompletionListener(mp -> {
            currentSongIndex = (currentSongIndex + 1) % songResources.length;
            playSong();
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        if (getArguments() != null) {
            Music newSelectedMusic = getArguments().getParcelable("selected_music");


            if (newSelectedMusic != null && (selectedMusic == null || !newSelectedMusic.equals(selectedMusic))) {
                selectedMusic = newSelectedMusic;
                playSelectedMusic(selectedMusic);
            }
        }
    }




    private void playRandomSong() {
        Random random = new Random();
        int newSongIndex;

        do {
            newSongIndex = random.nextInt(songResources.length);
        } while (newSongIndex == currentSongIndex);

        currentSongIndex = newSongIndex;
        playSong();
    }


    private void playSong() {
        stopAndReset();
        mediaPlayer = MediaPlayer.create(requireContext(), songResources[currentSongIndex]);
        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();
        updateSeekBar();

        mediaPlayer.setOnCompletionListener(mp -> {
            currentSongIndex = (currentSongIndex + 1) % songResources.length;
            playSong();
        });
    }

    private void playSelectedMusic(Music music) {
        stopAndReset();
        mediaPlayer = MediaPlayer.create(requireContext(), music.getMusicResource());
        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();
        updateSeekBar();



        mediaPlayer.setOnCompletionListener(mp -> {
            currentSongIndex = (currentSongIndex + 1) % songResources.length;
            playSong();
        });
    }




    private void stopAndReset() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
    }



    private void updateSeekBar() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(this::updateSeekBar, 1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopAndReset();
        handler.removeCallbacksAndMessages(null);
    }
}