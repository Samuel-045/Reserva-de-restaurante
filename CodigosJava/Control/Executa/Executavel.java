package Executa;

import java.awt.Component;
import java.net.MalformedURLException;

import javax.swing.JFrame;

import ProcessosBanco.CadastraRestaurante;
import TelaBase.TelaLogin;
import TelasAdm.CadastroRestaurante;
import TelasAdm.RelatorioReservas;
import TelasAdm.TelaAltera;
import TelasAdm.TelaCadastroADM;
import TelasAdm.TelaExclui;
import TelasAdm.TelaLoginADM;
import TelasAdm.TelaMenuAdm;
import TelasCliente.Agradecimento;
import TelasCliente.BuscarRestaurante;
import TelasCliente.Pagamento;

import TelasCliente.SelecionarRestaurante;
import TelasCliente.TelaCadastroCliente;
import TelasCliente.TelaLoginCliente;
import Usuario.Cliente;

public class Executavel extends JFrame{
		
public static void main(String[] args) throws MalformedURLException {
		new TelaLogin(true,"Minimo"); 
}
}
 