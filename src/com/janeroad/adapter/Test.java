package com.janeroad.adapter;

public class Test {
    public static void main(String[] args) {
        MusicPlayer player = new PlayerAdapter();
        player.play("mp3","七里香.mp3");
        player.play("wma","晴天.wma");
    }
}
