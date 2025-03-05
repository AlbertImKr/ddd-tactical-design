package kitchenpos.menus.tobe.infra;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, BigDecimal price) {
}
