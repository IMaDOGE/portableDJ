package hu.unideb.inf.mobil2023.projekt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String filePath;
    private final String testFileName = "test.mp3";

    private final MusicPlayer musicPlayerLeft = new MusicPlayer();
    private final MusicPlayer musicPlayerRight = new MusicPlayer();

    private TextView titleLeft;
    private TextView titleRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("## status", "entering onCreate..");
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // force landscape mode
        setContentView(R.layout.activity_main);

        filePath = this.getFilesDir().getParent();


        titleLeft = findViewById(R.id.songTitleLeft);

        titleRight = findViewById(R.id.songTitleRight);

        Log.e("## status", "leaving onCreate..");
    }

    public void leftPlayPressed(View view)
    {
        musicPlayerLeft.playThisSong(filePath + testFileName);
        titleRight.setText("playing");

        titleLeft.setText(testFileName);
    }

    public void leftPausePressed(View view)
    {
        // pause song temporarily

        titleLeft.setText("paused");
    }

    public void leftStopPressed(View view)
    {
        musicPlayerLeft.stopThisSong();

        titleLeft.setText("stopped");
    }

    public void rightPlayPressed(View view)
    {
        // plays song..

        titleRight.setText("playing");
    }

    public void rightPausePressed(View view)
    {
        // pause song temporarily

        titleRight.setText("paused");
    }

    public void rightStopPressed(View view)
    {
        // remove song completely

        titleRight.setText("stopped");
    }
}