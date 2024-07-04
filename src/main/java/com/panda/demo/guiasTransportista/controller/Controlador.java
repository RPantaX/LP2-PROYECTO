package com.panda.demo.guiasTransportista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.panda.demo.guiasTransportista.interfaceService.IGuiasTransportistaService;
import com.panda.demo.guiasTransportista.modelos.GuiaTransportista;
import com.panda.demo.guiasTransportista.request.GuiaTransportistaRequest;

@Controller
@RequestMapping
public class Controlador {
	@Autowired
	private IGuiasTransportistaService guiaTransportistaService;
	
	@GetMapping("/listar-guias-transportista")
	public String listar(Model model) {
		List<GuiaTransportista> guiasTransportista = guiaTransportistaService.listar();
		model.addAttribute("guiasTransportista", guiasTransportista);
		return "index";
	}
	@GetMapping("/nueva-guia-transportista")
	public String agregar(Model model) {
		
		model.addAttribute("guiaTransportistaRequest", new GuiaTransportistaRequest());
		return "form";
	}
	@PostMapping("registar-guia-transportista")
	public String registrarFactura(@Validated GuiaTransportistaRequest g, Model model) {
		guiaTransportistaService.save(g);
		return "redirect:/listar";
	}
}
