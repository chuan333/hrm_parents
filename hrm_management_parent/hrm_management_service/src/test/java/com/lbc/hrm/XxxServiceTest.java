package com.lbc.hrm;

import com.lbc.hrm.domain.Systemdictionary;
import com.lbc.hrm.domain.TenantType;
import com.lbc.hrm.service.ISystemdictionaryService;
import com.lbc.hrm.service.ITenantTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Management9001.class)
public class XxxServiceTest {
    @Autowired
    private ISystemdictionaryService systemdictionaryService;
    @Autowired
    private ITenantTypeService tenantTypeService;

    @Test
    public void test()throws Exception{
        for (Systemdictionary systemdictionary : systemdictionaryService.selectList(null)) {
            System.out.println(systemdictionary);
        }
    }
    @Test
    public void tenantTypeTest()throws Exception{
        for (TenantType tenantType : tenantTypeService.selectList(null)) {
            System.out.println(tenantType);
        }

    }
}
