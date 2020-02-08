package com.company;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Playable {

    private List<Playable> records = new ArrayList<>();


    public void addRecord(Playable playable){
        records.add(playable);
    }
    
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addRecord(new Movie("Casblanca"));
        playlist.addRecord(new Song("Brown Sugar","Rolling Stones"));

        Playlist playlist1 = new Playlist();
        playlist1.addRecord(new Movie("Killer"));
        playlist.addRecord(playlist1);
        playlist.play();
    }



    @Override
    public void play() {
        for ( Playable playable: records){
            playable.play();
        }
    }


}
