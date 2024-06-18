package PlaneMane.model.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Abstract class for entities in the game.
 */
public abstract class Entity extends Group {
  protected EntityGameModel model;
  protected Body body;
  protected World world;
  protected TextureRegion entitySprite;
  protected String texRegionString;

  public Entity(World world, float x, float y, float width, float height, EntityGameModel model,
      String texRegionString) {
    super();
    this.model = model;
    this.world = world;
    this.texRegionString = texRegionString;
    setSize(width, height);
    makeBody(x, y, width, height);
    body.setFixedRotation(true);

    update();
  }

  protected void makeBody(float x, float y, float width, float height) {
    BodyDef bodyDef = new BodyDef();
    setBodyType(bodyDef);
    bodyDef.position.set(x, y);
    body = world.createBody(bodyDef);

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(width / 2, height / 2);

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.density = 1.0f;
    fixtureDef.friction = 0.3f;
    fixtureDef.restitution = 0.1f;

    body.createFixture(fixtureDef);
    shape.dispose();

    update();
  }

  protected void setBodyType(BodyDef bodyDef) {
    bodyDef.type = BodyDef.BodyType.DynamicBody;
  }

  /**
   * Method to update the entity position
   */
  public void update() {
    setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
  }

  /**
   * Method to move the entity in a direction with a given speed
   */
  public void move(Vector2 direction, float speed) {
    Vector2 velocity = direction.scl(speed);
    body.setLinearVelocity(velocity.x, velocity.y);
  }

  /**
   * Method to stop the entity by setting the linear velocity to 0
   */
  public void stop() {
    body.setLinearVelocity(0, 0);
  }

  /**
   * Method to get the body of the entity
   * 
   * @return Body
   */
  public Body getBody() {
    return body;
  }

  /**
   * Method to get the entity sprite
   */
  public TextureRegion getEntitySprite() {
    return entitySprite;
  }

  /**
   * Netgod to get the texture region string
   */
  public String getTexRegionString() {
    return texRegionString;
  }

  /**
   * Removes objects when they collide with plane
   */
  protected void collision() {
    model.addEntityToRemove(this);
  }
}
