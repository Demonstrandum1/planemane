package PlaneMane.model;

import PlaneMane.model.utils.AudioUtil;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import PlaneMane.model.entities.Enemy;
import PlaneMane.model.entities.Energy;
import PlaneMane.model.entities.Pilot;
import PlaneMane.model.entities.Plane;
import PlaneMane.model.entities.Point;

public class PlaneContactListener implements ContactListener {

    private GameModel model;
    private AudioUtil audio;

    public PlaneContactListener(GameModel model) {
        this.model = model;
        this.audio = model.getAudio();
    }

    private boolean isPlane(Fixture f1, Fixture f2) {
        if (f1.getUserData() != null && (f1.getUserData() instanceof Plane) || f2.getUserData() != null
                && (f2.getUserData() instanceof Plane))
            return true;
        return false;
    }

    private boolean isBird(Fixture f1) {
        if (f1.getUserData() != null && (f1.getUserData() instanceof Enemy))
            return true;
        return false;
    }

    private boolean isEnergy(Fixture f1) {
        if (f1.getUserData() != null && (f1.getUserData() instanceof Energy))
            return true;
        return false;
    }

    private boolean isPoint(Fixture f1) {
        if (f1.getUserData() != null && (f1.getUserData() instanceof Point))
            return true;
        return false;
    }

    private boolean isPilot(Fixture f1) {
        if (f1.getUserData() != null && (f1.getUserData() instanceof Pilot))
            return true;
        return false;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture f1 = contact.getFixtureA(), f2 = contact.getFixtureB();
        if (isPlane(f1, f2) && (isBird(f1) || isBird(f2))) {
            if (isBird(f1)) {
                audio.playSoundEffects("audio/hit.mp3");
                ((Enemy) f1.getUserData()).kill();
            } else {
                audio.playSoundEffects("audio/hit.mp3");
                ((Enemy) f2.getUserData()).kill();
            }
            model.getPlane().decreaseEnergyLevel(15);
        }

        if (isPlane(f1, f2) && (isEnergy(f1) || isEnergy(f2))) {
            if (isEnergy(f1)) {
                audio.playSoundEffects("audio/energy.mp3");
                ((Energy) f1.getUserData()).takeEnergy();
            } else {
                audio.playSoundEffects("audio/energy.mp3");
                ((Energy) f2.getUserData()).takeEnergy();
            }
            model.getPlane().increaseEnergyLevel(15);
        }

        if (isPlane(f1, f2) && (isPoint(f1) || isPoint(f2))) {
            if (isPoint(f1)) {
                audio.playSoundEffects("audio/point.mp3");
                ((Point) f1.getUserData()).takePoint();
            } else {
                audio.playSoundEffects("audio/point.mp3");
                ((Point) f2.getUserData()).takePoint();
            }
            model.addPoints(100);
        }

        if (isPlane(f1, f2) && (isPilot(f1) || isPilot(f2))) {
            model.addEntityToRemove(model.getPilot());
            model.boardPlane();
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

}
