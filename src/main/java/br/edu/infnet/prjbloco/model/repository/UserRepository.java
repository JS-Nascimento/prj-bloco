package br.edu.infnet.prjbloco.model.repository;

import br.edu.infnet.prjbloco.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

   /* private static Map <Integer, User> mapUser = new HashMap <>();

    public static boolean create (User user){
        user.setId( id++ );
        try {
            mapUser.put( user.getId(), user );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static User delete(Integer id){
         return mapUser.remove( id );
    }

    public static List<User> getViewList(){
        return mapUser
                .values()
                .stream()
                .collect(Collectors.toList());

    }*/
}
