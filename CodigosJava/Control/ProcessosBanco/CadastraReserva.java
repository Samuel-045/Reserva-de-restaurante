package ProcessosBanco;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Servico.Reserva;
import Usuario.Cliente;

public class CadastraReserva {

	public void cadastraReserva(Reserva reserva) {
		String sql = "INSERT INTO Reservas(Nome_Cliente,Nome_Restaurante,Quantidade_Pessoas,Restricao_Especial,Tipo_Experiencia,Dia,Valor) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		
		try {
			ps=BancoDeDados.ConectaBanco().prepareStatement(sql);
			ps.setString(1, reserva.getCliente().getNome());
			ps.setString(2, reserva.getRestaurante().getNome());
			ps.setString(3, reserva.getQuantidadePessoas());
			ps.setString(4, reserva.getRestricaoEspecial());
			ps.setString(5, reserva.getExperiencia());
			ps.setString(6, formatarData(reserva.getData()));
			ps.setDouble(7, reserva.getValor());
			
			ps.execute();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
	 private String formatarData(String dataString) {
	        try {
	           
	            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.ENGLISH);
	            Date date = inputFormat.parse(dataString);

	          
	            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
	            return outputFormat.format(date);
	        } catch (ParseException e) {
	            e.printStackTrace();
	            return dataString; 
	        }
	 }
	
}
