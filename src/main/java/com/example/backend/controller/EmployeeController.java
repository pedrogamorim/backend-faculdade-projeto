package com.example.backend.controller;

import com.example.backend.model.Employee;
import com.example.backend.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> all() {
        return service.findAll();
    }

    @GetMapping("/{registro}")
    public ResponseEntity<Employee> get(@PathVariable String registro) {
        return service.findByRegistro(registro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee emp) {
        Employee created = service.create(emp);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{registro}")
    public ResponseEntity<Employee> update(@PathVariable String registro, @RequestBody Employee emp) {
        return service.update(registro, emp)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{registro}")
    public ResponseEntity<Void> delete(@PathVariable String registro) {
        boolean removed = service.delete(registro);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
