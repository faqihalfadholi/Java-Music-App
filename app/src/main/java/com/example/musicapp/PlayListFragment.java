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

        Music music1 = new Music("Baby by Me", "50 Cent", R.drawable.cent, R.raw.cent);
        Music music2 = new Music("The Boy Is Mine", "Ariana Grande", R.drawable.theboy, R.raw.theboyismine);
        Music music3 = new Music("We Can't Be Friends", "Ariana Grande", R.drawable.wecant, R.raw.wecantbefriend);
        Music music4 = new Music("Don't", "Bryson Tiller", R.drawable.dont, R.raw.dont);
        Music music5 = new Music("Collide", "Justine Skye ft. Tyga", R.drawable.collide, R.raw.collide);
        Music music6 = new Music("You Right", "Doja Cat, The Weeknd", R.drawable.youright, R.raw.youright);
        Music music7 = new Music("Lady Killers II", "G-Eazy", R.drawable.ladykiller, R.raw.ladykiller);
        Music music8 = new Music("Good For You", "The Weeknd", R.drawable.oneofthegirlxgoodforyou, R.raw.goodforyouxoneofthegirl);
        Music music9 = new Music("On The Floor", "Jennifer Lopez ft. Pitbull", R.drawable.onthefloor, R.raw.onthefloor);
        Music music10 = new Music("Confident", "Justin Bieber", R.drawable.confident, R.raw.confident);
        Music music11 = new Music("Rodeo Remix", "Lah Pat ft.Flo Milli", R.drawable.rodeolahpat, R.raw.rodeo);
        Music music12 = new Music("Lost Soul Down", "NBSPILV", R.drawable.theloustsouldown, R.raw.thelostsouldown);
        Music music13 = new Music("Obsessed", "Mariah Carey", R.drawable.obsessed, R.raw.obsessed);
        Music music14 = new Music("Kiss It Better", "Rihanna", R.drawable.kissitbetter, R.raw.kissitbetter);
        Music music15 = new Music("Espresso", "Sabrina Carpenter", R.drawable.espresso, R.raw.espresso);
        Music music16 = new Music("Say It", "Tory Lanez", R.drawable.sayit, R.raw.sayit);
        Music music17 = new Music("Mind Games", "Sickick", R.drawable.mindgames, R.raw.mindgames);
        Music music18 = new Music("Nobody Gets Me", "SZA", R.drawable.nobodygetme, R.raw.nobodygetsme);
        Music music19 = new Music("Snooze", "SZA", R.drawable.snooze, R.raw.snooze);
        Music music20 = new Music("2 On", "Tinashe", R.drawable.twoon, R.raw.twoon);
        Music music21 = new Music("Hey Daddy ", "Usher", R.drawable.heydaddy, R.raw.heydaddy);
        Music music22 = new Music("YAD", "YAD", R.drawable.yad, R.raw.yad);



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
        musicList.add(music11);
        musicList.add(music12);
        musicList.add(music13);
        musicList.add(music14);
        musicList.add(music15);
        musicList.add(music16);
        musicList.add(music17);
        musicList.add(music18);
        musicList.add(music19);
        musicList.add(music20);
        musicList.add(music21);
        musicList.add(music22);

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