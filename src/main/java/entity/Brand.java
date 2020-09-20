package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.EntityModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand extends EntityModel {

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "sale")
    private int sale;

    @OneToMany(mappedBy = "brandName",
            cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    private List<Product> products;

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        product.setBrandName(this);
    }

    public Brand(String name, int sale) {
        this.name = name;
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", country=" + country +
                ", sale=" + sale +
                '}';
    }
}
