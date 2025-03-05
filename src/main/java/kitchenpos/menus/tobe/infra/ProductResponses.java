package kitchenpos.menus.tobe.infra;

import kitchenpos.menus.tobe.domain.MenuProduct;
import kitchenpos.menus.tobe.domain.MenuProducts;
import kitchenpos.menus.tobe.ui.MenuProductRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public record ProductResponses(List<ProductResponse> productResponses) {

    private static MenuProductRequest getMenuProductRequest(List<MenuProductRequest> menuProductRequests, ProductResponse productResponse) {
        return menuProductRequests.stream()
                .filter(menuProductRequest -> menuProductRequest.productId().equals(productResponse.id()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public ProductResponse findById(UUID productId) {
        return productResponses.stream()
                .filter(productResponse -> productResponse.id().equals(productId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public int size() {
        return productResponses.size();
    }

    public BigDecimal getSumsWithRequests(List<MenuProductRequest> menuProductRequests) {
        return productResponses.stream()
                .map(productResponse -> {
                    MenuProductRequest menuProduct = getMenuProductRequest(menuProductRequests, productResponse);
                    return productResponse.price().multiply(BigDecimal.valueOf(menuProduct.quantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getSums(MenuProducts menuProducts) {
        return productResponses.stream()
                .map(productResponse -> {
                    MenuProduct menuProduct = menuProducts.getById(productResponse.id());
                    return productResponse.price().multiply(BigDecimal.valueOf(menuProduct.getQuantity().value()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
