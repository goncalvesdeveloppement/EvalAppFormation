package fr.fms;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.auth.Auth;
import fr.fms.business.EcomBusinessImpl;
import fr.fms.entities.Formation;

public class EcomFormationApp {
	private static Scanner scan = new Scanner(System.in);
	private static EcomBusinessImpl business = new EcomBusinessImpl();
	private static Auth auth = new Auth();

	public static final String TEXT_BLUE = "\u001B[36m";
	public static final String TEXT_RESET = "\u001B[0m";

	private static int idUser = 0;
	private static String login = null;

	public static void main(String[] args) {
		System.out.println(idUser);
		
		displayAllFormations();
		mainMenu();
	}

	public static void mainMenu() {
		System.out.println("\n👉🏻 Que voulez-vous faire ? [Saisissez votre choix] : \n");
		System.out.println("[1] Infos formation    [2] Filtrer catalogue  [3] Afficher le panier \n[4] Page suivante      [5] Page précédente    [6] Quitter\n\n-> ");
	}

	public static void displayAllFormations() {
		home();

		System.out.println("\n⭐ NOS FORMATIONS DISPONIBLES :");
		System.out.println(
				"----------------------------------------------------------------------------------------------------\n");
		DisplaySomeFormations(business.readFormations());
	}

	public static void DisplaySomeFormations(ArrayList<Formation> formations) {
		formations.forEach(
				f -> System.out.println("-> [" + String.format("%03d", f.getIdFormation()) + "] " + f.getName() + " — " + business.readOneCategory(f.getIdCategory()).getTitle().toUpperCase() + " — " + f.getPrice() + " € — " + (f.isRemoteWork() ? "À DISTANCE" : "PRÉSENTIEL")));
		System.out.println("AFFICHAGE DE 1-7 FORMATIONS SUR 7 AU TOTAL (PAGE 1/1)");
		
		System.out.printf(
				"\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
	}

	public static void home() {
		System.out.print(TEXT_RESET);
		System.out.println(
				"####################################################################################################");
		System.out.println(
				"#                                                                                                  #");
		System.out.println(
				"#                     B I E N V E N U E   S U R   F O R M A - S H O P . F R  !                     #");
		System.out.println(
				"#                                                                                                  #");
		System.out.println(
				"####################################################################################################");
	}

	public static void login() {
		home();

		if (login != null) {
			System.out.println("Déconnexion ?");
			String response = scan.next();

			if (response.equalsIgnoreCase("Oui")) {
				System.out.println("Bye bye, " + login + ".");
				login = null;
				idUser = 0;
			}
		} else {
			String username, password;

			System.out.print(
					"\nBienvenue sur FormaShop.\nEntrez votre nom d'utilisateur : \n(ou tapez 'quitter' pour quitter)\n\n-> ");
			username = scan.next();

			if (username.equalsIgnoreCase("quitter")) {
				System.out.println("\n👋🏻 Bye bye.");
				System.exit(0);
			}

			System.out.print("\nEntrez votre mot de passe : \n\n-> ");
			password = scan.next();

			int id = auth.existUser(username, password);

			if (id > 0) {
				login = username;
				idUser = id;
				System.out.print(TEXT_BLUE + "cool");
			} else {
				System.out.print("\n❌ Nom d'utilisateur ou mot de passe incorrect...\n");
				System.out.print("\n➕ Nouveau ? Tapez OK pour créer un compte.\n\n-> ");
				String ok = scan.next();
				if (ok.equalsIgnoreCase("ok")) {
					newUser();
				}
			}
		}
	}

	public static void newUser() {
		System.out.print("\nSaisissez un nom d'utilisateur :\n\n->");
		String login = scan.next();
		int id = auth.existUser(login);
		if (id == 0) {
			System.out.print("\nSaisissez votre mot de passe :\n\n->");
			String password = scan.next();
			auth.addUser(login, password);
			System.out.println("\n✔ Vous êtes inscrit, bienvenue !");
			System.out.println("\n⚠ Ne perdez pas ces infos de connexion...");
			stop(2);
		} else
			System.out.print("\n❌ Nom d'utilisateur déjà existant, veuillez vous connecter.\n");
	}

	public static void stop(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int scanInt() {
		while (!scan.hasNextInt()) {
			System.out.println("saisissez une valeur entière svp");
			scan.next();
		}
		return scan.nextInt();
	}

}
