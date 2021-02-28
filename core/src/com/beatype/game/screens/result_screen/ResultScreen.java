package com.beatype.game.screens.result_screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.beatype.game.BeatType;

public class ResultScreen implements Screen {

    TextureAtlas ui;
    TextureAtlas judge;
    TextureAtlas numbers;
    final BeatType game;

    public ResultScreen(final BeatType game, int perfect, int good, int bad, int miss, double acc, int score) {
        this.game = game;
        ui = new TextureAtlas("core/assets/result/Result.atlas");
        judge = new TextureAtlas("core/assets/gameplay/judgement/judgements.atlas");
        numbers = new TextureAtlas("");

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        game.batch.begin();

        game.batch.end();
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
        ui.dispose();
        judge.dispose();
    }
}
