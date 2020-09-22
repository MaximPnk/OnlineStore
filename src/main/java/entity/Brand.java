package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand extends EntityModel {

    @Pattern(regexp = "[a-zA-Zа-яА-Я\\s0-9]+", message = "Обязательно к заполнению")
    @Column(name = "name")
    private String name;

    @Valid
    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "country_id")
    private Country country;

    @Min(value = 0, message = "Скидка указана неверно")
    @Max(value = 99, message = "Скидка указана неверно")
    @Column(name = "sale")
    private int sale;

    @OneToMany(mappedBy = "brand",
            cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    private List<Product> products;

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        product.setBrand(this);
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
