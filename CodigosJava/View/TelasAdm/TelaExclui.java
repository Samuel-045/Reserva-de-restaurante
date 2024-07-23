package TelasAdm;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Geral.Restaurante;
import Icons.ImagensFeitas;
import ProcessosBanco.BuscaRestaurante;
import ProcessosBanco.ExcluiRestaurante;
import TelaBase.TelaLogin;
import TelasCliente.BuscarRestaurante;

public class TelaExclui {
	private static Point mouseDownCompCoords;
	ImagensFeitas icons = new ImagensFeitas();
	JFrame frame = new JFrame();
	Boolean apertou = false;
	ImageIcon imgpesquisarh;
	ImageIcon imgpesquisar;
	JButton maximo = new JButton("□");
	JButton fecha = new JButton("X");
	JButton minimo = new JButton("━");
	JButton seta = new JButton("←");
	JPanel painelMeio = new JPanel();
	 Restaurante restAltera = new Restaurante();
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
	ImageIcon imagem = new ImageIcon();
	ImageIcon imagemHover = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\ProjetoPi\\View\\Imagens\\pesquisarHover.png");
	JPanel pNomeRestaurante = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g.create();
            int width = getWidth();
            int height = getHeight();
            int cornerRadius = 15;
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);
            graphics2D.setColor(getBackground());
            graphics2D.fill(roundedRectangle);
            graphics2D.setColor(getForeground());
            graphics2D.draw(roundedRectangle);
            graphics2D.dispose();
        }
    };
	JButton pesquisar = new JButton();
	JLabel nomeRestaurante = new JLabel();
	ImageIcon rImagemg = new ImageIcon();
	ImageIcon estrela = new ImageIcon();
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
JLabel containerStrelas = new JLabel();
	JPanel restaurante = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g.create();
            int width = getWidth();
            int height = getHeight();
            int cornerRadius = 20;
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius,cornerRadius);
            graphics2D.setColor(getBackground());
            graphics2D.fill(roundedRectangle);
            graphics2D.setColor(getForeground());
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
			g.drawString("Excluir Restaurante", getWidth() / 2 - 30, 25);
		}
	};
	public TelaExclui() {
		
	}

	public TelaExclui(boolean ligaTela,String size) throws MalformedURLException {
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
		novaBarra.add(botoes, BorderLayout.EAST);
		novaBarra.add(seta,BorderLayout.WEST);

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
		criaBotao("fecha",fecha, 0, 0, 0, 0, Color.black, Color.white,fecha.getFont().deriveFont(Font.BOLD, 14));
		//Botao que maximiza a tela
		criaBotao("maximo",maximo, 0, 0, 0, 0, Color.black, Color.white,maximo.getFont().deriveFont(Font.BOLD, 14));
		//Botao que minimiza a tela
		criaBotao("minimo",minimo, 0, 0, 0, 0, Color.black, Color.white,minimo.getFont().deriveFont(Font.BOLD, 14));
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
		//Daqui para baixo é o codigo que vai no centro da tela

		// Define o tamanho do painel
		painelMeio.setBounds(0, 40, frame.getWidth(), frame.getHeight());
		painelMeio.setLayout(null);
		// Define a cor
		painelMeio.setBackground(Color.black);
		
		criaBotao("pesquisa",pesquisar,painelMeio.getWidth()/2+220,painelMeio.getHeight()/2-210, 30, 40, Color.black, Color.white,pesquisar.getFont().deriveFont(Font.BOLD, 14));
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
		
		nPesquisar.setBounds(painelMeio.getWidth() / 2 - 190, painelMeio.getHeight() / 2 - 200, 400, 30);
		nPesquisar.setBackground(Color.gray);
		nPesquisar.setBorder(null);
		nPesquisar.setText("Digite o nome do restaurante que deseja Excluir"); 
		nPesquisar.setForeground(Color.black);
		nPesquisar.setHorizontalAlignment(0);
		nPesquisar.setName("Texto");
		nPesquisar.addMouseListener(eventoMouseField(nPesquisar));
   
		
		restaurante.setBackground(new Color(23, 23, 23));
		restaurante.setBounds(painelMeio.getWidth() / 2 - 230, painelMeio.getHeight() / 2 - 100, 500, 200);
		restaurante.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona margem ao redor do painel
		restaurante.setLayout(null);
		restaurante.setOpaque(false);
		
		containerImagem.setBounds(restaurante.getWidth()/2-100,restaurante.getHeight()/2-50,200,110);
		containerImagem.setBackground(new Color(23, 23, 23));
		containerImagem.setForeground(new Color(23, 23, 23));
		containerImagem.setOpaque(false);
		rImagemg=icons.imagem("Restaurante");
		
		estrela=icons.imagem("Estrela");
		
		 Image imger = rImagemg.getImage();
     		Image imgScaleLogoN = imger.getScaledInstance(150,150, Image.SCALE_SMOOTH);
     		ImageIcon logoN = new ImageIcon(imgScaleLogoN);
		
		Image imge = estrela.getImage();
		Image imgScalee = imge.getScaledInstance(20,20, Image.SCALE_SMOOTH);
		ImageIcon estrelap = new ImageIcon(imgScalee);
		
	
		
		
		containerStrelas.setBounds(restaurante.getWidth()/2-55,restaurante.getHeight()/2+50, 120, 40);
		containerStrelas.setIcon(estrelap);
		
		
		restaurante.addMouseListener(eventoMouseRestaurante(restaurante));
		
		
		
		
		painelMeio.add(pesquisar);
		painelMeio.add(nPesquisar);
		painelMeio.add(restaurante);
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
			novaBarra.setBounds(0, 0, frame.getWidth(), 40);
			painelMeio.setBounds(0, 40, frame.getWidth(), frame.getHeight());
			pesquisar.setBounds(painelMeio.getWidth()/2+220,painelMeio.getHeight()/2-210, 30, 40);
			nPesquisar.setBounds(painelMeio.getWidth() / 2 - 190, painelMeio.getHeight() / 2 - 200, 400, 30);
			restaurante.setBounds(painelMeio.getWidth() / 2 - 230, painelMeio.getHeight() / 2 - 100, 500, 200);
			restaurante.add(pNomeRestaurante,BorderLayout.NORTH);
			maximo.setText("❐"); 
			apertou = true;
		} else {
			frame.setSize(1000, 600);
			frame.setLocationRelativeTo(null);
			novaBarra.setBounds(0, 0, frame.getWidth(), 40);
			painelMeio.setBounds(0, 40, frame.getWidth(), frame.getHeight());
			pesquisar.setBounds(painelMeio.getWidth()/2+220,painelMeio.getHeight()/2-210, 30, 40);
			nPesquisar.setBounds(painelMeio.getWidth() / 2 - 190, painelMeio.getHeight() / 2 - 200, 400, 30);
			restaurante.setBounds(painelMeio.getWidth() / 2 - 230, painelMeio.getHeight() / 2 - 100, 500, 200);
			restaurante.add(pNomeRestaurante,BorderLayout.NORTH);
			maximo.setText("□");
			apertou = false;
		}
	}
	//Metodo Cria botao
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
		}if(botao.getName().equalsIgnoreCase("pesquisa")){
			evento = new java.awt.event.MouseAdapter() {
				 public void mouseEntered(java.awt.event.MouseEvent evt) {
					 pesquisar.setIcon(imagemHover);
				    }
				 public void mouseClicked(java.awt.event.MouseEvent evt) {
						
					 BuscaRestaurante rest = new BuscaRestaurante();
					
				   restAltera=rest.BuscaAlt(nPesquisar.getText());
				   if(restAltera==null) {
					   JOptionPane.showMessageDialog(null, "Restaurante nao encontrado");
					   nomeRestaurante=null;
				   }else {
					   try {
						   JLabel nomeRestauranteN = new JLabel();
						   nomeRestauranteN.setText(restAltera.getNome());
						   nomeRestaurante=nomeRestauranteN;
						   JPanel pNomeRestauranteN = new JPanel();
							pNomeRestauranteN.setBounds(restaurante.getWidth()/2-247,restaurante.getHeight()/2-100, 495, 25);
							pNomeRestauranteN.setBackground(Color.red);
							pNomeRestauranteN.setOpaque(false);
							nomeRestauranteN.setForeground(Color.white);
							nomeRestauranteN.setHorizontalAlignment(0);
							pNomeRestauranteN.add(nomeRestauranteN,BorderLayout.CENTER);
							restaurante.add(pNomeRestauranteN);
					   }catch (Exception e) {
						
					}
					   
					   try {
						
						  
						   JLabel containerImagemNova = null;
							containerImagemNova = new JLabel(){
								
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
							restaurante.setBackground(new Color(23, 23, 23));
							restaurante.setBounds(painelMeio.getWidth() / 2 - 230, painelMeio.getHeight() / 2 - 100, 500, 200);
							restaurante.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona margem ao redor do painel
							restaurante.setLayout(null);
							restaurante.setOpaque(false);
							containerImagemNova.setBounds(restaurante.getWidth()/2-100,restaurante.getHeight()/2-50,200,110);
							containerImagemNova.setBackground(new Color(23, 23, 23));
							containerImagemNova.setForeground(new Color(23, 23, 23));
							containerImagemNova.setOpaque(false);
							
							@SuppressWarnings("deprecation")
							URL urlNova = new URL(restAltera.getUrlImagem());
							ImageIcon imagemN = new ImageIcon(urlNova);
							 Image imge = imagemN.getImage();
								Image  imgScaleLogoN =  imge.getScaledInstance(containerImagem.getWidth(),containerImagem.getHeight(), Image.SCALE_SMOOTH);
								ImageIcon logoN = new ImageIcon(imgScaleLogoN);
							
							containerImagemNova.setIcon(logoN);
							
							
							 restaurante.add(containerImagemNova);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					   
					   try {
						JPanel estrelasPanel = new JPanel();
						ImageIcon icon = new ImageIcon();
						icon=icons.imagem("Estrela");
						 
					        Image image = estrela.getImage();
					        Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); 
					        icon = new ImageIcon(newimg);
					        
					        estrelasPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
					      estrelasPanel.setBounds(restaurante.getWidth()/2-80,restaurante.getHeight()/2+55, 160, 50);
					        estrelasPanel.setBackground(new Color(23, 23, 23));
					        for (int i = 0; i < restAltera.getEstrelas(); i++) {
					            JLabel estrelaLabel = new JLabel(icon);
					            estrelasPanel.add(estrelaLabel);
					        }
					        estrelasPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
					        estrelasPanel.setOpaque(false);
						restaurante.add(estrelasPanel);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
					  
				   }
				   
					    }
				    public void mouseExited(java.awt.event.MouseEvent evt) {
				    	pesquisar.setIcon(imgpesquisar);
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
		}
		return evento;
	}
	public MouseAdapter eventoMouseField(JTextField j) {
		MouseAdapter evento = null;
		if(j.getName().equalsIgnoreCase("Texto")){
			evento = new java.awt.event.MouseAdapter() {
				 public void mouseClicked(java.awt.event.MouseEvent evt) {
				       j.setText("");
				       restaurante.removeAll();
				    }
				    };
		}
		return evento;
	}
	public MouseAdapter eventoMouseRestaurante(JPanel p ) {
		MouseAdapter evento = null;
		
			evento = new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					if(nomeRestaurante.getText().isEmpty()) {
						
					}else {
						int resposta = JOptionPane.showConfirmDialog(null, "Deseja exluir o restaurante?", "Confirmação", JOptionPane.YES_NO_OPTION);
						if (resposta == JOptionPane.YES_OPTION) {
				            ExcluiRestaurante exclui = new ExcluiRestaurante();
				            exclui.ExcluiRestaurante(nomeRestaurante.getText());
				            JOptionPane.showMessageDialog(p, "Excluiu o restaurante Com sucesso");
				            nomeRestaurante.setText(null);
				            restaurante.removeAll();
				        } else {
				           
				        }
					}
					
			    }
				 public void mouseEntered(java.awt.event.MouseEvent evt) {
				        p.setBackground(Color.white);
				        pNomeRestaurante.setBackground(Color.white);
				    }
				    public void mouseExited(java.awt.event.MouseEvent evt) {
				    	 p.setBackground(new Color(23, 23, 23));
				    	 pNomeRestaurante.setBackground(new Color(255, 180, 91));
				    }};
		
		return evento;
	}
	
}
