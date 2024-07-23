package Icons;
import java.net.*;

import javax.swing.ImageIcon;

public class ImagensFeitas {
	
	public ImageIcon imagem(String nome) throws MalformedURLException{
		if(nome.equalsIgnoreCase("User")) {
			URL urlImg = new URL("https://cdn-icons-png.flaticon.com/512/303/303593.png");
			ImageIcon rImagem = new ImageIcon(urlImg);
			return rImagem;	
		}
		if(nome.equalsIgnoreCase("Estrela")) {
			URL urlImg = new URL("https://cdn-icons-png.flaticon.com/512/541/541415.png");
			ImageIcon rImagem = new ImageIcon(urlImg);
			return rImagem;	
		}if(nome.equalsIgnoreCase("Seta")) {
			URL urlImg = new URL("https://png.pngtree.com/png-vector/20220924/ourlarge/pngtree-arrow-left-flat-white-color-icon-left-back-west-vector-png-image_19938706.jpg");
			ImageIcon rImagem = new ImageIcon(urlImg);
			return rImagem;	
		}
		if(nome.equalsIgnoreCase("Logo")) {
			URL urlImg = new URL("https://cdn-icons-png.flaticon.com/512/242/242452.png");
			ImageIcon rImagem = new ImageIcon(urlImg);
			return rImagem;	
		}
		if(nome.equalsIgnoreCase("Restaurante")) {
			URL urlImg = new URL("https://live.staticflickr.com/65535/53723362830_e1bf8457bc_z.jpg");
			ImageIcon rImagem = new ImageIcon(urlImg);
			return rImagem;	
		}
		if(nome.equalsIgnoreCase("Pesquisar")) {
			URL urlImg = new URL("https://icones.pro/wp-content/uploads/2021/02/loupe-et-icone-de-recherche-de-couleur-grise.png");
			ImageIcon rImagem = new ImageIcon(urlImg);
			return rImagem;	
		}
		return null;
	}
}
