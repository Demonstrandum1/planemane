package PlaneMane.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Energy class represents the energy in the game.
 */

public class Energy extends Entity {
  boolean isTaken = false;

  public Energy(World world, float x, float y, float width, float height, EntityGameModel model) {
    super(world, x, y, width, height, model, "energy");
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
   * Method to take the energy
   */
  public void takeEnergy() {
    isTaken = true;
    collision();
  }
}
