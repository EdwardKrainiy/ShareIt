package ru.practicum.shareit.item.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.utils.literal.JpaMappingDetails;

@Entity
@Getter
@Setter
@Table(name = JpaMappingDetails.ITEMS_TABLE)
@NoArgsConstructor
@AllArgsConstructor
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = JpaMappingDetails.ID, nullable = false)
  private Long id;

  @Column(name = JpaMappingDetails.NAME)
  private String name;

  @Column(name = JpaMappingDetails.DESCRIPTION)
  private String description;

  @Column(name = JpaMappingDetails.AVAILABLE)
  private Boolean available;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = JpaMappingDetails.OWNER_ID)
  private User owner;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = JpaMappingDetails.REQUEST_ID)
  private ItemRequest request;

  @OneToOne(mappedBy = JpaMappingDetails.ITEM)
  private Booking booking;
}
