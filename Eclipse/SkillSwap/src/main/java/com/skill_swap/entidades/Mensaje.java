package com.skill_swap.entidades;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Mensaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id_mensaje;
	
	@ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
	
	@ManyToOne
    @JoinColumn(name = "id_chat")
    private Chat chat;
	
	private String texto;
	
	private Date fecha;

	public Mensaje(Long id_mensaje, Usuario usuario, Chat chat, String texto, Date fecha) {
		super();
		this.id_mensaje = id_mensaje;
		this.usuario = usuario;
		this.chat = chat;
		this.texto = texto;
		this.fecha = fecha;
	}
	
	public Mensaje() {
		super();
	}

	public Long getId_mensaje() {
		return id_mensaje;
	}

	public void setId_mensaje(Long id_mensaje) {
		this.id_mensaje = id_mensaje;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}