package school.n1problem.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.n1problem.db.ClientRepository;
import school.n1problem.dto.ClientDto2;
import school.n1problem.mapper.ClientMapper;
import school.n1problem.service.ClientService2;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientController2 {

    private final ClientService2 clientService;
    private final ClientRepository repository;

    @GetMapping("/test-not-supported")
    public ResponseEntity<String> testNotSupported() {
        try {
            clientService.outerWithRollbackTest();
            return ResponseEntity.ok("Not A, Not B");
        } catch (RuntimeException ex) {
            return ResponseEntity.ok("roll-back");
        }
    }

    @GetMapping("/clients")
    public List<ClientDto2> getAllClients() {
        return repository.findAll()
                .stream()
                .map(ClientMapper::mapClientToDto2)
                .collect(Collectors.toList());
    }
}
