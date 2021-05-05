package beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import business.AccountBusiness;
import model.entities.User;

@ManagedBean(name = "userBean", eager = true)
@SessionScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String role;

	private User user;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public void processLogin() {
		AccountBusiness ab = new AccountBusiness();
		User user = ab.findUserByEmailAndPassword(email, password);

		try {
			if (user == null) {
				FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Wrong email or password"));
			} else {
				this.user = user;
				this.role = user.getUserRole().getName();
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", user);
				FacesContext.getCurrentInstance().getExternalContext().redirect("mainPage.xhtml");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processLogout() {
		try {
			this.user = null;
			this.email = "";
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", null);
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changePage(String page) {
		try {

			switch (page) {
			case "mainPage":
				FacesContext.getCurrentInstance().getExternalContext().redirect("mainPage.xhtml");
				break;
			case "historyPage":
				FacesContext.getCurrentInstance().getExternalContext().redirect("historyPage.xhtml");
				break;
			case "moneyDepositPage":
				FacesContext.getCurrentInstance().getExternalContext().redirect("moneyDepositPage.xhtml");
				break;
			case "configurationsPage":
				FacesContext.getCurrentInstance().getExternalContext().redirect("configurationsPage.xhtml");
				break;
			case "genreList":
				FacesContext.getCurrentInstance().getExternalContext().redirect("genreList.xhtml");
				break;
			case "locationList":
				FacesContext.getCurrentInstance().getExternalContext().redirect("locationList.xhtml");
				break;
			case "languageList":
				FacesContext.getCurrentInstance().getExternalContext().redirect("languageList.xhtml");
				break;
			case "movieTheaterList":
				FacesContext.getCurrentInstance().getExternalContext().redirect("movieTheaterList.xhtml");
				break;
			case "movieList":
				FacesContext.getCurrentInstance().getExternalContext().redirect("movieList.xhtml");
				break;
			case "roomList":
				FacesContext.getCurrentInstance().getExternalContext().redirect("roomList.xhtml");
				break;
			case "discountList":
				FacesContext.getCurrentInstance().getExternalContext().redirect("discountList.xhtml");
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
