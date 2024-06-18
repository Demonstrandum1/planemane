package PlaneMane.model.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioUtil {
    private Music musicPlayer;
    private Sound soundPlayer;
    private float volume = 1f;

    /**
     * 
     * Plays and loops music from the given file path, valid for WAV, MP3 and OGG
     * files. Makes use of the {@link #stopMusic()} to stop any previously playing
     * music before playing the chosen music. The music streams at full volume, and
     * must be disposed using the {@link #dispose()} method.
     * 
     * @param path the path to the music file to play.
     */
    public void streamMusic(String path) {
        stopMusic();
        musicPlayer = Gdx.audio.newMusic(Gdx.files.internal(path));
        this.musicPlayer.setLooping(true);
        this.musicPlayer.setVolume(volume);
        this.musicPlayer.play();
    }

    /**
     * 
     * Stops and disposes of currently playing music.
     * 
     * @see #streamMusic(String)
     */
    public void stopMusic() {
        if (this.musicPlayer != null) {
            this.musicPlayer.stop();
            disposeMusic();
        }
    }

    /**
     * Plays a sound effect file from the given path, valid for WAV, MP3 and OGG.
     * Multiple sound effects can be played at the same time, and must be disposed
     * using the {@link #dispose()} method. Files are played at full volume, and
     * cannot have a size of more than 1MB.
     * 
     * @param path the path to the sound effect file to play.
     */
    public void playSoundEffects(String path) {
        soundPlayer = Gdx.audio.newSound(Gdx.files.internal(path));
        this.soundPlayer.setVolume(soundPlayer.play(), volume);
        this.soundPlayer.play();
    }

    /**
     * Disposes all sound effects and music streams if not null.
     */
    public void disposeMusic() {
        if (this.musicPlayer != null) {
            this.musicPlayer.dispose();
            this.musicPlayer = null;
        }
        if (this.soundPlayer != null) {
            this.soundPlayer.dispose();
            this.soundPlayer = null;
        }
    }

    // Getters for testing purposes
    protected Sound getSoundPlayer() {
        return soundPlayer;
    }

    protected Music getMusicPlayer() {
        return musicPlayer;
    }

}