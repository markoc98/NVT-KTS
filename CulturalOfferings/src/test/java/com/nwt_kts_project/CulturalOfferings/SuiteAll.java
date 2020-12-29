package com.nwt_kts_project.CulturalOfferings;

import com.nwt_kts_project.CulturalOfferings.controller.*;
import com.nwt_kts_project.CulturalOfferings.repository.*;
import com.nwt_kts_project.CulturalOfferings.service.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

@RunWith(Suite.class)
@SuiteClasses({//ovde idu sve ..Test klase
		UserRepositoryIntegrationTest.class, UserServiceIntegrationTest.class, UserControllerIntegrationTest.class,
		CategoryServiceIntegrationTest.class,CategoryControllerIntegrationTest.class,CategoryRepositoryIntegrationTest.class,
		CategoryTypeServiceIntegrationTest.class, CategoryTypeRepositoryIntegrationTest.class, CategoryTypeControllerIntegrationTest.class,
		NewsletterControllerIntegrationTest.class, NewsletterRepositoryIntegrationTest.class, NewsletterServiceIntegrationTest.class,
		ReviewServiceIntegrationTest.class,ReviewControllerIntegrationTest.class,  ReviewRepositoryIntegrationTest.class
	})
@TestPropertySource("classpath:test.properties")
class SuiteAll {


}
