package ru.practicum.shareit.utils.literal;

public class ExceptionMessage {

  public static final String USER_NOT_FOUND = "User not found!";
  public static final String OWNER_OF_ITEM_NOT_FOUND = "Owner of an item not found!";
  public static final String BOOKINGS_OF_ITEM_NOT_FOUND = "Bookings of this item by this user not found!";
  public static final String UNKNOWN_STATE = "Unknown state: %s";
  public static final String USER_NOT_OWNER_OF_BOOKING_ITEM = "This user is not owner of booking item!";
  public static final String BOOKING_ALREADY_APPROVED = "This booking is already approved!";
  public static final String SHARER_ID_NOT_FOUND = "Sharer id is not found in header of query.";
  public static final String ITEM_NOT_FOUND = "Item not found!";
  public static final String ITEM_NOT_AVAILABLE = "Item is not available!";
  public static final String USER_ALREADY_EXISTS = "User with this email already exists!";
  public static final String INCORRECT_DATES = "Incorrect dates!";
  public static final String BOOKING_NOT_FOUND = "Booking not found!";
  public static final String BOOKER_IS_OWNER_OF_ITEM = "Booker is an owner of this item!";

  private ExceptionMessage() {
  }
}
