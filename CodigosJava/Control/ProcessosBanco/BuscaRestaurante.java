package ProcessosBanco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Geral.Restaurante;


public class BuscaRestaurante {

	
	public Restaurante Busca(String nome) {
		Restaurante buscado = null;
		try {
			String nomee = null;
			String url = null;
			int estrelas = 0;

			
			String sql = "SELECT * FROM Restaurantes WHERE Nome = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, nome);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				nomee = rs.getString("Nome");
				url = rs.getString("Url");
				estrelas = rs.getInt("Estrelas");
			}
			
			buscado= new Restaurante(nomee,null,null,estrelas,null,null,null,null,url);
			buscado.setNome(nomee);
			buscado.setUrlImagem(url);
			buscado.setEstrelas(estrelas);
			if(nomee==null) {
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println("Falha ao realizar a operação.");
			e.printStackTrace();
		}
		
	return buscado;
	}
	public Restaurante BuscaAlt(String nome) {
		Restaurante buscado = null;
		try {
			String nomee = null;
			String endereco = null;
			String chef= null;
			int estrelas = 0;
			String descricao= null;
			String basica= null;
			String completa= null;
			String vip= null;
			String url = null;

			
			String sql = "SELECT * FROM Restaurantes WHERE Nome = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, nome);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				nomee = rs.getString("Nome");
				endereco= rs.getString("Endereco");
				estrelas = rs.getInt("Estrelas");
				chef = rs.getString("Chef");
				descricao= rs.getString("Descricao");
				basica =rs.getString("Experiencia_Basica");
				completa =rs.getString("Experiencia_Completa");
				vip =rs.getString("Experiencia_Vip");
				url = rs.getString("Url");
			}
			
			buscado= new Restaurante(nomee,endereco,chef,estrelas,descricao,basica,completa,vip,url);
			buscado.setNome(nomee);
			buscado.setUrlImagem(url);
			buscado.setEstrelas(estrelas);
			if(nomee==null) {
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println("Falha ao realizar a operação.");
			e.printStackTrace();
		}
		
	return buscado;
	}
	public List BuscaTodos() {
		List<Restaurante> restaurantes = new ArrayList<>();
		
		try {
			String nomee = null;
			String endereco = null;
			String chef= null;
			int estrelas = 0;
			String descricao= null;
			String basica= null;
			String completa= null;
			String vip= null;
			String url = null;
			
			String sql = "SELECT * FROM restaurantes;";
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				
				nomee = rs.getString("Nome");
				endereco= rs.getString("Endereco");
				estrelas = rs.getInt("Estrelas");
				chef = rs.getString("Chef");
				descricao= rs.getString("Descricao");
				basica =rs.getString("Experiencia_Basica");
				completa =rs.getString("Experiencia_Completa");
				vip =rs.getString("Experiencia_Vip");
				url = rs.getString("Url");
				
				Restaurante restaurante = new Restaurante(nomee,endereco,chef,estrelas,descricao,basica,completa,vip,url);
				restaurantes.add(restaurante);
			}
			System.out.println(restaurantes.get(0).getUrlImagem());
			
			if(nomee==null) {
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println("Falha ao realizar a operação.");
			e.printStackTrace();
		}
		
	return restaurantes;
	}
}
