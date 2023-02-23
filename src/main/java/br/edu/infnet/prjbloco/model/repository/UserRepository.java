package br.edu.infnet.prjbloco.model.repository;

import br.edu.infnet.prjbloco.model.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private static Integer id = 1;
    private static Map <Integer, User> mapUser = new HashMap <>();

    public static boolean create (User user){

        try {
            mapUser.put( id++, user );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void delete(Integer id){
        mapUser.remove( id );
    }

    public static List<User> getViewList(){
        return (List <User>) mapUser.values();

    }
}
