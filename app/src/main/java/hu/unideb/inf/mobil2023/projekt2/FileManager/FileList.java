package hu.unideb.inf.mobil2023.projekt2.FileManager;

import java.util.ArrayList;
import java.util.List;

public class FileList
{
    List<java.lang.String> fileList;

    public FileList()
    {
        fileList = new ArrayList<>();

        // testing
        for(int i = 0; i < 10; i++)
        {
            addElement("testing.. " + i);
        }
    }

    public void addElement(String s)
    {
        this.fileList.add(s);
    }

    public List<String> getFileList()
    {
        return this.fileList;
    }
}
