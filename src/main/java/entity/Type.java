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
@Table(name = "type")
public class Type extends EntityModel {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "typeName",
            cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    private List<Product> products;

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        product.setTypeName(this);
    }

    public Type(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                '}';
    }
}
