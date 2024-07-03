package com.panda.demo.facturas.request;

import java.util.List;

import com.panda.demo.facturas.modelos.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacturaRequest {
	private Integer id;
    private String clienteRuc;

    private String observacion;
    
    private String seguieGuia;
    
    private int numeroGuia;
    private Long idUser;
    private List<Item> items;
}
