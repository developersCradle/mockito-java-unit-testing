package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class Test06Matchers {

  private BookingService bookingService; // Main Service to test dont mock

  private PaymentService paymentServiceMock;
  private RoomService roomServiceMock;
  private BookingDAO bookingDAOMock;
  private MailSender mailSenderMock;

  @BeforeEach
  void setup() {

    this.paymentServiceMock = mock(PaymentService.class);
    this.roomServiceMock = mock(RoomService.class);
    this.bookingDAOMock = mock(BookingDAO.class);
    this.mailSenderMock = mock(MailSender.class);

    this.bookingService =
        new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

  }

  @Test
  void shoud_NotCompleteBooking_When_PriceTooHigh() {

    // Given
    BookingRequest bookingRequest =
        new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, true);
    when(this.paymentServiceMock.pay(any(), anyDouble())).thenThrow(BusinessException.class);
    // any() any kind of input
    // When
    Executable executable = () -> bookingService.makeBooking(bookingRequest);

    // Then
    assertThrows(BusinessException.class, executable);
  }

}
