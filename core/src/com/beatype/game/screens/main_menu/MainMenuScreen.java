package com.beatype.game.screens.main_menu;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.beatype.game.BeatType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.beatype.game.screens.game_screen.GameplayScreen;

public class MainMenuScreen implements Screen {

    final BeatType game;

    private Texture logoImage;
    private Texture exitImage;
    private Texture playImage;

    private static final int EXIT_BUTTON_WIDTH = 237;
    private static final int EXIT_BUTTON_HEIGHT = 315;
    private static final int PLAY_BUTTON_WIDTH = 239;
    private static final int PLAY_BUTTON_HEIGHT = 316;
    private static final int EXIT_BUTTON_X = 300;
    private static final int EXIT_BUTTON_Y = 210;
    private static final int PLAY_BUTTON_X = 1050;
    private static final int PLAY_BUTTON_Y = 210;

    private final MainMenuScreen menuScreen = this;

    public MainMenuScreen(final BeatType game) {
        this.game = game;

        // define image paths
        logoImage = new Texture(Gdx.files.internal("Logo.png"));
        exitImage = new Texture(Gdx.files.internal("ExitArrow.png"));
        playImage = new Texture(Gdx.files.internal("PlayArrow.png"));

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //Exit button
                if (screenX >= EXIT_BUTTON_X && screenX <= EXIT_BUTTON_X + EXIT_BUTTON_WIDTH && 900 - screenY <= 210 + EXIT_BUTTON_HEIGHT && 900 - screenY >= 210) {
                    System.out.println("exit clicked!");
                    menuScreen.dispose();
                    Gdx.app.exit();
                }

                //Play game button
                if (screenX >= PLAY_BUTTON_X && screenX <= PLAY_BUTTON_X + PLAY_BUTTON_WIDTH && 900 - screenY <= 210 + PLAY_BUTTON_HEIGHT && 900 - screenY >= 210) {
                    System.out.println("play clicked!");
                    menuScreen.dispose();
                    game.setScreen(new GameplayScreen(game));
                }

                return super.touchUp(screenX, screenY, pointer, button);
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.70f, 0.82f, 0.93f, 1);

        game.batch.begin();
        // carefully calculated values and definitely not just
        // picking random ones until they work
        game.batch.draw(logoImage, 399, 150, 802, 664);
        game.batch.draw(exitImage, EXIT_BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        game.batch.draw(playImage, PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
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
        logoImage.dispose();
        exitImage.dispose();
        playImage.dispose();
    }


}
