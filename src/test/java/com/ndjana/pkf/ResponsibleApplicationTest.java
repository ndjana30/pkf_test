package com.ndjana.pkf;

import com.ndjana.pkf.applications.ResponsibleApplication;
import com.ndjana.pkf.models.Responsible;
import com.ndjana.pkf.repositories.ResponsibleRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResponsibleApplicationTest {

    @Mock
    private ResponsibleRepo responsibleRepo;

    @InjectMocks
    private ResponsibleApplication responsibleApplication;

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
    void createResponsible_Success_ReturnsOkResponse() {
        // Arrange
        // We tell the mock repo to return a dummy object (or null, as save just needs to not throw an error)
        when(responsibleRepo.save(any(Responsible.class))).thenReturn(new Responsible());

        // Act
        ResponseEntity<String> response = responsibleApplication.createResponsible(email, telephone, name, surname);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Responsible created", response.getBody());

        // Verify that the repository's save method was actually called once
        verify(responsibleRepo, times(1)).save(any(Responsible.class));
    }

    @Test
    void createResponsible_ExceptionThrown_ReturnsBadRequestResponse() {
        // Arrange
        String errorMessage = "Database connection failed";
        when(responsibleRepo.save(any(Responsible.class))).thenThrow(new RuntimeException(errorMessage));

        // Act
        ResponseEntity<String> response = responsibleApplication.createResponsible(email, telephone, name, surname);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());

        // Verify that save was called despite the failure
        verify(responsibleRepo, times(1)).save(any(Responsible.class));
    }
}