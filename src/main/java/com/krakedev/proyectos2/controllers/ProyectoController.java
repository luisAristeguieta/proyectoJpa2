package com.krakedev.proyectos2.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.proyectos2.entity.Proyecto;
import com.krakedev.proyectos2.services.ProyectoService;

@RestController
@RequestMapping("api/proyectos")
public class ProyectoController {

	private final ProyectoService servicio;

	public ProyectoController(ProyectoService servicio) {
		this.servicio = servicio;
	}

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Proyecto proyecto) {

		try {
			Proyecto nuevo = servicio.crear(proyecto);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear Proyecto");
		}
	}

	@GetMapping
	public ResponseEntity<?> listar() {
		try {
			List<Proyecto> proyectos = servicio.listar();
			return ResponseEntity.ok(proyectos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear Proyecto");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable int id) {
		try {
			Proyecto proyectoEncontrado = servicio.buscarPorId(id);
			return ResponseEntity.ok(proyectoEncontrado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear Proyecto");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Proyecto proyecto) {
		try {
			Proyecto actualizado = servicio.actualizar(id, proyecto);
			return ResponseEntity.ok(actualizado);
		} catch (RuntimeException e) {
			if (e.getMessage().contains("no existe")) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar proyecto");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) {
		try {
			servicio.eliminar(id);
			return ResponseEntity.ok("Proyecto eliminado correctamente");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar proyecto");
		}
	}

}
