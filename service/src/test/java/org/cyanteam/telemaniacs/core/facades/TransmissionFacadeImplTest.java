package org.cyanteam.telemaniacs.core.facades;

import javax.inject.Inject;

import org.cyanteam.telemaniacs.core.dto.TransmissionCreateDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dto.TransmissionDTO;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.helpers.TransmissionBuilder;
import org.cyanteam.telemaniacs.core.services.DozerObjectMapperServiceImpl;
import org.cyanteam.telemaniacs.core.services.ObjectMapperService;
import org.cyanteam.telemaniacs.core.services.TransmissionService;
import org.cyanteam.telemaniacs.core.utils.TvManagerDataAccessException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Simona Tinkova
 * Refactored by Miroslav Kubus
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TransmissionFacadeImplTest {
    
    @Inject
    @Spy
    private final ObjectMapperService mapper = new DozerObjectMapperServiceImpl();

    @Mock
    private TransmissionService transmissionService;
    
    @InjectMocks
    private final TransmissionFacadeImpl transmissionFacade = new TransmissionFacadeImpl();

    private Transmission childrenTransmission;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        childrenTransmission = TransmissionBuilder
                .sampleIceAgeBuilder()
                .id(1L)
                .build();        
        
        when(transmissionService.findByName("non-existing"))
                .thenThrow(new TvManagerDataAccessException());
    }

    @Test
    public void shouldCreateTransmission() throws Exception {
        TransmissionCreateDTO transmissionDTO = new TransmissionCreateDTO();
        transmissionDTO.setName("Create transmission");
        
        transmissionFacade.create(transmissionDTO);
        verify(transmissionService).create(any(Transmission.class));
    }

    @Test
    public void shouldFindByTransmissionId() throws Exception {
        transmissionFacade.findById(1L);
        verify(transmissionService, atLeastOnce()).findById(1L);
        
    }

    @Test
    public void shouldFindByTransmissionName() throws Exception {
        transmissionFacade.findByName("Football");
        verify(transmissionService, atLeastOnce()).findByName("Football");
    }

    @Test(expected = DataAccessException.class)
    public void findByTransmissionNameNonExisting() {
        transmissionFacade.findByName("non-existing");
    }

    @Test
    public void shouldFindAllTransmissions() throws Exception {
        transmissionFacade.findAll();
        verify(transmissionService, atLeastOnce()).findAll();
    }

    @Test
    public void shouldUpdateTransmission() throws Exception {
        TransmissionDTO transmissionDTO = new TransmissionDTO();
        transmissionDTO.setName("Update name");
        transmissionDTO.setId(1L);

        transmissionFacade.update(transmissionDTO);
        verify(transmissionService).update(any(Transmission.class));
    }

    @Test
    public void shouldDeleteTransmission() throws Exception {
        TransmissionDTO transmissionDTO = new TransmissionDTO();
        transmissionDTO.setId(1L);
        
        transmissionFacade.remove(transmissionDTO);
        verify(transmissionService).remove(any(Transmission.class));
    }
}
