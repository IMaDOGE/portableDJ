package hu.unideb.inf.mobil2023.projekt2.FileManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.mobil2023.projekt2.R;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.MyViewHolder>
{
    private List<String> fileList;

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

        public MyViewHolder(View FileListItemView)
        {
            super(FileListItemView);

            MyViewHolder.this.fileNameTextView = FileListItemView.findViewById(R.id.fileListItemTextView);
        }

    }
}
