package PlaneMane.view;

import PlaneMane.model.GameState;
import PlaneMane.model.entities.Entity;
import PlaneMane.model.entities.Pilot;
import PlaneMane.model.entities.Plane;
import PlaneMane.model.utils.AudioUtil;
import PlaneMane.model.utils.IGameAssets;

import com.badlogic.gdx.utils.Array;

/**
 * This interface is used to get information about the game from the model
 */
public interface ViewableGameModel {

    /**
     * Updates the game model
     *
     * @param delta
     */
    public void update(float delta);

    /**
     * Returns the entities
     *
     * @return Array<Entity>
     */
    public Array<Entity> getEntities();

    /**
     * Sets the game state
     *
     * @param gameState
     */
    public void setGameState(GameState gameState);

    /**
     * Returns the game state
     *
     * @return GameState
     */
    public GameState getGameState();

    /**
     * Removes entities
     */
    public void removeEntities();

    /**
     * Checks if the plane is in game
     *
     * @return boolean
     */
    public boolean isPlaneInGame();

    /**
     * Returns the audio
     *
     * @return AudioUtil
     */
    public AudioUtil getAudio();

    /**
     * Returns the assets
     *
     * @return IGameAssets
     */
    public IGameAssets getAssets();

    /**
     * Returns the plane
     *
     * @return Plane
     */
    public Plane getPlane();

    /**
     * Returns the pilot
     *
     * @return Pilot
     */
    public Pilot getPilot();

    /**
     * Returns the current games score
     *
     * @return long
     */
    public long getCurrentScore();

    /**
     * Checks if use of the controller is allowed.
     * 
     * @return boolean indicating whether use of controller is allowed or not.
     */
    public boolean getControllerAllowed();

    /**
     * Restarts the game
     */
    public void restartGame();

    /**
     * Pauses the game and sets it to
     * the given gamestate
     * 
     * @param gs
     */
    public void pauseGame(GameState gs);

    /**
     * Resumes the game
     */
    public void resumeGame();

    /**
     * Checks if the plane is currently in a legal position.
     */
    // public boolean isLegalPlanePosition(); TODO: Implement this method if needed

}
