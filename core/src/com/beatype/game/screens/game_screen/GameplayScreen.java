package com.beatype.game.screens.game_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.beatype.game.BeatType;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import com.beatype.game.screens.game_screen.notes.*;
import com.beatype.game.screens.result_screen.ResultScreen;

import java.io.File;
import java.util.Iterator;

public class GameplayScreen implements Screen {

    final BeatType game;

    Track trackMap;

    Array<Note> notes = new Array<Note>();
    Array<String> scoreNum;

    Texture trackDisplay;
    Texture background;
    Texture scoreDisplay;
    TextureAtlas numbers;
    TextureAtlas letters;
    TextureAtlas judgements;
    TextureAtlas hpBar;

    Music song;

    String judgementSprite;

    int judgementX, judgementY;

    Long startTime;
    DecimalFormat dfScore = new DecimalFormat("000000");

    int COUNTDOWN;

    int health;
    String hpIndicator;

    Timer timer;

    int perfect, good, bad, miss, score;

    public GameplayScreen(final BeatType game) {
        this.game = game;

        this.trackMap = chartReader("core/assets/gameplay/song.mp3", "core/assets/Ah Hah Yeah.txt");

        song = Gdx.audio.newMusic(Gdx.files.internal(trackMap.pathToSongFile));
        song.setVolume((float) 0.3);

        trackDisplay = new Texture("gameplay/play track.png");
        scoreDisplay = new Texture("gameplay/score.png");
        background = new Texture("gameplay/background.png");

        letters = new TextureAtlas(Gdx.files.internal("gameplay/letters/letters.atlas"));
        judgements = new TextureAtlas(Gdx.files.internal("gameplay/judgement/judgements.atlas"));
        hpBar = new TextureAtlas(Gdx.files.internal("gameplay/HP/hp.atlas"));
        numbers = new TextureAtlas(Gdx.files.internal("gameplay/Number/Numbers.atlas"));

        startTime = TimeUtils.nanoTime();

        judgementX = 120;
        judgementY = 580;

        scoreNum = new Array<String>();
        for (int i = 0; i < 6; i++) {
            scoreNum.add("0");
        }

        COUNTDOWN = 3;

        health = 100;
        hpIndicator = "Hp1";

        judgementSprite = "Blank";

        score = 0;

        timer = new Timer();

        perfect = 0;
        good = 0;
        bad = 0;
        miss = 0;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override public boolean keyDown (int keycode) {
                if (notes.size != 0) {
                    if (keycode == Input.Keys.valueOf(notes.get(0).letter) || (keycode == Input.Keys.SPACE && notes.get(0).letter == "SPACE")) {
                        if (notes.get(0).rectangle.x < 130 && notes.get(0).rectangle.x > 90) {
                            notes.removeIndex(0);
                            showJudgement("Perfect");
                            perfect++;
                            health+=2;
                            score+=100;
                            changeScore();
                        }
                        else if (notes.get(0).rectangle.x < 150 && notes.get(0).rectangle.x > 70) {
                            notes.removeIndex(0);
                            showJudgement("Good");
                            good++;
                            health+=1;
                            score+=50;
                            changeScore();
                        }
                        else if (notes.get(0).rectangle.x < 170 && notes.get(0).rectangle.x > 50) {
                            notes.removeIndex(0);
                            showJudgement("Bad");
                            bad++;
                            health+=1;
                            score+=10;
                            changeScore();
                        }
                        else if (notes.get(0).rectangle.x < 190 && notes.get(0).rectangle.x > 30) {
                            notes.removeIndex(0);
                            showJudgement("Miss");
                            miss++;
                            health-=5;
                        }
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.draw(trackDisplay, 0, 400, 1600, 170);
        for (Note note: notes) {
            if (note.letter.equals(" "))
                note.letter = "SPACE";
            game.batch.draw(letters.findRegion(note.letter), note.rectangle.x, note.rectangle.y);
        }
        game.batch.draw(judgements.findRegion(judgementSprite), judgementX, judgementY);
        game.batch.draw(hpBar.findRegion(hpIndicator), 20, 50);
        game.batch.draw(scoreDisplay, 1100, 750);
        game.batch.draw(numbers.findRegion(scoreNum.get(5)), 1125, 780);
        game.batch.draw(numbers.findRegion(scoreNum.get(4)), 1195, 780);
        game.batch.draw(numbers.findRegion(scoreNum.get(3)), 1265, 780);
        game.batch.draw(numbers.findRegion(scoreNum.get(2)), 1335, 780);
        game.batch.draw(numbers.findRegion(scoreNum.get(1)), 1405, 780);
        game.batch.draw(numbers.findRegion(scoreNum.get(0)), 1475, 780);
        game.batch.end();

        if (TimeUtils.nanosToMillis(TimeUtils.nanoTime()) - TimeUtils.nanosToMillis(startTime) >= 4500)
            song.play();

        Iterator<Note> iter = trackMap.notes.iterator();
        while (iter.hasNext()) {
            Note note = iter.next();
            if (note.time - 2000 <= TimeUtils.nanosToMillis(TimeUtils.timeSinceNanos(startTime)) - (COUNTDOWN * 1000) && !notes.contains(note, false)) {
                notes.add(note);
                iter.remove();
            }
        }

        Iterator<Note> iter2 = notes.iterator();
        while (iter2.hasNext()) {
            Note note = iter2.next();
            note.rectangle.x -= 400 * Gdx.graphics.getDeltaTime();
            if (note.rectangle.x < 0) {
                iter2.remove();
                showJudgement("Miss");
                miss++;
                health-=5;
            }
        }

        if (health > 100)
            health = 100;
        else if (health < 25)
            hpIndicator = "Hp4";
        else if (health < 50)
            hpIndicator = "Hp3";
        else if (health < 75)
            hpIndicator = "Hp2";
        else if (health < 100)
            hpIndicator = "Hp1";

        if (health < 0 || TimeUtils.nanosToMillis(TimeUtils.timeSinceNanos(startTime)) >= trackMap.songLength + 4700) {
            this.game.setScreen(new ResultScreen(game, perfect + "", good + "", bad + "", miss + "", dfScore.format(score)));
            dispose();
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
        letters.dispose();
        judgements.dispose();
        hpBar.dispose();
        song.dispose();
        background.dispose();
        scoreDisplay.dispose();
        numbers.dispose();
    }

    private void showJudgement(String judgement) {
        judgementSprite = judgement;
        judgementX += MathUtils.random(-10, 10);
        judgementY += MathUtils.random(-10, 10);
        if (judgementY <= 570)
            judgementY = 570;
        if (judgementX < 0)
            judgementX = 0;
        timer.clear();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                judgementSprite = "Blank";
            }
        }, 2, 2, 0);
    }

    private void changeScore() {
        String scoreString = dfScore.format(score);
        for (int i = 0; i < 6; i++) {
            scoreNum.set(i, scoreString.substring(i, i + 1));
        }
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
            track = new Track(length,"", songLocation, objects);
        } catch (FileNotFoundException e) {
            System.out.println("Song not found");
        }
        return track;
    }

}
