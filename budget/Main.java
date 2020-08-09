package budget;

import java.util.Locale;

public class Main
{
    public static void main(String[] args)
    {
        java.util.Locale.setDefault(Locale.US);
        Menu menu = new Menu();
        menu.displayMenu();
    }
}
