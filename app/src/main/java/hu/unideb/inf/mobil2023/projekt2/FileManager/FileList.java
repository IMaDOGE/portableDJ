package hu.unideb.inf.mobil2023.projekt2.FileManager;

import java.util.ArrayList;
import java.util.List;

public class FileList
{
    List<java.lang.String> fileList;

    public FileList()
    {
        fileList = new ArrayList<>();

        fileList.add("Dire Straits - 01. So Far Away.mp3");
        fileList.add("Dire Straits - 02. Money For Nothing.mp3");
        fileList.add("Dire Straits - 03. Walk Of Life.mp3");
        fileList.add("Dire Straits - 04. Your Latest Trick.mp3");

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
