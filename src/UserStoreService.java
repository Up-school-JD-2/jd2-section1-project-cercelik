
public class UserStoreService {

	private User userArr[] = new User[DomainConstants.USER_OPENING_COUNT];
	private UserRole userRoleArr[] = new UserRole[DomainConstants.USER_ROLE_OPENING_COUNT];
	private int userCount = 0, userRoleCount = 0;
	private boolean isLogin;
	private User currentUser;

	public User addUser(String userName, String password, String email) {

		if (userCount == userArr.length - 1) {
			reinitializeUserArray();
		}

		User u = new User(userName, password, email);
		userArr[userCount] = u;
		userCount++;

		return u;
	}

	public void reinitializeUserArray() {
		User[] newUserArr = new User[userArr.length + DomainConstants.USER_OPENING_COUNT];
		System.arraycopy(userArr, 0, newUserArr, 0, userArr.length);
		userArr = newUserArr;
	}

	public void printUser() {

		for (int i = 0; i < userArr.length; i++) {

			if (userArr[i] == null) {
				return;
			}
			System.out.println("#User " + userArr[i].getId() + " -> " + "*username: " + userArr[i].getUserName() + " "
					+ "*password: " + userArr[i].getPassword() + " " + "*email: " + userArr[i].getEmail());
		}
	}

	public User searchUserByName(String userName) {

		User searchedUser = null;
		for (User u : userArr) {
			if (u != null && u.getUserName().equalsIgnoreCase(userName)) {
				searchedUser = u;
				break;
			}
		}
		return searchedUser;

	}

	public void deleteUser(String userName) {

		User[] newUserArr = new User[userArr.length - 1];
		for (int i = 0, j = 0; i < userArr.length - 1; i++) {
			if (userArr[i] != null && userArr[i].getUserName().equalsIgnoreCase(userName)) {
				userLogout();
			}

			if (userArr[i] != null && !userArr[i].getUserName().equalsIgnoreCase(userName)) {
				{
					newUserArr[j] = userArr[i];
					j++;
				}
			}

		}

		userArr = newUserArr;

	}

	public User userLogin(String userName, String password) {

		for (User u : userArr) {
			if (u != null && u.getUserName().equals(userName) && u.getPassword().equals(password)) {
				isLogin = true;
				currentUser = u;
			}
		}
		return currentUser;
	}

	public boolean userLogout() {

		if (checkLogin()) {
			isLogin = false;
		}
		return isLogin;
	}

	public boolean checkLogin() {
		return isLogin;
	}

	public String addRoleToUser(int userId, String roleName) {
		UserRole ur = new UserRole(userId, roleName);
		userRoleArr[userRoleCount] = ur;
		userRoleCount++;

		return ur.getUserRole();
	}

	public void deleteUserRole(int userId, String roleName) {

		UserRole[] newUserRoleArr = new UserRole[userRoleArr.length - 1];
		for (int i = 0, j = 0; i < userRoleArr.length - 1; i++) {
			if (userRoleArr[i] != null && userRoleArr[i].getUserId() != userId) {

				newUserRoleArr[j] = userRoleArr[i];
				j++;
			}

			if (userRoleArr[i] != null && userRoleArr[i].getUserId() == userId
					&& !userRoleArr[i].getUserRole().equalsIgnoreCase(roleName)) {
				newUserRoleArr[j] = userRoleArr[i];
				j++;
			}
		}

		userRoleArr = newUserRoleArr;

	}

	public String[] getUserRoles(int userId) {
		String rolesArr[] = new String[DomainConstants.USER_ROLE_OPENING_COUNT];
		int i = 0;
		for (UserRole u : userRoleArr) {
			if (u != null && u.getUserId() == userId) {
				rolesArr[i] = u.getUserRole();
				i++;
			}
		}
		return rolesArr;
	}

}
