package com.springecommerce.ecommerce.repository;

import com.springecommerce.ecommerce.model.DetalleOrden;
import com.springecommerce.ecommerce.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {
}
