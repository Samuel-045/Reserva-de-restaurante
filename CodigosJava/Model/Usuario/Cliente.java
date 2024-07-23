package Usuario;
import ProcessosBanco.CadastraPessoa;
import Supers.Pessoa;

public class Cliente extends Pessoa {

public Cliente() {
	
}
public Cliente(String nome,String cpf,String email,String senha,String endereco) {
	this.setNome(nome);
	this.setCPF(cpf);
	this.setEmail(email);
	this.setSenha(senha);
	this.setEndereco(endereco);
}

public void cadastraBanco() {
	CadastraPessoa cadastro = new CadastraPessoa();
	cadastro.cadastraCliente(this);
}


public boolean realizaPagamento() {
	return true;
}

public boolean realizaReserva() {
	return true;
}


}
