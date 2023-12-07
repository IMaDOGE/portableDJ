package hu.unideb.inf.mobil2023.projekt2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FileListActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filelist);
    }

    public void exitFileBrowser(View view)
    {
        finish();
    }
}
