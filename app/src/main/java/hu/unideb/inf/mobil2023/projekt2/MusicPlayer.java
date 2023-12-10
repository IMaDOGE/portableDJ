package hu.unideb.inf.mobil2023.projekt2;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

public class MusicPlayer
{
    private Context context;
    private final AssetManager assetManager;
    private AssetFileDescriptor assetFileDescriptor;
    private Handler handler;
    private HandlerThread handlerThread;
    private final Object lock = new Object();
    private MediaPlayer musicPlayer;
    private boolean isPlaying = false;
    public MusicPlayer(Context context)
    {
        Log.i("Mplayer", "creating new music player instance");

        this.context = context;
        this.assetManager = context.getAssets();
    }

    public void playThisSong(String fileName)
    {
        if (isPlaying)
        {
            Log.i("Mplayer", "music player already running");

            return;
        }

        Log.i("Mplayer", "starting music player");

        musicPlayer = new MediaPlayer();

        try
        {
            assetFileDescriptor = assetManager.openFd(fileName);
            musicPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            Log.i("Mplayer", "requested song successfully loaded");
        }

        catch (Exception e)
        {
            Log.e("MPlayer E", "error while trying to load song");
            Log.e("MPlayer E", e.getMessage());
            throw new RuntimeException(e);
        }

        Log.i("Mplayer", "initializing new thread");

        this.handlerThread = new HandlerThread("BackgroundThread");
        this.handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper());

        handler.post(new Runnable() {
            @Override
            public void run()
            {
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
                        Log.e("MPlayer E", e.getMessage());
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
                        Log.e("MPlayer E", e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
                }

            }});
    }

    public void pauseThisSong()
    {
        try
        {
            Log.i("Mplayer", "pausing song");
            musicPlayer.pause();
        }

        catch(Exception e)
        {
            Log.e("MPlayer E", "error while trying to pause song");
            Log.e("MPlayer E", e.getMessage());
            throw new RuntimeException();
        }
    }

    public void stopThisSong()
    {
        if (!isPlaying)
        {
            Log.i("Mplayer", "the player is empty");

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
            Log.e("MPlayer E", e.getMessage());
            throw new RuntimeException();
        }
    }
}
