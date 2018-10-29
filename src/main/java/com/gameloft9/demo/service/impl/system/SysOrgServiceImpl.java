package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.SysOrganizeTestMapper;
import com.gameloft9.demo.dataaccess.model.system.SysOrganizeTest;
import com.gameloft9.demo.service.api.system.SysOrgService;
import com.gameloft9.demo.service.beans.system.OrgNodeResponse;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.NumberUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 组织机构服务实现类
 * Created by gameloft9 on 2017/12/19.
 */
@Slf4j
@Service
public class SysOrgServiceImpl implements SysOrgService {

    @Autowired
    SysOrganizeTestMapper sysOrganizeTestMapper;

    /**
     * 获取所有组织机构
     */
    public List<OrgNodeResponse> getAll() {
        List<SysOrganizeTest> orgList = new LinkedList<SysOrganizeTest>();
        orgList = sysOrganizeTestMapper.getAll();

        //转换成树
        return convert2TreeNodes(orgList);
    }

    /**
     * 根据id获取组织机构
     * @param id 主键
     * */
    public SysOrganizeTest getById(String id){
        CheckUtil.notBlank(id,"组织机构id为空");

        return sysOrganizeTestMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新组织机构
     * @param org 组织机构信息
     * */
    public Boolean update(SysOrganizeTest org){
        CheckUtil.notNull(org,"组织机构为空");
        CheckUtil.notBlank(org.getId(),"组织机构id为空");

        sysOrganizeTestMapper.updateByPrimaryKeySelective(org);
        return true;
    }

    /**
     * 删除组织机构
     * @param id 组织机构id
     * */
    public Boolean deleteById(String id){
        CheckUtil.notBlank(id,"组织机构id为空");

        //含有子机构不能删除
        int count = sysOrganizeTestMapper.countChildren(id);
        CheckUtil.check(count == 0,"含有子机构，不能删除");

        //删除
        sysOrganizeTestMapper.deleteByPrimaryKey(id);
        return true;
    }

    /**
     * 添加组织机构
     * @param org 组织机构信息
     * */
    public Boolean add(SysOrganizeTest org){
        CheckUtil.notNull(org,"组织机构为空");

        //名称不能重复
        SysOrganizeTest test = sysOrganizeTestMapper.getByName(org.getOrganizeName());
        CheckUtil.check(test == null,"该组织机构已经存在");

        //根机构只能有一个
        if(StringUtils.isBlank(org.getParentId())){
            log.info("添加根机构");
            test = sysOrganizeTestMapper.getRoot();
            CheckUtil.check(test == null,"根组织机构已经存在");

            org.setId(UUIDUtil.getUUID());
            org.setOrganizeCode(generateOrgCode(org));
            org.setLevel(1);
            org.setTreePath(org.getId());
            sysOrganizeTestMapper.insertSelective(org);
            return true;
        }

        log.info("添加子机构");
        SysOrganizeTest parent = sysOrganizeTestMapper.selectByPrimaryKey(org.getParentId());
        int level = parent.getLevel();
        org.setId(UUIDUtil.getUUID());
        org.setOrganizeCode(generateOrgCode(org));
        org.setLevel(level+1);
        org.setTreePath(parent.getTreePath()+"#"+org.getId());
        sysOrganizeTestMapper.insertSelective(org);
        return true;
    }

    /*************************私有方法区******************************/

    /**
     * 转成树
     *
     * @param orgList 组织机构列表
     */
    private List<OrgNodeResponse> convert2TreeNodes(List<SysOrganizeTest> orgList) {
        CheckUtil.notNull(orgList, "组织机构列表为空");
        CheckUtil.check(!orgList.isEmpty(), "组织机构列表为空");

        List<OrgNodeResponse> res = new LinkedList<OrgNodeResponse>();

        Iterator<SysOrganizeTest> it = orgList.iterator();
        while (it.hasNext()) {
            SysOrganizeTest org = it.next();
            if (StringUtils.isBlank(org.getParentId())) {//拿到根机构
                OrgNodeResponse node = new OrgNodeResponse();
                node.setId(org.getId());
                node.setName(org.getOrganizeName());
                node.setHref("#");
                node.setPath(org.getTreePath());
                node.setSpread(false);
                node.setChildren(getChildrenNodes(org.getId(), orgList));
                res.add(node);
            }
        }

        return res;
    }


    /**
     * 递归获取子节点
     *
     * @param parentId 父节点id
     * @param orgList  组织机构列表
     */
    private List<OrgNodeResponse> getChildrenNodes(String parentId, List<SysOrganizeTest> orgList) {
        List<OrgNodeResponse> children = new LinkedList<OrgNodeResponse>();

        Iterator<SysOrganizeTest> it = orgList.iterator();
        while (it.hasNext()) {
            SysOrganizeTest org = it.next();
            if (StringUtils.isNotBlank(org.getParentId())&&
                    org.getParentId().equals(parentId)) {
                OrgNodeResponse node = new OrgNodeResponse();
                node.setId(org.getId());
                node.setName(org.getOrganizeName());
                node.setHref("#");
                node.setPath(org.getTreePath());
                node.setSpread(false);
                node.setChildren(getChildrenNodes(node.getId(), orgList));
                children.add(node);
            }
        }

        return children;
    }

    /**
     * 生成组织机构编码
     * 编码规则2位层级+4位编码，例如：010001
     * @param
     * */
    private synchronized String generateOrgCode(SysOrganizeTest org){
        String parentId = org.getParentId();
        if(StringUtils.isBlank(parentId)){
            log.info("为根机构生成机构编码");
            return Constants.OrgCodeRule.ROOT_CODE;
        }

        log.info("为子机构生成编码");
        SysOrganizeTest parent = sysOrganizeTestMapper.selectByPrimaryKey(org.getParentId());
        int level = parent.getLevel() + 1;
        String prefix = NumberUtil.paddingNumber(level,2);
        log.info("编码前缀为:{}",prefix);

        String suffix = "";
        String maxOrgCode = sysOrganizeTestMapper.getMaxOrgCode(level);
        if(StringUtils.isBlank(maxOrgCode)){
            suffix = Constants.OrgCodeRule.INIT_SORT;
        }else{
            int sort = new Integer(maxOrgCode.substring(2));
            sort += 1;
            suffix = NumberUtil.paddingNumber(sort,4);
        }

        log.info("编码后缀为:{}",suffix);

        return prefix+suffix;
    }
}
