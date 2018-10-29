package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.service.api.system.SysAccessPermissionService;
import com.gameloft9.demo.dataaccess.dao.system.SysAccessPermissionTestMapper;
import com.gameloft9.demo.dataaccess.model.system.SysAccessPermissionTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gameloft9 on 2017/12/4.
 */
@Slf4j
@Service
public class SysAccessPermissionServiceImpl implements SysAccessPermissionService {

    @Autowired
    SysAccessPermissionTestMapper sysAccessPermissionTestDao;

    public List<SysAccessPermissionTest> getAll(){
        return sysAccessPermissionTestDao.selectAll();
    }


}
