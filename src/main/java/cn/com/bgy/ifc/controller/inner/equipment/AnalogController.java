package cn.com.bgy.ifc.controller.inner.equipment;

import cn.com.bgy.ifc.bgy.annotation.SystemLogAfterSave;
import cn.com.bgy.ifc.bgy.constant.EquipmentConstant;
import cn.com.bgy.ifc.bgy.utils.EnumUtil;
import cn.com.bgy.ifc.entity.po.equipment.Analog;
import cn.com.bgy.ifc.entity.po.equipment.EquipmentEvent;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.common.SelectVo;
import cn.com.bgy.ifc.entity.vo.equipment.EquipmentEventVo;
import cn.com.bgy.ifc.service.interfaces.inner.equipment.AnalogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: ZhangCheng
 * @description:模拟量管理
 * @date: 2019-01-15 16:27
 **/
@RestController
@RequestMapping("/equipment/analog")
public class AnalogController {
    @Autowired
    private AnalogService analogService;

    /**
     * @author: ZhangCheng
     * @description:模拟量分页查询
     * @param: [page, analog]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<com.github.pagehelper.PageInfo<cn.com.bgy.ifc.entity.po.equipment.Analog>>
     */
    @GetMapping("queryPageData")
    public ResponseVO<PageInfo<Analog>> queryPage(Page<Analog> page, Analog analog) {
        PageInfo<Analog> pageInfo = analogService.queryListByPage(page, analog);
        return ResponseVO.<PageInfo<Analog>>success().setData(pageInfo);
    }

    /**
     * @author: ZhangCheng
     * @description:模拟量数据同步
     * @param: `
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @GetMapping("synchroData")
    public ResponseVO<Object> synchroData() {
        return analogService.synchroAnalog(1, 100);
    }
    
    /**
     * @author: ZhangCheng
     * @description:模拟量异常类型
     * @param: []
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @GetMapping("getAnalogAbnormalType")
    public ResponseVO<Object> getAnalogAbnormalType() {
        List<SelectVo> list = EnumUtil.getSelectList(EquipmentConstant.AnalogAbnormalType.class);
        return ResponseVO.success().setData(list);
    }

    /**
     * @author: ZhangCheng
     * @description:删除模拟量信息
     * @param: [ids]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @PostMapping("deleteData")
    @SystemLogAfterSave(description = "删除模拟量信息")
    public ResponseVO<Object> deleteData(String ids) {
        if (ids.length() == 0) {
            return ResponseVO.deleteError();
        }
        return analogService.deleteAnalog(ids);
    }
}
