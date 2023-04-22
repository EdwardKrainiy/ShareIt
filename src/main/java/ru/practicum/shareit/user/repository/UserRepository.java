package ru.practicum.shareit.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserById(Long id);

  Optional<User> findUserByEmail(String email);

  boolean existsUserByEmail(String email);
}
