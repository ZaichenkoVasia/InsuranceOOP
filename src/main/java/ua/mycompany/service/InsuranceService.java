package ua.mycompany.service;

import ua.mycompany.domain.order.Insurance;

import java.util.ArrayList;

public interface InsuranceService {
    Insurance save(Insurance insurance);

    Insurance findById(Long id);

    ArrayList<Insurance> findAll();

    void update(Insurance insurance);

    Insurance deleteById(Long id);
}
