package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "country")
public class Country extends EntityModel {

    @Pattern(regexp = "[a-zA-Zа-яА-Я\\s]+", message = "Обязательно к заполнению")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
