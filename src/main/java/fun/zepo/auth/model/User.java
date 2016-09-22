package fun.zepo.auth.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Koustav <br>
 *         Year: {2016
 *         <hr>
 *         <strong>Our User Object</strong>
 */
@Entity
@Table(name = "user")
public class User
{

	private String	email;
	private String	password;

	private String	company;
	private String	name;

	@Id
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
