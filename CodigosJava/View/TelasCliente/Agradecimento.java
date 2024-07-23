package TelasCliente;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.net.MalformedURLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Geral.Restaurante;
import Servico.Reserva;
import Usuario.Cliente;

public class Agradecimento {

	private static Point mouseDownCompCoords;
	JFrame frame = new JFrame();
	Boolean apertou = false;
	
	JButton maximo = new JButton("□");
	JButton fecha = new JButton("X");
	JButton minimo = new JButton("━");
	JButton seta = new JButton("←");
	JButton botaoPagamento;
	Reserva reservaFinal = new Reserva();
	JRadioButton jrbPix,jrbDinheiro,jrbCartao;
	ButtonGroup grupoBotoes;
	JLabel labelPagameto, labelFormaPagamento, labelTipoPagamento, labelTotal, labelValor, labelConfirmacao;
	
	JPanel telaFundo = new JPanel();
	JPanel conteudoMeioDaTela = new JPanel();
	JPanel informacoesCentrais = new JPanel(){
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
            graphics2D.setStroke(new BasicStroke(10));
            graphics2D.draw(roundedRectangle);
            graphics2D.dispose();
        }
};
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
	JPanel novaBarra = new JPanel(new BorderLayout()) {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(fecha.getFont().deriveFont(Font.BOLD, 14));
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.white);
			g.drawString("", getWidth() / 2 - 80, 25);
		}
	};
	Restaurante restaurante = new Restaurante();
	Cliente cliente = new Cliente();
	
	public Agradecimento(boolean ligaTela,String size,Reserva reserva) {
		cliente = reserva.getCliente();
	restaurante= reserva.getRestaurante();
	reservaFinal=reserva;
	
		//Remove a barra de titulo padrao
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
		//adiciona o efeito hover nos botoes da barra
		minimo.addMouseListener(eventoMouse(minimo));
		maximo.addMouseListener(eventoMouse(maximo));
		fecha.addMouseListener(eventoMouse(fecha));
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
		
		// =======DAQUI PARA BAIXO É O CÓDIGO QUE VAI NO CENTRO DA TELA========

		//criacao do JPanel que vai no fundo da tela
		telaFundo.setBounds(0,0,frame.getWidth(),frame.getHeight());
		telaFundo.setBackground(Color.black);
		
		//criacao do JPanel que vai no meio da tela
		conteudoMeioDaTela.setBounds(frame.getWidth()/8,0,frame.getWidth()-frame.getWidth()/4,frame.getHeight()-20);
		conteudoMeioDaTela.setBackground(new Color(0,0,0));		
		conteudoMeioDaTela.setLayout(null);
		
		//cria a barra de laranja acima do 'form'
		barraNomeRestaurante.setBackground(new Color(255,180,91));
		barraNomeRestaurante.setBounds(0,40,conteudoMeioDaTela.getWidth(),80);
		barraNomeRestaurante.setForeground(new Color(0,0,0));
		barraNomeRestaurante.setLayout(null);
		
		//cria a label de pagamento que vai dentro da barra
		labelPagameto = criaLabel(barraNomeRestaurante.getWidth()/2+-barraNomeRestaurante.getWidth()/14, barraNomeRestaurante.getHeight()/4, 250, 30, "labelPagamento" , "Obrigado !");
		labelPagameto.setForeground(Color.black);
		labelPagameto.setFont(fecha.getFont().deriveFont(Font.BOLD, 20));
		barraNomeRestaurante.add(labelPagameto);
		conteudoMeioDaTela.add(barraNomeRestaurante);
		
		//criacao do JPanel central da tela
		informacoesCentrais.setBackground(Color.black);
		informacoesCentrais.setBounds(conteudoMeioDaTela.getWidth()/8, conteudoMeioDaTela.getHeight()/4, (conteudoMeioDaTela.getWidth()/4)*3, conteudoMeioDaTela.getHeight()-250);
		informacoesCentrais.setLayout(null);
		informacoesCentrais.setBorder(null);
		informacoesCentrais.setForeground(Color.WHITE);
		conteudoMeioDaTela.add(informacoesCentrais);
		
		//label de "titulo" da parte central
		labelFormaPagamento = criaLabel((informacoesCentrais.getWidth()/6)*2-40, (informacoesCentrais.getHeight()/10)+80, 450, 30, "labelFormaPagamento", "Enviamos um email de confirmaçao para");
		labelFormaPagamento.setFont(labelFormaPagamento.getFont().deriveFont(Font.BOLD, 14));
		informacoesCentrais.add(labelFormaPagamento);
		
		labelConfirmacao = criaLabel((informacoesCentrais.getWidth()/6)*2-10, (informacoesCentrais.getHeight()/10)+100, 450, 30, "labelFormaPagamento", reservaFinal.getCliente().getEmail());
		labelConfirmacao.setFont(labelConfirmacao.getFont().deriveFont(Font.BOLD, 18));
		informacoesCentrais.add(labelConfirmacao);
		
		labelPagameto = criaLabel((informacoesCentrais.getWidth()/6)*2-30, (informacoesCentrais.getHeight()/10)+240, 450, 30, "labelFormaPagamento", "Pague ou Mostre o comprovante no balcao ");
		labelPagameto.setFont(labelPagameto.getFont().deriveFont(Font.BOLD, 12));
		informacoesCentrais.add(labelPagameto);
		
		labelTipoPagamento = criaLabel((informacoesCentrais.getWidth()/6)*2-95, (informacoesCentrais.getHeight()/10)+220, 450, 30, "labelFormaPagamento", "Obrigado por fazer uma reserva conosco");
		labelTipoPagamento.setFont(labelTipoPagamento.getFont().deriveFont(Font.BOLD, 20));
		informacoesCentrais.add(labelTipoPagamento);
		frame.getContentPane().add(conteudoMeioDaTela);		
		frame.getContentPane().add(telaFundo);
		frame.setVisible(ligaTela);
		acoes();
		if(size.equalsIgnoreCase("max")) {
			maximiza(null);
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
			} 
			if(botao.getName().equalsIgnoreCase("Seta")) {
				evento = new java.awt.event.MouseAdapter() {
					 public void mouseEntered(java.awt.event.MouseEvent evt) {
					        
					    }
					 public void mouseClicked(java.awt.event.MouseEvent evt) {
					    	frame.dispose();
					    	
					    	if(frame.getExtendedState()==(JFrame.MAXIMIZED_BOTH)){
						    	try {
						    		BuscarRestaurante telaListagem = new BuscarRestaurante(true,"Max",reservaFinal.getCliente());
								} catch (MalformedURLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
					    	}else {
					    		try {
					    			BuscarRestaurante telaListagem = new BuscarRestaurante(true,"Minimo",reservaFinal.getCliente());
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
	
	//acoes de cada radio button
	public void acoes() {
		
		
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
		JTextField jtf = new JTextField();
		jtf.setName(nome);
		jtf.setBackground(Color.BLACK);
		jtf.setForeground(Color.white);
		jtf.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
		jtf.setBounds(x, y, width, heigth);
		
		return jtf;
	}

	public JButton criaBotao(int x, int y, int width, int heigth, String nome, String texto) {
		JButton jb = new JButton(){
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
		jb.setName(nome);
		jb.setText(texto);
		jb.setBounds(x, y, width, heigth);
		jb.setBackground(new Color(255, 180, 91));
		jb.setBorder(null);
		jb.setForeground(new Color(0,0,0));
		
		return jb;
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
	
	
	// Metodo para maximizar a tela e desmaximizar
	private void maximiza(ActionEvent actionEvent) {
		
		
		
		
	}
}

