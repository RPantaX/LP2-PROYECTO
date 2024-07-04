package com.panda.demo.facturas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.panda.demo.facturas.interfaceService.IFacturaService;
import com.panda.demo.facturas.modelos.Factura;
import com.panda.demo.facturas.request.FacturaRequest;

@Controller
@RequestMapping
public class Controlador {
	@Autowired
	private IFacturaService facturaService;
	
	@GetMapping("/listar-facturas")
	public String listar(Model model) {
		List<Factura> facturas = facturaService.listar();
		model.addAttribute("facturas", facturas);
		return "from";
	}
	@GetMapping("/nueva-factura")
	public String agregar(Model model) {
		
		model.addAttribute("facturaRequest", new FacturaRequest());
		return "form";
	}
	@PostMapping("registar-factura")
	public String registrarFactura(@Validated FacturaRequest f, Model model) {
		facturaService.save(f);
		return "redirect:/listar";
	}
}
