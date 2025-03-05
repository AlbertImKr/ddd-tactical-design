package kitchenpos.menus.tobe.application;

import kitchenpos.menus.tobe.domain.MenuPrice;
import kitchenpos.menus.tobe.domain.MenuProduct;
import kitchenpos.menus.tobe.domain.MenuProducts;
import kitchenpos.menus.tobe.infra.ProductClient;
import kitchenpos.menus.tobe.infra.ProductResponses;
import kitchenpos.menus.tobe.ui.MenuProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuProductService {

    private final ProductClient productClient;

    public MenuProductService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public List<MenuProduct> getMenuProducts(MenuPrice price, List<MenuProductRequest> menuProductRequests, List<UUID> productIds) {
        MenuValidator.validateMenuProductRequests(menuProductRequests);
        final ProductResponses productResponses = productClient.listByIds(productIds);
        MenuValidator.validateProductResponses(productResponses, productIds);
        MenuValidator.validateMenuPriceWithRequests(price, productResponses, menuProductRequests);
        return MenuProductCreator.create(menuProductRequests, productResponses);
    }

    public void checkMenuProductPrice(final MenuProducts menuProducts, final MenuPrice price) {
        List<UUID> productIds = menuProducts.getProductIds();
        final ProductResponses productResponses = productClient.listByIds(productIds);
        MenuValidator.validateMenuPrice(price, productResponses, menuProducts);
    }
}
