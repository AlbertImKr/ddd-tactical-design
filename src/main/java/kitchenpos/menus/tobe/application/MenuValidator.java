package kitchenpos.menus.tobe.application;

import kitchenpos.menus.tobe.domain.MenuPrice;
import kitchenpos.menus.tobe.domain.MenuProducts;
import kitchenpos.menus.tobe.infra.ProductResponses;
import kitchenpos.menus.tobe.ui.MenuProductRequest;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MenuValidator {

    private MenuValidator() {
        throw new AssertionError();
    }

    public static void validateMenuPriceWithRequests(MenuPrice price, ProductResponses productResponses, List<MenuProductRequest> menuProductRequests) {
        var sum = productResponses.getSumsWithRequests(menuProductRequests);
        if (price.isGreaterThan(sum)) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateMenuPrice(MenuPrice price, ProductResponses productResponses, MenuProducts menuProducts) {
        var sum = productResponses.getSums(menuProducts);
        if (price.isGreaterThan(sum)) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateProductResponses(ProductResponses productResponses, List<UUID> productIds) {
        if (productResponses.size() != productIds.size()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateMenuProductRequests(List<MenuProductRequest> menuProductRequests) {
        if (Objects.isNull(menuProductRequests) || menuProductRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
