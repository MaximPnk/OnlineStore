package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends EntityModel {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "paid")
    private boolean isPaid;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOrder> productOrderList;

    public void add(ProductOrder productOrder) {
        if (productOrderList == null) {
            productOrderList = new ArrayList<>();
        }
        productOrderList.add(productOrder);
        productOrder.getProduct().add(productOrder);
    }

    public Order(User user, LocalDateTime date, boolean isPaid) {
        this.user = user;
        this.date = date;
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "client=" + user +
                ", date=" + date +
                '}';
    }
}
