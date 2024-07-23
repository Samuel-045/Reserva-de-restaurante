package ProcessosBanco;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Geral.Restaurante;
import Usuario.Cliente;

public class ExcluiRestaurante {

	
	public void ExcluiRestaurante(String nome) {
		String sql = "DELETE FROM Restaurantes WHERE Nome = ?;";
		PreparedStatement ps = null;
		
		try {
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, nome);
			ps.execute();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
}
