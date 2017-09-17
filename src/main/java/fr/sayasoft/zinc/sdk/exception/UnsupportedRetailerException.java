package fr.sayasoft.zinc.sdk.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UnsupportedRetailerException extends Exception {
    public UnsupportedRetailerException(String message) {
        super(message);
    }
}
