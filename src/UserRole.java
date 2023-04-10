
public class UserRole {

	private int userId;
	private final String userRole;

	public UserRole(int userId, String userRole) {
		this.userId = userId;
		this.userRole = userRole;
	}


	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}
	

}
