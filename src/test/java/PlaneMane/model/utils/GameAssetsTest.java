package PlaneMane.model.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.assets.AssetManager;

public class GameAssetsTest {
    private GameAssets assets;
    private AssetManager assetManager;

    @BeforeEach
    void setUpEach() {

        assetManager = mock(AssetManager.class);
        assets = new GameAssets();
        assets.setAssetManager(assetManager);

        when(assetManager.update()).thenReturn(true);
    }

    @AfterEach
    void tearDown() {
        assets.disposeAssets();
    }

    @Test
    void testUpdate() {
        assertTrue(assets.update(), "Assets should be loaded after update");
    }

    @Test
    void testAllAssetsLoaded() {
        assertTrue(assets.update(), "Assets should be loaded after update");
        assertTrue(assets.allAssetsLoaded(), "All assets should be loaded");
    }

    @Test
    void testDisposeAssets() {
        assertTrue(assets.update(), "Assets should be loaded after update");

        assets.disposeAssets();
        assertFalse(assets.allAssetsLoaded(), "All assets should be disposed");
    }
}
