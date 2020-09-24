package dao;

import entity.Brand;

public interface BrandDao {
    Brand findBrandByNameAndSale(String brandName);

    void save(Brand brand);
}
