package com.example.musicapp;

import android.media.MediaPlayer;
import android.os.Parcelable;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    TabLayout tabLayout;
    PlayListFragment playListFragment = new PlayListFragment()  ;
    PlayerFragment playerFragment = new PlayerFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tablayout);

        // Initialize fragments
        playListFragment = new PlayListFragment();
        playerFragment = new PlayerFragment();

        // Set up music list and listener
        ArrayList<Music> musicList = new ArrayList<>();
        // TODO: Add your music items to the musicList

        Bundle args = new Bundle();
        args.putParcelableArrayList("music_list", musicList);
        playListFragment.setArguments(args);
        playerFragment.setArguments(args);

//         Set up listener
        MusicAdapter.OnMusicClickListener listener = new MusicAdapter.OnMusicClickListener() {
            @Override
            public void onMusicClick(Music music) {
                Bundle args = new Bundle();
                args.putParcelable("selected_music", music);
                playerFragment.setArguments(args);
                viewPager.setCurrentItem(1); // Switch to Player tab
            }
        };

        playListFragment.setOnMusicClickListener(listener);

        // Set up ViewPager2 with FragmentStateAdapter
        ViewPageAdapter viewPagerAdapter = new ViewPageAdapter(this);
        viewPagerAdapter.addFragment(playListFragment, "Playlist");
        viewPagerAdapter.addFragment(playerFragment, "Player");
        viewPager.setAdapter(viewPagerAdapter);


        // Connect TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(viewPagerAdapter.getFragmentTitle(position))
        ).attach();
    }

}