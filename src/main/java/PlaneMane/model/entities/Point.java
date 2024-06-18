package PlaneMane.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import PlaneMane.model.GameModel;

/**
 * Point class represents the points in the game.
 */

public class Point extends Entity {
    boolean isTaken = false;

    public Point(World world, float x, float y, float width, float height, GameModel model, String texRegionString) {
        super(world, x, y, width, height, model, "point");
        Fixture fix = body.getFixtureList().get(0);
        fix.setSensor(true);
        fix.setUserData(this);
    }

    @Override
    protected void setBodyType(BodyDef bodyDef) {
        bodyDef.type = BodyDef.BodyType.KinematicBody;
    }

    @Override
    public void update() {
        if (isTaken)
            return;

        super.update();

        Vector2 direction = new Vector2(-30, 0);
        float speed = 0.1f + (model.getGameTime() / 1000000) * 0.0001f;

        move(direction, speed);
    }

    /**
     * Method to take the point
     */
    public void takePoint() {
        isTaken = true;
        collision();
    }
}
