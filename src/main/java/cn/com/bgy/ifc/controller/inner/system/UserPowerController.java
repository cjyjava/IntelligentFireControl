package cn.com.bgy.ifc.controller.inner.system;

import cn.com.bgy.ifc.domain.interfaces.system.DepartmentDomain;
import cn.com.bgy.ifc.domain.interfaces.system.UserPowerDomain;
import cn.com.bgy.ifc.entity.po.system.Department;
import cn.com.bgy.ifc.entity.po.system.SystemUserPower;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/system/userPower")
public class UserPowerController {
    @Autowired
    private UserPowerDomain userPowerDomain;

    @Autowired
    private DepartmentDomain departmentDomain;
    /**
     * 分页查询
     *用户权限展示页面
     * @param systemUserPower
     * @return
     */
    @GetMapping("/searchPage")
    @ResponseBody
    public ResponseVO<PageInfo<SystemUserPower>> queryPageList(Page<Object> page,SystemUserPower systemUserPower) {
        PageInfo<SystemUserPower> pageInfo = userPowerDomain.queryPageList(page, systemUserPower);
        return ResponseVO.<PageInfo<SystemUserPower>>success().setData(pageInfo);
    }

}
