package PlaneMane.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import PlaneMane.controller.GameController;
import PlaneMane.model.Configurations;
import PlaneMane.model.GameState;
import PlaneMane.model.entities.Entity;

/**
 * Class from drawing and rendering the game
 */

public class GameView implements Screen {
    private ViewableGameModel model;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BackgroundView background;
    private StretchViewport viewport;
    private GameController controller;
    private HeartView hearts;
    private MenuView menu;
    private BitmapFont font;

    public GameView(ViewableGameModel model) {
        this.model = model;
        this.camera = new OrthographicCamera(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT);
        this.batch = new SpriteBatch();
        this.controller = new GameController(model.getPlane(), model.getPilot(),
                model);
        this.viewport = new StretchViewport(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT, camera);
        this.background = new BackgroundView(model, batch);
        this.hearts = new HeartView(model.getPlane(), model, batch);
        this.menu = new MenuView(model, batch);
        this.font = new BitmapFont();

        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void show() {
        model.getAudio().streamMusic("audio/pm-music1.mp3");
        setCameraPosition();
        camera.translate(-Configurations.WORLD_WIDTH + 1, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render(float delta) {
        if (!model.getAssets().allAssetsLoaded()) {
            model.getAssets().update();
            return;
        }

        if (model.getGameState() == GameState.GAME_OVER) {
            batch.begin();
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            menu.drawGameOverScreen(batch);
            batch.end();
            return;
        }
        Gdx.gl.glClearColor(0xAC / 255f, 0xDE / 255f, 0xFE / 255f, 1);

        if (model.getGameState() == GameState.HELP) {
            batch.begin();
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            menu.drawHelpScreen(batch);
            batch.end();
            return;
        }

        if (model.getGameState() == GameState.PAUSED) {
            batch.begin();
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            menu.drawPauseScreen(batch);
            batch.end();
            return;
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (model.getGameState() == GameState.PLAYING) {
            setCameraPosition();
        }

        model.removeEntities();
        model.update(delta);

        batch.begin();

        if (model.getGameState() == GameState.PLAYING) {
            background.update(delta);
            background.draw(batch);
            drawText(batch);
        }

        if (model.getGameState() == GameState.WELCOME_SCREEN) {
            menu.drawStartMenu(batch);
        }

        drawEntities();

        hearts.draw(batch);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        // ...
    }

    @Override
    public void hide() {
        // ...
    }

    @Override
    public void dispose() {
        batch.dispose();
        menu.dispose();
    }

    private void setCameraPosition() {
        camera.position.set(Configurations.WORLD_WIDTH / 2, Configurations.WORLD_HEIGHT / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    private void drawText(SpriteBatch batch) {
        
        String text = "SCORE. " + model.getCurrentScore();
        GlyphLayout layout = new GlyphLayout(font, text);
        float x = 0;
        float y = Configurations.WORLD_HEIGHT - layout.height;

        font.setColor(Color.BLACK);

        font.getData().setScale(0.1f, Configurations.WORLD_HEIGHT / 200);


        font.draw(batch, text, x, y);
    }

    private void drawEntities() {
        for (Entity entity : model.getEntities()) {
            TextureRegion entitySprite = entity.getEntitySprite();
            if (entitySprite == null && entity.getTexRegionString() != null) {
                entitySprite = model.getAssets().getRegion(entity.getTexRegionString());
            }
            if (entitySprite != null) {
                float width = entity.getWidth();
                float height = entity.getHeight();
                float x = entity.getBody().getPosition().x - width / 2;
                float y = entity.getBody().getPosition().y - height / 2;

                batch.draw(entitySprite, x, y, width, height);
            }
        }
    }

    /**
     * 
     * @return the HeartView instance
     */
    public HeartView getHeartView() {
        return hearts;
    }

}