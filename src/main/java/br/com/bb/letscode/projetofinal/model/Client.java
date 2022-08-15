package br.com.bb.letscode.projetofinal.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 100, message = "o nome deve ter no minimo {min} e no maximo {max} caracteres.")
    private String name;

    @NotNull
    @Min(value = 18, message = "O usuario precisa ter no minimo {value} anos")
    private int age;

    @NotNull
    @Pattern(regexp = "^[A-Z]{2}\\d{9}$")
    private String vat;

    @NotNull
    @Email
    private String email;

    @NotNull
    @ManyToOne
    private Category category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoria) {
        this.category = categoria;
    }

}