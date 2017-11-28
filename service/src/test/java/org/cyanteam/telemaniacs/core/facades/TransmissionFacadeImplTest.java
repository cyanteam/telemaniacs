package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dto.TransmissionDTO;
import org.cyanteam.telemaniacs.core.facade.TransmissionFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Simona Tinkova
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TransmissionFacadeImplTest {
    @Autowired
    @InjectMocks
    private TransmissionFacade transmissionFacade;

    private TransmissionDTO transmission1;
    private TransmissionDTO transmission2;
    private TransmissionDTO newTransmission;


    @Before
    public void createTransmissions() {
        transmission1 = getTransmissionHelper("tran1");
        transmission2 = getTransmissionHelper("tran2");
        newTransmission = getTransmissionHelper("new-transmission");

        transmissionFacade.create(transmission1);
        assertTrue(transmissionFacade.findAll().contains(transmission1));

        transmissionFacade.create(transmission2);
        assertTrue(transmissionFacade.findAll().contains(transmission2));

        transmission1 = transmissionFacade.findById(transmission1.getId());
        transmission2 = transmissionFacade.findById(transmission2.getId());
    }


    @After
    public void deleteTransmissions() {
        if (transmissionFacade.findAll().contains(transmission1)) {
            transmissionFacade.remove(transmission1);
            assertFalse(transmissionFacade.findAll().contains(transmission1));
        }

        if (transmissionFacade.findAll().contains(transmission2)) {
            transmissionFacade.remove(transmission2);
            assertFalse(transmissionFacade.findAll().contains(transmission2));
        }

        if (transmissionFacade.findAll().contains(newTransmission)) {
            transmissionFacade.remove(newTransmission);
            assertFalse(transmissionFacade.findAll().contains(newTransmission));
        }
    }

    @Test
    public void shouldCreateTransmission() throws Exception {
        transmissionFacade.create(newTransmission);
        assertEquals(transmissionFacade.findById(newTransmission.getId()), newTransmission);
        assertTrue(transmissionFacade.findAll().contains(newTransmission));
    }

    @Test
    public void shouldFindByTransmissionId() throws Exception {
        assertEquals(transmissionFacade.findById(transmission1.getId()), transmission1);
        assertEquals(transmissionFacade.findById(transmission2.getId()), transmission2);
    }

    @Test
    public void shouldFindByTransmissionName() throws Exception {
        assertEquals(transmissionFacade.findByName(transmission1.getName()), transmission1);
        assertEquals(transmissionFacade.findByName(transmission2.getName()), transmission2);
    }

    @Test(expected = DataAccessException.class)
    public void findByTransmissionNameNonExisting() {
        transmissionFacade.findByName("non-existing");
    }

    @Test
    public void shouldFindAllTransmissions() throws Exception {
        assertTrue(transmissionFacade.findAll().contains(transmission1));
        assertTrue(transmissionFacade.findAll().contains(transmission2));
        assertEquals(transmissionFacade.findAll().size(), 2);
    }

    @Test
    public void shouldUpdateTransmission() throws Exception {
        transmission1.setName("tran");
        //logger.info("Transmission id: " + transmission1.getId());
        transmissionFacade.update(transmission1);
        assertEquals(transmissionFacade.findAll().size(), 2);
        assertEquals(transmissionFacade.findById(transmission1.getId()), transmission1);
    }

    @Test
    public void shouldDeleteTransmission() throws Exception {
        assertNotNull(transmissionFacade.findById(transmission1.getId()));
        transmissionFacade.remove(transmission1);
        assertFalse(transmissionFacade.findAll().contains(transmission1));
    }


    private TransmissionDTO getTransmissionHelper(String name) {
        TransmissionDTO transmissionDTO = new TransmissionDTO();
        transmissionDTO.setName(name);
        return transmissionDTO;
    }
}
