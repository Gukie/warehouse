package com.baijia.warehouse.dao;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gushu
 * @date 2018/03/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
        "classpath:mybatis/datasource.xml",
        "classpath:mybatis/mybatis-spring.xml"
})
@Transactional
@Rollback(value=true)
public class BaseDAOTest {

}
