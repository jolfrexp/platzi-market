package com.plazt_market.persistence;

import com.plazt_market.domain.Purchase;
import com.plazt_market.domain.repository.PurchaseRepository;
import com.plazt_market.persistence.crud.CompraCrudRepostory;
import com.plazt_market.persistence.entity.Compra;
import com.plazt_market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepostory compraCrudRepostory;
    
    private PurchaseMapper mapper;
    
    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepostory.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepostory.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepostory.save(compra));
    }
}
