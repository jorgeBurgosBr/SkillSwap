package com.skill_swap.entidades;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Size(max = 20)
	private String nombre;

	@Size(max = 40)
	private String apellido;

	@Size(max = 30)
	private String email;

	private String fotoDePerfil;

	// Aqui no se pone nada de longitud xq se va a hashear
	private String contrasena;

	private String urlGitHub;

	@Size(max = 50)
	private String puestoEmpresa;

	@ManyToMany
	@JoinTable
	private List<Skill> skills;
	
	//CAMBIOS DEL LOGIN
	
	private boolean activo = true;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="usuario")
	private Set<UsuarioRol> usuarioRoles = new HashSet<>();

	public Usuario() {
		super();
	}
	
	public Usuario(Long id, @Size(max = 20) String nombre, @Size(max = 40) String apellido,
			@Size(max = 30) String email, String fotoDePerfil, String contrasena, String urlGitHub,
			@Size(max = 50) String puestoEmpresa, List<Skill> skills, boolean activo, Set<UsuarioRol> usuarioRoles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fotoDePerfil = fotoDePerfil;
		this.contrasena = contrasena;
		this.urlGitHub = urlGitHub;
		this.puestoEmpresa = puestoEmpresa;
		this.skills = skills;
		this.activo = activo;
		this.usuarioRoles = usuarioRoles;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFotoDePerfil() {
		return fotoDePerfil;
	}

	public void setFotoDePerfil(String fotoDePerfil) {
		this.fotoDePerfil = fotoDePerfil;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getUrlGitHub() {
		return urlGitHub;
	}

	public void setUrlGitHub(String urlGitHub) {
		this.urlGitHub = urlGitHub;
	}

	public String getPuestoEmpresa() {
		return puestoEmpresa;
	}

	public void setPuestoEmpresa(String puestoEmpresa) {
		this.puestoEmpresa = puestoEmpresa;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Set<UsuarioRol> getUsuarioRoles() {
		return usuarioRoles;
	}

	public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

}
