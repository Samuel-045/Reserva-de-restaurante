package ProcessosBanco;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Supers.Pessoa;
import Usuario.Administrador;
import Usuario.Cliente;

public class CadastraPessoa {

	
	public void cadastraCliente(Cliente cliente) {
		String sql = "INSERT INTO Clientes(NOME,CPF,EMAIL,SENHA,ENDERECO) VALUES(?,?,?,?,?)";
		PreparedStatement ps = null;
		
		try {
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCPF());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getSenha());
			ps.setString(5, cliente.getEndereco());
			
			ps.execute();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void cadastraAdministrador(Administrador administrador) {
		String sql = "INSERT INTO Administradores(NOME,CPF,EMAIL,SENHA,ENDERECO) VALUES(?,?,?,?,?)";
		PreparedStatement ps = null;
		
		try {
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, administrador.getNome());
			ps.setString(2, administrador.getCPF());
			ps.setString(3, administrador.getEmail());
			ps.setString(4, administrador.getSenha());
			ps.setString(5, administrador.getEndereco());
			
			ps.execute();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
}
