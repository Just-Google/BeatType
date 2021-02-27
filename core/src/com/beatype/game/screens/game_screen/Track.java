package com.beatype.game.screens.game_screen;

import com.badlogic.gdx.utils.Array;

public class Track {

    public String orientation;
    public int x, y;
    public Array<Note> trackMap;

    public Track (String orientation, int x, int y, Array<Note> trackMap) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.trackMap = trackMap;
    }

}
