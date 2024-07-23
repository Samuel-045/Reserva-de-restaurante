package ProcessosBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Supers.Pessoa;
import Usuario.Cliente;

public class LoginPessoa {
	public boolean loginCliente(String nome,String senha) {
		try {
			String nomee = null;
			String senhaa = null;
			String cpf=null;
			String email=null;
			String endereco=null;
			
			String sql = "SELECT * FROM clientes WHERE nome = ? AND senha = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, senha);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				nomee = rs.getString("Nome");
				senhaa = rs.getString("Senha");
				cpf = rs.getString("CPF");
				email = rs.getString("Email");
				endereco = rs.getString("Endereco");
			}
			Cliente cliente = new Cliente(nomee,cpf,email,senhaa,endereco);
			if(nomee==null && senhaa==null) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Falha ao realizar a operação.");
			e.printStackTrace();
		}
	return false;
	}
	public Cliente pegaCliente(String nome,String senha) {
		Cliente cliente=null;
		try {
			String nomee = null;
			String senhaa = null;
			String cpf=null;
			String email=null;
			String endereco=null;
			
			String sql = "SELECT * FROM clientes WHERE nome = ? AND senha = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, senha);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				nomee = rs.getString("Nome");
				senhaa = rs.getString("Senha");
				cpf = rs.getString("CPF");
				email = rs.getString("Email");
				endereco = rs.getString("Endereco");
			}
			cliente = new Cliente(nomee,cpf,email,senhaa,endereco);
		} catch (SQLException e) {
			System.out.println("Falha ao realizar a operação.");
			e.printStackTrace();
		}
	return cliente;
	}
	public boolean loginAdministrador(String nome,String senha) {
		try {
			String nomee = null;
			String senhaa = null;

			
			String sql = "SELECT * FROM Administradores WHERE nome = ? AND senha = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, senha);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				nomee = rs.getString("Nome");
				senhaa = rs.getString("Senha");
			}
			if(nomee==null && senhaa==null) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Falha ao realizar a operação.");
			e.printStackTrace();
		}
	return false;
	}

}
