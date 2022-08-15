package br.com.bb.letscode.projetofinal.repository;

import br.com.bb.letscode.projetofinal.model.Client;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<Client> {
}
