package hu.unideb.inf.mobil2023.projekt2.FileManager;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class FileManager
{
    // TODO: figure out how to load music from "Music" folder
    // feature likely out of scope



    //private final Context context;

    /*public FileManager(Context context) // non-functional
    {
        Log.i("FManager", "Starting FManager...");

        this.context = context;

        String[] projection = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.TITLE};
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        ContentResolver resolver = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = resolver.query(uri, projection, selection, null, sortOrder);

        if (cursor != null)
        {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                @SuppressLint("Range") String fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));

                @SuppressLint("Range") String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                Log.i("Music", "File Name: " + fileName + ", File Path: " + filePath);
            }
            cursor.close();
        }

        String fname = "functionality_test.txt";
        String text = "testing functionality of Scoped Storage API";

        /*File file = new File(context.getFilesDir(), fname);

        writeFile(fname, text);

        String testResult = readFile(fname);

        if (testResult.equals(text))
        {
            Log.i("FManager", "Scoped Storage API's basic functionality verified successfully.");
        }

        else
        {
            Log.e("FManager", "Scoped Storage API doesn't seem to function correctly.");
        }*/
    //}

    /*private String readFile(String fileName) // read test, unnecessary
    {
        String s = "Attempting to read file: " + fileName;
        String line;
        StringBuilder sb = new StringBuilder();

        Log.i("FManager", s);

        try
        {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            while ((line = br.readLine()) != null)
            {
                sb.append(line).append("\n");
            }
            fis.close();

            Log.i("FManager", "File reading successful.");
        }

        catch (IOException e) {
            Log.e("FManager", "Error trying to read file. ");
            Log.e("FManager", "" + e.getMessage());
        }

        return sb.toString();
    }*/

    /*private void writeFile(String fileName, String content) // write test, unnecessary
    {
        String s = "Attempting to create file: " + fileName;
        Log.i("FManager", s);

        try
        {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();

            Log.i("FManager", "File successfully written to the app-specific directory.");
        }

        catch (IOException e)
        {
            Log.e("FManager", "Error trying to write file.");
            Log.e("FManager", "" + e.getMessage());
        }
    }*/
}
