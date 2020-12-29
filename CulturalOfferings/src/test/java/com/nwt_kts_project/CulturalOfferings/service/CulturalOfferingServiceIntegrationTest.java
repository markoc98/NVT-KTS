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
        assertEquals(DB_CULTOFF_COUNT, found.size());
    }

    @Test
    public void testFindCultOffById() throws Exception{
        CulturalOffering cultOff = cultOffService.findOne(DB_CULTOFF_ID_FINDONE);
        assertEquals(DB_CULTOFF_ID_FINDONE, cultOff.getId().longValue());
    }

    @Test
    public void testCreateCultOff() throws Exception{
        CulturalOffering cultOff = new CulturalOffering(DB_NEW_CULTOFF_LOCATION, DB_NEW_CULTOFF_NAME, DB_NEW_CULTOFF_DESCRIPTION, DB_NEW_CULTOFF_RATING);
        CulturalOffering created = cultOffService.create(cultOff);
        assertEquals(DB_NEW_CULTOFF_NAME, created.getName());
    }

    @Test
    public void testUpdateCultOff() throws Exception{
        CulturalOffering cultOff = new CulturalOffering(DB_NEW_CULTOFF_LOCATION, DB_NEW_CULTOFF_NAME, DB_UPDATE_CULTOFF_DESCRIPTION, DB_NEW_CULTOFF_RATING);
        CulturalOffering updated = cultOffService.update(cultOff, DB_CULTOFF_ID);

        assertEquals(DB_UPDATE_CULTOFF_DESCRIPTION, updated.getDescription());
    }

    @Test
    public void testDeleteCultOff() throws Exception{
        cultOffService.delete(DB_DEL_CULTOFF_ID);
        assertEquals(DB_DELETE_CULTOFF_COUNT, cultOffService.findAll().size());
        CulturalOffering savedCultOff = new CulturalOffering(DB_NEW_CULTOFF_LOCATION, DB_NEW_CULTOFF_NAME, DB_UPDATE_CULTOFF_DESCRIPTION, DB_NEW_CULTOFF_RATING);
        savedCultOff.setId(DB_DEL_CULTOFF_ID);
    }


}
