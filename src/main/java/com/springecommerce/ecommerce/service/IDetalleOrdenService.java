package com.springecommerce.ecommerce.service;

import com.springecommerce.ecommerce.model.DetalleOrden;
import org.springframework.stereotype.Service;

public interface IDetalleOrdenService {
    DetalleOrden save(DetalleOrden detalleOrden);
}
