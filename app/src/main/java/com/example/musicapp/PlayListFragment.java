package com.example.musicapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class PlayListFragment extends Fragment {
    public ListView listView;
    public ArrayList<Music> musicList;
    public static MusicAdapter adapter;
    private MusicAdapter.OnMusicClickListener onMusicClickListener;
    private MediaPlayer mediaPlayer;


    public PlayListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play_list, container, false);

        listView = view.findViewById(R.id.listView);
        musicList = new ArrayList<>();

        Music music1 = new Music("Song 1", "Artist 1", R.drawable.ic_launcher_background, R.raw.song1);
        Music music2 = new Music("Song 2", "Artist 2", R.drawable.ic_launcher_background, R.raw.song2);
        Music music3 = new Music("Song 3", "Artist 3", R.drawable.ic_launcher_background, R.raw.song3);
        Music music4 = new Music("Song 4", "Artist 4", R.drawable.ic_launcher_background, R.raw.song4);
        Music music5 = new Music("Song 5", "Artist 5", R.drawable.ic_launcher_background, R.raw.song5);
        Music music6 = new Music("Song 6", "Artist 6", R.drawable.ic_launcher_background, R.raw.song6);
        Music music7 = new Music("Song 7", "Artist 7", R.drawable.ic_launcher_background, R.raw.song7);
        Music music8 = new Music("Song 8", "Artist 8", R.drawable.ic_launcher_background, R.raw.song8);
        Music music9 = new Music("Song 9", "Artist 9", R.drawable.ic_launcher_background, R.raw.song9);
        Music music10 = new Music("Song 10", "Artist 10", R.drawable.ic_launcher_background, R.raw.song10);

        musicList.add(music1);
        musicList.add(music2);
        musicList.add(music3);
        musicList.add(music4);
        musicList.add(music5);
        musicList.add(music6);
        musicList.add(music7);
        musicList.add(music8);
        musicList.add(music9);
        musicList.add(music10);

        adapter = new MusicAdapter(getContext(), musicList, onMusicClickListener);
        listView.setAdapter(adapter);






        return view;
    }

    public void setOnMusicClickListener(MusicAdapter.OnMusicClickListener listener) {
        this.onMusicClickListener = listener;
        playMusic(musicList.get(0));
    }

    private void playMusic(Music music) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(getContext(), music.getMusicResource());
        mediaPlayer.start();

        // Notify the activity or other fragments about the currently playing song
        if (onMusicClickListener != null) {
            onMusicClickListener.onMusicClick(music);
        }
    }


    public ArrayList<Music> getMusicList() {
        return musicList;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}