package ua.mycompany.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.order.Insurance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InsuranceRepositoryImpl implements InsuranceRepository {
    private Map<Long, Insurance> idToInsurance = new HashMap<>();
    private static Long counter = 0L;

    @Autowired
    public InsuranceRepositoryImpl() {
    }

    @Override
    public Insurance save(Insurance insurance) {
        return idToInsurance.put(++counter, insurance);
    }

    @Override
    public Optional<Insurance> findById(Long id) {
        return Optional.ofNullable(idToInsurance.get(id));
    }

    @Override
    public void update(Insurance insurance) {
        idToInsurance.replace(insurance.getId(), insurance);
    }

    @Override
    public Optional<Insurance> deleteById(Long id) {
        return Optional.ofNullable(idToInsurance.remove(id));
    }

    @Override
    public ArrayList<Insurance> findAll() {
        return new ArrayList<Insurance> (idToInsurance.values());
    }
}
