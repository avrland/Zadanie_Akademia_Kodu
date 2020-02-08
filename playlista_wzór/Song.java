package com.company;

public class Song implements Playable {

    private String name;

    private String author;

    public Song(String name, String author) {
        this.name = name;
        this.author = author;
    }

    @Override
    public void play() {
        System.out.println("Muzyka: "+author+", "+name);
    }
}
