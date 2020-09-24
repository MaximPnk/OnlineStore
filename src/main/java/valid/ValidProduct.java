package valid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ValidProduct {

    @Pattern(regexp = ".+", message = "Обязательно к заполнению")
    private String name;

    @Pattern(regexp = ".+", message = "Обязательно к заполнению")
    private String typeName;

    @Pattern(regexp = ".+", message = "Обязательно к заполнению")
    private String brandName;

    @Min(value = 0, message = "Скидка указана неверно")
    @Max(value = 99, message = "Скидка указана неверно")
    private int brandSale;

    @Pattern(regexp = ".+", message = "Обязательно к заполнению")
    private String countryName;

    @Min(value = 1, message = "Цена указана неверно")
    private BigDecimal price;

    @Min(value = 1, message = "Количество указано неверно")
    private int amount;
}
