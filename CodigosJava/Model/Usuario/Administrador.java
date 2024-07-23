package Usuario;
import ProcessosBanco.CadastraPessoa;
import Supers.Pessoa;

public class Administrador extends Pessoa{
private int chave;

public Administrador() {
	
}

public Administrador(String nome,String cpf,String email,String senha,String endereco) {
	this.setSenha(senha);
	this.setCPF(cpf);
	this.setNome(nome);
	this.setEmail(email);
	this.setEndereco(endereco);
}
public void cadastraBanco() {
	CadastraPessoa cadastro = new CadastraPessoa();
	cadastro.cadastraAdministrador(this);
}

public int getChave() {
	return chave;
}

public void setChave(int chave) {
	this.chave = chave;
}

}
