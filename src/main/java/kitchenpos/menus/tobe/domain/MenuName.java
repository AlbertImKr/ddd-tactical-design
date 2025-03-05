package kitchenpos.menus.tobe.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.function.Predicate;

@Embeddable
public class MenuName {

    @Column(name = "name", nullable = false)
    private String name;

    protected MenuName() {
    }

    public MenuName(final String name, final Predicate<String> profanityChecker) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("메뉴명은 필수로 입력해야 합니다.");
        }
        if (profanityChecker.test(name)) {
            throw new IllegalArgumentException("메뉴명에 욕설이 포함되어 있습니다.");
        }
        this.name = name;
    }

    public String value() {
        return name;
    }
}
