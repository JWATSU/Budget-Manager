package budget;

import java.util.ArrayList;
import java.util.List;

public class BudgetManager
{
    private double balance = 0;
    private double sumOfPurchases = 0;
    private final List<Product> purchases = new ArrayList<>();

    public BudgetManager()
    {

    }

    public void addPurchase(String productName, double price, int productCategory)
    {
        purchases.add(new Product(productName, price, productCategory));
        sumOfPurchases += price;
        balance -= price;
    }

    public void addIncome(double income)
    {
        balance += income;
    }

    public void displayPurchases()
    {
        if (purchases.isEmpty())
        {
            System.out.println("Purchase list is empty\n");
        } else
        {
            System.out.println("All:");
            for (Product product : purchases)
            {
                System.out.println(product);
            }
            System.out.println("Total sum: $" + sumOfPurchases);
        }
    }

    public void displayPurchases(String type)
    {
        double sum = 0;
        System.out.println(type + ":");
        ArrayList<Product> categoryList = new ArrayList<>();
        for (Product product : purchases)
        {
            if (product.getProductCategory() == ProductCategory.valueOf(type.toUpperCase()))
            {
                categoryList.add(product);
                sum += product.getPrice();
            }
        }
        if (categoryList.isEmpty())
        {
            System.out.println("Purchase list is empty!");

        } else
        {
            for (Product product : purchases)
            {
                System.out.println(product);
            }
            System.out.println("Total sum: $" + sum);
        }
    }

    public void displayBalance()
    {
        if (balance < 0)
        {
            balance = 0;
        }
        System.out.printf("\nBalance: $%.2f\n\n", balance);
    }

    public int getAmountOfPurchases()
    {
        return purchases.size();
    }
}
