package Servico;
import java.util.Date;

import Geral.Restaurante;
import ProcessosBanco.CadastraPessoa;
import ProcessosBanco.CadastraReserva;
import ProcessosBanco.CadastraRestaurante;
import ProcessosBanco.CadastraReserva;
import Usuario.Cliente;

public class Reserva {
private Cliente cliente;
private Restaurante restaurante;
private String data;
private String quantidadePessoas;
private String restricaoEspecial;
private String experiencia;
private double valor;

public Reserva() {
	
}

public Reserva(Cliente cliente,Restaurante restaurante,String data,
		String quantidadedePessoas,String restricaoExpecial,String experiencia
		,double valor) {
	this.cliente=cliente;
	this.restaurante=restaurante;
	this.data=data;
	this.quantidadePessoas=quantidadedePessoas;
	this.restricaoEspecial=restricaoExpecial;
	this.experiencia=experiencia;
	this.valor=valor;
}

public void cadastraBanco() {
	CadastraReserva cadastro = new CadastraReserva();
	cadastro.cadastraReserva(this);
}


public Cliente getCliente() {
	return cliente;
}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}

public Restaurante getRestaurante() {
	return restaurante;
}
public void setRestaurante(Restaurante restaurante) {
	this.restaurante = restaurante;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public String getQuantidadePessoas() {
	return quantidadePessoas;
}
public void setQuantidadePessoas(String quantidadePessoas) {
	this.quantidadePessoas = quantidadePessoas;
}
public double getValor() {
	return valor;
}
public void setValor(double valor) {
	this.valor = valor;
}
public String getRestricaoEspecial() {
	return restricaoEspecial;
}
public void setRestricaoEspecial(String restricaoEspecial) {
	this.restricaoEspecial = restricaoEspecial;
}
public String getExperiencia() {
	return experiencia;
}
public void setExperiencia(String experiencia) {
	this.experiencia = experiencia;
}





}
