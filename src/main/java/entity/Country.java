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
@Table(name = "country")
public class Country extends EntityModel {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Brand> brands;

    public void addBrand(Brand brand) {
        if (brands == null) {
            brands = new ArrayList<>();
        }
        brands.add(brand);
        brand.setCountry(this);
    }

    public Country(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }
}
