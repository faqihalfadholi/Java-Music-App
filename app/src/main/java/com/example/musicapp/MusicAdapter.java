package com.example.musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MusicAdapter extends ArrayAdapter<Music> {


    public interface OnMusicClickListener {
        void onMusicClick(Music music);
    }

    private final OnMusicClickListener listener;

    public MusicAdapter(Context context, ArrayList<Music> musicList, OnMusicClickListener listener) {
        super(context, R.layout.layout_list_music, musicList);
        this.listener = listener;
    }

    private static class ViewHolder {
        TextView musicTitle;
        TextView musicArtist;
        ImageView musicImage;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Music music = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_list_music, parent, false);

            holder.musicTitle = convertView.findViewById(R.id.music_title);
            holder.musicArtist = convertView.findViewById(R.id.music_artist);
            holder.musicImage = convertView.findViewById(R.id.music_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (music != null) {
            holder.musicTitle.setText(music.getTitle());
            holder.musicArtist.setText(music.getArtist());
            holder.musicImage.setImageResource(music.getImageMusic());

            convertView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMusicClick(music);
                }
            });
        }

        return convertView;
    }
}