package ru.practicum.shareit.utils.literal;

public class LogMessage {

  public static final String DEBUG_REQUEST_BODY_LOG = "Request body: {%s}";
  public static final String USER_NOT_FOUND_LOG =
      "User not found. Id of requested user: %d";
  public static final String UNKNOWN_STATE_LOG = "Unknown state: %s";
  public static final String USER_NOT_OWNER_OF_BOOKING_ITEM =
      "This user is not owner of booking item. Id of requested user: %d, id of requested item: %d, booking id: %d";
  public static final String BOOKING_ALREADY_APPROVED =
      "This booking already approved. Id of booking: %d";
  public static final String USER_CREATED_LOG =
      "User created. Id of User: %d";
  public static final String USER_UPDATED_LOG = "User was updated. Id of user: %d";
  public static final String USER_DELETED_LOG = "User was deleted. Id of user: %d";
  public static final String ITEM_NOT_FOUND_LOG =
      "Item not found. Id of requested item: %d";

  public static final String ITEM_IS_NOT_AVAILABLE_LOG =
      "Item isn't available. Id of requested item: %d";
  public static final String INCOORECT_DATES = "Incorrect dates. Start date: %s, End date: %s";
  public static final String ITEM_CREATED_LOG = "Item was created. Id of item: %d";
  public static final String BOOKING_CREATED_LOG = "Booking was created. Id of booking: %d";
  public static final String BOOKING_NOT_FOUND_LOG = "Booking not found. Id of booking: %d";
  public static final String ITEM_UPDATED_LOG = "Item was updated. Id of item: %d";
  public static final String USER_WITH_EMAIL_EXISTS_LOG = "User with this email already exists. Email: %s";

  private LogMessage() {
  }
}
