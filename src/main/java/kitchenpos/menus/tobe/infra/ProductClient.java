package kitchenpos.menus.tobe.infra;

import java.util.List;
import java.util.UUID;

public interface ProductClient {
    ProductResponses listByIds(List<UUID> productIds);
}
