package Geral;

import ProcessosBanco.CadastraRestaurante;
import Servico.Experiencia;
import TelasCliente.BuscarRestaurante;

public class Restaurante {
	private String nome;
	private String endereco;
	private String chef;
	private int estrelas;
	private String descricao;
	private String experienciaBasica;
	private String experienciaCompleta;
	private String experienciaVip;
	private String urlImagem;
	
	public Restaurante() {
		
	}
	
	public Restaurante(String nome, String endereco, String chef,int estrelas,String descricao,String experienciaBasica,String experienciaCompleta,String experienciaVip,String url) {
	this.nome=nome;
	this.endereco=endereco;
	this.chef=chef;
	this.estrelas=estrelas;
	this.descricao=descricao;
	this.experienciaBasica=experienciaBasica;
	this.experienciaCompleta=experienciaCompleta;
	this.experienciaVip=experienciaVip;
	this.urlImagem=url;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getEstrelas() {
		return estrelas;
	}
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setEstrelas(int estrelas) {
		this.estrelas = estrelas;
	}

	public String getChef() {
		return chef;
	}

	public void setChef(String chef) {
		this.chef = chef;
	}

	public String getExperienciaBasica() {
		return experienciaBasica;
	}

	public void setExperienciaBasica(String experienciaBasica) {
		this.experienciaBasica = experienciaBasica;
	}

	public String getExperienciaCompleta() {
		return experienciaCompleta;
	}

	public void setExperienciaCompleta(String experienciaCompleta) {
		this.experienciaCompleta = experienciaCompleta;
	}

	public String getExperienciaVip() {
		return experienciaVip;
	}

	public void setExperienciaVip(String experienciaVip) {
		this.experienciaVip = experienciaVip;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}
	public void cadastraBanco() {
		CadastraRestaurante cadastro = new CadastraRestaurante();
		cadastro.cadastraRestaurante(this);
	}
	
	
}
