package PlaneMane.model.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Class to simplify making walls.
 */
public class Wall extends Entity {
    public Wall(World world, float x, float y, float width, float height, EntityGameModel model) {
        super(world, x, y, width, height, model, null);
    }

    @Override
    protected void makeBody(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x + width / 2, y + height / 2);

        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        box.dispose();
    }
}
