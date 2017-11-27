package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.services.mapper.DestinationObject;
import org.cyanteam.telemaniacs.core.services.mapper.SourceObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DozerObjectMapperServiceTest {
    @Inject
    private ObjectMapperService objectMapperService;

    @Test
    public void singleMapTest() {
        SourceObject sourceObject = new SourceObject("text", 123);
        DestinationObject destinationObject = objectMapperService.map(sourceObject, DestinationObject.class);
        checkMapping(sourceObject, destinationObject);
    }

    @Test
    public void listMapTest() {
        SourceObject sourceObject1 = new SourceObject("text1", 12345);
        SourceObject sourceObject2 = new SourceObject("test2", 67890);

        List<SourceObject> sourceObjects = new ArrayList<>();
        sourceObjects.add(sourceObject1);
        sourceObjects.add(sourceObject2);

        List<DestinationObject> destinationObjects = objectMapperService.map(sourceObjects, DestinationObject.class);
        assertThat(destinationObjects.size()).isEqualTo(sourceObjects.size());
        checkMapping(sourceObject1, destinationObjects.get(0));
        checkMapping(sourceObject2, destinationObjects.get(1));
    }

    private void checkMapping(SourceObject sourceObject, DestinationObject destinationObject) {
        assertThat(destinationObject.getText()).isEqualTo(sourceObject.getText());
        assertThat(destinationObject.getNumber()).isEqualTo(sourceObject.getNumber());
    }
}
