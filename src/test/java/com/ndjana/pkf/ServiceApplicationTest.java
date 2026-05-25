package com.ndjana.pkf;

import com.ndjana.pkf.applications.RdvApplication;
import com.ndjana.pkf.applications.ServiceApplication;
import com.ndjana.pkf.models.Client;
import com.ndjana.pkf.models.RDV;
import com.ndjana.pkf.models.Responsible;
import com.ndjana.pkf.models.SService;
import com.ndjana.pkf.repositories.ClientRepo;
import com.ndjana.pkf.repositories.RdvRepo;
import com.ndjana.pkf.repositories.ResponsibleRepo;
import com.ndjana.pkf.repositories.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceApplicationTest {

    @Mock
    private ServiceRepository sr;

    @Mock
    private ResponsibleRepo rr;

    @InjectMocks
    private ServiceApplication serviceApplication;

    private SService sampleService;
    private Responsible sampleResponsible;

    @BeforeEach
    void setUp() {
        sampleService = new SService();
        sampleService.setId(1L);
        sampleService.setName("IT Support");

        sampleResponsible = new Responsible();
        sampleResponsible.setId(2L);
    }

    // ==========================================
    // Tests for createService
    // ==========================================

    @Test
    void createService_Success() {
        // Arrange
        String serviceName = "IT Support";
        when(sr.save(any(SService.class))).thenReturn(sampleService);

        // Act
        ResponseEntity<String> response = serviceApplication.createService(serviceName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Service created", response.getBody());
        verify(sr, times(1)).save(any(SService.class));
    }

    @Test
    void createService_ExceptionThrown_ReturnsBadRequest() {
        // Arrange
        String serviceName = "IT Support";
        when(sr.save(any(SService.class))).thenThrow(new RuntimeException("Database connection error"));

        // Act
        ResponseEntity<String> response = serviceApplication.createService(serviceName);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Database connection error", response.getBody());
    }

    // ==========================================
    // Tests for AssignResponsible
    // ==========================================

    @Test
    void assignResponsible_Success() {
        // Arrange
        Long serviceId = 1L;
        Long responsibleId = 2L;

        when(sr.findById(serviceId)).thenReturn(Optional.of(sampleService));
        when(rr.findById(responsibleId)).thenReturn(Optional.of(sampleResponsible));
        when(sr.save(any(SService.class))).thenReturn(sampleService);

        // Act
        ResponseEntity<String> response = serviceApplication.AssignResponsible(serviceId, responsibleId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Responsible Assigned", response.getBody());
        assertEquals(sampleResponsible, sampleService.getResponsible()); // Verifies relationship was set
        verify(sr, times(1)).save(sampleService);
    }

    @Test
    void assignResponsible_ServiceOrResponsibleNotFound_StillReturnsOkButDoesNotSave() {
        // Note: Due to the current logic in your code, if one is missing,
        // the ifPresent block is skipped, but it still returns HttpStatus.OK.

        // Arrange
        Long serviceId = 1L;
        Long responsibleId = 99L; // Non-existent ID

        when(sr.findById(serviceId)).thenReturn(Optional.of(sampleService));
        when(rr.findById(responsibleId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> response = serviceApplication.AssignResponsible(serviceId, responsibleId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Responsible Assigned", response.getBody());
        verify(sr, never()).save(any(SService.class)); // Verifies save was never called
    }

    @Test
    void assignResponsible_ExceptionThrown_ReturnsBadRequest() {
        // Arrange
        Long serviceId = 1L;
        Long responsibleId = 2L;

        when(sr.findById(serviceId)).thenThrow(new RuntimeException("Connection timeout"));

        // Act
        ResponseEntity<String> response = serviceApplication.AssignResponsible(serviceId, responsibleId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Connection timeout", response.getBody());
    }
}
