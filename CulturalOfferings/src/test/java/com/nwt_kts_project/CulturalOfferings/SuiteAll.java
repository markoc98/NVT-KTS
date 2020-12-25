package com.nwt_kts_project.CulturalOfferings;

import com.nwt_kts_project.CulturalOfferings.controller.CategoryControllerIntegrationTest;
import com.nwt_kts_project.CulturalOfferings.controller.UserController;
import com.nwt_kts_project.CulturalOfferings.controller.UserControllerIntegrationTest;
import com.nwt_kts_project.CulturalOfferings.repository.CategoryRepositoryIntegrationTest;
import com.nwt_kts_project.CulturalOfferings.repository.UserRepositoryIntegrationTest;
import com.nwt_kts_project.CulturalOfferings.service.CategoryServiceIntegrationTest;
import com.nwt_kts_project.CulturalOfferings.service.UserServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;
@RunWith(Suite.class)
@SuiteClasses({//ovde idu sve ..Test klase
		UserRepositoryIntegrationTest.class, UserServiceIntegrationTest.class, UserControllerIntegrationTest.class,
		CategoryRepositoryIntegrationTest.class, CategoryServiceIntegrationTest.class, CategoryControllerIntegrationTest.class,

	})
@TestPropertySource("classpath:test.properties")
class SuiteAll {


}
