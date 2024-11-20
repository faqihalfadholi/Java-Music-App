package com.example.musicapp;

import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button playButton, pauseButton, stopButton, nextButton, previousButton, randomButton;
    SeekBar seekBar;
    Handler handler = new Handler();
    int currentSongIndex = 0;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menghubungkan tombol dan SeekBar
        playButton = findViewById(R.id.button_play);
        pauseButton = findViewById(R.id.button_pause);
        stopButton = findViewById(R.id.button_stop);
        nextButton = findViewById(R.id.button_next);
        previousButton = findViewById(R.id.button_previous);
        seekBar = findViewById(R.id.seek_bar);
        randomButton = findViewById(R.id.button_random);

        mediaPlayer = MediaPlayer.create(this, songResources[currentSongIndex]);
        seekBar.setMax(mediaPlayer.getDuration());

        playButton.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                updateSeekBar();
            }

        });

        pauseButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        stopButton.setOnClickListener(v -> {
            stopAndReset();
        });

        nextButton.setOnClickListener(v -> {
            currentSongIndex = (currentSongIndex + 1) % songResources.length;
            playSong(currentSongIndex);
        });

        previousButton.setOnClickListener(v -> {
            currentSongIndex = (currentSongIndex - 1 + songResources.length) % songResources.length;
            playSong(currentSongIndex);
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
            playSong(currentSongIndex);
        });
    }


    private void playRandomSong() {
        Random random = new Random();
        int newSongIndex;

        do {
            newSongIndex = random.nextInt(songResources.length);
        } while (newSongIndex == currentSongIndex);

        currentSongIndex = newSongIndex;
        playSong(currentSongIndex);
    }

    private void playSong(int index) {
        stopAndReset();
        mediaPlayer = MediaPlayer.create(this, songResources[currentSongIndex]);
        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();
        updateSeekBar();
        mediaPlayer.setOnCompletionListener(mp -> {
            currentSongIndex++;
            if (currentSongIndex < songResources.length) {
                playSong(currentSongIndex);
            }else {
                currentSongIndex = 0;
            }
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
    protected void onDestroy() {
        super.onDestroy();
        stopAndReset();
        handler.removeCallbacksAndMessages(null);
    }
}