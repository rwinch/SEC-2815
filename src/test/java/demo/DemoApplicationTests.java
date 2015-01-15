package demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import demo.entities.DataCenter;
import demo.repositories.DataCenterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {

    @Autowired
    DataCenterRepository dataCenterRepository;

    @Test
    public void thatAllDataCentersReturned() {
        final Iterable< DataCenter > centers = dataCenterRepository.findAll();
        assertTrue(centers.iterator( ).hasNext( ) );
    }
}
