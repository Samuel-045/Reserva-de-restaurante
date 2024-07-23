package ProcessosBanco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Geral.Restaurante;

public class AlteraRestaurante {
	public boolean AlteraRest(Restaurante restaurante,String nome) {
		try {

			String sql = "UPDATE Restaurantes SET Nome = ?, Endereco = ?, Chef = ?, Estrelas = ?, Descricao = ?, Experiencia_Basica = ?, Experiencia_Completa = ?, Experiencia_Vip = ?, Url = ? WHERE Nome = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, restaurante.getNome());
			ps.setString(2, restaurante.getEndereco());
			ps.setString(3,restaurante.getChef());
			ps.setInt(4, restaurante.getEstrelas());
			ps.setString(5, restaurante.getDescricao());
			ps.setString(6, restaurante.getExperienciaBasica());
			ps.setString(7, restaurante.getExperienciaCompleta());
			ps.setString(8, restaurante.getExperienciaVip());
			ps.setString(9, restaurante.getUrlImagem());
			ps.setString(10, nome);

			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Falha ao realizar a operação.");
			e.printStackTrace();
		}
	return false;
	}
}
