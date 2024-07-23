package TelasCliente;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import Geral.Restaurante;
import ProcessosBanco.BuscaRestaurante;
import Servico.Reserva;
import TelasAdm.TelaMenuAdm;
import Usuario.Cliente;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
public class InformacoesRestaurante {
	private static Point mouseDownCompCoords;
	JFrame frame = new JFrame();
	Boolean apertou = false;
	JButton maximo = new JButton("□");
	Cliente clienteTela = new Cliente();
	JButton fecha = new JButton("X");
	JButton minimo = new JButton("━");
	JButton seta = new JButton("←");
	boolean ok=false;
	double valor;
	JPanel campoData= new JPanel(){
		 @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D graphics2D = (Graphics2D) g.create();
	            int width = getWidth();
	            int height = getHeight();
	            int cornerRadius = 30;
	            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius,cornerRadius);
	            graphics2D.setColor(getBackground());
	            graphics2D.setColor(getForeground());
	            graphics2D.setStroke(new BasicStroke(3));
	            graphics2D.draw(roundedRectangle);
	            graphics2D.dispose();
}
	};
	JTextArea  campoRestricao;
	JLabel labelNomeRestaurante,labelData, labelEndereco, labelChef,labelDescricao, labelExperiencia, labelLugaresMesa, labelRestricao;
	Reserva reserva = null;
	ImageIcon imagem = null;
	JLabel containerImagem = new JLabel(){
		 @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D graphics2D = (Graphics2D) g.create();
	            int width = getWidth();
	            int height = getHeight();
	            int cornerRadius = 30;
	            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius,cornerRadius);
	            graphics2D.setColor(getBackground());
	            graphics2D.setColor(getForeground());
	            graphics2D.setStroke(new BasicStroke(15));
	            graphics2D.draw(roundedRectangle);
	            graphics2D.dispose();
  }
	};
	
	JRadioButton rbBasica, rbCompleta, rbVip, rb2_4, rb6_8, rb12_16;
	ButtonGroup grupoBotoesExperiencias = new ButtonGroup();
	ButtonGroup grupoBotoesLugares = new ButtonGroup();
	Restaurante restaurante = new Restaurante();
	JButton botaoCadastrar;
	JCalendar calendario = new JCalendar();
	JPanel panelDeFundo = new JPanel();
	JPanel novaBarra = new JPanel(new BorderLayout()) {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(fecha.getFont().deriveFont(Font.BOLD, 14));
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.white);
			g.drawString("", getWidth() / 2 - 50, 25);
		}
	};
	JPanel conteudoMeioDaTela = new JPanel();
	JPanel barraNomeRestaurante = new JPanel(){
		 @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D graphics2D = (Graphics2D) g.create();
	            int width = getWidth();
	            int height = getHeight();
	            int cornerRadius = 30;
	            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius,cornerRadius);
	            graphics2D.setColor(getBackground());
	            graphics2D.setColor(getForeground());
	            graphics2D.setStroke(new BasicStroke(15));
	            graphics2D.draw(roundedRectangle);
	            graphics2D.dispose();
      }
	};
	JPanel conteudoRestaurante = new JPanel(){
		 @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D graphics2D = (Graphics2D) g.create();
	            int width = getWidth();
	            int height = getHeight();
	            int cornerRadius = 30;
	            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius,cornerRadius);
	            graphics2D.setColor(getBackground());
	            graphics2D.setColor(getForeground());
	            graphics2D.setStroke(new BasicStroke(15));
	            graphics2D.draw(roundedRectangle);
	            graphics2D.dispose();
     }
	};
	
	
	JScrollPane scrollPane = new JScrollPane(conteudoRestaurante);
	
	public InformacoesRestaurante(boolean ligaTela,String size,String nome,Cliente cliente) throws MalformedURLException{
		
		BuscaRestaurante busca = new BuscaRestaurante(); 
		restaurante = busca.BuscaAlt(nome);
		clienteTela=cliente;
		// Remove a barra de titulo padrao
		frame.setUndecorated(true);
		// Define o tamanho
		frame.setSize(1000, 600);
		// Centraliza
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
 
		// Cria um barra de titulo nova
		// define o tamanho da barra com a largura no tamanho da tela e a altura em 40
		novaBarra.setBounds(0, 0, frame.getWidth(), 40);
 
		// cria um painel para juntar os botoes
		JPanel botoes = new JPanel();
		// Define o tamanho do painel
		botoes.setSize(300, 30);
		// Define a cor
		botoes.setBackground(Color.black);
 
		// Adiciona uma açao no botao para fechar a tela
		fecha.addActionListener(e -> frame.dispose());
		// Muda a fonte e o tamanho da letra do botao
		fecha.setFont(fecha.getFont().deriveFont(Font.BOLD, 14));
		// Tira o design que fica em volta do botao
		fecha.setFocusPainted(false);
		// Tira o sublinhado do botao
		fecha.setBorderPainted(false);
		// Muda a cor
		fecha.setBackground(Color.black);
		fecha.setForeground(Color.white);
		fecha.setName("fecha");

 
		// Mesma coisa do botao de cima
		maximo.setFont(maximo.getFont().deriveFont(Font.BOLD, 14));
		maximo.setFocusPainted(false);
		maximo.setBorderPainted(false);
		maximo.setBackground(Color.black);
		maximo.setForeground(Color.white);
		maximo.setName("maximo");
		// Chama a funçao para evento de maximizar a tela
		maximo.addActionListener(this::maximiza);
 
		minimo.addActionListener(e -> frame.setState(JFrame.ICONIFIED));
		minimo.setFont(minimo.getFont().deriveFont(Font.BOLD, 14));
		minimo.setFocusPainted(false);
		minimo.setBorderPainted(false);
		minimo.setBackground(Color.black);
		minimo.setForeground(Color.white);
		minimo.setName("minimo");
		//adiciona evento de hover
		fecha.addMouseListener(eventoMouse(fecha));
		maximo.addMouseListener(eventoMouse(maximo));
		minimo.addMouseListener(eventoMouse(minimo));
		// Adiciona os botoes ao painel
		botoes.add(minimo);
		botoes.add(maximo);
		botoes.add(fecha);
		// adiciona o painel de botoes na barra de titulo
		criaBotao("Seta",seta,0,0, 30, 40, Color.black, Color.white,seta.getFont().deriveFont(Font.BOLD, 34));
		seta.addMouseListener(eventoMouse(seta));
		// adiciona o painel de botoes na barra de titulo
		novaBarra.add(botoes, BorderLayout.EAST);
		novaBarra.add(seta,BorderLayout.WEST);
 
		// Adiciona um evento para quando clicar o mouse na tela, pegar as coordenadas
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseDownCompCoords = e.getPoint();
			}
		});
		// Adiciona um evento para quando clicar o mouse na tela e arrastar pegar as
		// coordenadas e subtrair com a antiga parada
		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
			}
		});
		// Adiciona a barra de titulo na tela
		frame.getContentPane().add(novaBarra);
		panelDeFundo.setBackground(Color.black);
		panelDeFundo.setBounds(0,0,frame.getWidth(),frame.getHeight());
		panelDeFundo.setLayout(null);
		frame.getContentPane().add(panelDeFundo);
		
		conteudoMeioDaTela.setBackground(Color.black);
		conteudoMeioDaTela.setBounds(frame.getWidth()/8, 50, (frame.getWidth()/4)*3, frame.getHeight()-100);
		conteudoMeioDaTela.setLayout(null);
		panelDeFundo.add(conteudoMeioDaTela);
		
		barraNomeRestaurante.setBounds(0, 0, conteudoMeioDaTela.getWidth(),50);
		barraNomeRestaurante.setForeground(Color.black);
		barraNomeRestaurante.setBackground(new Color(255,180,91));
		barraNomeRestaurante.setLayout(null);
		barraNomeRestaurante.setAlignmentX(FlowLayout.CENTER);
		
		conteudoMeioDaTela.add(barraNomeRestaurante);
		
	
		
		labelNomeRestaurante = criaLabel(barraNomeRestaurante.getWidth()/2-70, 10, 150, 30, "labelNomeRestaurante", restaurante.getNome());
		labelNomeRestaurante.setFont(labelNomeRestaurante.getFont().deriveFont(Font.BOLD, 14));
		labelNomeRestaurante.setHorizontalAlignment(0);
		labelNomeRestaurante.setForeground(Color.black);
		barraNomeRestaurante.add(labelNomeRestaurante);
		
