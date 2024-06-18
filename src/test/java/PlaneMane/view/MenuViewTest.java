package PlaneMane.view;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import PlaneMane.model.Configurations;
import PlaneMane.model.GameState;
import PlaneMane.model.utils.IGameAssets;

public class MenuViewTest {
    private SpriteBatch mockBatch;
    private ViewableGameModel mockModel;
    private IGameAssets mockAssets;
    private MenuView menuView;
    private TextureRegion helpscreen, gameover, pausescreen;

    @BeforeEach
    void setUp() {
        mockBatch = mock(SpriteBatch.class);
        mockModel = mock(ViewableGameModel.class);
        mockAssets = mock(IGameAssets.class);
        when(mockModel.getAssets()).thenReturn(mockAssets);
        when(mockAssets.getRegion("helpscreen")).thenReturn(helpscreen);
        when(mockAssets.getRegion("gamover")).thenReturn(gameover);
        when(mockAssets.getRegion("pausescreen")).thenReturn(pausescreen);
        menuView = new MenuView(mockModel, mockBatch);

    }

    @Test
    void testInitAssets() {
        menuView.initAssets();

        assertTrue(menuView.assetsInit, "assetsInit should be true after initialization");
        verify(mockAssets).getRegion("helpscreen");
        verify(mockAssets).getRegion("gameoverscreen");
        verify(mockAssets).getRegion("pausescreen");
    }

    @Test
    void testDrawStartMenu() {
        menuView.assetsInit = true;

        menuView.drawStartMenu(mockBatch);

        verify(mockBatch).draw(eq(helpscreen), eq(-Configurations.WORLD_WIDTH+1), eq(0f),
                eq(Configurations.WORLD_WIDTH), eq(Configurations.WORLD_HEIGHT));
    }

    @Test
    void testDrawHelpScreen() {
        menuView.assetsInit = false;
        when(mockModel.isPlaneInGame()).thenReturn(false);
        when(mockModel.getGameState()).thenReturn(GameState.HELP);

        menuView.drawHelpScreen(mockBatch);

        verify(mockBatch).draw(eq(helpscreen), eq(-Configurations.WORLD_WIDTH + 1), eq(0f),
                eq(Configurations.WORLD_WIDTH), eq(Configurations.WORLD_HEIGHT));
    }

    @Test
    void testDrawHelpScreenAtGameStateWelcome() {
        menuView.assetsInit = true;
        when(mockModel.isPlaneInGame()).thenReturn(false);
        when(mockModel.getGameState()).thenReturn(GameState.WELCOME_SCREEN);

        menuView.drawHelpScreen(mockBatch);

        verify(mockBatch).draw(eq(helpscreen), eq(-Configurations.WORLD_WIDTH + 1), eq(0f),
                eq(Configurations.WORLD_WIDTH), eq(Configurations.WORLD_HEIGHT));
    }

    @Test
    void testDrawGameOverScreen() {
        menuView.drawGameOverScreen(mockBatch);

        verify(mockBatch).draw(eq(gameover), eq(0f), eq(0f),
                eq(Configurations.WORLD_WIDTH), eq(Configurations.WORLD_HEIGHT));

    }

    @Test
    void testDrawPauseScreenAtGameStatePaused() {
        menuView.assetsInit = true;
        when(mockModel.isPlaneInGame()).thenReturn(false);
        when(mockModel.getGameState()).thenReturn(GameState.PAUSED);

        menuView.drawPauseScreen(mockBatch);
        verify(mockBatch).draw(eq(pausescreen), eq(-Configurations.WORLD_WIDTH + 1), eq(0f),
                eq(Configurations.WORLD_WIDTH), eq(Configurations.WORLD_HEIGHT));
    }

    @Test
    void testDrawPauseScreenAtGameStateWelcomeScreen() {
        menuView.assetsInit = true;
        when(mockModel.isPlaneInGame()).thenReturn(false);
        when(mockModel.getGameState()).thenReturn(GameState.WELCOME_SCREEN);

        menuView.drawPauseScreen(mockBatch);
        verify(mockBatch).draw(eq(pausescreen), eq(-Configurations.WORLD_WIDTH + 1), eq(0f),
                eq(Configurations.WORLD_WIDTH), eq(Configurations.WORLD_HEIGHT));
    }

    @Test
    void testDrawPauseScreenAtGameStatePlaying() {
        menuView.assetsInit = true;
        when(mockModel.isPlaneInGame()).thenReturn(false);
        when(mockModel.getGameState()).thenReturn(GameState.PLAYING);

        menuView.drawPauseScreen(mockBatch);
        verify(mockBatch).draw(eq(pausescreen), eq(0f), eq(0f),
                eq(Configurations.WORLD_WIDTH), eq(Configurations.WORLD_HEIGHT));
    }

    @Test
    void testDispose() {
        menuView.dispose();
        verify(mockBatch, times(1)).dispose();
    }
}