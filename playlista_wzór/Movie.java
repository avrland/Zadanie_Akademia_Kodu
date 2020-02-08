package com.company;

public class Movie implements Playable {

    private String title;

    public Movie(String title) {
        this.title = title;
    }

    @Override
    public void play() {
        System.out.println("Film: "+title);
    }
}
