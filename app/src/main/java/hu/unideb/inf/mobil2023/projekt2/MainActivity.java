package hu.unideb.inf.mobil2023.projekt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hu.unideb.inf.mobil2023.projekt2.FileManager.FileListActivity;
import hu.unideb.inf.mobil2023.projekt2.FileManager.FileListAdapter;

public class MainActivity extends AppCompatActivity
{
    private MusicPlayer musicPlayerLeft;
    private MusicPlayer musicPlayerRight;
    private TextView titleLeft;
    private TextView titleRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // force landscape mode
        setContentView(R.layout.activity_main);

        musicPlayerLeft = new MusicPlayer(this);
        musicPlayerRight = new MusicPlayer(this);

        titleLeft = findViewById(R.id.songTitleLeft);
        titleRight = findViewById(R.id.songTitleRight);

        // FileManager FManager = new FileManager(this);
    }

    public void leftPlayPressed(View view)
    {
        String fName = FileListAdapter.getLeftSongPlaying();

        String songTitle = fName.split("\\.")[0];

        String s = getString(R.string.started_player) + ": " + songTitle;

        titleLeft.setText(s);

        if (!fName.equals("none"))
        {
            musicPlayerLeft.playThisSong(fName);
        }
    }

    public void rightPlayPressed(View view)
    {
        String fName = FileListAdapter.getRightSongPlaying();

        String songTitle = fName.split("\\.")[0];

        String s = getString(R.string.started_player) + ": " + songTitle;

        titleRight.setText(s);

        if (!fName.equals("none"))
        {
            musicPlayerRight.playThisSong(fName);
        }
    }

    public void leftPausePressed(View view)
    {
        titleLeft.setText(R.string.paused_player);
    }

    public void rightPausePressed(View view)
    {
        titleRight.setText(R.string.paused_player);
    }

    public void leftStopPressed(View view)
    {
        musicPlayerLeft.stopThisSong();

        titleLeft.setText(R.string.stopped_player);
    }

    public void rightStopPressed(View view)
    {
        musicPlayerRight.stopThisSong();

        titleRight.setText(R.string.stopped_player);
    }

    public void openLeftFileBrowser(View view)
    {
        Intent intent = new Intent(this, FileListActivity.class);

        startActivity(intent);
    }
}