package com.ndjana.pkf;


import com.ndjana.pkf.applications.ClientApplication;
import com.ndjana.pkf.applications.RdvApplication;
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

import java.time.LocalDate;
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
class RdvApplicationTest {

    @Mock
    private ClientRepo clientRepo;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ResponsibleRepo responsibleRepo;

    @Mock
    private RdvRepo rdvRepo;

    @InjectMocks
    private RdvApplication rdvApplication;

    private LocalDate testDate;
    private LocalTime testTime;
    private String testMotif;

    @BeforeEach
    void setUp() {
        testDate = LocalDate.MIN;
        testTime = LocalTime.of(14, 30);
        testMotif = "Consultation";
    }

    @Test
    void createRdv_Success_WhenAllEntitiesExist() {
        // Arrange
        Long clientId = 1L;
        Long serviceId = 2L;
        Long responsibleId = 3L;

        Client mockClient = new Client();
        SService mockService = new SService();
        Responsible mockResponsible = new Responsible();

        when(clientRepo.findById(clientId)).thenReturn(Optional.of(mockClient));
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(mockService));
        when(responsibleRepo.findById(responsibleId)).thenReturn(Optional.of(mockResponsible));

        // Act
        ResponseEntity<String> response = rdvApplication.createRdv(
                testDate, testMotif, testTime, clientId, responsibleId, serviceId
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rendez-vous cerated", response.getBody());

        // Verify that save was actually called since all entities were present
        verify(rdvRepo, times(1)).save(any(RDV.class));
    }

    @Test
    void createRdv_SuccessResponseButNoSave_WhenClientIsMissing() {
        // Arrange
        Long clientId = 1L;
        Long serviceId = 2L;
        Long responsibleId = 3L;

        SService mockService = new SService();
        Responsible mockResponsible = new Responsible();

        // Client returns empty, others return present
        when(clientRepo.findById(clientId)).thenReturn(Optional.empty());
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(mockService));
        when(responsibleRepo.findById(responsibleId)).thenReturn(Optional.of(mockResponsible));

        // Act
        ResponseEntity<String> response = rdvApplication.createRdv(
                testDate, testMotif, testTime, clientId, responsibleId, serviceId
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rendez-vous cerated", response.getBody());

        // Verify that rdvRepo.save() was NEVER called because the flatMap chain failed
        verify(rdvRepo, never()).save(any(RDV.class));
    }

    @Test
    void createRdv_BadRequest_WhenExceptionIsThrown() {
        // Arrange
        Long clientId = 1L;
        Long serviceId = 2L;
        Long responsibleId = 3L;

        // Force an exception when looking up the client
        when(clientRepo.findById(clientId)).thenThrow(new RuntimeException("Database error"));

        // Act
        ResponseEntity<String> response = rdvApplication.createRdv(
                testDate, testMotif, testTime, clientId, responsibleId, serviceId
        );

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Database error", response.getBody());
        verify(rdvRepo, never()).save(any(RDV.class));
    }
}
