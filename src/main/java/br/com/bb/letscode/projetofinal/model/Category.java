package br.com.bb.letscode.projetofinal.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Category {

    @Id
    private long code;
    private String name;

    @OneToMany(mappedBy = "id")
    private List<Client> clientes;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Client> getClientes() {
        return clientes;
    }

    public void setClientes(List<Client> clientes) {
        this.clientes = clientes;
    }
}
