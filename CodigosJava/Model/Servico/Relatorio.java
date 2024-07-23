package Servico;
import ProcessosBanco.BuscaReservas;
import ProcessosBanco.CadastraReserva;
import Usuario.Cliente;

public class Relatorio {
private String nomeCliente;
private String nomeRestaurante;
private String quantidadePessoas;
private String restricaoEspecial;
private String tipoExperiencia;
private String dia;
private String valor;

public Relatorio() {
	
}

public Relatorio(String nomeCliente,String nomeRestaurante,String quantidadePessoas,String restricaoEspecial,String tipoExperiencia,String dia,String valor){
this.nomeCliente=nomeCliente;
this.nomeRestaurante=nomeRestaurante;
this.quantidadePessoas=quantidadePessoas;
this.restricaoEspecial=restricaoEspecial;
this.tipoExperiencia=tipoExperiencia;
this.dia=dia;
this.valor=valor;
	
}




public String getNomeCliente() {
	return nomeCliente;
}

public void setNomeCliente(String nomeCliente) {
	this.nomeCliente = nomeCliente;
}

public String getNomeRestaurante() {
	return nomeRestaurante;
}

public void setNomeRestaurante(String nomeRestaurante) {
	this.nomeRestaurante = nomeRestaurante;
}


public String getQuantidadePessoas() {
	return quantidadePessoas;
}


public void setQuantidadePessoas(String quantidadePessoas) {
	this.quantidadePessoas = quantidadePessoas;
}


public String getRestricaoEspecial() {
	return restricaoEspecial;
}


public void setRestricaoEspecial(String restricaoEspecial) {
	this.restricaoEspecial = restricaoEspecial;
}


public String getTipoExperiencia() {
	return tipoExperiencia;
}


public void setTipoExperiencia(String tipoExperiencia) {
	this.tipoExperiencia = tipoExperiencia;
}


public String getDia() {
	return dia;
}


public void setDia(String dia) {
	this.dia = dia;
}


public String getValor() {
	return valor;
}


public void setValor(String valor) {
	this.valor = valor;
}



}
