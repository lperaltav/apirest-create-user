package cl.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column
    private String name;

	@Column
    private String email;
	
	@Column
    private String password;
	
	@Column
    private String token;
	
	@Column
    private String created;
	
	@Column
    private String modified;
	
	@Column
    private String lastLogin;
	
	@Column
    private String isactive;
	
	
   @OneToMany(cascade = CascadeType.ALL)
   private List<Phones> phones= new ArrayList<Phones>();
    


    public User(String name, String email) {
      this.name = name;
      this.email = email;
    }

   

}