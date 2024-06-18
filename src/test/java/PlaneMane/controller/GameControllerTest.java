package PlaneMane.controller;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import PlaneMane.model.GameState;
import PlaneMane.model.entities.Plane;
import PlaneMane.model.entities.Pilot;
import PlaneMane.view.ViewableGameModel;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {
    private ViewableGameModel model;
    private Plane mockPlane;
    private Body mockPlaneBody;
    private Pilot mockPilot;
    private GameController controller;

    @BeforeEach
    void setUpEach() {
        model = mock(ViewableGameModel.class);
        mockPlane = mock(Plane.class);
        mockPlaneBody = mock(Body.class);
        when(mockPlane.getBody()).thenReturn(mockPlaneBody);
        mockPilot = mock(Pilot.class);
        controller = new GameController(mockPlane, mockPilot, model);
    }

    @Test
    public void testGameController() {
        assertNotNull(controller);
        assertFalse(controller.up);
        assertFalse(controller.down);
        assertFalse(controller.left);
        assertFalse(controller.right);
    }

    @Test
    public void testPlayingToPause() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        assertTrue(model.getGameState() == GameState.PLAYING);
        assertTrue(controller.keyDown(Input.Keys.P));
        verify(model).pauseGame(GameState.PAUSED);
    }

    @Test
    public void testPlayingToHelp() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        assertTrue(model.getGameState() == GameState.PLAYING);
        controller.keyDown(Input.Keys.H);
        verify(model).pauseGame(GameState.HELP);
    }

    @Test
    public void testPauseToWelcomeScreen() {
        when(model.getGameState()).thenReturn(GameState.PAUSED);
        when(model.isPlaneInGame()).thenReturn(false);

        assertTrue(model.getGameState() == GameState.PAUSED);
        assertTrue(model.isPlaneInGame() == false);

        controller.keyDown(Input.Keys.P);
        verify(model).setGameState(GameState.WELCOME_SCREEN);
    }

    @Test
    public void testPauseToPlaying() {
        when(model.getGameState()).thenReturn(GameState.PAUSED);
        when(model.isPlaneInGame()).thenReturn(true);

        assertTrue(model.getGameState() == GameState.PAUSED);
        assertTrue(model.isPlaneInGame() == true);

        controller.keyDown(Input.Keys.P);
        verify(model).resumeGame();
    }

    @Test
    public void testPauseToHelp() {
        when(model.getGameState()).thenReturn(GameState.PAUSED);
        assertTrue(model.getGameState() == GameState.PAUSED);
        controller.keyDown(Input.Keys.H);
        verify(model).pauseGame(GameState.HELP);
    }

    @Test
    public void testHelpToWelcomeScreen() {
        when(model.getGameState()).thenReturn(GameState.HELP);
        when(model.isPlaneInGame()).thenReturn(false);

        assertTrue(model.getGameState() == GameState.HELP);
        assertTrue(model.isPlaneInGame() == false);

        controller.keyDown(Input.Keys.H);
        verify(model).setGameState(GameState.WELCOME_SCREEN);
    }

    @Test
    public void testHelpToPlaying() {
        when(model.getGameState()).thenReturn(GameState.HELP);
        when(model.isPlaneInGame()).thenReturn(true);
        assertTrue(model.getGameState() == GameState.HELP);
        assertTrue(model.isPlaneInGame() == true);
        controller.keyDown(Input.Keys.H);
        verify(model).resumeGame();
    }

    @Test
    public void testHelpToPause() {
        when(model.getGameState()).thenReturn(GameState.HELP);
        assertTrue(model.getGameState() == GameState.HELP);
        controller.keyDown(Input.Keys.P);
        verify(model).pauseGame(GameState.PAUSED);
    }

    @Test
    public void testGameOverToRestart() {
        when(model.getGameState()).thenReturn(GameState.GAME_OVER);
        assertTrue(model.getGameState() == GameState.GAME_OVER);
        controller.keyDown(Input.Keys.SPACE);
        verify(model).restartGame();
    }

    @Test
    public void testKeyDownChangeDirectionToTrue() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);

        controller.keyDown(Input.Keys.W);
        assertTrue(controller.up);
        assertFalse(controller.left);
        assertFalse(controller.down);
        assertFalse(controller.right);

        controller.keyDown(Input.Keys.A);
        assertTrue(controller.up);
        assertTrue(controller.left);
        assertFalse(controller.down);
        assertFalse(controller.right);

        controller.keyDown(Input.Keys.S);
        assertTrue(controller.up);
        assertTrue(controller.left);
        assertTrue(controller.down);
        assertFalse(controller.right);

        controller.keyDown(Input.Keys.D);
        assertTrue(controller.up);
        assertTrue(controller.left);
        assertTrue(controller.down);
        assertTrue(controller.right);
    }

    @Test
    public void testKeyUpChangeDirectionToFalse() {
        controller.up = true;
        controller.left = true;
        controller.down = true;
        controller.right = true;

        assertTrue(controller.up);
        assertTrue(controller.left);
        assertTrue(controller.down);
        assertTrue(controller.right);

        controller.keyUp(Input.Keys.W);
        assertFalse(controller.up);
        assertTrue(controller.down);
        assertTrue(controller.left);
        assertTrue(controller.right);

        controller.keyUp(Input.Keys.A);
        assertFalse(controller.up);
        assertFalse(controller.left);
        assertTrue(controller.down);
        assertTrue(controller.right);

        controller.keyUp(Input.Keys.S);
        assertFalse(controller.up);
        assertFalse(controller.left);
        assertFalse(controller.down);
        assertTrue(controller.right);

        controller.keyUp(Input.Keys.D);
        assertFalse(controller.up);
        assertFalse(controller.left);
        assertFalse(controller.down);
        assertFalse(controller.right);
    }

    @Test
    public void testKeysAreTheSame() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);

        assertEquals(controller.keyDown(Input.Keys.W), controller.keyDown(Input.Keys.UP));
        assertEquals(controller.keyDown(Input.Keys.A), controller.keyDown(Input.Keys.LEFT));
        assertEquals(controller.keyDown(Input.Keys.S), controller.keyDown(Input.Keys.DOWN));
        assertEquals(controller.keyDown(Input.Keys.D), controller.keyDown(Input.Keys.RIGHT));

        assertEquals(controller.keyUp(Input.Keys.W), controller.keyUp(Input.Keys.UP));
        assertEquals(controller.keyUp(Input.Keys.A), controller.keyUp(Input.Keys.LEFT));
        assertEquals(controller.keyUp(Input.Keys.S), controller.keyUp(Input.Keys.DOWN));
        assertEquals(controller.keyUp(Input.Keys.D), controller.keyUp(Input.Keys.RIGHT));
    }

    @Test
    public void testUpdateDirectionUp() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        when(model.getControllerAllowed()).thenReturn(true);

        assertTrue(controller.movementDirection.isZero());
        controller.keyDown(Input.Keys.W);
        assertTrue(controller.movementDirection.y == 1);
        assertTrue(controller.movementDirection.x == 0);
    }

    @Test
    public void testUpdateDirectionDown() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        when(model.getControllerAllowed()).thenReturn(true);

        assertTrue(controller.movementDirection.isZero());
        controller.keyDown(Input.Keys.S);
        assertTrue(controller.movementDirection.y == -1);
        assertTrue(controller.movementDirection.x == 0);
    }

    @Test
    public void testUpdateDirectionLeft() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        when(model.getControllerAllowed()).thenReturn(true);

        assertTrue(controller.movementDirection.isZero());
        controller.keyDown(Input.Keys.A);
        assertTrue(controller.movementDirection.y == 0);
        assertTrue(controller.movementDirection.x == -1);
    }

    @Test
    public void testUpdateDirectionRight() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        when(model.getControllerAllowed()).thenReturn(true);

        assertTrue(controller.movementDirection.isZero());
        controller.keyDown(Input.Keys.D);
        assertTrue(controller.movementDirection.y == 0);
        assertTrue(controller.movementDirection.x == 1);
    }

    @Test
    public void testUpdateDirectionUpAndDown() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        when(model.getControllerAllowed()).thenReturn(true);

        assertTrue(controller.movementDirection.isZero());
        controller.keyDown(Input.Keys.W);
        controller.keyDown(Input.Keys.S);

        Vector2 expectedDirection = new Vector2(0, 0);
        assertEquals(expectedDirection, controller.movementDirection);
    }

    @Test
    public void testUpdateDirectionLeftAndRight() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        when(model.getControllerAllowed()).thenReturn(true);

        assertTrue(controller.movementDirection.isZero());
        controller.keyDown(Input.Keys.A);
        controller.keyDown(Input.Keys.D);

        Vector2 expectedDirection = new Vector2(0, 0);
        assertEquals(expectedDirection, controller.movementDirection);
    }

    @Test
    public void testUpdateDirectionUpAndLeft() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        when(model.getControllerAllowed()).thenReturn(true);

        assertTrue(controller.movementDirection.isZero());
        controller.keyDown(Input.Keys.W);
        controller.keyDown(Input.Keys.A);

        Vector2 expectedDirection = new Vector2(-1, 1).nor();
        assertEquals(expectedDirection, controller.movementDirection);

    }

    @Test
    public void testUpdateDirectionUpAndRight() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        when(model.getControllerAllowed()).thenReturn(true);

        assertTrue(controller.movementDirection.isZero());
        controller.keyDown(Input.Keys.W);
        controller.keyDown(Input.Keys.D);

        Vector2 expectedDirection = new Vector2(1, 1).nor();
        assertEquals(expectedDirection, controller.movementDirection);
    }

    @Test
    public void testUpdateDirectionDownAndLeft() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        when(model.getControllerAllowed()).thenReturn(true);

        assertTrue(controller.movementDirection.isZero());
        controller.keyDown(Input.Keys.S);
        controller.keyDown(Input.Keys.A);

        Vector2 expectedDirection = new Vector2(-1, -1).nor();
        assertEquals(expectedDirection, controller.movementDirection);
    }

    @Test
    public void testUpdateDirectionDownAndRight() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        when(model.getControllerAllowed()).thenReturn(true);

        assertTrue(controller.movementDirection.isZero());
        controller.keyDown(Input.Keys.S);
        controller.keyDown(Input.Keys.D);

        Vector2 expectedDirection = new Vector2(1, -1).nor();
        assertEquals(expectedDirection, controller.movementDirection);
    }

    @Test
    public void testUpdateDirectionWelcomeScreen() {
        when(model.getGameState()).thenReturn(GameState.WELCOME_SCREEN);
        when(model.getControllerAllowed()).thenReturn(true);

        assertTrue(controller.movementDirection.isZero());
        controller.keyDown(Input.Keys.W);
        controller.keyDown(Input.Keys.A);
        controller.keyDown(Input.Keys.S);
        controller.keyDown(Input.Keys.D);
        assertTrue(controller.movementDirection.isZero());
    }

    @Test
    public void testSetGravity() {
        when(model.getGameState()).thenReturn(GameState.PLAYING);
        when(model.isPlaneInGame()).thenReturn(true);

        controller.keyDown(Input.Keys.W);
        verify(mockPlane.getBody()).setGravityScale(1);

        controller.keyUp(Input.Keys.W);
        verify(mockPlane.getBody(), times(2)).setGravityScale(1);
    }
}
