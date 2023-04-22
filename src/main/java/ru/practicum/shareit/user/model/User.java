package ru.practicum.shareit.user.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.utils.literal.JpaMappingDetails;

@Entity
@Getter
@Setter
@Table(name = JpaMappingDetails.USERS_TABLE)
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = JpaMappingDetails.ID, nullable = false)
  private Long id;

  @Column(name = JpaMappingDetails.NAME)
  private String name;

  @Column(name = JpaMappingDetails.EMAIL)
  private String email;

  @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = JpaMappingDetails.OWNER)
  private List<Item> items;

  @OneToOne(mappedBy = JpaMappingDetails.BOOKER)
  private Booking booking;

  @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = JpaMappingDetails.REQUESTOR)
  private List<ItemRequest> requests;

  public User(Long userId, String name, String email) {
    User user = new User();
    user.setId(userId);
    user.setName(name);
    user.setEmail(email);
  }
}
