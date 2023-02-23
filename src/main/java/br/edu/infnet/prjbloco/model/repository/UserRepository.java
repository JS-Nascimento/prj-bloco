package br.edu.infnet.prjbloco.model.repository;

import br.edu.infnet.prjbloco.model.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static List<User> list = new ArrayList <User>();

    public static boolean create (User user){
        try {
            list.add( user );
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static List<User> getViewList(){
        return list;
    }
}
