package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import business.AccountBusiness;
import model.entities.User;

@ManagedBean(name = "createUserBean", eager = true)
@ViewScoped
public class CreateUserBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String role;
	private String name;
	private String crEmail;
	private String crPassword;
	private String repeatCrPassword;
	
	@PostConstruct
	public void construct() {
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		if (user != null)
			setName(user.getName());
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCrEmail() {
		return crEmail;
	}

	public void setCrEmail(String crEmail) {
		this.crEmail = crEmail;
	}

	public String getCrPassword() {
		return crPassword;
	}

	public void setCrPassword(String crPassword) {
		this.crPassword = crPassword;
	}

	public String getRepeatCrPassword() {
		return repeatCrPassword;
	}

	public void setRepeatCrPassword(String repeatCrPassword) {
		this.repeatCrPassword = repeatCrPassword;
	}

	public String createUser() {

		if (this.name == "" || this.crEmail == "" || this.crPassword == "" || this.role == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Must fill all data."));
			PrimeFaces.current().executeScript("PF('crUser').show()");
			return "Must fill all data";
		} else if (!this.crPassword.equals(repeatCrPassword)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Passwords must match."));
			PrimeFaces.current().executeScript("PF('crUser').show()");
			return "Passwords must match.";
		}

		AccountBusiness account = new AccountBusiness();
		User user = account.findUserByEmail(crEmail);
		if (user == null) {
			account.addNewUser(name, crEmail, crPassword, role);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "User created.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			return "Created";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Email already exists."));
			PrimeFaces.current().executeScript("PF('crUser').show()");
			return "Email already exists";
		}
	}
}
