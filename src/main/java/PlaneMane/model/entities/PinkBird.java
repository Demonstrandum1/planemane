package PlaneMane.model.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;

/**
 * PinkBird class represents the pink bird in the game.
 * PinkBird extends the enemy class and is a bird that moves towards the plane.
 */
public class PinkBird extends Enemy {
    private boolean isDodging = false;

    public PinkBird(World world, float x, float y, float width, float height, EntityGameModel model) {
        super(world, x, y, width, height, model, "pinkBird");
    }

    @Override
    public void update() {
        if (checkIfOutOfScreen()
                && isDead) {
            collision();
        } else if (isDead) {
            return;
        }

        if (!isDodging) {
            Vector2 planePosition = model.getPlane().getBody().getPosition();
            Vector2 currentPosition = body.getPosition();

            if (shouldStartDodging(planePosition, currentPosition)) {
                isDodging = true;
            } else {
                Vector2 direction = planePosition.sub(currentPosition).nor();
                float speed = calculateSpeed();
                body.setLinearVelocity(direction.scl(speed));
            }
        } else {
            body.setLinearVelocity(new Vector2(-calculateSpeed(), 0));
        }
    }

    protected boolean shouldStartDodging(Vector2 planePosition, Vector2 currentPosition) {
        return Math.abs(planePosition.x - currentPosition.x) < 2;
    }

    private float calculateSpeed() {
        float randomSpeedFactor = (float) Math.random() * 0.2f + 0.1f;
        float gameSpeedIncrease = (model.getGameTime() / 1000000) * 0.0002f;
        return (randomSpeedFactor + gameSpeedIncrease) * 10;
    }
}
