package cl.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "PHONES")
@Data
@NoArgsConstructor
public class Phones {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String number;
    @Column
    private String citycode;
    @Column
    private String countrycode;
    
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;

}
