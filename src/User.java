
public class User {

	private final String userName;
	private final String password;
	private final String email;
	private int userId;
	private static int id = 1;

	public User(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.userId = id++;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return userId;
	}

	public String toString() {

		System.out.println("************ USER LIST ************");
		return "# " + this.getClass().getName() + " " + "User{" + "id=" + userId + ", name='" + userName + '\''
				+ ", password='" + password + '\'' + ", email='" + email + '\'' +

				'}';
	}

}
