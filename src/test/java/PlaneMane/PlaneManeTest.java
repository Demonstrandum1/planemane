package PlaneMane;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Game;

public class PlaneManeTest {

    @Test
    // Test the create method of the PlaneMane class
    public void testCreate() {

        // Create a new PlaneMane object
        PlaneMane planeMane = new PlaneMane();

        // Call the create method of the PlaneMane object
        planeMane.create();

        // Assert that the PlaneMane object is not null
        assert planeMane != null;

        // Assert that the PlaneMane object is an instance of the PlaneMane class
        assert planeMane instanceof PlaneMane;

        // Assert that the PlaneMane object is an instance of the Game class
        assert planeMane instanceof Game;

        // Assert that the PlaneMane object is an instance of the Object class
        assert planeMane instanceof Object;
    }

}
