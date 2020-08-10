package budget;

import java.util.*;

public class BudgetManager
{
    private double balance = 0;
    private double total = 0;
    private final List<Product> purchases = new ArrayList<>();

    public BudgetManager()
    {

    }

    public void addPurchase(String productName, double price, int productCategory)
    {
        purchases.add(new Product(productName, price, productCategory));
        total += price;
    }

    public List<Product> getPurchases()
    {
        return purchases;
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
            System.out.printf("Total sum: $%.2f\n", total);
        }
    }

    public void displayPurchases(String type)
    {
        double sum = 0;
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
            System.out.println(type + ":");
            for (Product product : categoryList)
            {
                System.out.println(product);
            }
            System.out.printf("Total sum: $%.2f\n", sum);
        }
    }

    public void displayBalance()
    {
        if (balance < 0)
        {
            balance = 0;
        }
        System.out.printf("\nBalance: $%.2f\n\n", balance - total);
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public void sortAllPurchases()
    {
        if (purchases.size() == 0)
        {
            System.out.println("Purchase list is empty!");
        } else
        {
            purchases.sort(Comparator.comparingDouble(Product::getPrice).reversed());
            displayPurchases();
        }
    }

    public void sortAllProductCategories()
    {
        HashMap<String, Double> categoriesAndValues = new HashMap<>();
        String[] productCategories = {"Food", "Entertainment", "Clothes", "Other"};

        for (String category : productCategories)
        {
            double sum = 0;
            for (Product product : purchases)
            {
                if (product.getProductCategory() == ProductCategory.valueOf(category.toUpperCase()))
                {
                    sum += product.getPrice();
                }
                categoriesAndValues.put(category, sum);
            }
        }
        List<Map.Entry<String, Double>> entries = new ArrayList<>(categoriesAndValues.entrySet());
        Collections.sort(entries, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        System.out.println("Types:");

        for (Map.Entry<String, Double> entry : entries)
        {
            System.out.printf("%s - $%.2f\n", entry.getKey(), entry.getValue());
        }
        System.out.printf("Total sum: $%.2f\n", total);

    }
}
