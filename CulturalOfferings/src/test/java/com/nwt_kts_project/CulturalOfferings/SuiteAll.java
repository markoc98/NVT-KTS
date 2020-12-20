package com.nwt_kts_project.CulturalOfferings;

import com.nwt_kts_project.CulturalOfferings.repository.UserRepositoryIntegrationTest;
import com.nwt_kts_project.CulturalOfferings.repository.UserRepositoryUnitTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
@RunWith(Suite.class)
@SuiteClasses({//ovde idu sve ..Test klase
		UserRepositoryIntegrationTest.class, UserRepositoryUnitTest.class

	})
@TestPropertySource("classpath:test.properties")
class SuiteAll {


}
