package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "product")
public class Product extends EntityModel {

    @Pattern(regexp = "[a-zA-Zа-яА-Я\\s]+", message = "Обязательно к заполнению")
    @Column(name = "name")
    private String name;

    @Valid
    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinColumn(name = "type_id")
    private Type type;

    @Valid
    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Min(value = 1, message = "Цена указана неверно")
    @Column(name = "price")
    private BigDecimal price;

    @Min(value = 1, message = "Количество указано неверно")
    @Column(name = "amount")
    private int amount;

    //TODO синхронизировать таблицы order, product и product_order, как добавлять сразу во все
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<ProductOrder> productOrderList;

    public Product(String name, BigDecimal price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", brand=" + brand +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
