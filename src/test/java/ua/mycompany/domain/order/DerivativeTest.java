package ua.mycompany.domain.order;

import org.junit.Before;
import org.junit.Test;
import ua.mycompany.domain.order.impl.HouseInsurance;
import ua.mycompany.domain.order.impl.LifeInsurance;
import ua.mycompany.exception.ActionDerivativeWithNullRuntimeException;
import ua.mycompany.exception.RangeUncorrectedRuntimeException;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DerivativeTest {
    private Derivative derivative;
    private LifeInsurance lifeInsurance;
    private HouseInsurance houseInsurance;

    @Before
    public void setUp() {
        derivative = new Derivative();
        lifeInsurance = new LifeInsurance(0.8,100,100);
        houseInsurance = new HouseInsurance(0.1,50,50);
        derivative.add(lifeInsurance);
        derivative.add(houseInsurance);
    }

    @Test
    public void add() {
        HouseInsurance insurance = new HouseInsurance(0.2,50,50);
        derivative.add(insurance);
        assertThat(derivative.getInsurances(), hasItem(insurance));
    }

    @Test
    public void remove() {
        HouseInsurance insurance = new HouseInsurance(0.2,50,50);
        derivative.add(insurance);
        derivative.remove(insurance);
        assertThat(derivative.getInsurances(), not(insurance));
    }

    @Test
    public void sort() {
        HouseInsurance insurance = new HouseInsurance(0.2,50,50);
        derivative.add(insurance);

        ArrayList<Insurance> insurances = new ArrayList<>();
        insurances.add(lifeInsurance);
        insurances.add(insurance);
        insurances.add(houseInsurance);

        assertThat(insurances, is(derivative.sortByRisk()));
    }

    @Test
    public void getSummaryOfCalories() {
        HouseInsurance insurance = new HouseInsurance(0.2,50,50);
        derivative.add(insurance);
        assertThat(200.0, is(derivative.sumOfInsurance()));
    }

    @Test
    public void searchElementCalories() {
        HouseInsurance insurance = new HouseInsurance(0.2,50,50);
        derivative.add(insurance);

        ArrayList<Insurance> insurances = new ArrayList<>();
        insurances.add(houseInsurance);
        insurances.add(insurance);

        assertThat(insurances, is(derivative.searchElementRisk(0.05, 0.3)));
    }

    @Test(expected = RangeUncorrectedRuntimeException.class)
    public void shouldReturnRangeUncorrectedRuntimeException() {
        derivative.searchElementPayment(-100,-100);
    }

    @Test(expected = ActionDerivativeWithNullRuntimeException.class)
    public void shouldReturnActionSaladWithNullRuntimeException() {
        derivative.add(null);
    }
}
