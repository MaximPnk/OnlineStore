package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_product")
public class ProductOrder extends EntityModel {

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count")
    private int count;

    public ProductOrder(Order order, Product product, int count) {
        this.order = order;
        this.product = product;
        this.count = count;
    }

    public ProductOrder(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public ProductOrder(int count) {
        this.count = count;
    }


}
