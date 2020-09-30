package rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductErrorResponse {

    private int status;
    private String message;
    private long time;
}
