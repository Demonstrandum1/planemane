package PlaneMane.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import PlaneMane.model.Configurations;
import PlaneMane.model.entities.Plane;
import PlaneMane.model.utils.IGameAssets;

public class HeartViewTest {

    private HeartView heartView;
    private SpriteBatch mockBatch;
    private ViewableGameModel mockModel;
    private Plane mockPlane;
    private IGameAssets mockAssets;
    private TextureRegion hearts1, hearts2, hearts3, hearts4, hearts5, hearts6, hearts7;

    @BeforeEach
    void setUp() {
        mockBatch = mock(SpriteBatch.class);
        mockModel = mock(ViewableGameModel.class);
        mockAssets = mock(IGameAssets.class);
        mockPlane = mock(Plane.class);

        hearts1 = mock(TextureRegion.class);
        hearts2 = mock(TextureRegion.class);
        hearts3 = mock(TextureRegion.class);
        hearts4 = mock(TextureRegion.class);
        hearts5 = mock(TextureRegion.class);
        hearts6 = mock(TextureRegion.class);
        hearts7 = mock(TextureRegion.class);

        when(mockModel.getAssets()).thenReturn(mockAssets);
        when(mockAssets.getRegion("hearts1")).thenReturn(hearts1);
        when(mockAssets.getRegion("hearts2")).thenReturn(hearts2);
        when(mockAssets.getRegion("hearts3")).thenReturn(hearts3);
        when(mockAssets.getRegion("hearts4")).thenReturn(hearts4);
        when(mockAssets.getRegion("hearts5")).thenReturn(hearts5);
        when(mockAssets.getRegion("hearts6")).thenReturn(hearts6);
        when(mockAssets.getRegion("hearts7")).thenReturn(hearts7);
        heartView = new HeartView(mockPlane, mockModel, mockBatch);
    }

    @Test
    public void testInitAssets() {
        assertFalse(heartView.assetsInit);
        heartView.getHeartTexture();
        assertTrue(heartView.assetsInit);
    }

    @Test
    public void testGetHeartsFromEnergyLevel() {
        for (int energyLevel = 0; energyLevel < 100; energyLevel += 5) {
            when(mockPlane.getEnergyLevel()).thenReturn(energyLevel);

            TextureRegion actualHeart = heartView.getHeartTexture();

            TextureRegion expectedHeart = getExpectedTexture(energyLevel);

            assertEquals(
                    expectedHeart,
                    actualHeart,
                    "Incorrect texture for energy level " + energyLevel);
        }
    }

    private TextureRegion getExpectedTexture(int energyLevel) {
        if (energyLevel > 85) {
            return hearts7;
        } else if (energyLevel > 70) {
            return hearts6;
        } else if (energyLevel > 55) {
            return hearts5;
        } else if (energyLevel > 40) {
            return hearts4;
        } else if (energyLevel > 25) {
            return hearts3;
        } else if (energyLevel > 10) {
            return hearts2;
        } else {
            return hearts1;
        }
    }

    @Test
    public void testDraw() {
        heartView.draw(mockBatch);
        verify(mockBatch).draw(any(TextureRegion.class),
                eq(Configurations.WORLD_WIDTH - Configurations.WORLD_WIDTH / 8),
                eq(Configurations.WORLD_HEIGHT - Configurations.WORLD_HEIGHT / 6),
                eq(Configurations.WORLD_WIDTH / 2 * 8 / 32),
                eq(Configurations.WORLD_HEIGHT / 2 * 8 / 32));
    }

    @Test
    public void testDispose() {
        heartView.dispose();
        verify(mockBatch).dispose();
    }
}
