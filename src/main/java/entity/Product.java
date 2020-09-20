package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.EntityModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends EntityModel {

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinColumn(name = "type_id")
    private Type typeName;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinColumn(name = "brand_id")
    private Brand brandName;

    @Column(name = "price")
    private BigDecimal price;

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
                ", typeName=" + typeName +
                ", brandName=" + brandName +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
