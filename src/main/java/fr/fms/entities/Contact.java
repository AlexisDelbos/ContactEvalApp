package fr.fms.entities;


import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_Contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    String lastName;
    String firstName;
    String email;
    String phone;
    String address;
    @ManyToOne
    private TypeContact typeContact;
}
