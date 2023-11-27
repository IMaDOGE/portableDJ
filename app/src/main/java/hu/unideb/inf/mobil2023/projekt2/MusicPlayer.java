package hu.unideb.inf.mobil2023.projekt2;

import android.media.MediaPlayer;
import android.util.Log;

public class MusicPlayer
{
    private final MediaPlayer musicPlayer = new MediaPlayer();
    public void playThisSong(String path) {
        try
        {
            if (!musicPlayer.isPlaying())
            {
                musicPlayer.setDataSource(path);
                musicPlayer.prepare();
                // Start playing the Music file
                musicPlayer.start();
            }
            else
            {
                musicPlayer.reset();
                musicPlayer.start();
            }
        }
        catch (Exception e)
        {
            Log.e("MusicPlayerError", e.getMessage());
        }
    }

    public void pauseThisSong()
    {
        try
        {
            musicPlayer.pause();
        }

        catch(Exception e)
        {
            Log.e("MusicPlayerError", e.getMessage());
        }
    }

    public void stopThisSong()
    {
        try
        {
            musicPlayer.stop();
        }

        catch (Exception e)
        {
            Log.e("MusicPlayerError", e.getMessage());
        }
    }
}
