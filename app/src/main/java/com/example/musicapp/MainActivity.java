package com.example.musicapp;

import android.graphics.Typeface;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
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


        playListFragment = new PlayListFragment();
        playerFragment = new PlayerFragment();


        ArrayList<Music> musicList = new ArrayList<>();


        Bundle args = new Bundle();
        args.putParcelableArrayList("music_list", musicList);
        playListFragment.setArguments(args);
        playerFragment.setArguments(args);


        MusicAdapter.OnMusicClickListener listener = new MusicAdapter.OnMusicClickListener() {
            @Override
            public void onMusicClick(Music music) {
                Bundle args = new Bundle();
                args.putParcelable("selected_music", music);
                playerFragment.setArguments(args);
                viewPager.setCurrentItem(1);
            }
        };

        playListFragment.setOnMusicClickListener(listener);


        ViewPageAdapter viewPagerAdapter = new ViewPageAdapter(this);
        viewPagerAdapter.addFragment(playListFragment, "Playlist");
        viewPagerAdapter.addFragment(playerFragment, "Player");
        viewPager.setAdapter(viewPagerAdapter);



        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(viewPagerAdapter.getFragmentTitle(position))
        ).attach();


        tabLayout.setTabTextColors(
                getResources().getColor(R.color.tab_unselected),
                getResources().getColor(R.color.tab_selected)
        );

    }

}