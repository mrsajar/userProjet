package Operation.example.Service;

import Operation.example.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User saveUser (User user);

    public User getUser (Long id);

    public List<User> findAll ();

    public void deleteUser(Long id);

    public User update (Long id, User user);
}
