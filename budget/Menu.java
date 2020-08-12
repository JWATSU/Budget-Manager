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

    public void displayMenuAndHandleUserInput()
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

            int userMenuChoice;
            try
            {
                userMenuChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e)
            {
                System.out.println("\nInvalid input: Please enter one of the choices displayed.\n");
                continue;
            }

            switch (userMenuChoice)
            {
                case 1:
                    addIncomeMenu();
                    break;
                case 2:
                    addPurchaseMenu();
                    break;
                case 3:
                    getPurchasesMenu();
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
                    sortPurchasesMenu();
                    break;
                case 0:
                    System.out.println("\nBye!");
                    continueLoop = false;
                default:
                    System.out.println("\nInvalid input: Please enter one of the choices displayed.\n");
            }
        }
    }

    private void addIncomeMenu()
    {
        System.out.println("\nEnter income:");
        double income;
        try
        {
            income = Double.parseDouble(scanner.nextLine());
            if (income <= 0)
            {
                System.out.println("\nInvalid input: Please enter a positive number.\n");
                return;
            }
        } catch (NumberFormatException e)
        {
            System.out.println("\nInvalid input: Please enter a positive number.\n");
            return;
        }
        budgetManager.addIncome(income);
        System.out.println("Income was added!\n");
    }

    private void sortPurchasesMenu()
    {
        while (true)
        {
            try
            {
                System.out.println("\nHow do you want to sort?\n" +
                        "1) Sort all purchases\n" +
                        "2) Sort by type\n" +
                        "3) Sort certain type\n" +
                        "4) Back");

                int sortingType = Integer.parseInt(scanner.nextLine());

                if (sortingType < 1 || sortingType > 4)
                {
                    System.out.println("Invalid input: Please enter one of the choices displayed.");
                    continue;
                }
                if (sortingType == 4)
                {
                    return;
                }

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
                        if (category < 1 || category > 4)
                        {
                            System.out.println("Invalid input: Please enter one of the choices displayed.");
                            continue;
                        }
                        budgetManager.sortAllPurchasesInCategory(category);
                        break;
                    case 4:
                        return;
                }
            } catch (NumberFormatException e)
            {
                System.out.println("\nInvalid input: Please try again.");
            }
        }
    }

    private void getPurchasesMenu()
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
                int categoryChoice = Integer.parseInt(scanner.nextLine());
                System.out.println();
                if (categoryChoice < 1 || categoryChoice > 6)
                {
                    System.out.println("Invalid input: Please enter one of the choices displayed.");
                    continue;
                }
                if (categoryChoice == 6)
                {
                    return;
                }

                switch (categoryChoice)
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

            } catch (NumberFormatException e)
            {
                System.out.println("\nInvalid input: Please try again.");
            }
        }
    }

    private void addPurchaseMenu()
    {
        while (true)
        {
            try
            {
                System.out.println("Choose the type of purchase\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other\n" +
                        "5) Back");
                int category = Integer.parseInt(scanner.nextLine());

                if (category < 1 || category > 6)
                {
                    System.out.println("Invalid input: Please enter one of the choices displayed.");
                    continue;
                }
                if (category == 5)
                {
                    break;
                }
                System.out.println("\nEnter purchase name:");
                String productName = scanner.nextLine();
                System.out.println("Enter its price:");
                double price = Double.parseDouble(scanner.nextLine());

                if (price <= 0)
                {
                    System.out.println("\nPrice must be a positive number.\n");
                    continue;
                }

                if ((budgetManager.getBalance() - price) < 0)
                {
                    System.out.println("\nNot enough money to process purchase:");
                    budgetManager.displayBalance();
                    continue;
                }
                budgetManager.addPurchase(productName, price, category);
                System.out.println("Purchase was added!");
            } catch (NumberFormatException e)
            {
                System.out.println("\nInvalid input: Please try again.\n");
            }
        }
    }
}
