package budget;

public enum ProductCategory
{
    FOOD, CLOTHES, ENTERTAINMENT, OTHER;


    @Override
    public String toString()
    {
        String name = name().toLowerCase();

        String readerFriendlyString = name.substring(0, 1).toUpperCase() + name.substring(1);

        return readerFriendlyString;

    }
}
