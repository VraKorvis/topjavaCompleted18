package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.JpaUtil;

public abstract class AbstractJpaUserServiceTest extends AbstractUserServiceTest{

    @Autowired
    JpaUtil jpaUtil;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
