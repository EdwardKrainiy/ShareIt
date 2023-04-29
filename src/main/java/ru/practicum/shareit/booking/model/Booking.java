package ru.practicum.shareit.booking.model;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.utils.enums.Status;
import ru.practicum.shareit.utils.literal.JpaMappingDetails;

@Entity
@Data
@Table(name = JpaMappingDetails.BOOKING_TABLE)
@RequiredArgsConstructor
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = JpaMappingDetails.ID, nullable = false)
  private Long id;

  @Column(name = JpaMappingDetails.START_DATE)
  private LocalDateTime startDate;

  @Column(name = JpaMappingDetails.END_DATE)
  private LocalDateTime endDate;

  @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = JpaMappingDetails.ITEM_ID)
  private Item item;

  @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = JpaMappingDetails.BOOKER_ID)
  private User booker;

  @Column(name = JpaMappingDetails.STATUS)
  @Enumerated(EnumType.STRING)
  private Status status;
}
