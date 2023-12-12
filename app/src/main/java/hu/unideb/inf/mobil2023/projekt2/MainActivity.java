package hu.unideb.inf.mobil2023.projekt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import hu.unideb.inf.mobil2023.projekt2.FileManager.FileListActivity;
import hu.unideb.inf.mobil2023.projekt2.FileManager.FileListAdapter;

public class MainActivity extends AppCompatActivity
{
    private MusicPlayer musicPlayerLeft;
    private MusicPlayer musicPlayerRight;
    private TextView titleLeft;
    private TextView titleRight;
    private SeekBar volumeControl;
    private final int SEEKBAR_SNAP_SENSITIVITY = 8;

    private boolean leftPaused = false;
    private boolean rightPaused = false;
    private boolean isPlayingLeft = false;
    private boolean isPlayingRight = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // force landscape mode
        setContentView(R.layout.activity_main);

        musicPlayerLeft = new MusicPlayer(this);
        musicPlayerRight = new MusicPlayer(this);

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
                if (Math.abs(seekBar.getProgress() - 50) <= SEEKBAR_SNAP_SENSITIVITY)
                {
                    volumeControl.setProgress(50);

                    Log.i("VBar","Snapped to middle position.");
                }

                else if(seekBar.getProgress() < SEEKBAR_SNAP_SENSITIVITY)
                {
                    volumeControl.setProgress(0);

                    Log.i("VBar","Snapped to left position.");
                }

                else if(seekBar.getProgress() > (100 - SEEKBAR_SNAP_SENSITIVITY))
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



        // FileManager FManager = new FileManager(this);
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