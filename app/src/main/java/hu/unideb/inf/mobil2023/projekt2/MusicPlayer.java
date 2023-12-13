package hu.unideb.inf.mobil2023.projekt2;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import java.util.Objects;

public class MusicPlayer
{
    private final AssetManager assetManager;
    private final Object lock = new Object();
    private MediaPlayer musicPlayer;
    private boolean isPlaying = false;
    private boolean isPaused = false;


    public MusicPlayer(Context context)
    {
        Log.i("Mplayer", "creating new music player instance");

        this.assetManager = context.getAssets();
    }

    public void setMusicVolume(int value)
    {
        float musicVolume = (float) value / 100;

        musicPlayer.setVolume(musicVolume, musicVolume);
    }

    public void playThisSong(String fileName)
    {
        if (isPaused)
        {
            Log.i("Mplayer", "resuming song");

            isPaused = false;

            musicPlayer.start();

            return;
        }

        if (isPlaying)
        {
            Log.i("Mplayer", "music player already running");

            return;
        }

        Log.i("Mplayer", "starting music player");

        musicPlayer = new MediaPlayer();

        try
        {
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(fileName);
            musicPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            Log.i("Mplayer", "requested song successfully loaded");
        }

        catch (Exception e)
        {
            Log.e("MPlayer E", "error while trying to load song");
            Log.e("MPlayer E", Objects.requireNonNull(e.getMessage()));
            throw new RuntimeException(e);
        }

        Log.i("Mplayer", "initializing new thread");

        HandlerThread handlerThread = new HandlerThread("BackgroundThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        handler.post(() -> {
            Log.i("Mplayer", "new thread successfully started");

            synchronized (lock)
            {
            if (!musicPlayer.isPlaying())
            {
                try
                {
                    musicPlayer.prepare();
                    musicPlayer.start();
                    isPlaying = true;
                    Log.i("Mplayer", "playing song");
                }
                catch (Exception e)
                {
                    Log.e("MPlayer E", "error while trying to play song");
                    Log.e("MPlayer E", Objects.requireNonNull(e.getMessage()));
                    throw new RuntimeException(e);
                }
            }

            else
            {
                try
                {
                    Log.i("Mplayer", "restarting song");
                    musicPlayer.reset();
                    musicPlayer.start();
                }
                catch (Exception e)
                {
                    Log.e("MPlayer E", "error while trying to replay song");
                    Log.e("MPlayer E", Objects.requireNonNull(e.getMessage()));
                    throw new RuntimeException(e);
                }
            }
            }

        });
    }

    public void pauseThisSong()
    {
        if (!isPlaying)
        {
            Log.i("Mplayer", "player stopped or empty, nothing to pause");

            return;
        }

        if (!isPaused)
        {
            try
            {
                Log.i("Mplayer", "pausing song");

                musicPlayer.pause();

                isPaused = true;
            }

            catch(Exception e)
            {
                Log.e("MPlayer E", "error while trying to pause song");
                Log.e("MPlayer E", Objects.requireNonNull(e.getMessage()));
                throw new RuntimeException();
            }
        }
        else
        {
            Log.i("Mplayer", "resuming song");

            musicPlayer.start();

            isPaused = false;
        }
    }

    public void stopThisSong()
    {
        if (!isPlaying)
        {
            Log.i("Mplayer", "the player is empty or already stopped");

            return;
        }

        try
        {
            Log.i("Mplayer", "stopping song");
            musicPlayer.stop();
            isPlaying = false;
        }

        catch (Exception e)
        {
            Log.e("MPlayer E", "error while trying to stop song");
            Log.e("MPlayer E", Objects.requireNonNull(e.getMessage()));
            throw new RuntimeException();
        }
    }
}
