package dao;

import configuration.MvcConfig;
import entity.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "dao")
@ContextConfiguration(classes = MvcConfig.class)
@WebAppConfiguration
public class CountryDaoImplTest {

    @Autowired
    CountryDao countryDao;

    @Test
    @Transactional
    public void findByNameTest1() {
        Country testCountry = new Country();
        testCountry.setName("Россия");

        Country country = countryDao.findCountryByName("Россия");

        assertEquals(testCountry.getName(), country.getName());
    }


}
