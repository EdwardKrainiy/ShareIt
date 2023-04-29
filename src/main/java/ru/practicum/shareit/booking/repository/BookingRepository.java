package ru.practicum.shareit.booking.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.user.model.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

  Optional<Booking> findBookingById(Long bookingId);

  @Query("SELECT b FROM Booking b JOIN FETCH b.item i WHERE b.id = :bookingId AND (i.owner.id = :ownerId OR b.booker.id = :bookerId)")
  Optional<Booking> findBookingByIdAndItemOwnerIdOrBookerId(@Param("bookingId") Long bookingId,
      @Param("ownerId") Long ownerId, @Param("bookerId") Long bookerId);

  List<Booking> findBookingsByBooker(User booker);

  List<Booking> findBookingsByBooker_IdAndItem_Id(Long bookerId, Long itemId);

  List<Booking> findBookingsByItem_Id(Long itemId);

  @Query("SELECT b FROM Booking b JOIN b.item i JOIN i.owner o WHERE o.id = :userId")
  List<Booking> findAllBookingsForCurrentUser(@Param("userId") Long userId);
}
