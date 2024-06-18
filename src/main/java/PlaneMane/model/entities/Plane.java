package PlaneMane.model.entities;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Plane class that represents the plane in the game.
 */

public class Plane extends Entity {
  private int energyLevel;
  private Fixture fix;

  public Plane(World world, float x, float y, float width, float height, EntityGameModel model) {
    super(world, x, y, width, height, model, "plane");
    fix = body.getFixtureList().get(0);
    fix.setUserData(this);
  }

  /**
   * Decreases the energy level of the plane by the given amount
   * 
   * @param amount
   */
  public void decreaseEnergyLevel(int amount) {
    energyLevel -= amount;
  }

  /**
   * Increases the energy level of the plane by the given amount
   * 
   * @param amount
   */
  public void increaseEnergyLevel(int amount) {
    if (energyLevel + amount > 100)
      energyLevel = 100;
    else
      energyLevel += amount;
  }

  /**
   * Returns the energy level of the plane
   * 
   * @return int
   */
  public int getEnergyLevel() {
    return energyLevel;
  }

  /**
   * Returns the fixture of the plane
   */
  public Fixture getFixture() {
    return fix;
  }
}
