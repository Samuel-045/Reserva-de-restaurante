package TelasCliente;
import javax.swing.*;

import Geral.Restaurante;
import Icons.ImagensFeitas;
import ProcessosBanco.BuscaRestaurante;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.net.MalformedURLException; 

public class SelecionarRestaurante {
	ImagensFeitas icons = new ImagensFeitas();
	ImageIcon imagemRestaurante;
	JPanel selectExpericencia = new JPanel();
	JPanel selecionaLugar = new JPanel();
	JLabel descricaoExpeicencia = new JLabel();
	JCheckBox vip = new JCheckBox("VIP");
	JCheckBox basica = new JCheckBox("Basica");
	JCheckBox completa = new JCheckBox("Completa");
	JCheckBox checkBox = new JCheckBox("2 - 4");
	JCheckBox checkBox2 = new JCheckBox("6 - 8");
	JCheckBox checkBox3 = new JCheckBox("12 - 16");
	JButton botao = new JButton("Realizar pagamento");
	JLabel espaco = new JLabel("   ");
	JPanel retanguloPreto = new JPanel();
	
	JPanel retanguloLaranja = new JPanel(new BorderLayout()) {
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

	JLabel imagemLabel = new JLabel(){
		
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
		private static Point mouseDownCompCoords;
		JFrame frame = new JFrame();
		Boolean apertou = false;
		JButton maximo = new JButton("□");
		JButton fecha = new JButton("X");
		JButton minimo = new JButton("━");
		JPanel painelMeio = new JPanel();
		JPanel novaBarra = new JPanel(new BorderLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.black);
				g.fillRect(0, 0, getWidth(), getHeight());
				g.setColor(Color.WHITE);
				g.drawString("Restaurante", getWidth() / 2 - 30, 25);
			}
		};
		public SelecionarRestaurante() {
			
		}
		

