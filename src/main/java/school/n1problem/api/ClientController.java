package school.n1problem.api;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import school.n1problem.service.ClientService;
import school.n1problem.service.ClientService2;
import school.n1problem.dto.ClientDto;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/clients/")
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;
    private final ClientService2 clientService2;

    @PostMapping("/save")
    public void create(@RequestBody ClientDto dto) {
        clientService.save(dto);
    }

    @GetMapping("/get1")
    public void getClient() {
        clientService2.method1();
    }

    @GetMapping("/all")
    public List<ClientDto> getAllClients() {
        return clientService.findAllClients();
    }

    @PutMapping("/update/{id}")
    public ClientDto update(@PathVariable Long id, @RequestBody ClientDto dto) {
        return clientService.update(id, dto);
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable Long id) {
        clientService.remove(id);
    }

    @PostMapping("/test-orphan/{clientId}")
    public void testOrphan(@PathVariable Long clientId) {
        clientService.testOrphanRemoval(clientId);
    }

    @DeleteMapping("/test-cascade/{id}")
    public void testCascade(@PathVariable Long id) {
        clientService.testCascadeRemove(id);
    }
}

