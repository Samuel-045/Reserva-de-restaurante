package TelasAdm;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneUI.BasicVerticalLayoutManager;

import Icons.ImagensFeitas;
import ProcessosBanco.BuscaReservas;
import ProcessosBanco.BuscaRestaurante;
import Servico.Relatorio;
import TelasCliente.BuscarRestaurante;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RelatorioReservas {

	private static Point mouseDownCompCoords;
	int distancia=70;
	JFrame frame = new JFrame();
	ImagensFeitas icons = new ImagensFeitas();
	Boolean apertou = false;
	ImageIcon imgpesquisarh;
	ImageIcon imgS;
	ImageIcon imgpesquisar;
	JButton maximo = new JButton("□");
	JButton fecha = new JButton("X");
	JButton minimo = new JButton("━");
	JPanel painelMeio = new JPanel();
	JButton pesquisar = new JButton();
	JButton seta = new JButton("←");
	JPanel painelInterno = new JPanel();
	boolean apertouP=false;
	ImageIcon imagemS = new ImageIcon();
	ImageIcon imagem = new ImageIcon();
	ImageIcon imagemHover = new ImageIcon();
	List<Relatorio> unitario = new ArrayList<>();
	JTextField nPesquisar = new JTextField() {        
		
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
            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.draw(roundedRectangle);
            graphics2D.dispose();
            
          
        }
        
	};
	JPanel painelReservas = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g.create();
            int width = getWidth();
            int height = getHeight();
            int cornerRadius = 100;
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius,cornerRadius);
            graphics2D.setColor(getBackground());
            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.fill(roundedRectangle);
            graphics2D.setColor(getForeground());
            graphics2D.draw(roundedRectangle);
            graphics2D.dispose();
            
            
        }
    };
    int alturaP=30;
    int tamanhoP=600;
    JPanel painelReservasUnitario = new JPanel(){        
		
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
            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.draw(roundedRectangle);
            graphics2D.dispose();
            
          
        }
        
	};

	JLabel tituloL = new JLabel();
	
	JPanel titulo = new JPanel(){        
		
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
            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.draw(roundedRectangle);
            graphics2D.dispose();
            
          
        }
        
	};
	JPanel novaBarra = new JPanel(new BorderLayout()) {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
			g.drawString("Relatorio de Reservas", getWidth() / 2 - 50, 25);
		}
	};
	 JScrollPane scrollPane = new JScrollPane(painelInterno);
	public RelatorioReservas() {
		
	}

	public RelatorioReservas(boolean ligaTela,String size) throws MalformedURLException {
		// Remove a barra de titulo padrao
		frame.setUndecorated(true);
		// Define o tamanho
		frame.setSize(1000, 600);
		// Centraliza
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		// Cria um barra de titulo nova
		// define o tamanho da barra com a largura no tamanho da tela e a altura em 30
		novaBarra.setBounds(0, 0, frame.getWidth(), 40);

		// cria um painel para juntar os botoes
		JPanel botoes = new JPanel();
		// Define o tamanho do painel
		botoes.setSize(300, 30);
		// Define a cor
		botoes.setBackground(Color.black);
		// Adiciona os botoes ao painel
		botoes.add(minimo);
		botoes.add(maximo);
		botoes.add(fecha);
		
		criaBotao("Seta",seta,0,0, 30, 40, Color.black, Color.white,seta.getFont().deriveFont(Font.BOLD, 34));
		seta.addMouseListener(eventoMouse(seta));
		
		
		// adiciona o painel de botoes na barra de titulo
		novaBarra.add(seta,BorderLayout.WEST);
		novaBarra.add(botoes, BorderLayout.EAST);

		// Adiciona um evento para quando clicar o mouse na tela, pegar as coordenadas
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseDownCompCoords = e.getPoint();
			}
		});
		// Adiciona um evento para quando clicar o mouse na tela e arrastar pegar as coordenadas e subtrair com a antiga parada
		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
			}
		});
		
		// Adiciona a barra de titulo na tela
		frame.getContentPane().add(novaBarra);
		
		
		//Daqui para baixo é o codigo que vai no centro da tela

		// Define o tamanho do painel
		painelMeio.setBounds(0, 40, frame.getWidth(), frame.getHeight());
		painelMeio.setLayout(null);
		// Define a cor
		painelMeio.setBackground(Color.black);
		//Botao X para fechar a tela
		criaBotao("fecha",fecha, 0, 0, 0, 0, Color.black, Color.white,fecha.getFont().deriveFont(Font.BOLD, 14));
		//Botao que maximiza a tela
		criaBotao("maximo",maximo, 0, 0, 0, 0, Color.black, Color.white,maximo.getFont().deriveFont(Font.BOLD, 14));
		//Botao que minimiza a tela
		criaBotao("minimo",minimo, 0, 0, 0, 0, Color.black, Color.white,minimo.getFont().deriveFont(Font.BOLD, 14));
		
		
		titulo.setBounds(painelMeio.getWidth()/2-190,painelMeio.getWidth()/2-450,400,30);
		titulo.setBackground(new Color(255, 180, 91));
		titulo.setForeground(Color.black);
		tituloL.setText("Relatorio de Reservas");
		
		titulo.add(tituloL);
	
		BuscaReservas busca = new BuscaReservas();
		List<Relatorio> reservas = new ArrayList<>();
		reservas=busca.BuscaTodas();
		
		 for (int i = 0; i < reservas.size(); i++) {
			 adicionaReserva(reservas.get(i).getNomeCliente(),reservas.get(i).getDia() , reservas.get(i).getNomeRestaurante(), reservas.get(i).getValor(), distancia);
	        }
		
		
		
		
		
		
		
		
		painelReservas.setBounds(painelMeio.getWidth()/2-360, painelMeio.getHeight()/2-200, 750, 400);
		painelReservas.setBackground(Color.black);
		painelReservas.setForeground(Color.white);
		painelReservas.setLayout(null);
		painelInterno.setBackground(Color.black);
		painelInterno.setLayout(new GridLayout(500,1));
		
		
		
	    scrollPane.setBorder(null);   
	    scrollPane.setBounds(painelReservas.getWidth()/2-350, painelReservas.getHeight()/2-140, 700, 320);
		painelReservas.add(scrollPane);
		
		criaBotao("pesquisa",pesquisar,painelReservas.getWidth()/2+190,painelReservas.getHeight()/2-185, 30, 40, Color.black, Color.white,pesquisar.getFont().deriveFont(Font.BOLD, 14));
		pesquisar.setLayout(null);
		imagem=icons.imagem("Pesquisar");
		Image imgp = imagem.getImage();
		Image imgScalep = imgp.getScaledInstance(35,35, Image.SCALE_SMOOTH);
		imgpesquisar = new ImageIcon(imgScalep);
		imagemHover=icons.imagem("Pesquisar");
		Image imgph = imagem.getImage();
		Image imgScaleph = imgp.getScaledInstance(35,35, Image.SCALE_SMOOTH);
		imgpesquisarh = new ImageIcon(imgScaleph);
		
		pesquisar.setIcon(imgpesquisar);
		pesquisar.addMouseListener(eventoMouse(pesquisar));
		
		nPesquisar.setBounds(painelReservas.getWidth() / 2-210, painelReservas.getHeight() / 2-180 , 400, 30);
		nPesquisar.setBackground(Color.gray);
		nPesquisar.setBorder(null);
		nPesquisar.setText("Digite uma data para pesquisar por reservas");
		nPesquisar.setForeground(Color.black);
		nPesquisar.setHorizontalAlignment(0);
		nPesquisar.setName("Texto");
		nPesquisar.addMouseListener(eventoMouseField(nPesquisar));
		
		
		
		painelReservas.add(pesquisar);
		painelReservas.add(nPesquisar);
		painelReservas.add(painelReservasUnitario);
		painelMeio.add(titulo);
		painelMeio.add(painelReservas);
		
		 
		// Chama a funcao para fechar a tela
		fecha.addActionListener(e -> frame.dispose());
		// Chama a funçao para evento de maximizar a tela
		maximo.addActionListener(this::maximiza);
		// Chama a funçao para evento de minimizar a tela
		minimo.addActionListener(e -> frame.setState(JFrame.ICONIFIED));
		//adiciona o metodo de hover nos botoes do barra de titulo e da nome a eles
				fecha.addMouseListener(eventoMouse(fecha));
				maximo.addMouseListener(eventoMouse(maximo));
				minimo.addMouseListener(eventoMouse(minimo));
		//adiciona o painel preto na tela
		frame.getContentPane().add(painelMeio);

		frame.setVisible(ligaTela);
		if(size.equalsIgnoreCase("max")) {
			maximiza(null);
		}
	}

	// Metodo para maximizar a tela e desmaximizar
	public void maximiza(ActionEvent actionEvent) {
		if (apertou == false) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			painelMeio.setBounds(0, 40, frame.getWidth(), frame.getHeight());
			titulo.setBounds(painelMeio.getWidth()/2-190,painelMeio.getWidth()/2-600,400,30);
			novaBarra.setBounds(0, 0, frame.getWidth(), 40);
			painelReservas.setBounds(painelMeio.getWidth()/2-490, painelMeio.getHeight()/2-250, 1000, 550);
			nPesquisar.setBounds(painelReservas.getWidth() / 2-210, painelReservas.getHeight() / 2-260 , 400, 30);
			criaBotao("pesquisa",pesquisar,painelReservas.getWidth()/2+190,painelReservas.getHeight()/2-265, 30, 40, Color.black, Color.white,pesquisar.getFont().deriveFont(Font.BOLD, 14));
			maximo.setText("❐");
			 scrollPane.setBounds(painelReservas.getWidth()/2-380, painelReservas.getHeight()/2-170, 800, 370);
			apertou = true;
			
		} else {
			frame.setSize(1000, 600);
			frame.setLocationRelativeTo(null);
			painelMeio.setBounds(0, 40, frame.getWidth(), frame.getHeight());
			titulo.setBounds(painelMeio.getWidth()/2-190,painelMeio.getWidth()/2-450,400,30);
			novaBarra.setBounds(0, 0, frame.getWidth(), 40);
			painelReservas.setBounds(painelMeio.getWidth()/2-360, painelMeio.getHeight()/2-200, 750, 400);
			criaBotao("pesquisa",pesquisar,painelReservas.getWidth()/2+190,painelReservas.getHeight()/2-185, 30, 40, Color.black, Color.white,pesquisar.getFont().deriveFont(Font.BOLD, 14));
			 scrollPane.setBounds(painelReservas.getWidth()/2-350, painelReservas.getHeight()/2-140, 700, 320);
			nPesquisar.setBounds(painelReservas.getWidth() / 2-210, painelReservas.getHeight() / 2-180 , 400, 30);
			maximo.setText("□");
			apertou = false;
		}
	}
	//Metodo Cria botao
	public void criaBotao(String nome,JButton botao,int x, int y, int largura, int altura,Color corB,Color corF,Font fonte) {
		botao.setName(nome);
		botao.setBounds(x, y, largura, altura);
		botao.setBackground(corB);
		botao.setForeground(corF);
		botao.setFocusPainted(false);
		botao.setBorderPainted(false);
		botao.setFont(fonte);
		
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
		}if(botao.getName().equalsIgnoreCase("Seta")) {
			evento = new java.awt.event.MouseAdapter() {
				 public void mouseEntered(java.awt.event.MouseEvent evt) {
				        
				    }
				 public void mouseClicked(java.awt.event.MouseEvent evt) {
				    	frame.dispose();
				    	
				    	if(frame.getExtendedState()==(JFrame.MAXIMIZED_BOTH)){
					    	try {
					    	TelaMenuAdm tela = new TelaMenuAdm(true, "Max");
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
				    	}else {
				    		try {
				    			TelaMenuAdm tela = new TelaMenuAdm(true, "Minimo");
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
				    	}
					    }
				    public void mouseExited(java.awt.event.MouseEvent evt) {
				        
				    }};
		}if(botao.getName().equalsIgnoreCase("pesquisa")){
			evento = new java.awt.event.MouseAdapter() {
				 public void mouseEntered(java.awt.event.MouseEvent evt) {
					 pesquisar.setIcon(imagemHover);
				    }
				 public void mouseClicked(java.awt.event.MouseEvent evt) {
					 
					BuscaReservas busca = new BuscaReservas();
					
				   unitario=busca.BuscaEspecifica(nPesquisar.getText());
				   if(unitario==null) {
					   JOptionPane.showMessageDialog(null, "Reserva nao encontrada");
						List<Relatorio> reservas = new ArrayList<>();
						reservas=busca.BuscaTodas();
						
						 for (int i = 0; i < reservas.size(); i++) {
							 adicionaReserva(reservas.get(i).getNomeCliente(),reservas.get(i).getDia() , reservas.get(i).getNomeRestaurante(), reservas.get(i).getValor(), distancia);
					        }
				   }else {
					   
					   painelInterno.setBackground(Color.black);
						painelInterno.setLayout(new GridLayout(500,1));
					   scrollPane.setBorder(null);   
					    scrollPane.setBounds(painelReservas.getWidth()/2-350, painelReservas.getHeight()/2-140, 700, 320);
						
						 for (int i = 0; i < unitario.size(); i++) {
							 adicionaReserva(unitario.get(i).getNomeCliente(),unitario.get(i).getDia() , unitario.get(i).getNomeRestaurante(), unitario.get(i).getValor(), distancia);
					        }
					   
				   }
				   
					    }
				 
				    public void mouseExited(java.awt.event.MouseEvent evt) {
				    	pesquisar.setIcon(imgpesquisar);
				    }};
		}
		return evento;
	}
	public void adicionaReserva(String nome, String data, String restaurante, String valor, int distancia) {
        JPanel reservaPanel = new JPanel(){
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
	            graphics2D.setStroke(new BasicStroke(2));
	            graphics2D.draw(roundedRectangle);
	            graphics2D.dispose();
    }
	}; 
        reservaPanel.setBackground(Color.BLACK); 
        reservaPanel.setForeground(Color.WHITE); 

      
        JLabel nomeLabel = new JLabel("Nome: " + nome);
        JLabel dataLabel = new JLabel("Data: " + data); 
        JLabel restauranteLabel = new JLabel("Restaurante: " + restaurante);
        JLabel valorLabel = new JLabel("Valor: R$" + valor);

        nomeLabel.setForeground(Color.WHITE); 
        dataLabel.setForeground(Color.WHITE); 
        restauranteLabel.setForeground(Color.WHITE); 
        valorLabel.setForeground(Color.WHITE); 

       
        reservaPanel.add(nomeLabel);
        reservaPanel.add(dataLabel);
        reservaPanel.add(restauranteLabel);
        reservaPanel.add(valorLabel);

      
        painelInterno.add(reservaPanel);

       
    }
	public void adicionaReserva(JPanel painel,String nome, String data, String restaurante, String valor, int distancia) {
        JPanel reservaPanel = new JPanel(){
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
	            graphics2D.setStroke(new BasicStroke(2));
	            graphics2D.draw(roundedRectangle);
	            graphics2D.dispose();
    }
	}; 
        reservaPanel.setBackground(Color.BLACK); 
        reservaPanel.setForeground(Color.WHITE); 

      
        JLabel nomeLabel = new JLabel("Nome: " + nome);
        JLabel dataLabel = new JLabel("Data: " + data); 
        JLabel restauranteLabel = new JLabel("Restaurante: " + restaurante);
        JLabel valorLabel = new JLabel("Valor: R$" + valor);

        nomeLabel.setForeground(Color.WHITE); 
        dataLabel.setForeground(Color.WHITE); 
        restauranteLabel.setForeground(Color.WHITE); 
        valorLabel.setForeground(Color.WHITE); 

       
        reservaPanel.add(nomeLabel);
        reservaPanel.add(dataLabel);
        reservaPanel.add(restauranteLabel);
        reservaPanel.add(valorLabel);

      
        painel.add(reservaPanel);

       
    }
	 
	public MouseAdapter eventoMouseField(JTextField j) {
		MouseAdapter evento = null;
		if(j.getName().equalsIgnoreCase("Texto")){
			evento = new java.awt.event.MouseAdapter() {
				 public void mouseClicked(java.awt.event.MouseEvent evt) {
				       j.setText("");
				       painelInterno.removeAll();
				    }
				    };
		}
		return evento;
	}
}