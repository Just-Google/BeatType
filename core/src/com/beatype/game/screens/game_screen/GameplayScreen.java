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
import com.beatype.game.BeatType;
import com.beatype.game.screens.game_screen.notes.*;
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

        this.trackMap = new Track(10000, 1, "test", "", new Array<Note>());

        track = new Rectangle();
        trackDisplay = new Texture("Playfieldpng.png");

        letters = new TextureAtlas(Gdx.files.internal("letters/pack/letters.atlas"));

        startTime = TimeUtils.nanoTime();

        COUNTDOWN = 3;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override public boolean keyDown (int keycode) {
                if (keycode == Input.Keys.valueOf(notes.get(0).letter)) {
                    System.out.println(notes.get(0).letter);
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
            game.batch.draw(letters.findRegion(note.letter), note.rectangle.x, note.rectangle.y);
        }
        game.batch.end();

        Iterator<Note> iter = trackMap.notes.iterator();
        while (iter.hasNext()) {
            Note note = iter.next();
            if ((trackMap.songLength / ( (double) trackMap.songBPM / 60 * 1000)) * note.beat <= TimeUtils.nanosToMillis(TimeUtils.timeSinceNanos(startTime) - (COUNTDOWN * 1000) ) && !notes.contains(note, false)) {
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


}
