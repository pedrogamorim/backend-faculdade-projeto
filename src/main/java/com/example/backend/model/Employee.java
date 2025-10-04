
package com.example.backend.model;

import java.util.List;

public class Employee {
    private String nome;
    private String registro;
    private String area;
    private List<String> epis;
    private List<String> leis;

    public Employee(){}

    public Employee(String nome,String registro,String area,List<String> epis,List<String> leis){
        this.nome=nome;this.registro=registro;this.area=area;this.epis=epis;this.leis=leis;
    }

    public String getNome(){return nome;}
    public void setNome(String nome){this.nome=nome;}
    public String getRegistro(){return registro;}
    public void setRegistro(String registro){this.registro=registro;}
    public String getArea(){return area;}
    public void setArea(String area){this.area=area;}
    public List<String> getEpis(){return epis;}
    public void setEpis(List<String> epis){this.epis=epis;}
    public List<String> getLeis(){return leis;}
    public void setLeis(List<String> leis){this.leis=leis;}
}
