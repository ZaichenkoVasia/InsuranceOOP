package ua.mycompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mycompany.domain.order.Insurance;
import ua.mycompany.exception.InsuranceNotExistRuntimeException;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.repository.InsuranceRepository;
import ua.mycompany.service.InsuranceService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class InsuranceServiceImpl implements InsuranceService {
    private InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public Insurance save(Insurance insurance) {
        if (insurance == null) {
            throw new InsuranceNotExistRuntimeException("Insurance is not exist");
        }
        return insuranceRepository.save(insurance);
    }

    @Override
    public Insurance findById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id of insurance uncorrected");
        }
        Optional<Insurance> insuranceFindById = insuranceRepository.findById(id);
        return insuranceFindById.orElseThrow(() -> new UncorrectedIdRuntimeException("Id of insurance uncorrected"));
    }


    @Override
    public ArrayList<Insurance> findAll() {
        return insuranceRepository.findAll();
    }

    @Override
    public void update(Insurance insurance) {
        if (insurance == null) {
            throw new InsuranceNotExistRuntimeException("Insurance is not exist");
        }
        insuranceRepository.update(insurance);
    }


    @Override
    public Insurance deleteById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id of insurance uncorrected");
        }
        Optional<Insurance> insuranceDeleteById = insuranceRepository.deleteById(id);
        if (insuranceDeleteById.isPresent()) {
            return insuranceDeleteById.get();
        }
        throw new UncorrectedIdRuntimeException("Id of customer uncorrected");
    }
}
