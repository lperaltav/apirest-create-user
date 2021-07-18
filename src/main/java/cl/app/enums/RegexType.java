package cl.app.enums;

import lombok.Getter;

@Getter
public enum RegexType {

    EMAIL(0),

    PASS(1);

    private final Integer value;

    RegexType(Integer value) {
        this.value = value;
    }
}
