package PlaneMane.model.entities;

import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.math.Vector2;

/**
 * WhiteBird class that represents the white bird in the game.
 */
public class WhiteBird extends Enemy {
    public WhiteBird(World world, float x, float y, float width, float height, EntityGameModel model) {
        super(world, x, y, width, height, model, "bird");
    }

    @Override
    public void update() {
        if (checkIfOutOfScreen()
                && isDead) {
            collision();
        } else if (isDead) {
            return;
        }

        Vector2 direction = new Vector2(-30, 0);
        float randomSpeedFactor = (float) Math.random() * 0.2f + 0.1f;
        float speed = randomSpeedFactor + (model.getGameTime() / 1000000) * 0.0002f;
        move(direction, speed);

        body.setLinearVelocity(direction.scl(speed));
    }
}
