package fr.sayasoft.zinc.sdk.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ZincCondition {
    New("New"),
    Refurbished("Refurbished"),
    Used_Like_New("Used - Like New"),
    Used_Very_Good("Used - Very Good"),
    Used_Good("Used - Good"),
    Used_Acceptable("Used - Acceptable"),
    Unacceptable("Unacceptable");

    private String meaning;

    ZincCondition(String _meaning) {
        this.meaning = _meaning;
    }

}
