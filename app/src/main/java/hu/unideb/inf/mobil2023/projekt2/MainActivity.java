package hu.unideb.inf.mobil2023.projekt2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import hu.unideb.inf.mobil2023.projekt2.FileManager.FileList;
import hu.unideb.inf.mobil2023.projekt2.FileManager.FileListActivity;
import hu.unideb.inf.mobil2023.projekt2.FileManager.FileListAdapter;

public class MainActivity extends AppCompatActivity
{
    private MusicPlayer musicPlayerLeft;
    private MusicPlayer musicPlayerRight;
    private MusicPlayer effectPlayer;
    private TextView titleLeft;
    private TextView titleRight;

    private SeekBar volumeControl;
    private final int SEEKBAR_SNAP_SENSITIVITY_SIDES = 8;
    private final int SEEKBAR_SNAP_SENSITIVITY_MIDDLE = 14;

    private boolean leftPaused = false;
    private boolean rightPaused = false;
    private boolean isPlayingLeft = false;
    private boolean isPlayingRight = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // force landscape mode
        setContentView(R.layout.activity_main);

        musicPlayerLeft = new MusicPlayer(this, 0.5f);
        musicPlayerRight = new MusicPlayer(this, 0.5f);
        effectPlayer = new MusicPlayer(this, 1.0f);

        ImageView turntableLeft = findViewById(R.id.turntableLeft);
        ImageView turntableRight = findViewById(R.id.turntableRight);

        titleLeft = findViewById(R.id.songTitleLeft);
        titleRight = findViewById(R.id.songTitleRight);

        volumeControl = findViewById(R.id.volumeSeekBar);
        volumeControl.setEnabled(false);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if (isPlayingLeft)
                {
                    musicPlayerLeft.setMusicVolume(100-progress);
                }

                if (isPlayingRight)
                {
                    musicPlayerRight.setMusicVolume(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                if (Math.abs(seekBar.getProgress() - 50) <= SEEKBAR_SNAP_SENSITIVITY_MIDDLE)
                {
                    volumeControl.setProgress(50);

                    Log.i("VBar","Snapped to middle position.");
                }

                else if(seekBar.getProgress() < SEEKBAR_SNAP_SENSITIVITY_SIDES)
                {
                    volumeControl.setProgress(0);

                    Log.i("VBar","Snapped to left position.");
                }

                else if(seekBar.getProgress() > (100 - SEEKBAR_SNAP_SENSITIVITY_SIDES))
                {
                    volumeControl.setProgress(100);

                    Log.i("VBar","Snapped to right position.");
                }

                else
                {
                    Log.i("VBar", "Current position is: " + seekBar.getProgress());
                }
            }
        });

        turntableLeft.setOnTouchListener((v, event) ->
                {
                    if(isPlayingLeft && !leftPaused)
                    {
                        switch (event.getAction())
                        {
                            case (MotionEvent.ACTION_DOWN):
                            {
                                Log.i("TTableLeft", "holding left");

                                musicPlayerLeft.pauseThisSong();

                                String effectName = FileList.getRandomScratchSFX();

                                Log.i("TTableLeft", "effect: " + effectName);

                                effectPlayer.playThisSong(effectName);

                                return true;
                            }
                            case (MotionEvent.ACTION_UP):
                            {
                                Log.i("TTableLeft", "released left");

                                musicPlayerLeft.pauseThisSong();

                                effectPlayer.stopThisSong();

                                return true;
                            }
                        }
                    }
                    return false;
                }
        );

        turntableRight.setOnTouchListener((v, event) ->
                {
                    if(isPlayingRight && !rightPaused)
                    {
                        switch (event.getAction())
                        {
                            case (MotionEvent.ACTION_DOWN):
                            {
                                Log.i("TTableRight", "holding right");

                                musicPlayerRight.pauseThisSong();

                                String effectName = FileList.getRandomScratchSFX();

                                Log.i("TTableRight", "effect: " + effectName);

                                effectPlayer.playThisSong(effectName);

                                return true;
                            }
                            case (MotionEvent.ACTION_UP):
                            {
                                Log.i("TTableRight", "released right");

                                musicPlayerRight.pauseThisSong();

                                effectPlayer.stopThisSong();

                                return true;
                            }
                        }
                    }
                    return false;
                }
        );
    }

    public void leftPlayPressed(View view)
    {
        volumeControl.setEnabled(true);

        if (leftPaused)
        {
            return;
        }

        String fName = FileListAdapter.getLeftSongPlaying();

        String songTitle = fName.split("\\.")[0];

        String s = getString(R.string.started_player) + ": " + songTitle;

        titleLeft.setText(s);

        if (!fName.equals("none"))
        {
            musicPlayerLeft.playThisSong(fName);

            isPlayingLeft = true;
        }
    }

    public void rightPlayPressed(View view)
    {
        volumeControl.setEnabled(true);

        if (rightPaused)
        {
            return;
        }

        String fName = FileListAdapter.getRightSongPlaying();

        String songTitle = fName.split("\\.")[0];

        String s = getString(R.string.started_player) + ": " + songTitle;

        titleRight.setText(s);

        if (!fName.equals("none"))
        {
            musicPlayerRight.playThisSong(fName);

            isPlayingRight = true;
        }
    }

    public void leftPausePressed(View view)
    {
        Button button = (Button) view;

        if (isPlayingLeft)
        {
            if (!leftPaused)
            {
                button.setText(R.string.resume_button_text);

                leftPaused = true;

                if (!isPlayingRight)
                {
                    volumeControl.setEnabled(false);
                }
            }

            else
            {
                button.setText(R.string.pause_button_text);

                leftPaused = false;

                volumeControl.setEnabled(true);
            }
        }
        musicPlayerLeft.pauseThisSong();
    }


    public void rightPausePressed(View view)
    {
        Button button = (Button) view;

        if (isPlayingRight)
        {
            if (!rightPaused)
            {
                button.setText(R.string.resume_button_text);

                rightPaused = true;

                if (!isPlayingLeft)
                {
                    volumeControl.setEnabled(false);
                }
            }

            else
            {
                button.setText(R.string.pause_button_text);

                rightPaused = false;

                volumeControl.setEnabled(true);
            }
        }
        musicPlayerRight.pauseThisSong();
    }

    public void leftStopPressed(View view)
    {
        musicPlayerLeft.stopThisSong();

        titleLeft.setText(R.string.stopped_player);

        isPlayingLeft = false;

        leftPaused = false;

        if (!isPlayingRight)
        {
            volumeControl.setEnabled(false);
        }
    }

    public void rightStopPressed(View view)
    {
        musicPlayerRight.stopThisSong();

        titleRight.setText(R.string.stopped_player);

        isPlayingRight = false;

        rightPaused = false;

        if (!isPlayingLeft)
        {
            volumeControl.setEnabled(false);
        }
    }

    public void openLeftFileBrowser(View view)
    {
        Intent intent = new Intent(this, FileListActivity.class);

        startActivity(intent);
    }
}