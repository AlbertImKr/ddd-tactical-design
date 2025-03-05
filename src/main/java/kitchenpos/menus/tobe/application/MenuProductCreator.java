package kitchenpos.menus.tobe.application;

import kitchenpos.menus.tobe.domain.MenuProduct;
import kitchenpos.menus.tobe.domain.MenuProductQuantity;
import kitchenpos.menus.tobe.infra.ProductResponse;
import kitchenpos.menus.tobe.infra.ProductResponses;
import kitchenpos.menus.tobe.ui.MenuProductRequest;

import java.util.ArrayList;
import java.util.List;

public class MenuProductCreator {

    private MenuProductCreator() {
        throw new AssertionError();
    }

    public static List<MenuProduct> create(List<MenuProductRequest> menuProductRequests, ProductResponses productResponses) {
        final List<MenuProduct> menuProducts = new ArrayList<>();
        for (MenuProductRequest menuProductRequest : menuProductRequests) {
            final MenuProductQuantity quantity = new MenuProductQuantity(menuProductRequest.quantity());
            final ProductResponse productResponse = productResponses.findById(menuProductRequest.productId());
            menuProducts.add(new MenuProduct(productResponse.id(), quantity));
        }
        return menuProducts;
    }
}
