package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends EntityModel {

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "paid")
    private boolean isPaid;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOrder> productOrderList;

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
