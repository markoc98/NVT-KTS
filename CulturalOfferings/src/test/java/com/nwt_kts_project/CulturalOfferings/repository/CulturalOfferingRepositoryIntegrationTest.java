package com.nwt_kts_project.CulturalOfferings.repository;

import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import static com.nwt_kts_project.CulturalOfferings.constants.CulturalOfferingConstants.DB_CULTOFF_NAME;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingRepositoryIntegrationTest {

    @Autowired
    private CulturalOfferingRepository cultOffRepository;

    @Test
    public void testFindByName()
    {
        CulturalOffering found = cultOffRepository.findByName(DB_CULTOFF_NAME);
        assertEquals(DB_CULTOFF_NAME, found.getName());
    }
}
