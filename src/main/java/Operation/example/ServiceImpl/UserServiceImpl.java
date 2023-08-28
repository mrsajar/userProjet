package Operation.example.ServiceImpl;

import Operation.example.CustomeExeption.AlreadyExistException;
import Operation.example.CustomeExeption.ResourceNotFoundException;
import Operation.example.Entity.User;
import Operation.example.Repository.UserRepository;
import Operation.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        Optional<User> findUser = userRepository.findById(user.getId());
        if (findUser.isPresent()) {
            throw new AlreadyExistException("user already exist with id"+ user.getId());
        } else {
            User userAdd = new User();
            userAdd.setId(user.getId());
            userAdd.setName(user.getName());
            userAdd.setPhone(user.getPhone());
            return userRepository.save(userAdd);
        }
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("user not found with id "+ id));
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(loop -> userList.add(loop));
        return userList;
    }

    public void deleteUser (Long id) {
        User user = userRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("user  not found with id "+ id));
        userRepository.deleteById(id);
    }

    @Override
    public User update(Long id, User user) {
        User findUser = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("user not found with id  " + id));
        findUser.setId(user.getId());
        findUser.setName(user.getName());
        findUser.setPhone(user.getPhone());
        return userRepository.save(findUser);
    }
}
