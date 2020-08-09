package budget;

import java.io.*;

public class FileManager
{
    private final File file = new File("purchases.txt");

    public FileManager()
    {
        try
        {
            file.createNewFile();
        } catch (IOException e)
        {
            System.out.println("File creation failed : " + file.getAbsolutePath());
        }
    }

    public void writeToFile(BudgetManager budgetManager)
    {
        try (PrintWriter writer = new PrintWriter(file))
        {
            writer.println(budgetManager.getBalance());
            for (Product product : budgetManager.getPurchases())
            {
                writer.println(product.getDescription() + " " + product.getPrice() + " " + product.getProductCategory().ordinal());
            }
            System.out.println("Purchases were saved!");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveToFile(BudgetManager budgetManager)
    {

    }

    public File getFile()
    {
        return file;
    }
}
