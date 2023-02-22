package br.edu.infnet.prjbloco.model.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String nome;
    private String senha;
    private String email;
    private String[] caracteristicas;
    private String tipo;
    private String setor;
    private int idade;
    private float salario;

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
                "O usuário %s tem as credenciais %s e %s, %d anos e ganha R$%.2f. "
                        + "É um usuário do tipo %s, atua no setor %s e tem %d características",
                nome,
                email,
                senha,
                idade,
                salario,
                tipo,
                setor,
                caracteristicas.length
        );
    }


}
