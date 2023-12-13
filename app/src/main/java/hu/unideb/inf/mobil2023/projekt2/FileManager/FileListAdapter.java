package hu.unideb.inf.mobil2023.projekt2.FileManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.mobil2023.projekt2.R;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.MyViewHolder>
{
    private List<String> fileList;
    private static String leftSongPlaying = "none";
    private static String rightSongPlaying = "none";

    public static String getLeftSongPlaying()
    {
        return leftSongPlaying;
    }

    public static String getRightSongPlaying()
    {
        return rightSongPlaying;
    }

    public void setFileList(List<String> files)
    {
        this.fileList = new ArrayList<>();

        this.fileList.addAll(files);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent , false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.fileNameTextView.setText(fileList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return fileList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView fileNameTextView;
        TextView statusTextView;
        Button leftButton;
        Button rightButton;

        public MyViewHolder(View FileListItemView)
        {
            super(FileListItemView);

            MyViewHolder.this.fileNameTextView = FileListItemView.findViewById(R.id.fileListItemTextView);
            MyViewHolder.this.statusTextView = FileListItemView.findViewById(R.id.statusTextView);
            MyViewHolder.this.leftButton = FileListItemView.findViewById(R.id.addToLeft);
            MyViewHolder.this.rightButton = FileListItemView.findViewById(R.id.addToRight);

            leftButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    String s = fileNameTextView.getText().toString();
                    leftSongPlaying = s;
                    Log.i("FLAdapter", "The following song title was sent to the left side: " + s);
                    statusTextView.setText(R.string.filelist_added_to_left);
                }
            });

            rightButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    String s = fileNameTextView.getText().toString();
                    rightSongPlaying = s;
                    Log.i("FLAdapter", "The following song title was sent to the right side: " + s);
                    statusTextView.setText(R.string.filelist_added_to_right);
                }
            });
        }
    }
}
