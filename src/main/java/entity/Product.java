package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "product")
public class Product extends EntityModel {

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "amount")
    private int amount;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order",cascade = CascadeType.ALL)
    private List<ProductOrder> productOrderList;

    public void add(ProductOrder productOrder) {
        if (productOrderList == null) {
            productOrderList = new ArrayList<>();
        }
        productOrderList.add(productOrder);
    }

    public Product(String name, BigDecimal price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Product(String name, BigDecimal price, int amount, Brand brand, Type type) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.brand = brand;
        this.type = type;
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
