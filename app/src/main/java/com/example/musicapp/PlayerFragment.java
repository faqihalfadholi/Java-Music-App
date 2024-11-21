package com.example.musicapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

public class PlayerFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private ImageButton  nextButton, previousButton, randomButton;
    private ToggleButton playButton;
    private SeekBar seekBar;
    private Handler handler = new Handler(Looper.getMainLooper());
    private int currentSongIndex = 0;

    private int[] songResources = {
            R.raw.song1,
            R.raw.song2,
            R.raw.song3,
            R.raw.song4,
            R.raw.song5,
            R.raw.song6,
            R.raw.song7,
            R.raw.song8,
            R.raw.song9,
            R.raw.song10
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


        mediaPlayer = MediaPlayer.create(requireContext(), songResources[currentSongIndex]);
        seekBar.setMax(mediaPlayer.getDuration());

        playButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
                updateSeekBar();
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
            playRandomSong();
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

    // Fungsi untuk memutar lagu acak
    private void playRandomSong() {
        Random random = new Random();
        int newSongIndex;

        // Pilih lagu secara acak, pastikan tidak sama dengan lagu yang sedang diputar
        do {
            newSongIndex = random.nextInt(songResources.length);
        } while (newSongIndex == currentSongIndex);

        currentSongIndex = newSongIndex;
        playSong();
    }

    // Memulai lagu dan mengatur ulang MediaPlayer
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

    // Menghentikan dan mereset MediaPlayer
    private void stopAndReset() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
    }

    // Update SeekBar secara berkala
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