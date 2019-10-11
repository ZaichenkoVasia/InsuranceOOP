package ua.mycompany.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.mycompany.domain.order.Insurance;
import ua.mycompany.domain.order.impl.LifeInsurance;
import ua.mycompany.exception.InsuranceNotExistRuntimeException;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.repository.InsuranceRepository;

import java.util.ArrayList;

import static java.util.Optional.ofNullable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.Silent.class)
@RunWith(MockitoJUnitRunner.class)
public class InsuranceServiceImplTest {

    @Mock
    private InsuranceRepository insuranceRepository;

    @InjectMocks
    private InsuranceServiceImpl insuranceService;

    private Insurance insurance;

    @Before
    public void setUp(){
        insurance = new LifeInsurance(0.3, 1000, 10000);
    }

    @Test
    public void shouldReturnSavedInsurance() {
        when(insuranceRepository.save(insurance)).thenReturn(insurance);

        Insurance insuranceActual = insuranceService.save(insurance);
        assertThat(insurance, is(insuranceActual));
    }

    @Test
    public void shouldReturnInsuranceById() {
        when(insuranceRepository.findById(1L)).thenReturn(ofNullable(insurance));

        Insurance insuranceActual = insuranceService.findById(1L);
        assertThat(insurance, is(insuranceActual));
    }

    @Test
    public void shouldReturnAllInsurance() {
        Insurance lifeInsurance = new LifeInsurance(0.3, 500, 50000);
        ArrayList<Insurance> insurances = new ArrayList<>();
        insurances.add(insurance);
        insurances.add(lifeInsurance);

        when(insuranceRepository.findAll()).thenReturn(insurances);

        ArrayList<Insurance> insuranceActual = insuranceService.findAll();
        assertThat(insurances, is(insuranceActual));
    }

    @Test
    public void shouldReturnDeletedInsuranceById() {
        when(insuranceRepository.deleteById(1L)).thenReturn(ofNullable(insurance));

        Insurance insuranceActual = insuranceService.deleteById(1L);
        assertThat(insurance, is(insuranceActual));
    }

    @Test(expected = InsuranceNotExistRuntimeException.class)
    public void shouldReturnInsuranceNotExistRuntimeExceptionInSave() {
        insuranceService.save(null);
    }

    @Test(expected = UncorrectedIdRuntimeException.class)
    public void shouldReturnUncorrectedIdRuntimeExceptionInFindById() {
        insuranceService.findById(-1L);
    }

    @Test(expected = InsuranceNotExistRuntimeException.class)
    public void shouldReturnInsuranceNotExistRuntimeExceptionInUpdate() {
        insuranceService.update(null);
    }

    @Test(expected = UncorrectedIdRuntimeException.class)
    public void shouldReturnUncorrectedIdRuntimeExceptionInDeleteById() {
        insuranceService.deleteById(-1L);
    }

}
