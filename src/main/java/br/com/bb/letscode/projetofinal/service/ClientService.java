package br.com.bb.letscode.projetofinal.service;

import br.com.bb.letscode.projetofinal.dto.ClientDto;
import br.com.bb.letscode.projetofinal.form.ClientForm;
import br.com.bb.letscode.projetofinal.model.Category;
import br.com.bb.letscode.projetofinal.model.Client;
import br.com.bb.letscode.projetofinal.repository.CategoryRepository;
import br.com.bb.letscode.projetofinal.repository.ClientRepository;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClientService {

    @Inject
    ClientRepository clientRepository;
    @Inject
    CategoryRepository categoryRepository;

    public List<ClientDto> listClients() {
        ModelMapper modelMapper = new ModelMapper();
        List<ClientDto> listClient = clientRepository.streamAll()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .collect(Collectors.toList());

        return listClient;
    }


    public ClientDto getClient(long id) {
        try {
            Client client = clientRepository.findById(id);
            ModelMapper modelMapper = new ModelMapper();
            ClientDto clientDto = modelMapper.map(client, ClientDto.class);
            return clientDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public ClientForm createClient(@Valid ClientForm clientForm)  {
        validateClient(clientForm);
        Category category = categoryRepository.findById(clientForm.getCategoryCode());

        Client client = new Client();

        client.setName(clientForm.getName());
        client.setAge(clientForm.getAge());
        client.setVat(clientForm.getVat());
        client.setEmail(clientForm.getEmail());
        client.setCategory(category);

        try {
            clientRepository.persist(client);
            return clientForm;
        } catch (Exception e) {
            throw new RuntimeException( e.getMessage());
        }
    }

    @Transactional
    public Client updateClient(long id, @Valid ClientForm clientForm) {
        validateClient(clientForm);

        Client client = clientRepository.findById(id);
        Category category = categoryRepository.findById(clientForm.getCategoryCode());

        if (client == null) {
            throw new NotFoundException();
        }

        client.setName(clientForm.getName());
        client.setAge(clientForm.getAge());
        client.setVat(clientForm.getVat());
        client.setEmail(clientForm.getEmail());
        client.setCategory(category);

        return client;

    }

    @Transactional
    public void deletClient(long id) {
        Client client = clientRepository.findById(id);
        if (client == null) {
            throw new NotFoundException();
        }
        clientRepository.delete(client);
    }

    public void validateClient(ClientForm clientForm){
        final int MINIMUM_NAME_SIZE = 5;
        final int MAXIMUM_NAME_SIZE = 100;
        final int MINIMUM_CLIENT_AGE = 18;
        final Pattern VAT_PATTERN = Pattern.compile("^[A-Z]{2}\\d{9}$");
        final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        
        if (clientForm.getName().length() < MINIMUM_NAME_SIZE || clientForm.getName().length() > MAXIMUM_NAME_SIZE){
            throw new RuntimeException(String.format("O nome precisa ter entre %d e %d caracteres.",
                    MINIMUM_NAME_SIZE, MAXIMUM_NAME_SIZE ));
        }

        if (clientForm.getAge() < MINIMUM_CLIENT_AGE ){
            throw new RuntimeException(String.format("O cliente precisa ser maior que %d anos.",
                    MINIMUM_CLIENT_AGE));
        }

        if (!VAT_PATTERN.matcher(clientForm.getVat()).matches()){
            throw new RuntimeException(String.format("Formato do Vat Number esta incorreto."));
        }

        if (!EMAIL_PATTERN.matcher(clientForm.getEmail()).matches()){
            throw new RuntimeException(String.format("Formato do Email esta incorreto."));
        }

    }


}
