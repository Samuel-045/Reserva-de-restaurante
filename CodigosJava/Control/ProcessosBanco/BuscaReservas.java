package ProcessosBanco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Geral.Restaurante;
import Servico.Relatorio;
import Servico.Reserva;

public class BuscaReservas {
	
	public List BuscaTodas() {
		List<Relatorio> reservas = new ArrayList<>();
		
		try {
			String nomeCliente = null;
			String nomeRestaurante = null;
			String quantidadePessoas= null;
			String restricaoEspecial = null;
			String tipoExperiencia= null;
			String dia= null;
			String valor= null;
			
			
			String sql = "SELECT * FROM reservas;";
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				
				nomeCliente = rs.getString("Nome_Cliente");
				nomeRestaurante= rs.getString("Nome_Restaurante");
				quantidadePessoas = rs.getString("Quantidade_Pessoas");
				restricaoEspecial = rs.getString("Restricao_Especial");
				tipoExperiencia= rs.getString("Tipo_Experiencia");
				dia =rs.getString("Dia");
				valor =rs.getString("valor");
			
				
				Relatorio reserva = new Relatorio(nomeCliente,nomeRestaurante,quantidadePessoas,restricaoEspecial,tipoExperiencia,dia,valor);
				reservas.add(reserva);
			}
			
		} catch (SQLException e) {
			System.out.println("Falha ao realizar a operação.");
			e.printStackTrace();
		}
		
	return reservas;
	}
	
	public List BuscaEspecifica(String data) {
		List<Relatorio> reservas = new ArrayList<>();
		try {
			String nomeCliente = null;
			String nomeRestaurante = null;
			String quantidadePessoas= null;
			String restricaoEspecial = null;
			String tipoExperiencia= null;
			String dia= null;
			String valor= null;
			
			
			String sql = "SELECT * FROM reservas WHERE Dia = ?;";
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, data);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				nomeCliente = rs.getString("Nome_Cliente");
				nomeRestaurante= rs.getString("Nome_Restaurante");
				quantidadePessoas = rs.getString("Quantidade_Pessoas");
				restricaoEspecial = rs.getString("Restricao_Especial");
				tipoExperiencia= rs.getString("Tipo_Experiencia");
				dia =rs.getString("Dia");
				valor =rs.getString("valor");
			
				
				Relatorio reserva = new Relatorio(nomeCliente,nomeRestaurante,quantidadePessoas,restricaoEspecial,tipoExperiencia,dia,valor);
			reservas.add(reserva);
			}
			
		} catch (SQLException e) {
			System.out.println("Falha ao realizar a operação.");
			e.printStackTrace();
		}
		
	return reservas;
	}

}
