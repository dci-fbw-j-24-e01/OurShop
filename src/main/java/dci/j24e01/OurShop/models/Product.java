package dci.j24e01.OurShop.models;

public record Product(
        Long id,
        String name,
        Long categoryId,
        Category category,
        Long stock
) {
}
