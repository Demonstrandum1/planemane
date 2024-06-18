package PlaneMane.view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import PlaneMane.model.Configurations;
import PlaneMane.model.utils.IGameAssets;

public class BackgroundViewTest {

    private BackgroundView backgroundView;
    private SpriteBatch mockBatch;
    private ViewableGameModel mockModel;
    private IGameAssets mockAssets;
    private TextureRegion mockGround, mockRoad, mockTrees, mockClouds;

    @BeforeEach
    void setUp() {
        mockBatch = mock(SpriteBatch.class);
        mockModel = mock(ViewableGameModel.class);
        mockAssets = mock(IGameAssets.class);

        mockGround = mock(TextureRegion.class);
        mockRoad = mock(TextureRegion.class);
        mockTrees = mock(TextureRegion.class);
        mockClouds = mock(TextureRegion.class);

        when(mockModel.getAssets()).thenReturn(mockAssets);
        when(mockAssets.getRegion("ground")).thenReturn(mockGround);
        when(mockAssets.getRegion("road")).thenReturn(mockRoad);
        when(mockAssets.getRegion("trees")).thenReturn(mockTrees);
        when(mockAssets.getRegion("clouds")).thenReturn(mockClouds);

        backgroundView = new BackgroundView(mockModel, mockBatch);
    }

    @Test
    void testInitialAssetInitialization() {
        backgroundView.update(0);
        verify(mockAssets, times(1)).getRegion("ground");
        verify(mockAssets, times(1)).getRegion("road");
        verify(mockAssets, times(1)).getRegion("trees");
        verify(mockAssets, times(1)).getRegion("clouds");
        assertTrue(backgroundView.assetsInit, "Assets should be initialized after update call.");
    }

    @Test
    void testBackgroundScrollingWhenPlaneIsActive() {
        when(mockModel.isPlaneInGame()).thenReturn(true);
        float initialX = 0;
        float delta = 0.1f;

        backgroundView.update(delta);
        float expectedGroundX = initialX - (Configurations.GROUND_SCROLL_SPEED * delta);
        float expectedRoadX = initialX - (Configurations.ROAD_SCROLL_SPEED * delta);
        float expectedTreesX = initialX - (Configurations.TREES_SCROLL_SPEED * delta);
        float expectedCloudsX = initialX - (Configurations.CLOUDS_SCROLL_SPEED * delta);

        assertEquals(expectedGroundX, backgroundView.groundX, "Ground layer should scroll correctly.");
        assertEquals(expectedRoadX, backgroundView.roadX, "Road layer should scroll correctly.");
        assertEquals(expectedTreesX, backgroundView.treesX, "Trees layer should scroll correctly.");
        assertEquals(expectedCloudsX, backgroundView.cloudsX, "Clouds layer should scroll correctly.");
    }

    @Test
    void testDrawingBackground() {
        backgroundView.update(0);
        backgroundView.draw(mockBatch);
        verify(mockBatch, times(2)).draw(eq(mockGround), anyFloat(), anyFloat(), anyFloat(), anyFloat());
        verify(mockBatch, times(2)).draw(eq(mockRoad), anyFloat(), anyFloat(), anyFloat(), anyFloat());
        verify(mockBatch, times(2)).draw(eq(mockTrees), anyFloat(), anyFloat(), anyFloat(), anyFloat());
        verify(mockBatch, times(2)).draw(eq(mockClouds), anyFloat(), anyFloat(), anyFloat(), anyFloat());
    }

    @Test
    void testNoScrollingWhenPlaneIsNotInGame() {
        when(mockModel.isPlaneInGame()).thenReturn(false);
        float delta = 0.1f;
        backgroundView.update(delta);
        assertEquals(-Configurations.WORLD_WIDTH + 1, backgroundView.groundX,
                "When plane is not in game, ground should not scroll.");
        assertEquals(-Configurations.WORLD_WIDTH + 1, backgroundView.roadX,
                "When plane is not in game, road should not scroll.");
        assertEquals(-Configurations.WORLD_WIDTH + 1, backgroundView.treesX,
                "When plane is not in game, trees should not scroll.");
        assertEquals(-Configurations.WORLD_WIDTH + 1, backgroundView.cloudsX,
                "When plane is not in game, clouds should not scroll.");
    }

    @Test 
    void testDispose() {
        backgroundView.dispose();
        verify(mockBatch).dispose();
    }
}
