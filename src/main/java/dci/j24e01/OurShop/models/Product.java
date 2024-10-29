package dci.j24e01.OurShop.models;

public record Product(
        Long id,
        String name,
        Long stock,
        Long categoryId
) {
}
