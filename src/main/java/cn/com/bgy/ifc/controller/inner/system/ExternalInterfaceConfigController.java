package cn.com.bgy.ifc.controller.inner.system;

import cn.com.bgy.ifc.bgy.annotation.SystemLogAfterSave;
import cn.com.bgy.ifc.bgy.utils.CopyUtil;
import cn.com.bgy.ifc.bgy.utils.ListUtil;
import cn.com.bgy.ifc.entity.po.system.ExternalInterfaceConfig;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.system.ExternalInterfaceConfigVo;
import cn.com.bgy.ifc.service.interfaces.inner.system.ExternalInterfaceConfigService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: ZhangCheng
 * @description:接入接口配置
 * @date: 2018-12-17 16:57
 **/
@RestController
@RequestMapping("/basic/interfaceConfig")
public class ExternalInterfaceConfigController {

    @Autowired
    private ExternalInterfaceConfigService externalInterfaceConfigService;

    /**
     * @author: ZhangCheng
     * @description:分页查询外部接口配置
     * @param: [page, platformValue]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<com.github.pagehelper.PageInfo < cn.com.bgy.ifc.entity.po.system.ExternalInterfaceConfig>>
     */
    @PostMapping("queryPage")
    public ResponseVO<PageInfo<ExternalInterfaceConfig>> searchPage(Page<ExternalInterfaceConfig> page, Integer platformValue) {
        PageInfo<ExternalInterfaceConfig> pageInfo = externalInterfaceConfigService.queryListByPage(page, platformValue);
        return ResponseVO.<PageInfo<ExternalInterfaceConfig>>success().setData(pageInfo);
    }

    /**
     * @author: ZhangCheng
     * @description:ID查询接入接口
     * @param: [id]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @GetMapping("findById")
    public ResponseVO<Object> findById(Long id) {
        ExternalInterfaceConfig config = externalInterfaceConfigService.findById(id);
        return ResponseVO.success().setData(config);
    }

    /**
     * @author: ZhangCheng
     * @description:状态更改
     * @param: [id, state]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @PostMapping("statusEnabled")
    public ResponseVO<Object> statusEnabled(Long id,Integer state){
        ExternalInterfaceConfig config = new ExternalInterfaceConfig();
        config.setId(id);
        config.setState(state);
        int result = externalInterfaceConfigService.updateSelective(config);
        if (result == 1) {
            return ResponseVO.success().setMsg("操作成功");
        } else {
            return ResponseVO.error().setMsg("操作失败！");
        }
    }

    /**
     * @author: ZhangCheng
     * @description:修改接入接口配置
     * @param: [externalInterfaceConfigVo, error]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @PostMapping("edit")
    @SystemLogAfterSave(type = 1, description = "修改接入接口配置")
    public ResponseVO<Object> edit(@Validated ExternalInterfaceConfigVo externalInterfaceConfigVo, BindingResult error) {
        if (error.hasErrors()) {
            return ResponseVO.error().setMsg(error.getFieldError().getDefaultMessage());
        }
        ExternalInterfaceConfig config = new ExternalInterfaceConfig();
        CopyUtil.copyProperties(externalInterfaceConfigVo, config);
        int result = externalInterfaceConfigService.updateSelective(config);
        if (result == 1) {
            return ResponseVO.editSuccess();
        } else {
            return ResponseVO.editError();
        }
    }

    /**
     * @author: ZhangCheng
     * @description:删除接入接口配置
     * @param: [ids]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @PostMapping("delete")
    @SystemLogAfterSave(type = 1, description = "删除接入接口配置")
    public ResponseVO<Object> deleteBatch(String ids) {
        if (ids.length() == 0) {
            return ResponseVO.deleteError();
        }
        List<Long> list = ListUtil.getListId(ids);
        int resultCount = externalInterfaceConfigService.deleteBatch(list);
        if (resultCount == list.size()) {
            return ResponseVO.deleteSuccess();
        } else {
            return ResponseVO.deleteError();
        }
    }

}
