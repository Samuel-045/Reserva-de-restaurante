package TelasCliente;

import javax.swing.*;

import Geral.Restaurante;
import Icons.ImagensFeitas;
import ProcessosBanco.BuscaRestaurante;
import TelasAdm.TelaMenuAdm;
import Usuario.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.RoundRectangle2D;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class BuscarRestaurante extends JFrame {
    int distTopo = 20;
    JButton pesquisar = new JButton();
    JTextField txtBuscar;
	JPanel panel = new JPanel();
	ImagensFeitas icons = new ImagensFeitas();
	private static Point mouseDownCompCoords;
	JFrame frame = new JFrame();
	Boolean apertou = false;
	 Restaurante restAltera = new Restaurante();
	JButton maximo = new JButton("□");
	JButton fecha = new JButton("X");
	JButton minimo = new JButton("━");
	ImageIcon imgpesquisar;
	ImageIcon imagem = new ImageIcon();
	ImageIcon imagemHover = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\ProjetoPi\\View\\Imagens\\pesquisarHover.png");
	JPanel painelMeio = new JPanel();
	Cliente clienteTela = new Cliente();
	ListaRestaurante panelRestaurantes;
	ListaRestaurante panelRestaurantesN;
	JPanel novaBarra = new JPanel(new BorderLayout()) {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
			g.drawString("Restaurantes", getWidth() / 2 - 30, 25);
		}
	};
	public BuscarRestaurante() {
		
	}

	public BuscarRestaurante(boolean ligaTela,String size,Cliente cliente) throws MalformedURLException{
		clienteTela=cliente;
		frame.setUndecorated(true);
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		novaBarra.setBounds(0, 0, frame.getWidth(), 40);

		JPanel botoes = new JPanel();
		botoes.setSize(300, 30);
		botoes.setBackground(Color.black);
		botoes.add(minimo);
		botoes.add(maximo);
		botoes.add(fecha);
		novaBarra.add(botoes, BorderLayout.EAST);

		// Adiciona um evento para quando clicar o mouse na tela, pegar as coordenadas
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseDownCompCoords = e.getPoint();
			}
		});
		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
			}
		});
		frame.getContentPane().add(novaBarra);
		
		painelMeio.setBounds(0, 40, frame.getWidth(), frame.getHeight());
		painelMeio.setLayout(null);
		painelMeio.setBackground(Color.black);
		criaBotao("fecha",fecha, 0, 0, 0, 0, Color.black, Color.white,fecha.getFont().deriveFont(Font.BOLD, 14));
		criaBotao("maximo",maximo, 0, 0, 0, 0, Color.black, Color.white,maximo.getFont().deriveFont(Font.BOLD, 14));
		criaBotao("minimo",minimo, 0, 0, 0, 0, Color.black, Color.white,minimo.getFont().deriveFont(Font.BOLD, 14));
		
		// Chama a funcao para fechar a tela
		fecha.addActionListener(e -> frame.dispose());
		maximo.addActionListener(this::maximiza);
		minimo.addActionListener(e -> frame.setState(JFrame.ICONIFIED));
		//adiciona o metodo de hover nos botoes do barra de titulo e da nome a eles
				fecha.addMouseListener(eventoMouse(fecha));
				maximo.addMouseListener(eventoMouse(maximo));
				minimo.addMouseListener(eventoMouse(minimo));
		frame.getContentPane().add(painelMeio);
        

		//CÓDIGO DA TELA BUSCAR RESTAURANTE
		//código para o botao pesquisar
		imagem=icons.imagem("Pesquisar");
		Image imgp = imagem.getImage();
		Image imgScalep = imgp.getScaledInstance(35,35, Image.SCALE_SMOOTH);
		imgpesquisar = new ImageIcon(imgScalep);
		
		pesquisar.setBackground(Color.black);
		pesquisar.setForeground(Color.black);
		pesquisar.setFocusPainted(false);
		pesquisar.setBorderPainted(false);
		pesquisar.setName("Pesquisar");
		pesquisar.setIcon(imgpesquisar);
		
		pesquisar.addMouseListener(eventoMouse(pesquisar));

        frame.getContentPane().add(pesquisar);
       
		//adicionando ao painel meio
		txtBuscar = criarJTextField(20);
        painelMeio.add(txtBuscar);
		pesquisar.setBounds(790,20,40,30);
		painelMeio.add(pesquisar);

		
		
		
		// Criando lista de restaurantes exemplo
		List<PainelRestaurante> restaurantes = new ArrayList<>();
		
		BuscaRestaurante busca = new BuscaRestaurante();
		List<Restaurante> restaurante =  new ArrayList<>();
		restaurante=busca.BuscaTodos();
		// Criando objetos Restaurante
		
		for (int i = 0; i < restaurante.size(); i++) {
	
			
			PainelRestaurante painel = new PainelRestaurante(restaurante.get(i).getNome(),restaurante.get(i).getUrlImagem(), restaurante.get(i).getEstrelas());
			painel.addMouseListener(eventoMouse(painel,restaurante.get(i)));
			restaurantes.add(painel);
		}
	
		// Criando o RestaurantePanel com a lista de restaurantes
		panelRestaurantes = new ListaRestaurante(restaurantes);
		panelRestaurantes.setPreferredSize(new Dimension(600, 600)); //tamanho do painel restaurante
		panel.setBounds(400/2-247,300/2-100, 495, 25);
		
		//adionando a um painel
		panel.add(panelRestaurantes); 
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 100, frame.getWidth(), frame.getHeight()); //tamanho do painel
		painelMeio.add(panel);
		

        frame.setVisible(ligaTela);
        if(size.equalsIgnoreCase("max")) {
			maximiza(null);
		}
       
	}
    private JTextField criarJTextField(int distTopo) {
        JTextField jt = new JTextField(){        
		
			//Pesquisar 
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
        jt.setSize(580,30);
        jt.selectAll();
		jt.setBorder(null);
		jt.setBackground(Color.gray);
		jt.setText("Digite o nome do restaurante que deseja"); 
		jt.setHorizontalAlignment(0);
		jt.setForeground(Color.black);
        jt.setLocation(210, distTopo);
        jt.addMouseListener(eventoMouseField(jt));
        return jt;
    }

	// Metodo para maximizar a tela e desmaximizar
	public void maximiza(ActionEvent actionEvent) {
		if (apertou == false) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			novaBarra.setBounds(0, 0, frame.getWidth(), 40);
			painelMeio.setBounds(0, 40, frame.getWidth(), frame.getHeight());
			panel.setBounds(0,80, frame.getWidth(), frame.getHeight());
			panel.setBackground(Color.BLACK);
			pesquisar.setLocation(frame.getWidth()/2+270,20);
			txtBuscar.setLocation(frame.getWidth()/4+20,20);

			maximo.setText("❐");
			apertou = true;
		} else {
			frame.setSize(1000, 600);
			frame.setLocationRelativeTo(null);
			novaBarra.setBounds(0, 0, frame.getWidth(), 40);
			panel.setBounds(0, 100, frame.getWidth(), frame.getHeight());
			
			pesquisar.setLocation(760, 20);
			txtBuscar.setLocation(170, 20);

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
	
	public MouseAdapter eventoMouseField(JTextField j) {
		MouseAdapter evento = null;
		
			evento = new java.awt.event.MouseAdapter() {
				 public void mouseClicked(java.awt.event.MouseEvent evt) {
				       j.setText("");
				       
				    }
				    };
		
		return evento;
	}
	public MouseAdapter eventoMouse(JPanel painel,Restaurante restaurante) {
		MouseAdapter evento = null;
			evento = new java.awt.event.MouseAdapter() {
				 public void mouseClicked(java.awt.event.MouseEvent evt) {
		                
					
		               
		            }};
		return evento;
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
		}if(botao.getName().equalsIgnoreCase("Pesquisar")){
			evento = new java.awt.event.MouseAdapter() {
				 public void mouseEntered(java.awt.event.MouseEvent evt) {
					 pesquisar.setIcon(imagemHover);
				    }
				 public void mouseClicked(java.awt.event.MouseEvent evt) {
					
					    }
				 
				    public void mouseExited(java.awt.event.MouseEvent evt) {
				    	pesquisar.setIcon(imgpesquisar);
				    }};
		}
		return evento;
	}
	
	class PainelRestaurante extends JPanel {
	    private String nome;
	    private String imagem;
	    private int estrelas;
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
	    
	    

	    public PainelRestaurante(String nome, String url, int estrelas) throws MalformedURLException {
	        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	        setBackground(new Color(23, 23, 23));
			setBounds(300/ 2 - 230, 180 / 2 - 100, 500, 200);
			setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona margem ao redor do painel
			setOpaque(false);
	       
	        this.estrelas = estrelas;
	        JPanel estrelasPanel = new JPanel();
	        ImageIcon icon = new ImageIcon();
	        icon=icons.imagem("Estrela");
	        Image image = icon.getImage();
	        Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); 
	        icon = new ImageIcon(newimg);
	        
	        estrelasPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	      
	        estrelasPanel.setBackground(new Color(23, 23, 23));
	        for (int i = 0; i < estrelas; i++) {
	            JLabel estrelaLabel = new JLabel(icon);
	            estrelasPanel.add(estrelaLabel);
	        }
	        estrelasPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	        estrelasPanel.setOpaque(false);
	    

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
	        JPanel containerImagemP = new JPanel();
	       
	        this.imagem=url;
	        URL urlImg = new URL(url);
			ImageIcon rImagem = new ImageIcon(urlImg);
	        Image img = rImagem.getImage();
			Image imgScale = img.getScaledInstance(200,140, Image.SCALE_SMOOTH);
			ImageIcon imagempronta = new ImageIcon(imgScale);
			containerImagem.setBackground(new Color(23, 23, 23));
	        containerImagem.setLayout(new FlowLayout(FlowLayout.CENTER));
	        containerImagem.setHorizontalAlignment(0);
	       
			containerImagem.setBackground(new Color(23, 23, 23));
			containerImagem.setForeground(new Color(23, 23, 23));
			containerImagem.setOpaque(false);
	       containerImagem.setBorder(null);

			containerImagem.setOpaque(false);
			containerImagem.setIcon(imagempronta);
			containerImagemP.add(containerImagem);
			containerImagemP.setBackground(new Color(23, 23, 23));
			containerImagemP.setForeground(new Color(23, 23, 23));
		
			




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
	        pNomeRestaurante.setBounds(400/2-247,300/2-100, 495, 25);
			pNomeRestaurante.setBackground(new Color(255, 180, 91));
			pNomeRestaurante.setOpaque(false);
			
			this.nome = nome;
	        JLabel nomeRestaurante = new JLabel(nome);
	        nomeRestaurante.setForeground(Color.black);
			nomeRestaurante.setHorizontalAlignment(0);
	        pNomeRestaurante.add(nomeRestaurante,BorderLayout.CENTER);
	        add(pNomeRestaurante);
	        add(containerImagemP);
	        add(estrelasPanel);
	        
	        this.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                // Código exemplo para lidar com o evento de clique
				 frame.dispose();
			    	if(frame.getExtendedState()==(JFrame.MAXIMIZED_BOTH)){
				    	try {
							InformacoesRestaurante tela = new InformacoesRestaurante(true,"Max",nome,clienteTela);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	}else {
			    		try {
			    			InformacoesRestaurante tela = new InformacoesRestaurante(true,"Minimo",nome,clienteTela);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	}
	               
	            }
	            @Override
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	                setBackground(Color.white);
	                nomeRestaurante.setBackground(Color.white);
	                
	                containerImagemP.setBackground(Color.white);
	                containerImagemP.setForeground(Color.white);
	                containerImagem.setBackground(Color.white);
	            }

	            @Override
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	                nomeRestaurante.setBackground(new Color(255, 180, 91));
	                setBackground(new Color(23, 23, 23));
	                containerImagemP.setBackground(new Color(23, 23, 23));
	                containerImagemP.setForeground(new Color(23, 23, 23));
	                containerImagem.setBackground(new Color(23, 23, 23));
	               
	            }
	        });
	    }
	    
	    public String getNome() {
	        return nome;
	    }

	    public String getImagem() {
	        return imagem;
	    }

	    public int getEstrelas() {
	        return estrelas;
	    }
	}
	 class ListaRestaurante extends JPanel {

		    private List<PainelRestaurante> restaurantes;

		    public ListaRestaurante(List<PainelRestaurante> restaurantes) throws MalformedURLException {
		        this.restaurantes = restaurantes;

		        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Define o layout vertical

		        // Cria um JPanel principal para conter a lista de restaurantes
		        JPanel listaRestaurantesPanel = new JPanel();
		        listaRestaurantesPanel.setLayout(new GridLayout(300,1));
		        listaRestaurantesPanel.setBackground(new Color(23,23,23));
		        
		        // Adiciona o JPanel da lista de restaurantes ao painel principal
		        for (PainelRestaurante restaurante : restaurantes) {
		        	PainelRestaurante panelRestaurante = new PainelRestaurante(
		                restaurante.getNome(), 
		                restaurante.getImagem(), 
		               restaurante.getEstrelas()
		            );
		            listaRestaurantesPanel.add(panelRestaurante,BorderLayout.CENTER);
		        }

		        // Cria um JScrollPane e adiciona o JPanel da lista de restaurantes a ele
		        JScrollPane scrollPane = new JScrollPane(listaRestaurantesPanel);
		        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		      
		        scrollPane.setBorder(null);

		        // Adiciona o JScrollPane ao painel principal
		        add(scrollPane);
		    }
		}
	
}


