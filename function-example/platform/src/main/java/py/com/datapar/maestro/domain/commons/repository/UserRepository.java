package py.com.datapar.maestro.domain.commons.repository;

import py.com.datapar.maestro.domain.commons.model.Usuario;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class UserRepository extends JpaRepository<Usuario> {

    public UserRepository() {
        super(Usuario.class);
    }

}