
package com.example.backend.service;

import com.example.backend.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Paths;
import java.util.*;

@Service
public class EmployeeService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File dataFile = Paths.get(System.getProperty("user.dir"),"data","employees.json").toFile();
    private List<Employee> employees=new ArrayList<>();

    @PostConstruct
    public void init(){
        try{
            dataFile.getParentFile().mkdirs();
            if(!dataFile.exists()){
                employees = List.of(
                    new Employee("Pedro Gabriel Jeronimo Amorim","759058","Administrativo",AreaMapping.getEpis("Administrativo"),AreaMapping.getLeis("Administrativo")),
                    new Employee("Guilherme Rodrigues Sulkovski","760547","Produção",AreaMapping.getEpis("Produção"),AreaMapping.getLeis("Produção")),
                    new Employee("Olivia Santos Satyro","758091","Laboratório",AreaMapping.getEpis("Laboratório"),AreaMapping.getLeis("Laboratório")),
                    new Employee("Bianca Lopes da Silva","760566","Saúde",AreaMapping.getEpis("Saúde"),AreaMapping.getLeis("Saúde")),
                    new Employee("Henrique Vieira de Souza","759939","Construção",AreaMapping.getEpis("Construção"),AreaMapping.getLeis("Construção")),
                    new Employee("Leonardo José dos Santos","760326","Produção",AreaMapping.getEpis("Produção"),AreaMapping.getLeis("Produção")),
                    new Employee("José Willian de Camargo Souza","759376","Serviços Gerais",AreaMapping.getEpis("Serviços Gerais"),AreaMapping.getLeis("Serviços Gerais"))
                );
                save();
            }else{
                employees=mapper.readValue(dataFile,new TypeReference<List<Employee>>(){});
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public List<Employee> findAll(){return employees;}
    public Optional<Employee> findByRegistro(String registro){return employees.stream().filter(e->e.getRegistro().equals(registro)).findFirst();}
    public Employee create(Employee emp){
        if(emp.getArea()!=null){emp.setEpis(AreaMapping.getEpis(emp.getArea()));emp.setLeis(AreaMapping.getLeis(emp.getArea()));}
        employees.add(emp);save();return emp;
    }
    public Optional<Employee> update(String registro,Employee updated){
        return findByRegistro(registro).map(orig->{orig.setNome(updated.getNome());orig.setRegistro(updated.getRegistro());
            if(updated.getArea()!=null){orig.setArea(updated.getArea());orig.setEpis(AreaMapping.getEpis(updated.getArea()));orig.setLeis(AreaMapping.getLeis(updated.getArea()));}
            save();return orig;});
    }
    public boolean delete(String registro){boolean r=employees.removeIf(e->e.getRegistro().equals(registro));if(r)save();return r;}
    private void save(){try{mapper.writerWithDefaultPrettyPrinter().writeValue(dataFile,employees);}catch(Exception e){e.printStackTrace();}}
}
