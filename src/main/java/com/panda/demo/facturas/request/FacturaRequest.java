package com.panda.demo.facturas.request;

import java.util.List;

import com.panda.demo.facturas.modelos.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaRequest {
    private String clienteRuc;
    private String observacion;
    private int numeroGuia;
    private Long idUser;
    private List<Item> items;
}
