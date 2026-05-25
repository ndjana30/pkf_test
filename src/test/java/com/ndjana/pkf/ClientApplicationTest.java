package com.ndjana.pkf;

import com.ndjana.pkf.applications.ClientApplication;
import com.ndjana.pkf.models.Client;
import com.ndjana.pkf.repositories.ClientRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientApplicationTest {

    @Mock
    private ClientRepo clientRepo;

    @InjectMocks
    private ClientApplication clientApplication;

    private String email;
    private String telephone;
    private String name;
    private String surname;

    @BeforeEach
    void setUp() {
        email = "john.doe@example.com";
        telephone = "+123456789";
        name = "John";
        surname = "Doe";
    }

    @Test
    void createClient_Success() {
        // Arrange
        // We mock the save method to return a dummy Client object when called
        Client expectedClient = new Client(email, telephone, name, surname);
        when(clientRepo.save(any(Client.class))).thenReturn(expectedClient);

        // Act
        ResponseEntity<String> response = clientApplication.createClient(email, telephone, name, surname);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client with name :" + name + " created", response.getBody());

        // Verify that the save method was explicitly called once
        verify(clientRepo, times(1)).save(any(Client.class));
    }

    @Test
    void createClient_ExceptionThrown_ReturnsBadRequest() {
        // Arrange
        String errorMessage = "Database connection failure";
        // Simulate an exception being thrown when trying to save to the database
        when(clientRepo.save(any(Client.class))).thenThrow(new RuntimeException(errorMessage));

        // Act
        ResponseEntity<String> response = clientApplication.createClient(email, telephone, name, surname);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());

        // Verify that the save method was attempted once
        verify(clientRepo, times(1)).save(any(Client.class));
    }
}