
package com.example.backend.service;

import java.util.*;

public class AreaMapping {
    private static final Map<String,Map<String,List<String>>> MAP = new HashMap<>();
    static{
        MAP.put("Administrativo", Map.of("epis",List.of("Máscara simples","Protetor auricular"),"leis",List.of("NR-17")));
        MAP.put("Produção", Map.of("epis",List.of("Capacete","Luvas","Óculos de proteção","Botas"),"leis",List.of("NR-6","NR-12")));
        MAP.put("Construção", Map.of("epis",List.of("Capacete","Colete refletivo","Botas biqueira"),"leis",List.of("NR-6","NR-18")));
        MAP.put("Saúde", Map.of("epis",List.of("Luvas descartáveis","Máscara N95","Avental"),"leis",List.of("NR-32")));
        MAP.put("Laboratório", Map.of("epis",List.of("Óculos de proteção","Avental resistente","Luvas químicas"),"leis",List.of("NR-6")));
        MAP.put("Serviços Gerais", Map.of("epis",List.of("Luvas","Calçado fechado"),"leis",List.of("NR-6")));
    }
    public static List<String> getEpis(String area){ return MAP.containsKey(area)?MAP.get(area).get("epis"):List.of();}
    public static List<String> getLeis(String area){ return MAP.containsKey(area)?MAP.get(area).get("leis"):List.of();}
}
