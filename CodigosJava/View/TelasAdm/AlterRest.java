package TelasAdm;
 
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Geral.Restaurante;
import Icons.ImagensFeitas;
import ProcessosBanco.AlteraRestaurante;
import TelaBase.TelaLogin;
import Usuario.Administrador;
 
public class AlterRest {
	private static Point mouseDownCompCoords;
	JFrame frame = new JFrame();
	ImagensFeitas icons = new ImagensFeitas();
	Boolean apertou = false;
	boolean ok=false;
	JButton maximo = new JButton("□");
	JButton fecha = new JButton("X");
	JButton minimo = new JButton("━");
	String descBasica,desCompleta,descVip;
	ImageIcon imagem = new ImageIcon();
	JLabel containerImagem = new JLabel();
	Image imge;
	Image imgScaleLogo ;
	ImageIcon logo = new ImageIcon();
	Restaurante mandarAlterar;
	String restNomeAltera;
	JTextArea  campoDescricao,aux, campoDescricaoExperienciaBasica,campoDescricaoExperienciaCompleta,campoDescricaoExperienciaVip;
	JTextField campoUrl,campoEndereco,campoEstrelas, campoNome, campoChef, campoHorario;
	JLabel labelCadastro,labelEstrelas, labelUrl,labelEndereco, labelNome, labelDescricao, labelChef, labelHorario, labelExperiencia, labelDescricaoExperiencia, labelFeedback;
	JRadioButton rbBasica, rbCompleta, rbVip;
	JButton botaoCadastrar;
	ButtonGroup grupoBotoes = new ButtonGroup();
	int localizacaoX1;
	
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
	
