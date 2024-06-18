package PlaneMane;

import com.badlogic.gdx.Game;

import PlaneMane.model.GameModel;
import PlaneMane.model.utils.GameAssets;
import PlaneMane.view.GameView;

/**
 * PLanemane class used for creating and rendering the game
 */
public class PlaneMane extends Game {
    private GameAssets assets;
    private boolean loaded = false;

    @Override
    public void create() {
        assets = new GameAssets();
    }

    @Override
    public void render() {
        super.render();
        if (!loaded && assets.update()) {
            loaded = true;

            GameModel model = new GameModel(assets);
            this.setScreen(new GameView(model));
        }
    }

}
