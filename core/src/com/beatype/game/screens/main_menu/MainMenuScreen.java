package com.beatype.game.screens.main_menu;

import com.badlogic.gdx.Screen;
import com.beatype.game.BeatType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class MainMenuScreen implements Screen {

    final BeatType game;

    private Texture logoImage;
    private Texture exitImage;
    private Texture playImage;

    private SpriteBatch batch;


    public MainMenuScreen(final BeatType game) {
        this.game = game;

        logoImage = new Texture(Gdx.files.internal("Logo.png"));
        exitImage = new Texture(Gdx.files.internal("ExitArrow.png"));
        playImage = new Texture(Gdx.files.internal(("PlayArrow.png")));



    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.8f, 0.8f, 0.95f, 1);

        batch.begin();
        batch.draw(logoImage, 0, 0);
        batch.draw(exitImage, 0, 0);
        batch.draw(playImage, 0, 0);

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
        logoImage.dispose();
        exitImage.dispose();
        playImage.dispose();
    }


}
