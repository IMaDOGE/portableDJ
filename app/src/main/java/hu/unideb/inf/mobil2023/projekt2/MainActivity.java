package hu.unideb.inf.mobil2023.projekt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // force landscape mode

        setContentView(R.layout.activity_main);
    }

    public void leftPlayPressed(View view) {
    }

    public void leftPausePressed(View view) {
    }

    public void leftStopPressed(View view) {
    }

    public void rightPlayPressed(View view) {
    }

    public void rightPausePressed(View view) {
    }

    public void rightStopPressed(View view) {
    }
}