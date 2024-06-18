package PlaneMane.controller;

import PlaneMane.model.entities.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

import PlaneMane.model.GameState;
import PlaneMane.view.ViewableGameModel;

/**
 * Class for the game controller
 */
public class GameController extends InputAdapter {
    private Entity plane;
    private Entity pilot;
    private ViewableGameModel model;
    protected Vector2 movementDirection = new Vector2();
    protected boolean up = false;
    protected boolean down = false;
    protected boolean left = false;
    protected boolean right = false;

    public GameController(Entity plane, Entity pilot, ViewableGameModel model) {
        this.model = model;
        this.plane = plane;
        this.pilot = pilot;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (model.getGameState()) {
            case PLAYING:
                if (keycode == Input.Keys.P) {
                    model.pauseGame(GameState.PAUSED);
                    return true;
                } else if (keycode == Input.Keys.H) {
                    model.pauseGame(GameState.HELP);
                    return true;
                }
                break;
            case PAUSED:
                if (keycode == Input.Keys.P) {
                    if (!model.isPlaneInGame()) {
                        model.setGameState(GameState.WELCOME_SCREEN);
                    } else {
                        model.resumeGame();
                    }
                    return true;
                } else if (keycode == Input.Keys.H) {
                    model.pauseGame(GameState.HELP);
                    return true;
                }
                break;
            case HELP:
                if (keycode == Input.Keys.H) {
                    if (!model.isPlaneInGame()) {
                        model.setGameState(GameState.WELCOME_SCREEN);
                    } else {
                        model.resumeGame();
                    }
                    return true;
                } else if (keycode == Input.Keys.P) {
                    model.pauseGame(GameState.PAUSED);
                    return true;
                }
                break;
            case GAME_OVER:
                if (keycode == Input.Keys.SPACE) {
                    model.restartGame();
                    return true;
                }
                break;
            default:
                break;
        }
        updateDirectionBasedOnKeys(keycode, true);
        if ((up || down || left || right) && model.isPlaneInGame()) {
            plane.getBody().setGravityScale(1);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        updateDirectionBasedOnKeys(keycode, false);
        if (!up && !down && !left && !right && model.isPlaneInGame()) {
            plane.getBody().setGravityScale(1);
        }
        return true;
    }

    private void updateDirectionBasedOnKeys(int keycode, boolean pressed) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
                up = pressed;
                updateDirection();
                break;
            case Input.Keys.A:
            case Input.Keys.LEFT:
                left = pressed;
                updateDirection();
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                down = pressed;
                updateDirection();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                right = pressed;
                updateDirection();
                break;
            case Input.Keys.ESCAPE:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    private void updateDirection() {
        if (model.getControllerAllowed() == true) {
            movementDirection.set(0, 0);

            if (left && !right) {
                movementDirection.x = -1;
            } else if (!left && right) {
                movementDirection.x = 1;
            }

            if (up && !down) {
                movementDirection.y = 1;
            } else if (down && !up) {
                movementDirection.y = -1;
            }

            if (!movementDirection.isZero()) {
                movementDirection.nor();
            }

            if (model.getGameState() == GameState.WELCOME_SCREEN) {
                pilot.move(movementDirection, 8);
            } else if (model.getGameState() == GameState.PLAYING) {
                plane.move(movementDirection, 15);
            }
        }
    }
}
