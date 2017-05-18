package net.mem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import net.mem.dao.AllergeneRepository;
import net.mem.dao.CategorieRepository;
import net.mem.dao.CommandeRepository;
import net.mem.dao.LieuRepository;
import net.mem.dao.ProduitRepository;
import net.mem.dao.UtilisateurRepository;
import net.mem.dao.entities.Allergene;
import net.mem.dao.entities.Categorie;
import net.mem.dao.entities.Lieu;
import net.mem.dao.entities.Utilisateur;

@SpringBootApplication
public class PataNegraApplication implements CommandLineRunner  {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	@Autowired 
	private ProduitRepository produitRepository;
	
	@Autowired
	private LieuRepository lieuRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private AllergeneRepository allergeneRepository; 

	
	public static void main(String[] args)  {
		SpringApplication.run(PataNegraApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Md5PasswordEncoder encryptMD5 = new Md5PasswordEncoder();
//		Utilisateur u1 = utilisateurRepository.save(new Utilisateur("Alessandro","alessandro@gmail.com","La Monica","Alessandro",encryptMD5.encodePassword("test1",null)));
		Utilisateur u2 = utilisateurRepository.save(new Utilisateur("Benedetto","benedetto.lamonica@gmail.com","La Monica","Benedetto",encryptMD5.encodePassword("test1",null)));

		lieuRepository.save(new Lieu(new Long(1),"Italie"));
		lieuRepository.save(new Lieu(new Long(2),"Belgique"));
		lieuRepository.save(new Lieu(new Long(3),"France"));
		lieuRepository.save(new Lieu(new Long(4),"Espagne"));
		
		categorieRepository.save(new Categorie(new Long(1),"Fromage","Fromage"));
		categorieRepository.save(new Categorie(new Long(2),"Jambon","Jambon"));
		categorieRepository.save(new Categorie(new Long(3),"Saucisson","Saucisson"));
		categorieRepository.save(new Categorie(new Long(4),"Préparation Maison","Préparation Maison"));
		
		allergeneRepository.save(new Allergene(new Long(1),"Gluten","Céréales contenant du gluten, à savoir: blé (comme épeautre et blé de Khorasan), seigle, orge, avoine ou leurs souches hybridées"));
		allergeneRepository.save(new Allergene(new Long(2),"Oeufs","des oeufs"));
		allergeneRepository.save(new Allergene(new Long(3),"Crustacés","Crustacés"));
		allergeneRepository.save(new Allergene(new Long(4),"Poissons","Poissons"));
		allergeneRepository.save(new Allergene(new Long(5),"Arachides","Arachides"));
		allergeneRepository.save(new Allergene(new Long(6),"Soja","Soja"));
		allergeneRepository.save(new Allergene(new Long(7),"Lait","Lait (y compris le lactose)"));
		allergeneRepository.save(new Allergene(new Long(8),"Fruits à coque","Fruits à coque, à savoir: amandes,noisettes, noix, noix de cajou, noix de pécan,noix du Brésil, pistaches, noix de Macadamiaou du Queensland"));
		allergeneRepository.save(new Allergene(new Long(9),"Crustacés","Crustacés"));
		allergeneRepository.save(new Allergene(new Long(10),"Céleri","Céleri"));		
		allergeneRepository.save(new Allergene(new Long(11),"Moutarde","Moutarde"));
		allergeneRepository.save(new Allergene(new Long(12),"Anhydride sulfureux et sulfites","Anhydride sulfureux et sulfites en concentrations de plus de 10 mg/kg ou 10 mg/l"));
		allergeneRepository.save(new Allergene(new Long(13),"Lupin","Lupin"));
		allergeneRepository.save(new Allergene(new Long(14),"Mollusques","Mollusques"));

		
		
//		Produit p1 = produitRepository.save(new Produit(new Long(1),"PECC NONNA", "Pecorino Nonna",italie,32.90,"kg", null,"test",fromage));
//		
//		Commande c1 = commandeRepository.save(new Commande(new Long(1), u2));
//		Ligne l1 = new Ligne(new Id(p1.getId(),c1.getId()), p1, 2, c1);
//		c1.getLignes().add(l1);
//		commandeRepository.save(c1);
	}
 
}
