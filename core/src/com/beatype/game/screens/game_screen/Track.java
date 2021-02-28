package com.beatype.game.screens.game_screen;

import com.badlogic.gdx.utils.Array;
import com.beatype.game.screens.game_screen.notes.Note;

public class Track {

    public Array<Note> notes;

    public double songLength;
    public String sentence, pathToSongFile;

    public Track (double songLength, String sentence, String pathToSongFile, Array<Note> notes) {
        this.songLength = songLength;
        this.sentence = sentence;
        this.pathToSongFile = pathToSongFile;
        this.notes = notes;

//        String b = "THIS IS FOR TESTING PURPOSE YES";
//        //String b = "TTT";
//
//        for (int i = 0; i < b.length(); i++) {
//            notes.add(new Note(i, b.substring(i, i + 1)));
//        }
    }

}
