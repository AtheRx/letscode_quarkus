package br.com.bb.letscode.projetofinal.form;

import br.com.bb.letscode.projetofinal.model.Category;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.validation.constraints.*;

@RegisterForReflection
public class ClientForm {

    @NotEmpty
    @Size(min = 5, max = 100, message = "o nome deve ter no minimo {min} e no maximo {max} caracteres.")
    private String name;
    @NotEmpty
    @Min(value = 18, message = "O usuario precisa ter no minimo 18 anos")
    private int age;
    @NotEmpty
    @Pattern(regexp = "^[A-Z]{2}\\d{9}$")
    private String vat;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private long categoryCode;

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

    public long getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(long categoryCode) {
        this.categoryCode = categoryCode;
    }
}
