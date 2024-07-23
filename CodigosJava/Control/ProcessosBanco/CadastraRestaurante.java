package ProcessosBanco;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Geral.Restaurante;
import Usuario.Administrador;

public class CadastraRestaurante {
	
	public void cadastraRestaurante(Restaurante restaurante) {
		String sql = "INSERT INTO Restaurantes(NOME,ENDERECO,CHEF,ESTRELAS,DESCRICAO,EXPERIENCIA_BASICA,EXPERIENCIA_COMPLETA,EXPERIENCIA_VIP,URL) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		
		try {
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, restaurante.getNome());
			ps.setString(2, restaurante.getEndereco());
			ps.setString(3, restaurante.getChef());
			ps.setInt(4, restaurante.getEstrelas());
			ps.setString(5, restaurante.getDescricao());
			ps.setString(6,  restaurante.getExperienciaBasica());
			ps.setString(7,  restaurante.getExperienciaCompleta());
			ps.setString(8,  restaurante.getExperienciaVip());
			ps.setString(9,  restaurante.getUrlImagem());
			
			ps.execute();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
}
