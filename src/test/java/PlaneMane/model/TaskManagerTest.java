package PlaneMane.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application;

import PlaneMane.model.factories.EntityFactory;

public class TaskManagerTest {

    private EntityFactory mockEnemyFactory;
    private EntityFactory mockObjectFactory;
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {

        Gdx.app = mock(Application.class);

        mockEnemyFactory = mock(EntityFactory.class);
        mockObjectFactory = mock(EntityFactory.class);
        String entityTypes = "PEC";
        taskManager = new TaskManager(mockEnemyFactory, mockObjectFactory, entityTypes);
    }

    @Test
    void testStartGameTasks() {
        taskManager.startGameTasks();
        taskManager.run();
        verify(mockEnemyFactory, times(1)).addEntity('P');
    }

    @Test
    void testRestartGameTasks() {
        taskManager.restartGameTasks();
        verify(mockEnemyFactory, times(0)).addEntity('P');
    }

    @Test
    void testPauseGameTasks() {
        taskManager.pauseGameTasks();
        assertTrue(taskManager.isGameTasksPaused());
    }

    @Test
    void testResumeGameTasks() {
        taskManager.resumeGameTasks();
        assertFalse(taskManager.isGameTasksPaused());
    }

    @Test
    void testIsGameTasksPaused() {
        assertFalse(taskManager.isGameTasksPaused());
    }

    @Test
    void testRun() {
        taskManager.run();
        verify(mockEnemyFactory, times(1)).addEntity('P');
        verify(mockObjectFactory, times(1)).addEntity('E');
        verify(mockObjectFactory, times(1)).addEntity('C');

    }
}