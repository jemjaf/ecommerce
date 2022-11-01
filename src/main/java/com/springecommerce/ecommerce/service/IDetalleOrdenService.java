package com.springecommerce.ecommerce.service;

import com.springecommerce.ecommerce.model.DetalleOrden;
import com.springecommerce.ecommerce.model.Orden;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IDetalleOrdenService {
    DetalleOrden save(DetalleOrden detalleOrden);

}
