package budget;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Product
{
    private final double price;
    private final String description;
    private final static DecimalFormat formatter = new DecimalFormat("#0.00");
    private ProductCategory productCategory;

    static
    {
        DecimalFormatSymbols dfs = formatter.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dfs);
    }

    Product(String description, double price, int productCategory)
    {
        this.description = description;
        this.price = price;

        switch (productCategory)
        {
            case 1:
                this.productCategory = ProductCategory.FOOD;
                break;
            case 2:
                this.productCategory = ProductCategory.CLOTHES;
                break;
            case 3:
                this.productCategory = ProductCategory.ENTERTAINMENT;
                break;
            case 4:
                this.productCategory = ProductCategory.OTHER;
                break;
            default:
                break;
        }
    }

    double getPrice()
    {
        return this.price;
    }

    String getDescription()
    {
        return this.description;
    }

    public ProductCategory getProductCategory()
    {
        return productCategory;
    }

    @Override
    public String toString()
    {
        return description + " $" + formatter.format(price);
    }
}