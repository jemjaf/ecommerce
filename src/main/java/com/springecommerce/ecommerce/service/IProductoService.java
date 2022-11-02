    package com.springecommerce.ecommerce.service;

    import com.springecommerce.ecommerce.model.Producto;

    import java.util.List;
    import java.util.Optional;

    public interface IProductoService {

        Producto save(Producto producto);

        Optional<Producto> get(Integer id);

        void update(Producto producto);

        void delete(Integer id);

        List<Producto> findAll();

    }
