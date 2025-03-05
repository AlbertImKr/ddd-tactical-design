package kitchenpos.menus.tobe.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Embeddable
public class MenuProducts {

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "menu_id",
            nullable = false,
            columnDefinition = "binary(16)",
            foreignKey = @ForeignKey(name = "fk_menu_product_to_menu")
    )
    private List<MenuProduct> menuProductList;

    protected MenuProducts() {
    }

    public MenuProducts(final List<MenuProduct> menuProducts) {
        this.menuProductList = menuProducts;
    }

    public List<UUID> getProductIds() {
        return menuProductList.stream()
                .map(MenuProduct::getProductId)
                .toList();
    }

    public MenuProduct getById(final UUID id) {
        return menuProductList.stream()
                .filter(menuProduct -> menuProduct.getProductId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
