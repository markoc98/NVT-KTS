package com.nwt_kts_project.CulturalOfferings.service;


import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.nwt_kts_project.CulturalOfferings.constants.CulturalOfferingConstants.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingServiceIntegrationTest {

    @Autowired
    private CulturalOfferingService cultOffService;

    @Test
    public void testFindAllCultOff() throws Exception{
        List<CulturalOffering> found = cultOffService.findAll();
        assertEquals(SERVICE_FIND_ALL_COUNT, found.size());
    }

    @Test
    public void testFindCultOffById() throws Exception{
        CulturalOffering cultOff = cultOffService.findOne(SERVICE_CULTOFF_ID_FINDONE);
        assertEquals(SERVICE_CULTOFF_ID_FINDONE, cultOff.getId());
    }

    @Test
    public void testCreateCultOff() throws Exception{
        CulturalOffering cultOff = new CulturalOffering(SERVICE_NEW_CULTOFF_LOCATION, SERVICE_NEW_CULTOFF_NAME1, SERVICE_NEW_CULTOFF_DESCRIPTION, SERVICE_NEW_CULTOFF_RATING);
        CulturalOffering created = cultOffService.create(cultOff);
        assertEquals(SERVICE_NEW_CULTOFF_NAME1, created.getName());
    }

//    @Test
//    public void testUpdateCultOff() throws Exception{
//        CulturalOffering cultOff = new CulturalOffering(SERVICE_NEW_CULTOFF_LOCATION, SERVICE_NEW_CULTOFF_NAME, SERVICE_UPDATE_CULTOFF_DESCRIPTION, SERVICE_NEW_CULTOFF_RATING);
//        CulturalOffering updated = cultOffService.update(cultOff, SERVICE_UPDATE_CULTOFF_ID);
//
//        assertEquals(SERVICE_UPDATE_CULTOFF_DESCRIPTION, updated.getDescription());
//    }

    @Test
    public void testDeleteCultOff() throws Exception{
        cultOffService.delete(SERVICE_DEL_CULTOFF_ID);
        assertEquals(SERVICE_COUNT_AFTER_DELETE, cultOffService.findAll().size());
        CulturalOffering savedCultOff = new CulturalOffering(SERVICE_NEW_CULTOFF_LOCATION, SERVICE_NEW_CULTOFF_NAME, SERVICE_UPDATE_CULTOFF_DESCRIPTION, SERVICE_NEW_CULTOFF_RATING);
        savedCultOff.setId(SERVICE_DEL_CULTOFF_ID);
        cultOffService.create(savedCultOff);
    }

}
