package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

class Test04MultipleThenReturnCalls {

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

		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

		
	}
	
	@Test
	public void should_CountAvailablePlaces_When_CalledMultipleTimes(){
		
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1", 5)));
		int expected = 5;
		
		// when
		int actual = bookingService.getAvailablePlaceCount();
		
		// then
		assertEquals(expected, actual);
		}
	



}
