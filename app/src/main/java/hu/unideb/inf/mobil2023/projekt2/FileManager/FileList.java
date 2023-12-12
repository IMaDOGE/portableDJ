package hu.unideb.inf.mobil2023.projekt2.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileList
{
    public List<String> fileList;
    private static List<String> scratchSFX;

    private static Random rtd;

    public FileList()
    {
        fileList = new ArrayList<>();
        scratchSFX = new ArrayList<>();

        rtd = new Random();

        fileList.add("Dire Straits - 01. So Far Away.mp3");
        fileList.add("Dire Straits - 02. Money For Nothing.mp3");
        fileList.add("Dire Straits - 03. Walk Of Life.mp3");
        fileList.add("Dire Straits - 04. Your Latest Trick.mp3");
        fileList.add("Antidote Audio - FX Scratch 13.wav");

        scratchSFX.add("Antidote Audio - FX Scratch 02.wav");
        scratchSFX.add("Antidote Audio - FX Scratch 06.wav");
        scratchSFX.add("Antidote Audio - FX Scratch 08.wav");
        scratchSFX.add("Antidote Audio - FX Scratch 13.wav");
        scratchSFX.add("Antidote Audio - FX Scratch 17.wav");
        scratchSFX.add("Antidote Audio - FX Scratch 18.wav");
        scratchSFX.add("Antidote Audio - Vocal Scratch 02.wav");

        // testing
        for(int i = 0; i < 10; i++)
        {
            addElement("testing.. " + i);
        }
    }

    public static String getRandomScratchSFX()
    {
        int len = scratchSFX.size();

        int n = rtd.nextInt(len);

        return scratchSFX.get(n);
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
