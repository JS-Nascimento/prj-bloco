package br.edu.infnet.prjbloco.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@NoArgsConstructor
@AllArgsConstructor
@Data
@SessionAttributes("user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String senha;
    @Column(name = "email", unique = true)
    private String email;
    private String secretKey;
    @Column(name = "nivel_acesso")
    private Integer nivelAcesso;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Costumer> costumerList;


    public User(String email, String senha) {
        this.setEmail(email);
        this.setSenha(senha);
    }

    public User(String nome, String email, String senha, Integer nivelAcesso) {
        this(email, senha);
        this.setNome(nome);
        this.setNivelAcesso(nivelAcesso);
    }


}
