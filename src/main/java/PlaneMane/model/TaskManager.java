package PlaneMane.model;

import PlaneMane.model.factories.EntityFactory;
import com.badlogic.gdx.utils.Timer;

/**
 * TaskManager class that manages the tasks in the game
 */

public class TaskManager extends Timer.Task {
    private EntityFactory enemyFactory;
    private EntityFactory objectFactory;
    private String entityTypes;
    private boolean isPaused;

    public TaskManager(EntityFactory enemyFactory, EntityFactory objectFactory, String entityTypes) {
        this.enemyFactory = enemyFactory;
        this.objectFactory = objectFactory;
        this.entityTypes = entityTypes;
        this.isPaused = false;
    }

    /**
     * Starts the game tasks
     */
    public void startGameTasks() {
        scheduleEntityTask(15);
    }

    /**
     * Restarts the game tasks
     */
    public void restartGameTasks() {
        Timer.instance().clear();
    }

    /**
     * Pauses the game tasks
     */
    public void pauseGameTasks() {
        Timer.instance().stop();
        isPaused = true;
    }

    /**
     * Resumes the game tasks
     */
    public void resumeGameTasks() {
        Timer.instance().start();
        isPaused = false;
    }

    /**
     * Checks if the gametasks are paused
     * 
     * @return boolean
     */
    public boolean isGameTasksPaused() {
        return isPaused;
    }

    private void scheduleEntityTask(float delaySeconds) {
        Timer.instance().scheduleTask(this, delaySeconds, delaySeconds);
    }

    @Override
    public void run() {
        for (char entityType : entityTypes.toCharArray()) {
            if (entityType == 'P') {
                enemyFactory.addEntity(entityType);
            } else if (entityType == 'E' || entityType == 'C') {
                objectFactory.addEntity(entityType);
            }
        }
    }
}
