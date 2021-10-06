package com.janeroad.adapter;

public class PlayerAdapter implements MusicPlayer{
    private MyPlayer player;

    public PlayerAdapter(){
        this.player = new MyPlayer();
    }

    @Override
    public void play(String type, String filename) {
        if ("mp3".equals(type)){
            this.player.playMp3(filename);
        }
        if ("wma".equals(type)){
            this.player.playWma(filename);
        }
    }
}
