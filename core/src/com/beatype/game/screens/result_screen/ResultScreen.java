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

    String perfect, good, bad, miss, acc, score;

    public ResultScreen(final BeatType game, String perfect, String good, String bad, String miss, String acc, String score) {
        this.game = game;
        this.perfect = perfect;
        this.good = good;
        this.bad = bad;
        this.miss = miss;
        this.acc = acc;
        this.score = score;
        ui = new TextureAtlas("core/assets/result/Result.atlas");
        judge = new TextureAtlas("core/assets/gameplay/judgement/judgements.atlas");
        numbers = new TextureAtlas("core/assets/gameplay/Number/Numbers.atlas");

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        game.batch.begin();
        game.batch.draw(ui.findRegion("RankingPanel"), 40, 200);
        game.batch.draw(ui.findRegion("ET"), 800, 400);
        game.batch.draw(judge.findRegion("Perfect"), 60, 700);
        renderNumber(perfect, 140, 710);
        game.batch.draw(judge.findRegion("Good"), 400, 700);
        renderNumber(good, 480, 710);
        game.batch.draw(judge.findRegion("Bad"), 60, 500);
        renderNumber(bad, 140, 510);
        game.batch.draw(judge.findRegion("Miss"), 400, 500);
        renderNumber(miss, 480, 510);
        renderNumber(score, 420, 250);
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
        numbers.dispose();
    }

    private void renderNumber(String number, int x, int y) {
        int count = 0;
        for (char i : number.toCharArray()) {
            game.batch.draw(numbers.findRegion(String.valueOf(i)), x + 50 * (count + 1), y);
            count++;
        }
    }
}