	JPanel conteudoMeioDaTela = new JPanel();
	JPanel panelImagem = new JPanel();
	JPanel experiencias = new JPanel() {
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
	           graphics2D.setStroke(new BasicStroke(5));
	           graphics2D.draw(roundedRectangle);
	           graphics2D.dispose();
			}
	};
	JPanel panelDeFundo = new JPanel();
	
	JScrollPane scrollPane = new JScrollPane(conteudoMeioDaTela) {
        @Override
        protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D graphics2D = (Graphics2D) g.create();
			int width = getWidth();
			int height = getHeight();
			int cornerRadius = 20;
			RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius,
					cornerRadius);
			graphics2D.setColor(Color.white);
			graphics2D.setStroke(new BasicStroke(5));
			graphics2D.draw(roundedRectangle);
			graphics2D.dispose();
        }
    };
	
	public AlterRest(boolean ligaTela,String size,Restaurante restaurante,String nome) throws MalformedURLException {
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
		novaBarra.add(botoes, BorderLayout.EAST);
 
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
		frame.add(panelDeFundo);
		
		//cria a barra de laranja acima do 'form'
		barraCadastro.setBounds(frame.getWidth()/2-frame.getWidth()/4,30,frame.getWidth()/2,50);
		barraCadastro.setBackground(new Color(255,180,91));
		barraCadastro.setForeground(Color.black);
		barraCadastro.setLayout(null);
		
		//cria a label de login
		labelCadastro = criaLabel(barraCadastro.getWidth()/2+-80, 10, barraCadastro.getWidth()/3, 30, "labelCadastro" , "Alterar Restaurante");
		labelCadastro.setForeground(Color.black);
		labelCadastro.setFont(fecha.getFont().deriveFont(Font.BOLD, 14));
		barraCadastro.add(labelCadastro);
		panelDeFundo.add(barraCadastro);
		
		//	=======DAQUI PARA BAIXO É O CÓDIGO QUE VAI NO CENTRO DA TELA========	
//		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.black);
		scrollPane.setForeground(Color.white);
		scrollPane.setBounds( (frame.getWidth()/4)/2 , 100, (frame.getWidth()/4)*3, frame.getHeight()-150);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelDeFundo.add(scrollPane);
		
		conteudoMeioDaTela.setPreferredSize(new Dimension((frame.getWidth() / 4) * 3, 1000));
		conteudoMeioDaTela.setForeground(Color.white);
		conteudoMeioDaTela.setBackground(Color.black);
		conteudoMeioDaTela.setLayout(null);
		
		//Adiciona a imagem em um painel
		
		
		panelImagem.add(containerImagem);
		panelImagem.setBackground(Color.black);
		panelImagem.setBounds(40, 10, 150, 150);
		
		conteudoMeioDaTela.add(panelImagem);
		
		labelUrl = criaLabel(scrollPane.getWidth()/3, 20, 160, 20, "labelUrl", "URL(imagem)");
		conteudoMeioDaTela.add(labelUrl);
		
		campoUrl = criaCampo(scrollPane.getWidth()/3, 50, scrollPane.getWidth()/2, 30, "campoUrl");
		campoUrl.setText(restaurante.getUrlImagem());
		campoUrl.addMouseListener(eventoText(campoUrl));
		conteudoMeioDaTela.add(campoUrl);
		
		labelNome = criaLabel(scrollPane.getWidth()/3, 90, 160, 20, "labelNome", "Nome");
		conteudoMeioDaTela.add(labelNome);
		
		campoNome = criaCampo(scrollPane.getWidth()/3, 120, scrollPane.getWidth()/2, 30, "campoNome");
		campoNome.setText(restaurante.getNome());
		conteudoMeioDaTela.add(campoNome);
		
		labelDescricao = criaLabel(60, 170, 160, 20, "labelDescricao", "Descrição");
		conteudoMeioDaTela.add(labelDescricao);
		
		campoDescricao = criaCampo(60, 200, (scrollPane.getWidth()/4)*3, 80, "campoDescricao",true);
		campoDescricao.setText(restaurante.getDescricao());
		conteudoMeioDaTela.add(campoDescricao);
		
		labelChef = criaLabel(60, 290, 160, 20, "labelChef", "Chef");
		conteudoMeioDaTela.add(labelChef);
		
		campoChef = criaCampo(60, 320, (scrollPane.getWidth()/4)*3, 30, "campoChef");
		campoChef.setText(restaurante.getChef());
		conteudoMeioDaTela.add(campoChef);
		
		labelHorario = criaLabel(60, 360, 160, 20, "labelHorario", "Horário de Funcionamento");
		conteudoMeioDaTela.add(labelHorario);
		
		campoHorario = criaCampo(60, 390, (scrollPane.getWidth()/4)*3, 30, "campoHorario");
		
		conteudoMeioDaTela.add(campoHorario);
		
		campoEndereco = criaCampo(60, 450, (scrollPane.getWidth()/4)*3, 30, "campoEndereco");
		campoEndereco.setText(restaurante.getEndereco());
		conteudoMeioDaTela.add(campoEndereco);
		labelEndereco = criaLabel(60, 425, 160, 20, "labelEndereco", "Endereço");
		conteudoMeioDaTela.add(labelEndereco);
		
		campoEstrelas = criaCampo(60, 510, (scrollPane.getWidth()/4)*3, 30, "campoEstrelas");
		campoEstrelas.setText(String.valueOf(restaurante.getEstrelas()));
		conteudoMeioDaTela.add(campoEstrelas);
		labelEstrelas = criaLabel(60, 485, 160, 20, "labelEstrelas", "Quantidade de Estrelas");
		conteudoMeioDaTela.add(labelEstrelas);
		
		labelExperiencia = criaLabel(60, 545, 160, 20, "labelExperiencia", "Experiências");
		conteudoMeioDaTela.add(labelExperiencia);
		
		experiencias.setBounds(60, 570,(scrollPane.getWidth()/4)*3, 300);
		experiencias.setBackground(Color.black);
		experiencias.setForeground(Color.white);
		experiencias.setLayout(null);
		conteudoMeioDaTela.add(experiencias);
		
		rbBasica = criaRbBotao(80, 40, 80, 20, "rdBasica", "Basica");
		rbBasica.addMouseListener(eventoSelecao(rbBasica));
		experiencias.add(rbBasica);
		
		rbCompleta = criaRbBotao(260, 40, 80, 20, "rdCompleta", "Completa");
		rbCompleta.addMouseListener(eventoSelecao(rbCompleta));
		experiencias.add(rbCompleta);
		
		rbVip = criaRbBotao(440, 40, 80, 20, "rdVip", "Vip");
		rbVip.addMouseListener(eventoSelecao(rbVip));
		experiencias.add(rbVip);
		
		//Adiciona os botoes em um grupo, evitando acionar varios botoes simultaneamente
		grupoBotoes.add(rbBasica);
		grupoBotoes.add(rbCompleta);
		grupoBotoes.add(rbVip);
		scrollPane.setBorder(null);
		
		labelDescricaoExperiencia = criaLabel(80, 90, 230, 20, "labelExperienciaDescrita", "Descrição da experiência");
		labelDescricaoExperiencia.setVisible(false);
		experiencias.add(labelDescricaoExperiencia);
		
		campoDescricaoExperienciaBasica = criaCampo(80, 120, experiencias.getWidth()/4*3 , 170, "campoDescricao", true);
		campoDescricaoExperienciaBasica.setText(restaurante.getExperienciaBasica());
		campoDescricaoExperienciaBasica.setVisible(false);
		
		campoDescricaoExperienciaCompleta = criaCampo(80, 120, experiencias.getWidth()/4*3 , 170, "campoDescricao", true);
		campoDescricaoExperienciaCompleta.setText(restaurante.getExperienciaCompleta());
		campoDescricaoExperienciaCompleta.setVisible(false);
		
		campoDescricaoExperienciaVip = criaCampo(80, 120, experiencias.getWidth()/4*3 , 170, "campoDescricao", true);
		campoDescricaoExperienciaVip.setText(restaurante.getExperienciaVip());
		campoDescricaoExperienciaVip.setVisible(false);
	
		
		experiencias.add(campoDescricaoExperienciaBasica);
		experiencias.add(campoDescricaoExperienciaCompleta);
		experiencias.add(campoDescricaoExperienciaVip);
		
		localizacaoX1 = ( ( scrollPane.getWidth() - (scrollPane.getWidth()/4) ) /2); 
		botaoCadastrar = criaBotao(localizacaoX1, 890, 190, 30, "botaoCadastrar", "Alterar");
		conteudoMeioDaTela.add(botaoCadastrar);
		botaoCadastrar.addMouseListener(eventoMouse(botaoCadastrar));
		
		labelFeedback = criaLabel(frame.getWidth()/4-40, 840, 300, 30, "labelFeedback", "");
		conteudoMeioDaTela.add(labelFeedback);
		frame.setVisible(ligaTela);
		acoes();
		if(size.equalsIgnoreCase("max")) {
			maximiza(null);
		}
		
		restNomeAltera=nome;
		
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
			panelDeFundo.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			scrollPane.setBounds((frame.getWidth()/4)/2 , 100, (frame.getWidth()/4)*3, frame.getHeight()-150);
			//cria uma variável que altera o eixo x de forma dinâmica
			int localizacaoX2 = ( ( frame.getWidth() - (frame.getWidth()/3) ) /2);
			barraCadastro.setLocation(localizacaoX2,30);
			
			int localizacaoX4 =  ( ( scrollPane.getWidth() - (scrollPane.getWidth()/2)-30 ) /2); 
			panelImagem.setLocation(localizacaoX4-20, 10);
			
			labelUrl.setLocation((scrollPane.getWidth()/2)-scrollPane.getWidth()/12,20);
			campoUrl.setLocation((scrollPane.getWidth()/2)-scrollPane.getWidth()/12,50);
			
			labelNome.setLocation((scrollPane.getWidth()/2)-scrollPane.getWidth()/12, 90);
			campoNome.setLocation((scrollPane.getWidth()/2)-scrollPane.getWidth()/12,120);
			
			labelDescricao.setLocation(localizacaoX4, 170);
			campoDescricao.setLocation(localizacaoX4,200);
			
			labelChef.setLocation(localizacaoX4, 290);
			campoChef.setLocation(localizacaoX4,320);
			
			labelHorario.setLocation(localizacaoX4, 360);
			campoHorario.setLocation(localizacaoX4,390);
			
			labelEndereco.setLocation(localizacaoX4,425);
			campoEndereco.setLocation(localizacaoX4,450);
			
			labelEstrelas.setLocation(localizacaoX4, 485);
			campoEstrelas.setLocation(localizacaoX4, 510);
			
			labelExperiencia.setLocation(localizacaoX4, 545);
			experiencias.setLocation(localizacaoX4,570);
			
			localizacaoX1 = ( ( scrollPane.getWidth() - (scrollPane.getWidth()/7) ) /2);
			botaoCadastrar.setLocation(localizacaoX1,  890);			
			maximo.setText("❐");
			apertou = true;
		} else {
			frame.setSize(1000, 600);
			frame.setLocationRelativeTo(null);
			novaBarra.setBounds(0, 0, frame.getWidth(), 40);
			panelDeFundo.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			scrollPane.setBounds((frame.getWidth()/4)/2 , 100, (frame.getWidth()/4)*3, frame.getHeight()-150);
			
			barraCadastro.setLocation(frame.getWidth()/2-frame.getWidth()/4,30);
			
			panelImagem.setLocation(40, 10);
			
			labelUrl.setLocation(scrollPane.getWidth()/3,20);
			campoUrl.setLocation(scrollPane.getWidth()/3,50);
			
			labelNome.setLocation(scrollPane.getWidth()/3, 90);
			campoNome.setLocation(scrollPane.getWidth()/3,120);
			
			labelDescricao.setLocation(60, 170);
			campoDescricao.setLocation(60,200);
			
			labelChef.setLocation(60, 290);
			campoChef.setLocation(60,320);
			
			labelHorario.setLocation(60, 360);
			campoHorario.setLocation(60,390);
			
			labelEndereco.setLocation(60,425);
			campoEndereco.setLocation(60,450);
			
			labelEstrelas.setLocation(60, 485);
			campoEstrelas.setLocation(60, 510);
			
			labelExperiencia.setLocation(60, 545);
			experiencias.setLocation(60,570);
			
			localizacaoX1 = ( ( scrollPane.getWidth() - (scrollPane.getWidth()/4) ) /2);
			botaoCadastrar.setLocation(localizacaoX1, 890);
			
			

			maximo.setText("□");
			apertou = false;
		}
	}
	public void acoes() {
		botaoCadastrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(campoUrl.getText()!=""&&campoNome.getText()!=""&&campoDescricao.getText()!=""&&campoChef.getText()!=""
						&&campoHorario.getText()!=""&&
						(rbBasica.isSelected()||rbCompleta.isSelected()||rbVip.isSelected())) {
					labelFeedback.setText("Restaurante "+ campoNome.getText()+" cadastrado com sucesso");
				}
				
			}
		});
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
					mandarAlterar = new Restaurante(campoNome.getText(),campoEndereco.getText(),campoChef.getText(),  Integer.parseInt(campoEstrelas.getText()),campoDescricao.getText(),campoDescricaoExperienciaBasica.getText(),campoDescricaoExperienciaCompleta.getText(),campoDescricaoExperienciaVip.getText(),campoUrl.getText());
					 if(campoNome.getText().isEmpty()||campoEndereco.getText().isEmpty()
								||campoChef.getText().isEmpty()||campoEstrelas.getText().isEmpty()||campoDescricaoExperienciaBasica.getText().isEmpty()||campoDescricaoExperienciaCompleta.getText().isEmpty()||campoDescricaoExperienciaVip.getText().isEmpty()) {
						 JOptionPane.showMessageDialog(null, "Existem campos vazios");
								 
							 }else {
								ok=true;
							 }
					if(ok==true) {
						AlteraRestaurante alt = new AlteraRestaurante();
				    	alt.AlteraRest(mandarAlterar,restNomeAltera);
				    	JOptionPane.showMessageDialog(null, "Restaurante alterado com sucesso");
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
		}
		return evento;
	}
	
	public MouseAdapter eventoText(JTextField campo) {
	    MouseAdapter evento = null;
	    if(campo.getName().equalsIgnoreCase("campoUrl")) {
	        evento = new java.awt.event.MouseAdapter() {
	        	public void mouseEntered(java.awt.event.MouseEvent evt) {
	        		try {
						URL urlNova = new URL(campo.getText());
						ImageIcon imagemN = new ImageIcon(urlNova);
						 Image imge = imagemN.getImage();
				      		Image imgScaleLogoN = imge.getScaledInstance(150,150, Image.SCALE_SMOOTH);
				      		ImageIcon logoN = new ImageIcon(imgScaleLogoN);
				      		JLabel containerImagemNova = new JLabel(logoN);
				      		panelImagem.add(containerImagemNova);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	              try {
					URL urlNova = new URL(campo.getText());
					ImageIcon imagemN = new ImageIcon(urlNova);
					 Image imge = imagemN.getImage();
			      		Image imgScaleLogoN = imge.getScaledInstance(150,150, Image.SCALE_SMOOTH);
			      		ImageIcon logoN = new ImageIcon(imgScaleLogoN);
			      		JLabel containerImagemNova = new JLabel(logoN);
			      		panelImagem.add(containerImagemNova);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
	            }
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	            	 try {
	 					URL urlNova = new URL(campo.getText());
	 					ImageIcon imagemN = new ImageIcon(urlNova);
	 					 Image imge = imagemN.getImage();
	 			      		Image imgScaleLogoN = imge.getScaledInstance(150,150, Image.SCALE_SMOOTH);
	 			      		ImageIcon logoN = new ImageIcon(imgScaleLogoN);
	 			      		JLabel containerImagemNova = new JLabel(logoN);
	 			      		panelImagem.add(containerImagemNova);
	 				} catch (MalformedURLException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
				}
	        };
	    } 
	    return evento;
	}
	
	public MouseAdapter eventoSelecao(JRadioButton caixa) {
	    MouseAdapter evento = null;
	    evento = new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	if(caixa.getName().equalsIgnoreCase("rdBasica") && caixa.isSelected()) {
            		campoDescricaoExperienciaBasica.setVisible(true);
            		campoDescricaoExperienciaCompleta.setVisible(false);
            		campoDescricaoExperienciaVip.setVisible(false);
            		
            		labelDescricaoExperiencia.setText("Descrição da experiência:Básica");
        	    	labelDescricaoExperiencia.setVisible(true);
        	        
        	    }
        	    if(caixa.getName().equalsIgnoreCase("rdCompleta") && caixa.isSelected()) {
        	    	campoDescricaoExperienciaBasica.setVisible(false);
            		campoDescricaoExperienciaCompleta.setVisible(true);
            		campoDescricaoExperienciaVip.setVisible(false);
        	    	labelDescricaoExperiencia.setText("Descrição da experiência:Completa");
        	    	labelDescricaoExperiencia.setVisible(true);
        	    	
        	    	
        	    }
        	    if(caixa.getName().equalsIgnoreCase("rdVip") && caixa.isSelected()) {
        	    	campoDescricaoExperienciaBasica.setVisible(false);
            		campoDescricaoExperienciaCompleta.setVisible(false);
            		campoDescricaoExperienciaVip.setVisible(true);
        	    	labelDescricaoExperiencia.setText("Descrição da experiência:Vip");
        	    	labelDescricaoExperiencia.setVisible(true);
        	    	
        	    	
        	    }
           
            }
        };
	    return evento;
	}
}
