package com.springecommerce.ecommerce.service;

import com.springecommerce.ecommerce.model.Orden;
import com.springecommerce.ecommerce.repository.IOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenServiceImp implements IOrdenService {

    @Autowired
    private IOrdenRepository iOrdenRepository;

    @Override
    public List<Orden> findAll() {
        return iOrdenRepository.findAll();
    }

    @Override
    public Orden save(Orden orden) {

        return iOrdenRepository.save(orden);
    }

    public String generarNumeroOrden(){
        Integer numeroOrden =0;
        String numeroConcat = "";

        List<Orden> ordenes = findAll();
        List<Integer> numeros = new ArrayList<Integer>();

        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

        if (ordenes.isEmpty()){
            numeroOrden=1;
        }else{
            numeroOrden=numeros.stream().max(Integer::compare).get();
            numeroOrden++;
        }

        numeroConcat=numeroOrden.toString();
        for (int i=numeroConcat.length(); i<10 ; i++ ){
            numeroConcat = "0"+ numeroConcat;
        }

        return numeroConcat;
    }
}
