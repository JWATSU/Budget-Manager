package budget;

public class Product
{
    private final double price;
    private final String description;
    private ProductCategory productCategory;


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
        return description + " $" + price;
    }
}