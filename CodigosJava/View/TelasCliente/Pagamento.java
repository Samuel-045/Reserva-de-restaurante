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
import TelasAdm.CadastroRestaurante;
import Usuario.Cliente;

public class Pagamento {

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
	
	public Pagamento(boolean ligaTela,String size,Reserva reserva) {
		cliente = reserva.getCliente();
		restaurante= reserva.getRestaurante();
		
		reservaFinal=reserva;
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
		labelPagameto = criaLabel(barraNomeRestaurante.getWidth()/2+-barraNomeRestaurante.getWidth()/14, barraNomeRestaurante.getHeight()/4, 250, 30, "labelPagamento" , "Pagamento");
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
		labelFormaPagamento = criaLabel((informacoesCentrais.getWidth()/6)*2+10, (informacoesCentrais.getHeight()/10), 250, 30, "labelFormaPagamento", "Selecione a forma de pagamento");
		informacoesCentrais.add(labelFormaPagamento);
		
		//radio button do pix
		jrbPix = criRdBotao(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()/3, 100, 30, "Pix", "Pix");
		informacoesCentrais.add(jrbPix);
		
		//radio button do dinheiro
		jrbDinheiro = criRdBotao((informacoesCentrais.getWidth()/8)*3+40, informacoesCentrais.getHeight()/3, 100, 30, "Dinheiro", "Dinheiro");
		informacoesCentrais.add(jrbDinheiro);

		//radio button do cartao
		jrbCartao = criRdBotao((informacoesCentrais.getWidth()/8)*6, informacoesCentrais.getHeight()/3, 100, 30, "Cartão", "Cartão");
		informacoesCentrais.add(jrbCartao);
		
		//criacao do button group para adicionar os radios buttons em um único grupo
		grupoBotoes = new ButtonGroup();
		grupoBotoes.add(jrbPix);
		grupoBotoes.add(jrbDinheiro);
		grupoBotoes.add(jrbCartao);
		
		//label que mostra o tipo de pagamento escolhido
		labelTipoPagamento = criaLabel(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()-90, 300,30, "labelTipoPagamento", "Forma de pagamento escolhido:");
		informacoesCentrais.add(labelTipoPagamento);
		
		//label que vem antes do valor total da compra
		labelTotal = criaLabel(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()-70, 200,30, "labelTotal", "Total a Pagar: R$");
		//label que ira mostrar o valor
		labelValor = criaLabel(informacoesCentrais.getWidth()/8+100, informacoesCentrais.getHeight()-70, 200,30, "labelPreco", ""+reservaFinal.getValor()); /////// o valor vai ser alterado de forma dinÂmica
		informacoesCentrais.add(labelTotal);
		informacoesCentrais.add(labelValor);
		
		//label que é responsável por dar o retorno de compra efetuada
		labelConfirmacao = criaLabel(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()-40, 400,30, "labelConfirmacao", "");
		informacoesCentrais.add(labelConfirmacao);
		
		//botao de pagamento, e logo após a adição do efeito hover no botão criado
		botaoPagamento = criaBotao(conteudoMeioDaTela.getWidth()/2+-conteudoMeioDaTela.getWidth()/6, conteudoMeioDaTela.getHeight()-70, conteudoMeioDaTela.getWidth()/3, 50, "BotaoPagamento", "Realizar Pagamento");
		botaoPagamento.addMouseListener(eventoMouse(botaoPagamento));
		conteudoMeioDaTela.add(botaoPagamento);
		
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
			} else if (botao.getName().equalsIgnoreCase("BotaoPagamento")) {
				evento = new java.awt.event.MouseAdapter() {
					public void mouseEntered(java.awt.event.MouseEvent evt) {
						botao.setBackground(new Color(217, 217, 217));
					}
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						reservaFinal.cadastraBanco();
				    	
						frame.dispose();
				    	if(frame.getExtendedState()==(JFrame.MAXIMIZED_BOTH)){
					    	Agradecimento tela = new Agradecimento(true, "Max",reservaFinal);	
				    	}else {
				    		Agradecimento tela = new Agradecimento(true, "Minimo",reservaFinal);	
				    	}
					    }

					public void mouseExited(java.awt.event.MouseEvent evt) {
						botao.setBackground(new Color(255, 180, 91));
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
						    		InformacoesRestaurante tela = new InformacoesRestaurante(true,"Max",restaurante.getNome(),cliente);
								} catch (MalformedURLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
					    	}else {
					    		try {
					    			InformacoesRestaurante tela = new InformacoesRestaurante(true,"Minimo",restaurante.getNome(),cliente);
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
		jrbDinheiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jrbDinheiro.isSelected()) {
					labelTipoPagamento.setText("Forma de pagamento escolhido: Dinheiro");
				}
			}
		});
		
		jrbPix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jrbPix.isSelected()) {
					labelTipoPagamento.setText("Forma de pagamento escolhido: Pix");
				}
			}
		});
		
		jrbCartao.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (jrbCartao.isSelected()) {
					labelTipoPagamento.setText("Forma de pagamento escolhido: Cartão");
				}
			}

		});
		
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
	
	public JRadioButton criRdBotao(int x, int y, int width, int heigth, String nome, String texto) {
		JRadioButton rdbt = new JRadioButton();
		rdbt.setName(nome);
		rdbt.setText(texto);
		rdbt.setBounds(x, y, width, heigth);
		rdbt.setForeground(Color.white);
		rdbt.setBackground(Color.black);
		
		return rdbt;
	}
	
	// Metodo para maximizar a tela e desmaximizar
	private void maximiza(ActionEvent actionEvent) {
		
		if (apertou == false) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			novaBarra.setBounds(0, 0, frame.getWidth(), 40);
			telaFundo.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			conteudoMeioDaTela.setBounds(frame.getWidth()/8,0,frame.getWidth()-frame.getWidth()/4,(frame.getHeight()/4)*3+100);
			barraNomeRestaurante.setBounds(0,40,conteudoMeioDaTela.getWidth(),80);
			//cria uma variável que altera o eixo x de forma dinâmica
			int localizacaoX = ( ( barraNomeRestaurante.getWidth() - (barraNomeRestaurante.getWidth()/9) ) /2);
			labelPagameto.setLocation(localizacaoX,barraNomeRestaurante.getHeight()/4);
			informacoesCentrais.setBounds(conteudoMeioDaTela.getWidth()/8, conteudoMeioDaTela.getHeight()/5, (conteudoMeioDaTela.getWidth()/4)*3, conteudoMeioDaTela.getHeight()-250);
			labelFormaPagamento.setBounds((informacoesCentrais.getWidth()/6)*2+40, (informacoesCentrais.getHeight()/10), 250, 30);
			jrbPix.setLocation(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()/3);
			jrbDinheiro.setLocation((informacoesCentrais.getWidth()/8)*3+40, informacoesCentrais.getHeight()/3);
			jrbCartao.setLocation((informacoesCentrais.getWidth()/8)*6, informacoesCentrais.getHeight()/3);
			labelTipoPagamento.setLocation(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()-90);
			labelTotal.setLocation(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()-70);
			labelValor.setLocation(informacoesCentrais.getWidth()/8+100, informacoesCentrais.getHeight()-70);
			labelConfirmacao.setLocation(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()-40);
			//cria uma variável que altera o eixo x de forma dinâmica
			int localizacaoX2 = ( ( conteudoMeioDaTela.getWidth() - ( conteudoMeioDaTela.getWidth()/4 ) ) /2 );
			botaoPagamento.setLocation(localizacaoX2, conteudoMeioDaTela.getHeight()-70);
			
			maximo.setText("❐");
			apertou = true;
		} else {
			frame.setSize(1000, 600);
			novaBarra.setBounds(0, 0, frame.getWidth(), 40);
			telaFundo.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			conteudoMeioDaTela.setBounds(frame.getWidth()/8,0,frame.getWidth()-frame.getWidth()/4,frame.getHeight()-20);
			barraNomeRestaurante.setBounds(0,40,conteudoMeioDaTela.getWidth(),80);
			labelPagameto.setLocation(barraNomeRestaurante.getWidth()/2+-barraNomeRestaurante.getWidth()/14, barraNomeRestaurante.getHeight()/4);
			informacoesCentrais.setBounds(conteudoMeioDaTela.getWidth()/8, conteudoMeioDaTela.getHeight()/4, (conteudoMeioDaTela.getWidth()/4)*3, conteudoMeioDaTela.getHeight()-250);
			labelFormaPagamento.setBounds((informacoesCentrais.getWidth()/6)*2+10, (informacoesCentrais.getHeight()/10), 250, 30);
			jrbPix.setLocation(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()/3);
			jrbDinheiro.setLocation((informacoesCentrais.getWidth()/8)*3+40, informacoesCentrais.getHeight()/3);
			jrbCartao.setLocation((informacoesCentrais.getWidth()/8)*6, informacoesCentrais.getHeight()/3);
			labelTipoPagamento.setLocation(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()-90);
			labelTotal.setLocation(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()-70);
			labelValor.setLocation(informacoesCentrais.getWidth()/8+100, informacoesCentrais.getHeight()-70);
			labelConfirmacao.setLocation(informacoesCentrais.getWidth()/8, informacoesCentrais.getHeight()-40);
			botaoPagamento.setLocation(conteudoMeioDaTela.getWidth()/2+-conteudoMeioDaTela.getWidth()/6, conteudoMeioDaTela.getHeight()-70);
			
			frame.setLocationRelativeTo(null);
			maximo.setText("□");
			apertou = false;
		}
		
		
	}
}

