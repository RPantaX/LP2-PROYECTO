package com.panda.demo.guiasTransportista.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuiaTransportistaRequest {
    private String partida;
    private String llegada;
    private Timestamp fechaTraslado;
    private String remitenteRuc;
    private String destinatarioRuc;
    private BigDecimal pesoCarga;
    private String numDocChofer;
    private String rucPagadorDelFlete;
    private String placaVehiculo;
}