		public SelecionarRestaurante(boolean ligaTela,String size,String nome) throws MalformedURLException {
			Restaurante restaurante = new Restaurante();
			BuscaRestaurante busca = new BuscaRestaurante(); 
			restaurante = busca.BuscaAlt(nome);
			
			// Remove a barra de titulo padrao
			frame.setUndecorated(true);
			// Define o tamanho
			frame.setSize(650, 800);
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
			// adiciona o painel de botoes na barra de titulo
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

	        //Adiciona retângulo para nome do restaurante
			
	        // Define o tamanho e posição do painel laranja
	        retanguloLaranja.setBounds((frame.getWidth() -350) / 2, 0, 400, 60);
	        retanguloLaranja.setBackground(new Color(255, 180, 91));
	        retanguloLaranja.setOpaque(false);
			
	        // Adiciona o painel laranja ao painel central
	        painelMeio.add(retanguloLaranja);

	        // Cria um novo JPanel para o retângulo preto
	        
	        retanguloPreto.setBackground(Color.BLACK);
	        retanguloPreto.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

	        // Configura as dimensões e posição do painel preto
	        retanguloPreto.setBounds(100, 75, 500, frame.getHeight() - 200);
			// Configura o layout do painel preto para organizar os elementos verticalmente
			retanguloPreto.setLayout(new BoxLayout(retanguloPreto, BoxLayout.Y_AXIS));

			// Cria e adiciona os componentes para a imagem, endereço, chef e descrição
			imagemLabel.setSize(200,150);
			
			imagemRestaurante = new ImageIcon(restaurante.getUrlImagem());
			// Adiciona a imagem do restaurante
			Image image = imagemRestaurante.getImage();
			Image imageScale = image.getScaledInstance(imagemLabel.getWidth(), imagemLabel.getHeight(), image.SCALE_SMOOTH);
			ImageIcon imagemPronta = new ImageIcon(imageScale);
			imagemLabel.setIcon(imagemPronta);
			imagemLabel.setBackground(Color.black);
			imagemLabel.setForeground(Color.black);
			imagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza a imagem
			retanguloPreto.add(imagemLabel);

			// Adiciona o endereço do restaurante
			JLabel enderecoLabel = new JLabel(restaurante.getEndereco());
			enderecoLabel.setForeground(Color.WHITE);
			enderecoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o texto
			retanguloPreto.add(enderecoLabel);

			// Adiciona o chef do restaurante
			JLabel chefLabel = new JLabel("Chef: "+restaurante.getChef());
			chefLabel.setForeground(Color.WHITE);
			chefLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o texto
			retanguloPreto.add(chefLabel);

			// Adiciona a descrição do restaurante
			JLabel descricaoLabel = new JLabel("Descrição do Restaurante: "+restaurante.getDescricao());
			descricaoLabel.setForeground(Color.WHITE);
			descricaoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o texto
			retanguloPreto.add(descricaoLabel);

			painelMeio.add(Box.createRigidArea(new Dimension(100, 100)));

			//Título: Experiencia
			JLabel experiencia = new JLabel("Experiência");
			experiencia.setAlignmentX(Component.CENTER_ALIGNMENT);
			experiencia.setForeground(Color.white);
			retanguloPreto.add(experiencia);

			// descricaoExperiencia
			descricaoExpeicencia.setText("Selecione um tipo de experiencia");
			descricaoExpeicencia.setForeground(Color.white);
			descricaoExpeicencia.setAlignmentX(Component.CENTER_ALIGNMENT);

			//definindo checkbox
			vip.setForeground(Color.white);
            vip.setBackground(Color.black);
			basica.setForeground(Color.white);
            basica.setBackground(Color.black);
			completa.setForeground(Color.white);
            completa.setBackground(Color.black);
			//Agrupando checkbox para selecionar apenas uma
			ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(vip);
            buttonGroup.add(basica);
            buttonGroup.add(completa);
			selectExpericencia.setLayout(null);
			selectExpericencia.setSize(20,20);
			//selectExpericencia.setBounds(5,0,0,0);
			selectExpericencia.setBackground(Color.red);
			
			selectExpericencia.add(createExamplePanel());
			
			retanguloPreto.add(createExamplePanel());
			retanguloPreto.add(descricaoExpeicencia);


			JLabel selectMesa = new JLabel("Lugares");
			selectMesa.setForeground(Color.white);
			selectMesa.setAlignmentX(Component.CENTER_ALIGNMENT);
			retanguloPreto.add(selectMesa);

			//checkbox  lugares na mesa
			selecionaLugar.setBackground(Color.black);
            checkBox.setForeground(Color.white);
			
            checkBox2.setForeground(Color.white);
			
            checkBox3.setForeground(Color.white);
            checkBox.setBackground(Color.black);
            checkBox2.setBackground(Color.black);
            checkBox3.setBackground(Color.black);
            
            //Apenas uma ciaxa ser selecionada
            ButtonGroup buttonGroup2 = new ButtonGroup();
            buttonGroup2.add(checkBox);
            buttonGroup2.add(checkBox2);
            buttonGroup2.add(checkBox3);
            selecionaLugar.add(checkBox);
            selecionaLugar.add(checkBox2);
            selecionaLugar.add(checkBox3);
			
			
            retanguloPreto.add(selecionaLugar);

			botao.setBackground(new Color(255, 180, 91));
			botao.setForeground(Color.black);
			botao.setFocusPainted(false);
			botao.setBorderPainted(false);
			botao.setBounds(760,20,120,30);
			botao.setAlignmentX(Component.CENTER_ALIGNMENT);
			
            
			JLabel restricao = new JLabel("Restrições");
			restricao.setBackground(Color.black);
			restricao.setForeground(Color.white);
			restricao.setAlignmentX(Component.CENTER_ALIGNMENT);
			retanguloPreto.add(restricao);
            JTextField txt;
            txt = criarJTextField(20);
            txt.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			txt.setBounds((frame.getWidth() -400) / 2, 0, 200, 60);
        	retanguloPreto.add(txt);
            retanguloPreto.add(botao);

	        // Adiciona o painel preto ao painel central
	        painelMeio.add(retanguloPreto);
			

			   // Cria um JScrollPane e adiciona o JPanel da lista de restaurantes a ele
			   JScrollPane scrollPane = new JScrollPane();
			   scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			   scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	   
			   // Adiciona o JScrollPane ao painel principal
			//   painelMeio.add(scrollPane);
			frame.setVisible(ligaTela);

			acoes(restaurante);
			if(size.equalsIgnoreCase("max")) {
				maximiza(null);
			}
		}
		public void acoes(Restaurante restaurante) {
			vip.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(vip.isSelected()) {
						descricaoExpeicencia.setText(restaurante.getExperienciaVip());
					}
				}
			});
			
			basica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(basica.isSelected()) {
						descricaoExpeicencia.setText(restaurante.getExperienciaBasica());
						
					}
				}
			});
			
			completa.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					if (completa.isSelected()) {
						descricaoExpeicencia.setText(restaurante.getExperienciaCompleta());
					}
				}
	 
			});
			
			
		}
		
		private JPanel createExamplePanel() {
			JPanel panel = new JPanel();
			panel.add(basica);
			panel.add(completa);
			panel.add(vip);
			//panel.setBounds((frame.getWidth() -400) / 2, 0, 400, 60);
			panel.setBackground(Color.black);
			panel.setAlignmentX(Component.CENTER_ALIGNMENT);
			return panel;
		}
	
		
		public JTextField criarJTextField(int distTopo) {
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
	        jt.setSize(100,30);
	        jt.selectAll();
			jt.setBorder(null);
			jt.setBackground(Color.gray);
			jt.setText("Caso tenha alguma restrição alimentar, escreva aqui"); 
			jt.setHorizontalAlignment(0);
			jt.setForeground(Color.white);
	        jt.setLocation(10, distTopo);
	        return jt;
	    }

		// Metodo para maximizar a tela e desmaximizar
		public void maximiza(ActionEvent actionEvent) {
			if (apertou == false) {
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				novaBarra.setBounds(0, 0, frame.getWidth(), 40);
				painelMeio.setBounds(0, 40, frame.getWidth(), frame.getHeight());
				retanguloLaranja.setLocation(frame.getWidth()-870,30);
				retanguloPreto.setLocation( frame.getWidth()/3,100);
				maximo.setText("❐");
				apertou = true;
			} else {
				frame.setSize(650, 800);
				frame.setLocationRelativeTo(null);
				novaBarra.setBounds(0, 0, frame.getWidth(), 40);
				painelMeio.setBounds(0, 40, frame.getWidth(), frame.getHeight());
				retanguloLaranja.setBounds((frame.getWidth() -400) / 2, 0, 400, 60);
				retanguloPreto.setBounds(100, 75, 500, frame.getHeight() - 200);

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
			}
			return evento;
		}
		
		
		
		
	
}
