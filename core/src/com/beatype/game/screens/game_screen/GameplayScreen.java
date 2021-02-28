package com.beatype.game.screens.game_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.beatype.game.BeatType;
import com.beatype.game.screens.game_screen.notes.*;

import java.io.File;
import java.util.Iterator;

public class GameplayScreen implements Screen {

    final BeatType game;

    Track trackMap;

    Array<Note> notes = new Array<Note>();

    Texture trackDisplay;
    Rectangle track;

    TextureAtlas letters;

    Long startTime;

    int COUNTDOWN;


    public GameplayScreen(final BeatType game) {
        this.game = game;

        track = new Rectangle();
        trackDisplay = new Texture("gameplay/play track.png");

        letters = new TextureAtlas(Gdx.files.internal("gameplay/letters/letters.atlas"));

        startTime = TimeUtils.nanoTime();

        COUNTDOWN = 3;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override public boolean keyDown (int keycode) {
                if (keycode == Input.Keys.valueOf(notes.get(0).letter)) {
                    if (notes.get(0).rectangle.x < 250)
                        notes.removeIndex(0);
                }
                if (keycode == Input.Keys.SPACE && notes.get(0).letter == "SPACE") {
                    if (notes.get(0).rectangle.x < 250)
                        notes.removeIndex(0);
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        game.batch.begin();
        game.batch.draw(trackDisplay, 0, 400, 1600, 170);
        for (Note note: notes) {
            if (note.letter.equals(" "))
                note.letter = "SPACE";
            game.batch.draw(letters.findRegion(note.letter), note.rectangle.x, note.rectangle.y);
        }
        game.batch.end();

        Iterator<Note> iter = trackMap.notes.iterator();
        while (iter.hasNext()) {
            Note note = iter.next();
            //Remember to fix equation
            if ((trackMap.songLength * note.time <= TimeUtils.nanosToMillis(TimeUtils.timeSinceNanos(startTime) - (COUNTDOWN * 1000) ) && !notes.contains(note, false))) {
                notes.add(note);
                iter.remove();
            }
        }

        Iterator<Note> iter2 = notes.iterator();
        while (iter2.hasNext()) {
            Note note = iter2.next();
            note.rectangle.x -= 200 * Gdx.graphics.getDeltaTime();
            if (note.rectangle.x < 0)
                iter2.remove();
        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        trackDisplay.dispose();
    }

    public Track chartReader(String songLocation, String chartLocation) {
        File chartFile = new File(chartLocation);
        Array<Note> objects = new Array<Note>();
        Track track = null;
        try {
            boolean parseChart = false;
            double length = 0;
            Scanner parser = new Scanner(chartFile);
            while (!parseChart) {
                String line = parser.nextLine();
                if (line.contains("\"Length\"")) length = Double.valueOf(line.substring(9));
                if (line.contains("\"Objects\"")) parseChart = true;
            }
            while (parser.hasNextLine()) {
                String line = parser.nextLine();
                double time;
                String letter;
                Note note = null;
                if (line.contains("normal")) note = new Note(Double.valueOf(line.substring(line.indexOf(":")+1, line.indexOf(","))),
                        line.substring(line.lastIndexOf("\"")-1,line.lastIndexOf("\"")));
                else note = new NoteHold(Double.valueOf(line.substring(line.indexOf(":")+1, line.indexOf(","))),
                        line.substring(line.lastIndexOf("\"")-1,line.lastIndexOf("\"")),
                        Double.valueOf(line.substring(line.indexOf("End\":")+5, line.indexOf(",\"key"))));
                objects.add(note);
                note = null;
            }
            track = new Track(length,"",songLocation, objects);
        } catch (FileNotFoundException e) {
            System.out.println("Song not found");
        }
        return track;
    }
}
