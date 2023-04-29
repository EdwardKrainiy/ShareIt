package ru.practicum.shareit.item.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.utils.literal.JpaMappingDetails;

@Entity
@Data
@Table(name = JpaMappingDetails.COMMENTS_TABLE)
@RequiredArgsConstructor
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = JpaMappingDetails.ID, nullable = false)
  private Long id;

  @Column(name = JpaMappingDetails.TEXT)
  private String text;

  @Column(name = JpaMappingDetails.AUTHOR_NAME)
  private String authorName;

  @Column(name = JpaMappingDetails.CREATED)
  private LocalDateTime created;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = JpaMappingDetails.ITEM_ID)
  private Item item;
}
