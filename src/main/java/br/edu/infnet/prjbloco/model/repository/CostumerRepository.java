package br.edu.infnet.prjbloco.model.repository;

import br.edu.infnet.prjbloco.model.domain.Costumer;
import br.edu.infnet.prjbloco.model.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CostumerRepository {
    private static Integer id = 1;
    private static Map <Integer, Costumer> mapCostumer = new HashMap <>();

    public static boolean create ( Costumer costumer){
        costumer.setId( id++ );
        try {
            mapCostumer.put( costumer.getId(), costumer );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Costumer delete(Integer id){
         return mapCostumer.remove( id );
    }

    public static List<Costumer> getViewList(){
        return mapCostumer
                .values()
                .stream()
                .collect(Collectors.toList());

    }
}
