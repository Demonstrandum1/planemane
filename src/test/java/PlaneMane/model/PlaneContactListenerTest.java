package PlaneMane.model;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.physics.box2d.*;

import PlaneMane.model.entities.Enemy;
import PlaneMane.model.entities.Energy;
import PlaneMane.model.entities.Entity;
import PlaneMane.model.entities.Pilot;
import PlaneMane.model.entities.Point;
import PlaneMane.model.utils.GameAssets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PlaneContactListenerTest {

    private PlaneContactListener listener;
    private GameModel model;
    private Fixture planeFixture;

    @BeforeAll
    static void setUp() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {
            }

            @Override
            public void resize(int width, int height) {
            }

            @Override
            public void render() {
            }

            @Override
            public void pause() {
            }

            @Override
            public void resume() {
            }

            @Override
            public void dispose() {
            }
        }, config);
    }

    @BeforeEach
    void setUpEach() {
        GameAssets assets = mock(GameAssets.class);
        model = new GameModel(assets);
        listener = new PlaneContactListener(model);
        planeFixture = model.getPlane().getFixture();

    }

    // create a mock contact between plane and a fixture
    private Contact createMockContactPlaneAndFixture(Fixture fixtureA, Fixture fixtureB) {
        Contact mockContact = mock(Contact.class);
        when(mockContact.getFixtureA()).thenReturn(fixtureA);
        when(mockContact.getFixtureB()).thenReturn(fixtureB);
        return mockContact;
    }

    @Test
    void testBeginContactPlaneAndEnemy() {
        // create enemy
        Fixture mockFixtureEnemy = mock(Fixture.class);
        Enemy enemy = mock(Enemy.class);
        when(mockFixtureEnemy.getUserData()).thenReturn(enemy);

        // create contact
        Contact mockContact = createMockContactPlaneAndFixture(planeFixture, mockFixtureEnemy);

        // assert that the plane energy level is 100 before contact
        assertTrue(model.getPlane().getEnergyLevel() == 100);

        // begin contact
        listener.beginContact(mockContact);

        // verify that enemy is killed and plane energy level is reduced
        verify(enemy).kill();
        assertTrue(model.getPlane().getEnergyLevel() == 85);

        Contact mockContactOtherWay = createMockContactPlaneAndFixture(mockFixtureEnemy, planeFixture);
        listener.beginContact(mockContactOtherWay);

    }

    @Test
    void testBeginContactPlaneAndEnergy() {
        // create energy
        Fixture mockFixtureEnergy = mock(Fixture.class);
        Energy energy = mock(Energy.class);
        when(mockFixtureEnergy.getUserData()).thenReturn(energy);

        // assert that the plane energy level is 50 before contact
        model.getPlane().decreaseEnergyLevel(50);
        assertTrue(model.getPlane().getEnergyLevel() == 50);

        // create contact
        Contact mockContact = createMockContactPlaneAndFixture(planeFixture, mockFixtureEnergy);

        // begin contact
        listener.beginContact(mockContact);

        // verify that energy is taken and plane energy level is increased
        verify(energy).takeEnergy();
        assertTrue(model.getPlane().getEnergyLevel() == 65);
    }

    @Test
    void testBeginContactPlaneAndPoint() {
        // create point
        Fixture mockFixturePoint = mock(Fixture.class);
        Point point = mock(Point.class);
        when(mockFixturePoint.getUserData()).thenReturn(point);

        // create contact
        Contact mockContact = createMockContactPlaneAndFixture(planeFixture, mockFixturePoint);

        // assert that the current score is 0 before contact
        assertTrue(model.getCurrentScore() == 0);

        // begin contact
        listener.beginContact(mockContact);

        // verify that point is taken and score is increased
        verify(point).takePoint();
        assertTrue(model.getCurrentScore() == 100);

    }

    @Test
    void testBeginContactPlaneAndPilot() {
        // create pilot
        Fixture mockFixturePilot = mock(Fixture.class);
        Pilot pilot = mock(Pilot.class);
        when(mockFixturePilot.getUserData()).thenReturn(pilot);

        // assert that the pilot is in the game before contact
        boolean containsPilot = false;
        for (Entity entity : model.getEntities()) {
            if (entity instanceof Pilot) {
                containsPilot = true;
                break;
            }
        }
        assertTrue(containsPilot);

        // create contact
        Contact mockContact = createMockContactPlaneAndFixture(planeFixture, mockFixturePilot);

        // begin contact
        listener.beginContact(mockContact);

        // assert that the pilot is not in the game after contact
        boolean containsPilot2 = false;
        for (Entity entity : model.getEntities()) {
            if (entity instanceof Pilot) {
                containsPilot = true;
                break;
            }
        }
        assertFalse(containsPilot2);
        assertFalse(model.getControllerAllowed());
        assertTrue(model.getGameState() == GameState.PLAYING);
    }

    // The tests below are almost the same as the previous ones, but they test the
    // other way around
    @Test
    void testBeginContactPlaneAndEnemyOtherWay() {
        // create enemy
        Fixture mockFixtureEnemy = mock(Fixture.class);
        Enemy enemy = mock(Enemy.class);
        when(mockFixtureEnemy.getUserData()).thenReturn(enemy);

        // create contact
        Contact mockContact = createMockContactPlaneAndFixture(mockFixtureEnemy, planeFixture);

        // assert that the plane energy level is 100 before contact
        assertTrue(model.getPlane().getEnergyLevel() == 100);

        // begin contact
        listener.beginContact(mockContact);

        // verify that enemy is killed and plane energy level is reduced
        verify(enemy).kill();
        assertTrue(model.getPlane().getEnergyLevel() == 85);
    }

    @Test
    void testBeginContactPlaneAndEnergyOtherWay() {
        // create energy
        Fixture mockFixtureEnergy = mock(Fixture.class);
        Energy energy = mock(Energy.class);
        when(mockFixtureEnergy.getUserData()).thenReturn(energy);

        // assert that the plane energy level is 50 before contact
        model.getPlane().decreaseEnergyLevel(50);
        assertTrue(model.getPlane().getEnergyLevel() == 50);

        // create contact
        Contact mockContact = createMockContactPlaneAndFixture(mockFixtureEnergy, planeFixture);

        // begin contact
        listener.beginContact(mockContact);

        // verify that energy is taken and plane energy level is increased
        verify(energy).takeEnergy();
        assertTrue(model.getPlane().getEnergyLevel() == 65);
    }

    @Test
    void testBeginContactPlaneAndPointOtherWay() {
        // create point
        Fixture mockFixturePoint = mock(Fixture.class);
        Point point = mock(Point.class);
        when(mockFixturePoint.getUserData()).thenReturn(point);

        // create contact
        Contact mockContact = createMockContactPlaneAndFixture(mockFixturePoint, planeFixture);

        // assert that the current score is 0 before contact
        assertTrue(model.getCurrentScore() == 0);

        // begin contact
        listener.beginContact(mockContact);

        // verify that point is taken and score is increased
        verify(point).takePoint();
        assertTrue(model.getCurrentScore() == 100);

    }

    @Test
    void testBeginContactPlaneAndPilotOtherWay() {
        // create pilot
        Fixture mockFixturePilot = mock(Fixture.class);
        Pilot pilot = mock(Pilot.class);
        when(mockFixturePilot.getUserData()).thenReturn(pilot);

        // assert that the pilot is in the game before contact
        boolean containsPilot = false;
        for (Entity entity : model.getEntities()) {
            if (entity instanceof Pilot) {
                containsPilot = true;
                break;
            }
        }
        assertTrue(containsPilot);

        // create contact
        Contact mockContact = createMockContactPlaneAndFixture(mockFixturePilot, planeFixture);

        // begin contact
        listener.beginContact(mockContact);

        // assert that the pilot is not in the game after contact
        boolean containsPilot2 = false;
        for (Entity entity : model.getEntities()) {
            if (entity instanceof Pilot) {
                containsPilot = true;
                break;
            }
        }
        assertFalse(containsPilot2);
        assertFalse(model.getControllerAllowed());
        assertTrue(model.getGameState() == GameState.PLAYING);
    }
}
