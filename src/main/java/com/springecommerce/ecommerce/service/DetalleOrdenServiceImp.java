package com.springecommerce.ecommerce.service;

import com.springecommerce.ecommerce.model.DetalleOrden;
import com.springecommerce.ecommerce.repository.IDetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenServiceImp implements IDetalleOrdenService{

    @Autowired
    private IDetalleOrdenRepository iDetalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return iDetalleOrdenRepository.save(detalleOrden);
    }
}
