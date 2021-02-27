package com.beatype.game.screens.game_screen;

public class Note {

    public double songLength, holdEnd;
    public int songBPM;
    public String sentence, pathToSongFile, type;

    public Note (int songLength, int songBPM, String sentence, String pathToSongFile) {
        this.songLength = songLength;
        this.songBPM = songBPM;
        this.sentence = sentence;
        this.pathToSongFile = pathToSongFile;
    }

    public Note (int songLength, int songBPM, String sentence, String pathToSongFile, String type, double holdEnd) {
        this.songLength = songLength;
        this.songBPM = songBPM;
        this.sentence = sentence;
        this.pathToSongFile = pathToSongFile;
        this.type = type;
        this.holdEnd = holdEnd;
    }
}
