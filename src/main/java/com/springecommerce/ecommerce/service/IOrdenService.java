package com.springecommerce.ecommerce.service;

import com.springecommerce.ecommerce.model.Orden;
import org.springframework.stereotype.Service;


public interface IOrdenService {
    Orden save(Orden orden);

}
