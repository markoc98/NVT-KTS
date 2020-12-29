package com.nwt_kts_project.CulturalOfferings.service;


import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingServiceIntegrationTest {

    @Autowired
    private CulturalOfferingService cultOffService;

    @Test
    public void testFindAllCultOff() {
        List<CulturalOffering> found = cultOffService.findAll();
        assertEquals(DB_CULTOFF_COUNT, found.size());
    }

    @Test
    public void testFindCultOffById() {
        CulturalOffering cultOff = cultOffService.findOne(DB_CULTOFF_ID);
        assertEquals(DB_CULTOFF_ID, found.getId());
    }

    @Test
    public void testCreateCultOff() {
        CulturalOffering cultOff = new CulturalOffering(NEW_CULTOFF_LOCATION, NEW_CULTOFF_NAME, NEW_CULTOFF_DESCRIPTION, NEW_CULTOFF_RATING);
        CulturalOffering created = cultOffService.create(cultOff);
        assertEquals(NEW_CULTOFF_NAME, created.getName());
    }

    @Test
    public void testUpdateCultOff() {
        CulturalOffering cultOff = new CulturalOffering(NEW_CULTOFF_LOCATION, NEW_CULTOFF_NAME, UPDATE_CULTOFF_DESCRIPTION, NEW_CULTOFF_RATING);
        CulturalOffering updated = cultOffService.update(cultOff, DB_CULTOFF_ID);

        assertEquals(UPDATE_CULTOFF_DESCRIPTION, updated.getDescription());
    }

    @Test
    public void testDeleteCultOff() {
        cultOffService.delete(DB_DEL_CULTOFF_ID);

        CulturalOffering savedCultOff = new CulturalOffering(DB_DEL_CULTOFF_LOCATION, DB_DEL_CULTOFF_NAME, DB_DEL_CULTOFF_DESCRIPTION, DB_DEL_CULTOFF_RATING);
        savedCultOff.setId(DB_DEL_CULTOFF_ID);
    }


}
