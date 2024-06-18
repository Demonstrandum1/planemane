package PlaneMane.model.entities;

import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Abstract class for enemies in the game.
 */

public abstract class Enemy extends Entity {
    boolean isDead = false;

    public Enemy(World world, float x, float y, float width, float height, EntityGameModel model,
            String texRegionString) {
        super(world, x, y, width, height, model, texRegionString);
        Fixture fix = body.getFixtureList().get(0);
        fix.setSensor(true);
        fix.setUserData(this);
    }

    @Override
    protected void setBodyType(BodyDef bodyDef) {
        bodyDef.type = BodyDef.BodyType.KinematicBody;
    }

    @Override
    public abstract void update();

    /**
     * Method to kill the enemy
     */
    public void kill() {
        isDead = true;
        stop();
    }

    /**
     * Method to stop the enemy
     */
    @Override
    public void stop() {
        body.setLinearVelocity(0, -20);
    }

    /**
     * Method to check if the enemy is out of the screen
     */
    public boolean checkIfOutOfScreen() {
        return (body.getPosition().y < -1);
    }
}
