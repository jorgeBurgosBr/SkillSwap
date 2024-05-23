package com.skill_swap.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skill_swap.entidades.Articulo;
import com.skill_swap.entidades.Valoracion;
import com.skill_swap.repositorios.ArticuloRepositorio;
import com.skill_swap.repositorios.UsuarioRepositorio;
import com.skill_swap.repositorios.ValoracionRepositorio;

@Service
public class ValoracionServicio {

	@Autowired
	private ValoracionRepositorio valoracionRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private ArticuloRepositorio articuloRepositorio;
	
	public List<Valoracion> obtenerTodasLasValoraciones(){
		return valoracionRepositorio.findAll();
	}
	
	public Optional<Valoracion> obtenerValoracionPorId(Long id){
		return valoracionRepositorio.findById(id);
	}
	
	public Optional<List<Valoracion>> obtenerValoracionPorUsuario(Long id){
		return valoracionRepositorio.findByUsuario(usuarioRepositorio.findById(id).get());
	}
	
	public Optional<List<Valoracion>> obtenerValoracionPorArticulo(Long id){
		return valoracionRepositorio.findByArticulo(articuloRepositorio.findById(id).get());
	}
	
	public Valoracion crearValoracion(Valoracion valoracion) {
		return valoracionRepositorio.save(valoracion);
	}
	
	public boolean eliminarValoraciono(Long id) {
	    if (valoracionRepositorio.findById(id).isPresent()) {
	    	valoracionRepositorio.deleteById(id);
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public Valoracion actualizarValoracion(Long id, Valoracion valoracion) {
		Optional<Valoracion> valoracionExistente = valoracionRepositorio.findById(id);

		if (valoracionExistente.isPresent()) {
			Valoracion valoracionActualizada = valoracionExistente.get();
			valoracionActualizada.setUsuario(valoracionExistente.get().getUsuario());
			valoracionActualizada.setArticulo(valoracionExistente.get().getArticulo());
			valoracionActualizada.setPuntuacion(valoracion.getPuntuacion());

			return valoracionRepositorio.save(valoracionActualizada);
		} else {
			return null;
		}
	}
	
	
	
	
}
