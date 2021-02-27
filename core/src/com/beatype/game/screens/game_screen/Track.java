package com.beatype.game.screens.game_screen;

import com.badlogic.gdx.utils.Array;

public class Track {

    public Array<Note> trackMap;

    public double songLength, holdEnd;
    public int songBPM;
    public String sentence, pathToSongFile, type;

    public Track (double songLength, int songBPM, String sentence, String pathToSongFile, Array<Note> trackMap) {
        this.songLength = songLength;
        this.songBPM = songBPM;
        this.sentence = sentence;
        this.pathToSongFile = pathToSongFile;
        this.trackMap = trackMap;
    }

    public Track (double songLength, int songBPM, String sentence, String pathToSongFile, String type, double holdEnd, Array<Note> trackMap) {
        this.songLength = songLength;
        this.songBPM = songBPM;
        this.sentence = sentence;
        this.pathToSongFile = pathToSongFile;
        this.type = type;
        this.holdEnd = holdEnd;
        this.trackMap = trackMap;
    }

}
