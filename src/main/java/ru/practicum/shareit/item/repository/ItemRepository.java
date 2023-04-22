package ru.practicum.shareit.item.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

  Optional<Item> findItemById(Long id);

  Optional<Item> findItemByIdAndOwner(Long id, User owner);

  List<Item> findItemsByOwner(User owner);

  List<Item> findItemsByAvailable(boolean available);
}