//		=======DAQUI PARA BAIXO É O CÓDIGO QUE VAI NO CENTRO DA TELA========	
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.black);
		scrollPane.setForeground(Color.white);
		int localizacaoX =  ( ( conteudoMeioDaTela.getWidth() - (conteudoMeioDaTela.getWidth()/4)*3)  /2); 
		scrollPane.setBounds( localizacaoX , 80, (conteudoMeioDaTela.getWidth()/4)*3, conteudoMeioDaTela.getHeight()-150);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		conteudoMeioDaTela.add(scrollPane);
		
		conteudoRestaurante.setPreferredSize(new Dimension((conteudoMeioDaTela.getWidth() / 3) * 2, 800));
		conteudoRestaurante.setBackground(Color.BLACK);
		conteudoRestaurante.setForeground(Color.black);
		conteudoRestaurante.setLayout(null);
		
		containerImagem.setBounds(180,0,200,180);
		containerImagem.setForeground(Color.black);
		URL urlNova = new URL(restaurante.getUrlImagem());
		ImageIcon imagemN = new ImageIcon(urlNova);
		Image imge = imagemN.getImage();
		Image  imgScaleLogoN =  imge.getScaledInstance(containerImagem.getWidth(),containerImagem.getHeight(), Image.SCALE_SMOOTH);
		imagem = new ImageIcon(imgScaleLogoN);
		containerImagem.setIcon(imagem);
		conteudoRestaurante.add(containerImagem);
		
		labelEndereco = criaLabel(100, 190, 400, 20, "labelEndereco","Endereço: "+restaurante.getEndereco());
		conteudoRestaurante.add(labelEndereco);
		
		labelChef = criaLabel(245, 210, 200, 20, "labelChef","Chef: "+restaurante.getChef());
		conteudoRestaurante.add(labelChef);
		
		labelDescricao = criaLabel(100, 220, 400, 40, "labelDescricao","Descriçao: "+restaurante.getDescricao());
		conteudoRestaurante.add(labelDescricao);
		
		labelExperiencia = criaLabel(243, 270, 200, 20, "labelExperiencia", "Experiencia");
		conteudoRestaurante.add(labelExperiencia);
		
		rbBasica = criaRbBotao(40, 300, 100, 20, "rbBasica", "Basica");
		rbBasica.addMouseListener(eventoSelecao(rbBasica, restaurante));
		conteudoRestaurante.add(rbBasica);
		
		rbCompleta = criaRbBotao(250, 300, 100, 20, "rbCompleta", "Completa");
		rbCompleta.addMouseListener(eventoSelecao(rbCompleta, restaurante));
		conteudoRestaurante.add(rbCompleta);
		
		rbVip = criaRbBotao(460, 300, 100, 20, "rbVip", "Vip");
		rbVip.addMouseListener(eventoSelecao(rbVip, restaurante));
		conteudoRestaurante.add(rbVip);
		
		grupoBotoesExperiencias.add(rbBasica);
		grupoBotoesExperiencias.add(rbCompleta);
		grupoBotoesExperiencias.add(rbVip);
		
		labelLugaresMesa = criaLabel(230, 340, 200, 20, "labelLugarMesa", "Lugares na mesa");
		conteudoRestaurante.add(labelLugaresMesa);

		rb2_4 = criaRbBotao(40, 360, 60, 20, "rb2-4", "2-4");
		conteudoRestaurante.add(rb2_4);
		
		rb6_8 = criaRbBotao(250, 360, 60, 20,"rb6-8", "5-10");
		conteudoRestaurante.add(rb6_8);
		
		rb12_16 = criaRbBotao(460, 360, 60, 20,"rb12-16", "11-15");
		conteudoRestaurante.add(rb12_16);
		
		grupoBotoesLugares.add(rb2_4);
		grupoBotoesLugares.add(rb6_8);
		grupoBotoesLugares.add(rb12_16);
		
		labelRestricao = criaLabel(225, 390, 250, 20, "labelRestricao", "Restrição Especial");
		conteudoRestaurante.add(labelRestricao);
		
		campoRestricao = criaCampo(20, 410, conteudoMeioDaTela.getWidth()-240, 120, "campoRestricao", true);
		campoRestricao.setForeground(Color.white);
		conteudoRestaurante.add(campoRestricao);
		
		labelData = criaLabel(225, 540, 250, 20, "labelDara", "Data da reserva");
		conteudoRestaurante.add(labelData);
		
		campoData.setBounds(140, 560, conteudoMeioDaTela.getWidth()-500, 180);
		campoData.setName("campoData");
		campoData.setBackground(Color.black);
		campoData.setForeground(Color.white);
		
		
		campoData.add(escolheData(calendario));
		
		conteudoRestaurante.add(campoData);
		
		
		botaoCadastrar = criaBotao(300, conteudoMeioDaTela.getHeight()-50, 150, 30, "botaoCadastrar", "Realizar Pagamento");
		botaoCadastrar.addMouseListener(eventoMouse(botaoCadastrar));
		conteudoMeioDaTela.add(botaoCadastrar);
		
		frame.setVisible(true);
		 if(size.equalsIgnoreCase("max")) {
				maximiza(null);
			}
		acoes();
	}
	
	public JRadioButton criaRbBotao(int x, int y, int width, int heigth, String nome, String texto)  {
		JRadioButton rb = new JRadioButton();
		rb.setText(texto);
		rb.setForeground(Color.white);
		rb.setName(nome);
		rb.setBounds(x, y, width, heigth);
		rb.setBackground(Color.black);
		
		return rb;
	}
	
	public JLabel criaLabel(int x, int y, int width, int heigth, String nome, String texto) {
		JLabel jl = new JLabel();
		jl.setText(texto);
		jl.setName(nome);
		jl.setForeground(Color.white);
		jl.setBounds(x, y, width, heigth);
		return jl;
	}
	
	public JTextArea criaCampo(int x, int y, int width, int heigth, String nome, boolean editavel) {
		JTextArea ta = new JTextArea(){
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D graphics2D = (Graphics2D) g.create();
	            int width = getWidth();
	            int height = getHeight();
	            int cornerRadius = 20;
	            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius,cornerRadius);
	            graphics2D.setColor(getBackground());
	            graphics2D.setColor(getForeground());
	            graphics2D.setStroke(new BasicStroke(2));
	            graphics2D.draw(roundedRectangle);
	            graphics2D.dispose();

	        }
		};
		ta.setName(nome);
		ta.setBackground(Color.BLACK);
		ta.setForeground(Color.white);
		ta.setBorder(null);
		ta.setBounds(x, y, width, heigth);
		ta.setEditable(editavel);
		
		return ta;
	}
	
	public JButton criaBotao(int x, int y, int width, int heigth, String nome, String Texto) {
		JButton jb = new JButton(){
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D graphics2D = (Graphics2D) g.create();
	            int width = getWidth();
	            int height = getHeight();
	            int cornerRadius = 10;
	            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius,cornerRadius);
	            graphics2D.setColor(getBackground());
	            graphics2D.setColor(getForeground());
	            graphics2D.setStroke(new BasicStroke(6));
	            graphics2D.draw(roundedRectangle);
	            graphics2D.dispose();

	        }
		};
		jb.setText(Texto);
		jb.setName(nome);
		jb.setBackground(new Color(255,180,91));
		jb.setBorder(null);
		jb.setForeground(Color.BLACK);
		jb.setBounds(x, y, width, heigth);
		return jb;
	}
	
	// Metodo para maximizar a tela e desmaximizar
		private void maximiza(ActionEvent actionEvent) {
			if (apertou == false) {
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				novaBarra.setBounds(0, 0, frame.getWidth(), 40);
				panelDeFundo.setBounds(0,0,frame.getWidth(),frame.getHeight());
				int localizacaoX =  ( ( frame.getWidth() - 1100)  /2); 
				conteudoMeioDaTela.setBounds(localizacaoX, 50, 1100, frame.getHeight()-100);
				barraNomeRestaurante.setSize(conteudoMeioDaTela.getWidth(),50);
				//cria uma variável que altera o eixo x de forma dinâmica
				localizacaoX =  ( ( conteudoMeioDaTela.getWidth() - (conteudoMeioDaTela.getWidth()/4)*3)  /2); 
				scrollPane.setBounds( localizacaoX , 80, (conteudoMeioDaTela.getWidth()/4)*3, conteudoMeioDaTela.getHeight()-150);
				containerImagem.setLocation(320,0);
				
				labelNomeRestaurante.setLocation(barraNomeRestaurante.getWidth()/2-70, 10);
				
				labelEndereco.setLocation(200, 200); 
				labelEndereco.setSize(600,20);
				labelChef.setLocation(350, 220); 
				labelDescricao.setLocation(150, 240); 
				labelDescricao.setSize(600,20);
				
				labelExperiencia.setLocation(385,270);
				labelData.setLocation(385, 540);
				campoData.setBounds(270, 560, conteudoMeioDaTela.getWidth()-800, 180);
				
				rbBasica.setLocation(70, 300);
				rbCompleta.setLocation(390, 300);
				rbVip.setLocation(670, 300);
				
				labelLugaresMesa.setLocation(375, 340); 
				
				rb2_4.setLocation(70, 360);
				rb6_8.setLocation(390, 360);
				rb12_16.setLocation(670, 360);
				
				labelRestricao.setLocation(370, 390); 
				campoRestricao.setBounds(40, 410, conteudoMeioDaTela.getWidth()-360, 120);
				
				localizacaoX = ( ( conteudoMeioDaTela.getWidth() - 140)  /2);
				botaoCadastrar.setLocation(localizacaoX, conteudoMeioDaTela.getHeight()-50);
				
				maximo.setText("❐");
				apertou = true;
			} else {
				frame.setSize(1000, 600);
				frame.setLocationRelativeTo(null);
				novaBarra.setBounds(0, 0, frame.getWidth(), 40);
				panelDeFundo.setBounds(0,0,frame.getWidth(),frame.getHeight());
				conteudoMeioDaTela.setBounds(frame.getWidth()/8, 50, (frame.getWidth()/4)*3, frame.getHeight()-100);
				barraNomeRestaurante.setSize(conteudoMeioDaTela.getWidth(),50);
				int localizacaoX =  ( ( conteudoMeioDaTela.getWidth() - (conteudoMeioDaTela.getWidth()/4)*3)  /2); 
				scrollPane.setBounds( localizacaoX , 80, (conteudoMeioDaTela.getWidth()/4)*3, conteudoMeioDaTela.getHeight()-150);
				containerImagem.setLocation(180,0);
				
				labelNomeRestaurante.setLocation(barraNomeRestaurante.getWidth()/2-70, 10);
				
				labelEndereco.setLocation(100, 200); 
				labelEndereco.setSize(400,20);
				labelChef.setLocation(200, 220); 
				labelDescricao.setLocation(100, 240); 
				labelDescricao.setSize(400,20);
				
				labelExperiencia.setLocation(243,270);
				
				labelData.setLocation(225, 540);
				campoData.setBounds(140, 560, conteudoMeioDaTela.getWidth()-500, 180);
				rbBasica.setLocation(40, 300);
				rbCompleta.setLocation(250, 300);
				rbVip.setLocation(460, 300);
				
				labelLugaresMesa.setLocation(230, 340); 
				
				rb2_4.setLocation(40, 360);
				rb6_8.setLocation(250, 360);
				rb12_16.setLocation(460, 360);
				
				labelRestricao.setLocation(225, 390); 
				campoRestricao.setBounds(20, 410, conteudoMeioDaTela.getWidth()-240, 120);
				
				botaoCadastrar.setLocation(300, conteudoMeioDaTela.getHeight()-50);

				maximo.setText("□");
				apertou = false;
			}
		};
	
	
	public void acoes() {
		
	}
 
	//Metodo adiciona evento de mouse botoes
	public MouseAdapter eventoMouse(JButton botao) {
		MouseAdapter evento = null;
		if(botao.getName().equalsIgnoreCase("fecha") || botao.getName().equalsIgnoreCase("maximo") || botao.getName().equalsIgnoreCase("minimo")){
			evento = new java.awt.event.MouseAdapter() {
				 public void mouseEntered(java.awt.event.MouseEvent evt) {
				        botao.setBackground(Color.red);
				    }
				    public void mouseExited(java.awt.event.MouseEvent evt) {
				        botao.setBackground(Color.black);
				    }};
		} else if (botao.getName().equalsIgnoreCase("botaoCadastrar")) {
			evento = new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					botao.setBackground(new Color(217, 217, 217));
				}
				public void mouseClicked(java.awt.event.MouseEvent evt) {
			    	if(campoRestricao.getText().isEmpty()) {
					 JOptionPane.showMessageDialog(null, "Existem campos vazios"); 
						 }else{
							 if(!rbBasica.isSelected()&&!rbCompleta.isSelected()&&!rbVip.isSelected()) {
								 JOptionPane.showMessageDialog(null, "Selecione uma experiencia");
							 }
							 if(!rb2_4.isSelected()&&!rb6_8.isSelected()&&!rb12_16.isSelected()) {
								 JOptionPane.showMessageDialog(null, "Selecione a quantidade de lugares na mesa");
							 }
						 }
			    	 if((rb2_4.isSelected()||rb6_8.isSelected()||rb12_16.isSelected())&&(rbBasica.isSelected()||rbCompleta.isSelected()||rbVip.isSelected())) {
						 ok=true;
					 }
			    	if(ok==true) {
			    		
			    		String experiencia=null;
			    		if(rbBasica.isSelected()) {
			    			experiencia="Basica";
			    		}else if(rbCompleta.isSelected()) {
			    			experiencia="Completa";
			    		}else {
			    			experiencia="Vip";
			    		}
			    		String pessoas=null;
			    		if(rb12_16.isSelected()) {
			    			pessoas="11-15";
			    		}else if(rb6_8.isSelected()) {
			    			pessoas="5-10";
			    		}else {
			    			pessoas="2-5";
			    		}
			    		if(experiencia.equalsIgnoreCase("Basica")) {
			    			if(pessoas.equalsIgnoreCase("11-15")) {
			    				valor+=500;
			    			}else if(pessoas.equalsIgnoreCase("5-10")){
			    				valor+=350;
			    			}else {
			    				valor+=200;
			    			}
			    		}else if(experiencia.equalsIgnoreCase("Completa")) {
			    			if(pessoas.equalsIgnoreCase("11-15")) {
			    				valor+=1000;
			    			}else if(pessoas.equalsIgnoreCase("5-10")){
			    				valor+=700;
			    			}else {
			    				valor+=400;
			    			}
			    		}else if(experiencia.equalsIgnoreCase("Vip")) {
			    			if(pessoas.equalsIgnoreCase("11-15")) {
			    				valor+=2000;
			    			}else if(pessoas.equalsIgnoreCase("5-10")){
			    				valor+=1400;
			    			}else {
			    				valor+=800;
			    			}
			    		}
			    		
						Reserva reservaa = new Reserva(clienteTela, restaurante,""+calendario.getDate(), pessoas, campoRestricao.getText(), experiencia, valor);
						reserva=reservaa;
				    	JOptionPane.showMessageDialog(null, "Reserva em andamento, Faça o pagamento");
				    	frame.dispose();
				    	if(frame.getExtendedState()==(JFrame.MAXIMIZED_BOTH)){
					    	Pagamento tela = new Pagamento(true,"Max",reserva);	
				    	}else {
				    		Pagamento tela = new Pagamento(true,"Minimo",reserva);	
				    	}
					}
				    }
 
				public void mouseExited(java.awt.event.MouseEvent evt) {
					botao.setBackground(new Color(255, 180, 91));
				}
			};
		}if(botao.getName().equalsIgnoreCase("Seta")) {
			evento = new java.awt.event.MouseAdapter() {
				 public void mouseEntered(java.awt.event.MouseEvent evt) {
				        
				    }
				 public void mouseClicked(java.awt.event.MouseEvent evt) {
				    	frame.dispose();
				    	
				    	if(frame.getExtendedState()==(JFrame.MAXIMIZED_BOTH)){
					    	try {
					    		BuscarRestaurante telaListagem = new BuscarRestaurante(true,"Max",clienteTela);
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
				    	}else {
				    		try {
				    			BuscarRestaurante telaListagem = new BuscarRestaurante(true,"Minimo",clienteTela);
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
				    	}
					    }
				    public void mouseExited(java.awt.event.MouseEvent evt) {
				        
				    }};
				    
				  
		}
		return evento;
	}
	public MouseAdapter eventoSelecao(JRadioButton caixa,Restaurante restaurante) {
	    MouseAdapter evento = null;
	    evento = new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	if(caixa.getName().equalsIgnoreCase("rbBasica") && caixa.isSelected()) {
            		StringBuilder mensagem = new StringBuilder();
            		mensagem.append(restaurante.getExperienciaBasica()+"\n");
            		mensagem.append("RS200.00 2-4 Lugares \n");
            		mensagem.append("RS350.00 5-10 Lugares \n");
            		mensagem.append("RS500.00 11-15 Lugares");
            		 JOptionPane.showMessageDialog(null, mensagem);
        	        
        	    }
        	    if(caixa.getName().equalsIgnoreCase("rbCompleta") && caixa.isSelected()) {
        	    	StringBuilder mensagem = new StringBuilder();
            		mensagem.append(restaurante.getExperienciaCompleta()+"\n");
            		mensagem.append("RS400.00 2-4 Lugares \n");
            		mensagem.append("RS700.00 5-10 Lugares \n");
            		mensagem.append("RS1000.00 11-15 Lugares");
            		 JOptionPane.showMessageDialog(null, mensagem);
        	    	
        	    	
        	    }
        	    if(caixa.getName().equalsIgnoreCase("rbVip") && caixa.isSelected()) {
        	    	
        	    	StringBuilder mensagem = new StringBuilder();
            		mensagem.append(restaurante.getExperienciaVip()+"\n");
            		mensagem.append("RS800.00 2-4 Lugares \n");
            		mensagem.append("RS1400.00 5-10 Lugares \n");
            		mensagem.append("RS2000.00 11-15 Lugares ");
            		 JOptionPane.showMessageDialog(null, mensagem);
        	    	
        	    }
           
            }
        };
	    return evento;
	}
	public JButton criaBotao(String nome,JButton botao,int x, int y, int largura, int altura,Color corB,Color corF,Font fonte) {
		botao.setName(nome);
		botao.setBounds(x, y, largura, altura);
		botao.setBackground(corB);
		botao.setForeground(corF);
		botao.setFocusPainted(false);
		botao.setBorderPainted(false);
		botao.setFont(fonte);
		return botao;
	}
	
	public JPanel escolheData(JCalendar calendar) {
		JPanel data = new JPanel();
		data.setLayout(new BorderLayout());
		data.setBackground(Color.black);
		
		data.add(calendar);
		
		
       
        return data;
	}
 

}
