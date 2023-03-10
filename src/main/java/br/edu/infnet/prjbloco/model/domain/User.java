package br.edu.infnet.prjbloco.model.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String senha;
    private String email;



    public User(String email, String senha) {
        this.setEmail(email);
        this.setSenha(senha);
    }

    public User(String nome, String email, String senha) {
        this(email, senha);
        this.setNome(nome);
    }

    @Override
    public String toString() {

        return String.format(
                "O usuário %s tem as credenciais %s e %s . ",
                nome,
                email,
                senha
        );
    }


}
