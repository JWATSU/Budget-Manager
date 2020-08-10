package budget;

import java.io.*;
import java.util.Scanner;

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
                // We need to add +1 to the ordinal value to get the correct category. The code should probably be rewritten so that we don't have to do this.
                writer.println(product.getDescription() + "," + product.getPrice() + "," + (product.getProductCategory().ordinal() + 1));
            }
            System.out.println("Purchases were saved!");
        } catch (IOException e)
        {
            System.out.println("Error when writing to file: ");
            e.printStackTrace();
        }
    }

    public void loadFromFile(BudgetManager budgetManager)
    {
        budgetManager.getPurchases().clear();
        budgetManager.setTotal(0);

        try (Scanner scanner = new Scanner(file))
        {
            budgetManager.setBalance(Double.parseDouble(scanner.nextLine()));
            while (scanner.hasNext())
            {
                String[] productInfo = scanner.nextLine().split(",");
                budgetManager.addPurchase(productInfo[0], Double.parseDouble(productInfo[1]), Integer.parseInt(productInfo[2]));
            }

            System.out.println("\nPurchases were loaded!\n");
        } catch (FileNotFoundException e)
        {
            System.out.println("No file found: " + file.getAbsolutePath());
        }

    }

    public File getFile()
    {
        return file;
    }
}
