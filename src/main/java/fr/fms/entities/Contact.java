package fr.fms.entities;


import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "T_Contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    @Size(min = 5, max = 25)
    String lastName;
    @NotNull
    @Size(min = 5, max = 25)
    String firstName;
    @NotNull
    @Size(min = 5, max = 25)
    String email;
    @NotNull
    @Size(min = 10, max = 10)
    String phone;
    @NotNull
    @Size(min = 5, max = 25)
    String address;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private TypeContact typeContact;
}
