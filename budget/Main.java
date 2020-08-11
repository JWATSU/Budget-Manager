package budget;

import java.util.Locale;

public class Main
{
    public static void main(String[] args)
    {
        java.util.Locale.setDefault(Locale.US);
        String fileName = "Budget Manager\\task\\src\\budget\\purchases.txt";

        Menu menu = new Menu(new FileManager(fileName));
        menu.displayMenuAndHandleUserInput();
    }
}
