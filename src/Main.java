
import java.util.Scanner;

public class Main {

	private static String userName, password, email;
	private static String movieName, movieDirector, movieReleaseDate, description, movieStars, genre;

	private static User currentUser;
	private static UserStoreService userStore = new UserStoreService();
	private static MovieStoreService movieStore = new MovieStoreService();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// MOVIEADMIN
		User movieAdmin = userStore.addUser("cerencelik", "1234", "celikceren1@gmail.com");
		
		// USER ADMIN
		User userAdmin = userStore.addUser("erencelik", "1234", "celikeren@gmail.com");

		userStore.addRoleToUser(movieAdmin.getId(), "MovieAdmin");
		userStore.addRoleToUser(userAdmin.getId(), "UserAdmin");


		printMainMenu();
		chooseAction();

	}

	private static void printMainMenu() {
		System.out.println("##### Menu #####");
		System.out.println("Login");
		login();
		printMenu();

	}

	private static void printMenu() {
		System.out.println("##### Menu #####");

		if (currentUser == null) {
			return;
		}

		String currentUserRoles[] = userStore.getUserRoles(currentUser.getId());
		for (String s : currentUserRoles) {

			if (s != null && s.equals("UserAdmin")) {
				System.out.println("1: Create a user");
				System.out.println("2: List users");
				System.out.println("3: Search user by name");
				System.out.println("4: Delete user");
				System.out.println("5: Add role to a user");
				System.out.println("6: Remove role from user");
			}

			if (s != null && s.equals("MovieAdmin")) {
				System.out.println("7: Add a movie");
				System.out.println("8: Delete movies");
			}
		}

		System.out.println("9: List movies");
		System.out.println("10: Search movies");
		System.out.println("11: Logout");
		System.out.println("-1: Exit \n\n");
	}

	private static void chooseAction() {

		int choice = 0;
		do {
			System.out.print("Seçiminiz: ");
			Scanner sn = new Scanner(System.in);
			choice = sn.nextInt();
			String currentUserRoles[] = userStore.getUserRoles(currentUser.getId());

			switch (choice) {

			case 1:
				if (helper("UserAdmin", currentUserRoles)) {
					createUser();
				}
				break;
			case 2:
				if (helper("UserAdmin", currentUserRoles)) {
					printUser();
				}
				break;
			case 3:
				if (helper("UserAdmin", currentUserRoles)) {
					searchUser();
				}
				break;
			case 4:
				if (helper("UserAdmin", currentUserRoles)) {
					deleteUser();
				}
				break;
			case 5:
				if (helper("UserAdmin", currentUserRoles)) {
					addRoleToAUser();
				}
				break;
			case 6:
				if (helper("UserAdmin", currentUserRoles)) {
					deleteUserRoles();
				}
				break;
			case 7:
				if (helper("MovieAdmin", currentUserRoles)) {
					addMovie();
				}
				
				break;
			case 8:
				if (helper("MovieAdmin", currentUserRoles)) {
					deleteMovie();
				}
				break;
				
			case 9:
				printMovie();
				break;
			case 10:
				searchMovie();
				break;
			case 11:
				logout();
				printMainMenu();
				break;
				
			default:
				printMenu();
				break;
			}
		} while (choice != -1);
	}

	private static void login() {

		Scanner s = new Scanner(System.in);

		System.out.println("Please enter your username: ");
		String userName = s.nextLine();

		System.out.println("Please enter your password: ");
		String password = s.nextLine();
		User user = userStore.userLogin(userName, password);

		if (user != null) {
			System.out.println("Login succesfull! ");
			currentUser = user;
		} else {
			System.out.println("Username or password is incorrect. Please try again.");
			login();
		}
	}

	private static void createUser() {

		Scanner s = new Scanner(System.in);

		System.out.println("************ USER CREATION ************");

		System.out.println("Enter user name: ");
		userName = s.nextLine();

		System.out.println("Enter user password: ");
		password = s.nextLine();

		System.out.println("Enter user email: ");
		email = s.nextLine();

		userStore.addUser(userName, password, email);

		System.out.println("User added successfully! ");

	}

	private static void searchUser() {

		Scanner su = new Scanner(System.in);
		System.out.println("************ SEARCH USER ************");
		System.out.println("Please enter username you look for: ");
		String searchedUser = su.nextLine();
		User user = userStore.searchUserByName(searchedUser);
		if (user == null) {
			System.out.println("User not found");
			return;
		}
		System.out.println(user);
		return;
	}

	private static void printUser() {

		userStore.printUser();

	}

	private static void deleteUser() {

		Scanner sp = new Scanner(System.in);
		System.out.println("Please enter user name you want to delete: ");
		String toBeDeletedUserName = sp.nextLine();

		User user = userStore.searchUserByName(toBeDeletedUserName);
		if (user == null) {
			System.out.println("User not found! ");
			return;

		} else {
			userStore.deleteUser(toBeDeletedUserName);
			System.out.println("User deleted. ");

		}

	}

	private static void addRoleToAUser() {
		Scanner sp = new Scanner(System.in);
		System.out.println("Please enter user ID you want to add a role: ");
		int userIdToAddRole = sp.nextInt();
		System.out.println("Please enter the role you want to give the user: (Type UserAdmin / MovieAdmin)");
		sp.nextLine();
		String userRole = sp.nextLine();

		userStore.addRoleToUser(userIdToAddRole, userRole);
		System.out.println(userRole + " role added to the user:  " + userIdToAddRole);

	}

	private static void deleteUserRoles() {
		Scanner sp = new Scanner(System.in);
		System.out.println("Please enter ID of the user whose role you want to delete: ");
		int userIdToDeleteRole = sp.nextInt();
		System.out
				.println("Please enter the role type you want to delete from the user: (Type UserAdmin / MovieAdmin)");
		sp.nextLine();
		String userRole = sp.nextLine();

		userStore.deleteUserRole(userIdToDeleteRole, userRole);

		System.out.println(userRole + " role deleted from the user:  " + userIdToDeleteRole);

	}

	private static void logout() {

		if (!userStore.userLogout()) {
			System.out.println("Logout successful");
		}
	}

	private static void addMovie() {
		Scanner s = new Scanner(System.in);

		System.out.println("Enter movie name: ");
		movieName = s.nextLine();

		System.out.println("Enter movie director name: ");
		movieDirector = s.nextLine();

		System.out.println("Enter name of the movie stars with a comma between them ");
		movieStars = s.nextLine();

		System.out.println("Enter movie Release date: ");
		movieReleaseDate = s.nextLine();

		System.out.println("Enter movie description: ");
		description = s.nextLine();

		System.out.println("Enter movie genres with a comma between them ");
		genre = s.nextLine();

		movieStore.addMovie(movieName, movieDirector, movieReleaseDate, description, movieStars, genre);

		System.out.println("Movie added successfully! ");

	}

	private static void searchMovie() {

		Scanner su = new Scanner(System.in);
		System.out.println("************ SEARCH MOVIE ************");
		System.out.println("Please enter movie name you look for: ");
		String searchedMovie = su.nextLine();
		Movie movie = movieStore.searchMovieByName(searchedMovie);
		if (movie == null) {
			System.out.println("Movie not found");
			return;
		}
		System.out.println(movie);
		return;

	}

	private static void printMovie() {
		movieStore.printMovie();
	}

	private static void deleteMovie() {
		Scanner sp = new Scanner(System.in);
		System.out.println("Please enter movie name you want to delete: ");
		String toBeDeletedMovieName = sp.nextLine();

		Movie movie = movieStore.searchMovieByName(toBeDeletedMovieName);
		if (movie == null) {
			System.out.println("Movie not found! ");
			return;

		} else {
			movieStore.deleteMovie(toBeDeletedMovieName);
			System.out.println("Movie deleted. ");
		}
	}

	private static boolean helper(String userRole, String[] arr) {
		boolean isAdmin=false;
		String currentUserRoles[] = userStore.getUserRoles(currentUser.getId());
		for (String s : currentUserRoles) {
			if (s != null && s.equals(userRole)) {
				isAdmin=true;
			}
		}
		return isAdmin;
	}

}
