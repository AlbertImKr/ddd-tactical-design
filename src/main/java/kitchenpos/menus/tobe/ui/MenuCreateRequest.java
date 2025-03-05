package kitchenpos.menus.tobe.ui;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record MenuCreateRequest(
        BigDecimal price,
        String name,
        boolean displayed,
        UUID menuGroupId,
        List<MenuProductRequest> menuProducts
) {
    public List<UUID> productIds() {
        return menuProducts.stream()
                .map(MenuProductRequest::productId)
                .toList();
    }
}
