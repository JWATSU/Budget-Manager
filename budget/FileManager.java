package budget;

import java.io.File;
import java.io.IOException;

public class FileManager
{
    private final File file = new File("purchases.txt");

    public FileManager()
    {
        try
        {
            this.file.createNewFile();
        } catch (IOException e)
        {
            System.out.println("File creation failed : " + file.getAbsolutePath());
        }
    }

    public File getFile()
    {
        return file;
    }
}
