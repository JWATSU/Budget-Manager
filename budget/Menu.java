package budget;

import java.util.Scanner;

public class Menu
{
    private final BudgetManager budgetManager = new BudgetManager();
    private final Scanner scanner = new Scanner(System.in);
    private final FileManager fileManager;

    public Menu(FileManager fileManager)
    {
        this.fileManager = fileManager;
    }

    public void displayMenu()
    {
        boolean continueLoop = true;
        while (continueLoop)
        {
            System.out.println("Choose your action:\n" +
                    "1) Add income\n" +
                    "2) Add purchase\n" +
                    "3) Show list of purchases\n" +
                    "4) Balance\n" +
                    "5) Save\n" +
                    "6) Load\n" +
                    "7) Analyze (Sort)\n" +
                    "0) Exit");

            int answer = Integer.parseInt(scanner.nextLine());

            switch (answer)
            {
                case 1:
                    addIncome();
                    break;
                case 2:
                    addPurchase();
                    break;
                case 3:
                    getPurchases();
                    break;
                case 4:
                    budgetManager.displayBalance();
                    break;
                case 5:
                    fileManager.writeToFile(budgetManager);
                    break;
                case 6:
                    fileManager.loadFromFile(budgetManager);
                    break;
                case 7:
                    sortPurchases();
                    break;
                case 0:
                    System.out.println("\nBye!");
                    continueLoop = false;
            }
        }
    }

    private void addIncome()
    {
        System.out.println("\nEnter income:");
        double income = Double.parseDouble(scanner.nextLine());
        budgetManager.addIncome(income);
        System.out.println("Income was added!\n");
    }

    private void sortPurchases()
    {
        while (true)
        {
            System.out.println("\nHow do you want to sort?\n" +
                    "1) Sort all purchases\n" +
                    "2) Sort by type\n" +
                    "3) Sort certain type\n" +
                    "4) Back\n");

            int sortingType = Integer.parseInt(scanner.nextLine());

            if (sortingType == 4 || sortingType < 1 || sortingType > 4)
            {
                return;
            } else
            {
                switch (sortingType)
                {
                    case 1:
                        budgetManager.sortAllPurchases();
                        break;
                    case 2:
                        budgetManager.sortAllProductCategories();
                        break;
                    case 3:
                        System.out.println("Choose the type of purchase\n" +
                                "1) Food\n" +
                                "2) Clothes\n" +
                                "3) Entertainment\n" +
                                "4) Other");
                        int category = Integer.parseInt(scanner.nextLine());
                        budgetManager.sortAllPurchasesInCategory(category);
                        break;
                    case 4:
                        return;
                }
            }
        }
    }

    private void getPurchases()
    {
        if (budgetManager.getPurchases().size() == 0)
        {
            System.out.println("\nPurchase list is empty!\n");
            return;
        }
        while (true)
        {
            try
            {
                System.out.println("\nChoose the type of purchases\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other\n" +
                        "5) All\n" +
                        "6) Back");
                int type = Integer.parseInt(scanner.nextLine());
                System.out.println();
                if (type == 6 || type < 1 || type > 6)
                {
                    return;
                } else
                {
                    switch (type)
                    {
                        case 1:
                            budgetManager.displayPurchases("Food");
                            break;
                        case 2:
                            budgetManager.displayPurchases("Clothes");
                            break;
                        case 3:
                            budgetManager.displayPurchases("Entertainment");
                            break;
                        case 4:
                            budgetManager.displayPurchases("Other");
                            break;
                        case 5:
                            budgetManager.displayPurchases();
                            break;
                    }
                }
            } catch (NumberFormatException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addPurchase()
    {
        while (true)
        {
            try
            {
                System.out.println("\nChoose the type of purchase\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other\n" +
                        "5) Back");
                int category = Integer.parseInt(scanner.nextLine());
                if (category == 5 || category < 1 || category > 5)
                {
                    System.out.println();
                    return;
                }
                System.out.println("\nEnter purchase name:");
                String productName = scanner.nextLine();
                System.out.println("Enter its price:");
                double price = Double.parseDouble(scanner.nextLine());
                budgetManager.addPurchase(productName, price, category);
                System.out.println("Purchase was added!");
            } catch (NumberFormatException e)
            {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
