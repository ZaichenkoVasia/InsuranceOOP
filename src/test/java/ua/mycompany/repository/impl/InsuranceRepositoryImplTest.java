package ua.mycompany.repository.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.mycompany.domain.order.Insurance;
import ua.mycompany.domain.order.impl.HouseInsurance;
import ua.mycompany.domain.order.impl.LifeInsurance;
import ua.mycompany.repository.InsuranceRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InsuranceRepositoryImplTest {
    private static InsuranceRepository insuranceRepository;
    private static Insurance insurance;

    @BeforeClass
    public static void setUp(){
        insuranceRepository = new InsuranceRepositoryImpl();
        insurance = new LifeInsurance(0.2, 3000, 70000);
        insuranceRepository.save(insurance);
    }

    @Test
    public void shouldCorrectSaveInsuranceFromRepo() {
        assertThat(insurance, is(insuranceRepository.findById(1L).get()));
    }

    @Test
    public void shouldReturnCorrectInsuranceFromRepoById() {
        assertThat(insurance, is(insuranceRepository.findById(1L).get()));
    }

    @Test
    public void shouldReturnAllInsurancesFromRepo() {
        HouseInsurance houseInsurance = new HouseInsurance(0.3, 3000, 90000);
        List<Insurance> insurances = new ArrayList<>();
        insurances.add(insurance);
        insurances.add(houseInsurance);
        insuranceRepository.save(houseInsurance);
        assertThat(insurances, is(insuranceRepository.findAll()));
        insuranceRepository.deleteById(2L);
    }

    @Test
    public void shouldReturnDeleteInsuranceFromRepoById() {
        assertThat(insurance, is(insuranceRepository.findById(1L).get()));
        insuranceRepository.deleteById(1L);
        assertThat(insuranceRepository.findAll(), is(emptyCollectionOf(Insurance.class)));
    }
}
