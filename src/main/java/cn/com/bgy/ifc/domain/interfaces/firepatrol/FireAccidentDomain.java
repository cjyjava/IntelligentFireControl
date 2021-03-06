package cn.com.bgy.ifc.domain.interfaces.firepatrol;

import cn.com.bgy.ifc.domain.interfaces.base.BaseDomain;
import cn.com.bgy.ifc.entity.po.firepatrol.FireAccident;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public interface FireAccidentDomain extends BaseDomain<FireAccident> {

    /**
     * 分页查询火灾事故情况
     * @param page
     * @param fireAccident
     * @return
     */
    PageInfo<FireAccident> queryListByPage(Page<FireAccident> page, FireAccident fireAccident);
}