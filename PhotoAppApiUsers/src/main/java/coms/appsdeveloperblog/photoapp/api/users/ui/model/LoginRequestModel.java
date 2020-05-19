package coms.appsdeveloperblog.photoapp.api.users.ui.model;

public class LoginRequestModel {
	public String getemail() {
		return email;
	}
	public void setemail(String Email) {
		this.email = Email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String email;
	private String password;

}
