package PlaneMane.model.entities;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Pilot class that represents the pilot in the game.
 */
public class Pilot extends Entity {
  public Pilot(World world, float x, float y, float width, float height, EntityGameModel model) {
    super(world, x, y, width, height, model, "planemane");
    Fixture fix = body.getFixtureList().get(0);
    fix.setUserData(this);
  }
}
