package hu.unideb.inf.mobil2023.projekt2.FileManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hu.unideb.inf.mobil2023.projekt2.R;

public class FileListActivity extends AppCompatActivity
{
    RecyclerView fileRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filelist);

        fileRecyclerView = findViewById(R.id.file_recycler_view);
        fileRecyclerView.setLayoutManager(new LinearLayoutManager(FileListActivity.this));

        FileList files = new FileList();
        // files.addElement("##test");

        FileListAdapter fileAdapter = new FileListAdapter();
        fileAdapter.setFileList(files.getFileList());
        fileRecyclerView.setAdapter(fileAdapter);
    }

    public void exitFileBrowser(View view)
    {
        finish();
    }

    public void addToLeftPlayer(View view)
    {
        Log.i("# testing add button", "Left added!");
    }
    public void addToRightPlayer(View view)
    {
        Log.i("# testing add button", "Right added!");
    }

}
