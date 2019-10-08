package ua.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.order.Insurance;
import ua.mycompany.service.InsuranceService;

import java.util.ArrayList;

@Component
public class InsuranceController {

    private InsuranceService insuranceService;

    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    public Insurance save(Insurance insurance) {
        return insuranceService.save(insurance);
    }

    public Insurance findById(Long id) {
        return insuranceService.findById(id);
    }

    public ArrayList<Insurance> findAll() {
        return insuranceService.findAll();
    }

    public void update(Insurance insurance) {
        insuranceService.update(insurance);
    }

    public Insurance deleteById(Long id) {
        return insuranceService.deleteById(id);
    }
}
