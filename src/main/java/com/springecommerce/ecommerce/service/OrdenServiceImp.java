package com.springecommerce.ecommerce.service;

import com.springecommerce.ecommerce.model.Orden;
import com.springecommerce.ecommerce.repository.IOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenServiceImp implements IOrdenService {

    @Autowired
    private IOrdenRepository iOrdenRepository;

    @Override
    public Orden save(Orden orden) {

        return iOrdenRepository.save(orden);
    }
}
