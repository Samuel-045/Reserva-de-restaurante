package TelasAdm;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Icons.ImagensFeitas;
import TelaBase.TelaLogin;
import TelasCliente.BuscarRestaurante;
import Usuario.Administrador;
import Usuario.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.net.MalformedURLException;

public class TelaCadastroADM {

	private static Point mouseDownCompCoords;
	JFrame frame = new JFrame();
	boolean ok = false;
	Boolean apertou = false;
	ImagensFeitas icons = new ImagensFeitas();
	JButton maximo = new JButton("□");
	JButton fecha = new JButton("X");
	JButton minimo = new JButton("━");
	JButton seta = new JButton("←");
	ImageIcon imagem = new ImageIcon("C:\\eclipse\\eclipse\\eclipse\\projeto_10_05\\Assets\\user.png");
	JLabel containerImagem = new JLabel(imagem);
	
	JTextField campoNome, campoSenha, campoCPF, campoEmail, campoConfirmSenha, campoEndereco;
	JLabel labelNome, labelSenha, labelCadastro, labelCPF, labelEmail, labelConfirmSenha, labelEndereco;
	
	JButton botaoCadastrar;
	
	JPanel barraCadastro = new JPanel(){
		
			
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
	JPanel panelImagem = new JPanel();
	JPanel conteudoMeioDaTela = new JPanel(){
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
	JPanel panelGeral = new JPanel();
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

	public TelaCadastroADM(boolean ligaTela,String size) throws MalformedURLException {
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
		
		//cria a barra de laranja acima do 'form'
		barraCadastro.setBounds(20,30,frame.getWidth()-40,50);
		barraCadastro.setBackground(new Color(255,180,91));
		barraCadastro.setForeground(Color.black);
		barraCadastro.setLayout(null);

		//cria a label de login
		labelCadastro = criaLabel(barraCadastro.getWidth()/2+-90, 10, 250, 30, "labelCadastro" , "Cadastro Administrador");
		labelCadastro.setForeground(Color.black);
		labelCadastro.setFont(fecha.getFont().deriveFont(Font.BOLD, 14));
		
		barraCadastro.add(labelCadastro);
				
		// =======DAQUI PARA BAIXO É O CÓDIGO QUE VAI NO CENTRO DA TELA========
		
		// Define o tamanho e posicao do painel de fundo
		panelGeral.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		panelGeral.setLayout(null);
		// Define a cor
		panelGeral.setBackground(Color.BLACK);

		// Define o tamanho e posicao do painel do conteúdo do meio da tela
		conteudoMeioDaTela.setBounds(frame.getHeight()/20, frame.getWidth()/10, frame.getWidth()-50, frame.getHeight()-240);
		conteudoMeioDaTela.setLayout(null);
		// Define a cor
		conteudoMeioDaTela.setBackground(Color.black);
		conteudoMeioDaTela.setForeground(Color.white);
		
		imagem=icons.imagem("User");
		Image imge = imagem.getImage();
		Image imgScaleLogo = imge.getScaledInstance(150,150, Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(imgScaleLogo);
		JLabel containerImagem = new JLabel(logo);
		
		//Adiciona a imagem em um painel
		panelImagem.add(containerImagem);
		panelImagem.setBackground(Color.black);
		panelImagem.setBounds(conteudoMeioDaTela.getWidth() / 20+20, conteudoMeioDaTela.getHeight() -170, 200, 200);

		//Cria os campos da tela
		//lado esquerdo
		campoNome = criaCampo(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-310,280,30,"campoNome");
		campoSenha = criaCampo(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-210,280,30,"campoSenha");
		campoCPF = criaCampo(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-110,280,30,"campoCPF");
		//lado direito
		campoEmail = criaCampo(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-310,280,30,"campoEmail");
		campoConfirmSenha = criaCampo(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-210,280,30,"campoConfirmSenha");
		campoEndereco = criaCampo(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-110,280,30,"campoEndereco");
		
		//Adiciona os campos em um painel
		conteudoMeioDaTela.add(campoNome);
		conteudoMeioDaTela.add(campoSenha);
		conteudoMeioDaTela.add(campoCPF);
		conteudoMeioDaTela.add(campoEmail);
		conteudoMeioDaTela.add(campoConfirmSenha);
		conteudoMeioDaTela.add(campoEndereco);
		
		//cria os labels da tela
		//lado esquerdo
		labelNome = criaLabel(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-340,60,30,"labelNome","Nome");
		labelSenha = criaLabel(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-240,60,30,"labelSenha","Senha");
		labelCPF = criaLabel(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-140,60,30,"labelSenha","CPF");
		//lado direito
		labelEmail = criaLabel(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-340,60,30,"labelEmail","Email");
		labelConfirmSenha = criaLabel(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-240,140,30,"labelConfirmSenha","Confirmar senha");
		labelEndereco = criaLabel(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-140,60,30,"labelEndereco","Endereço");
		
		//Adiciona os labels em um painel
		conteudoMeioDaTela.add(labelNome);
		conteudoMeioDaTela.add(labelSenha);
		conteudoMeioDaTela.add(labelCPF);
		conteudoMeioDaTela.add(labelEmail);
		conteudoMeioDaTela.add(labelConfirmSenha);
		conteudoMeioDaTela.add(labelEndereco);
		
		//cria o botão da tela
		botaoCadastrar = criaBotao(panelGeral.getWidth()/2+-150, panelGeral.getHeight()/2+200,280,40,"botaoCadastrar", "Entrar");
		//Adiciona os campos em um painel
		panelGeral.add(botaoCadastrar);
		
		//adiciona o evento hover no botao
		botaoCadastrar.addMouseListener(eventoMouse(botaoCadastrar));
		
		//adiciona os paineis na tela
		frame.getContentPane().add(barraCadastro);
		frame.getContentPane().add(panelImagem);
		frame.getContentPane().add(conteudoMeioDaTela);
		frame.getContentPane().add(panelGeral);
		
		
		frame.setVisible(ligaTela);
		acoes();
		if(size.equalsIgnoreCase("max")) {
			maximiza(null);
		}
}
	
	public JLabel criaLabel(int x, int y, int width, int heigth, String nome, String texto) {
		JLabel jl = new JLabel();
		jl.setText(texto);
		jl.setName(nome);
		jl.setForeground(Color.white);
		jl.setBounds(x, y, width, heigth);
		
		return jl;
	}
	
	public JTextField criaCampo(int x, int y, int width, int heigth, String nome) {
		JTextField jtf = new JTextField(){
			
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
		jtf.setName(nome);
		jtf.setBackground(Color.BLACK);
		jtf.setForeground(Color.white);
		jtf.setBorder(null);
		jtf.setBounds(x, y, width, heigth);
		
		return jtf;
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
	            graphics2D.setStroke(new BasicStroke(5));
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
	public void criaBotao(String nome,JButton botao,int x, int y, int largura, int altura,Color corB,Color corF,Font fonte) {
		botao.setName(nome);
		botao.setBounds(x, y, largura, altura);
		botao.setBackground(corB);
		botao.setForeground(corF);
		botao.setFocusPainted(false);
		botao.setBorderPainted(false);
		botao.setFont(fonte);
		
	}
	
	// Metodo para maximizar a tela e desmaximizar
	private void maximiza(ActionEvent actionEvent) {
		if (apertou == false) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			novaBarra.setBounds(0, 0, frame.getWidth(), 40);
			panelGeral.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			int localizacaoX = ( frame.getWidth() - (frame.getWidth()-250) ) /2;
			conteudoMeioDaTela.setBounds(localizacaoX, frame.getWidth()/10, frame.getWidth()-250, frame.getHeight()-420);
			panelImagem.setLocation(conteudoMeioDaTela.getWidth() / 8, conteudoMeioDaTela.getHeight() / 4+100);
			//cria uma variável que altera o eixo x de forma dinâmica
			int localizacaoX2 = ( frame.getWidth() - (frame.getWidth()/2) ) /2;
			barraCadastro.setBounds(localizacaoX2,frame.getHeight()/12,frame.getWidth()/2,50);
			
			int localizacaoX3 = barraCadastro.getWidth()/2+-barraCadastro.getWidth()/8;
			labelCadastro.setLocation(localizacaoX3, 10);
			
			//---------LABELS e CAMPOS do form------------//
			//lado esquerdo
			campoNome.setLocation(conteudoMeioDaTela.getWidth()/4+50, (conteudoMeioDaTela.getHeight()/5));
			campoSenha.setLocation(conteudoMeioDaTela.getWidth()/4+50, (conteudoMeioDaTela.getHeight()/5)*2);
			campoCPF.setLocation(conteudoMeioDaTela.getWidth()/4+50, (conteudoMeioDaTela.getHeight()/5)*3);
			//lado direito
			campoEmail.setLocation(conteudoMeioDaTela.getWidth()/4+410, (conteudoMeioDaTela.getHeight()/5));
			campoConfirmSenha.setLocation(conteudoMeioDaTela.getWidth()/4+410, (conteudoMeioDaTela.getHeight()/5)*2);
			campoEndereco.setLocation(conteudoMeioDaTela.getWidth()/4+410, (conteudoMeioDaTela.getHeight()/5)*3);
			
			//lado esquerdo
			labelNome.setLocation(conteudoMeioDaTela.getWidth()/4+50, (conteudoMeioDaTela.getHeight()/5)-40);
			labelSenha.setLocation(conteudoMeioDaTela.getWidth()/4+50, (conteudoMeioDaTela.getHeight()/5)*2-40);
			labelCPF.setLocation(conteudoMeioDaTela.getWidth()/4+50, (conteudoMeioDaTela.getHeight()/5)*3-40);
			//lado direito
			labelEmail.setLocation(conteudoMeioDaTela.getWidth()/4+410, (conteudoMeioDaTela.getHeight()/5)-40);
			labelConfirmSenha.setLocation(conteudoMeioDaTela.getWidth()/4+410, (conteudoMeioDaTela.getHeight()/5)*2-40);
			labelEndereco.setLocation(conteudoMeioDaTela.getWidth()/4+410, (conteudoMeioDaTela.getHeight()/5)*3-40);
			
			int localizacaoX4 = ( frame.getWidth() - 280 ) /2;	
			botaoCadastrar.setBounds(localizacaoX4, frame.getHeight()/2+frame.getHeight()/3,280,40);
			
			maximo.setText("❐");
			apertou = true;
		}else {
			frame.setSize(1000, 600);
			frame.setLocationRelativeTo(null);
			novaBarra.setBounds(0, 0, frame.getWidth(), 40);
			conteudoMeioDaTela.setBounds(frame.getHeight()/20, frame.getWidth()/10, frame.getWidth()-50, frame.getHeight()-240);
			panelImagem.setLocation(conteudoMeioDaTela.getWidth() / 20+20, conteudoMeioDaTela.getHeight() -170);
			botaoCadastrar.setLocation(conteudoMeioDaTela.getWidth()/2+-130, conteudoMeioDaTela.getHeight()+140);
			barraCadastro.setBounds(20,30,frame.getWidth()-40,50);
			labelCadastro.setLocation(barraCadastro.getWidth()/2+-90, 10);
			
			//---------LABELS e CAMPOS do form------------//
			campoNome.setLocation(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-310);
			campoSenha.setLocation(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-210);
			campoCPF.setLocation(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-110);
			//lado direito
			campoEmail.setLocation(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-310);
			campoConfirmSenha.setLocation(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-210);
			campoEndereco.setLocation(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-110);
			
			//lado esquerdo
			labelNome.setLocation(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-340);
			labelSenha.setLocation(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-240);
			labelCPF.setLocation(conteudoMeioDaTela.getWidth()/4+40, conteudoMeioDaTela.getHeight()+-140);
			//lado direito
			labelEmail.setLocation(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-340);
			labelConfirmSenha.setLocation(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-240);
			labelEndereco.setLocation(conteudoMeioDaTela.getWidth()/4+400, conteudoMeioDaTela.getHeight()+-140);
			
			maximo.setText("□");
			apertou = false;
		}
	}
	
	//Metodo adiciona evento de mouse botoes
	public MouseAdapter eventoMouse(JButton botao) {
		MouseAdapter evento = null;
		if (botao.getName().equalsIgnoreCase("fecha") || botao.getName().equalsIgnoreCase("maximo")
				|| botao.getName().equalsIgnoreCase("minimo")) {
			evento = new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					botao.setBackground(Color.red);
				}

				public void mouseExited(java.awt.event.MouseEvent evt) {
					botao.setBackground(Color.black);
				}
			};
		}else if (botao.getName().equalsIgnoreCase("botaoCadastrar")) {
			
			evento = new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					botao.setBackground(new Color(217, 217, 217));
				}
				 public void mouseClicked(java.awt.event.MouseEvent evt) {
						 if(campoNome.getText().isEmpty()||campoCPF.getText().isEmpty()
									||campoEmail.getText().isEmpty()||campoSenha.getText().isEmpty()||campoEndereco.getText().isEmpty()) {
							 JOptionPane.showMessageDialog(null, "Existem campos vazios");
									 
								 }else {
									 if(!campoEmail.getText().contains("@")) {
										 JOptionPane.showMessageDialog(null, "Digite um email valido");
									 }else if(!campoSenha.getText().equals(campoConfirmSenha.getText())) {
										 JOptionPane.showMessageDialog(null, "As senhas nao sao iguais");
									 }
									 if(campoEmail.getText().contains("@")&&campoSenha.getText().equals(campoConfirmSenha.getText())) {
										 ok=true;
									 }
								 }
						if(ok==true) {
							Administrador administrador = new Administrador(campoNome.getText(),campoCPF.getText(),campoEmail.getText(),campoSenha.getText(),campoEndereco.getText());
					    	administrador.cadastraBanco();
					    	JOptionPane.showMessageDialog(null, "Voce se Cadastrou com sucesso");
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
					    	TelaLogin tela = new TelaLogin(true, "Max");
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
				    	}else {
				    		try {
				    			TelaLogin tela = new TelaLogin(true, "Minimo");
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
	
	//Funcao destinada as acoes dos botoes
	public void acoes() {
		String nome,senha,email,confirmEmail,CPF,endereco;
		
		nome= campoNome.getText();
		senha=campoSenha.getText();
		CPF=campoCPF.getText();
		email=campoEmail.getText();
		confirmEmail=campoConfirmSenha.getText();
		endereco= campoEndereco.getText();
		
		if(nome.length()>0 && senha.length()>0 && CPF.length()>0 && email.length()>0 && confirmEmail.length()>0 && endereco.length()>0) {
			//Logica
		}
		
	}
	
	
}
