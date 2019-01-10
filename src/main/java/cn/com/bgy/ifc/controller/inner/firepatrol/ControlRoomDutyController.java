package cn.com.bgy.ifc.controller.inner.firepatrol;

import cn.com.bgy.ifc.bgy.utils.CopyUtil;
import cn.com.bgy.ifc.bgy.utils.ListUtil;
import cn.com.bgy.ifc.controller.inner.common.BaseController;
import cn.com.bgy.ifc.domain.interfaces.firepatrol.ControlRoomDutyDomain;
import cn.com.bgy.ifc.entity.po.firepatrol.ControlRoomDuty;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/firepatrol/controlRoomDuty")
public class ControlRoomDutyController extends BaseController {
    @Autowired
    private ControlRoomDutyDomain controlRoomDutyDomain;

    /**
     * @description:分页查询消防演练及宣传情况
     * @param: [page, controlRoomDuty]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<com.github.pagehelper.PageInfo<cn.com.bgy.ifc.entity.po.firepatrol.ControlRoomDuty>>
     * @auther: chenlie
     * @date: 2019/1/7 17:24
     */
    @GetMapping("queryPageData")
    public ResponseVO<PageInfo<ControlRoomDuty>> queryListByPage(Page<ControlRoomDuty> page, ControlRoomDuty controlRoomDuty) {

        PageInfo<ControlRoomDuty> pageInfo = controlRoomDutyDomain.queryListByPage(page, controlRoomDuty);
        return ResponseVO.<PageInfo<ControlRoomDuty>>success().setData(pageInfo);
    }
    /**
     * @description:根据id查询消防演练及宣传情况
     * @param: [id]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<cn.com.bgy.ifc.entity.po.firepatrol.ControlRoomDuty>
     * @auther: chenlie
     * @date: 2019/1/8 10:44
     */
    @GetMapping("findById")
    public ResponseVO<ControlRoomDuty> findById(Long id) {

        ControlRoomDuty controlRoomDuty= controlRoomDutyDomain.findById(id);
        return ResponseVO.<ControlRoomDuty>success().setData(controlRoomDuty);
    }



    /**
     * @description:批量逻辑删除
     * @param: [id]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<cn.com.bgy.ifc.entity.po.firepatrol.ControlRoomDuty>
     * @auther: chenlie
     * @date: 2019/1/8 10:44
     */
    @GetMapping("deleteData")
    public ResponseVO<Object> deleteData(String ids) {
        if(ids==null|| ids.isEmpty() ){
            return ResponseVO.error().setMsg("参数异常");
        }
        List<Long> list= ListUtil.getListId(ids);
        int res= controlRoomDutyDomain.deleteBatch(list);
        if(res>0){
            return ResponseVO.success();
        } else{
            return ResponseVO.deleteError();
        }
    }

    /**
     * @description:修改
     * @param: [controlRoomDuty]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     * @auther: chenlie
     * @date: 2019/1/8 11:28
     */
    @GetMapping("editData")
    public ResponseVO<Object> editData(ControlRoomDuty controlRoomDuty) {

        ControlRoomDuty  query =controlRoomDutyDomain.findById(controlRoomDuty.getId());
        CopyUtil.copyProperties(controlRoomDuty,query);
        int res= controlRoomDutyDomain.update(query);
        if(res>0){
            return ResponseVO.success();
        } else{
            return ResponseVO.editError();
        }
    }


    /**
     * @description:新增
     * @param: [controlRoomDuty]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     * @auther: chenlie
     * @date: 2019/1/8 11:28
     */
    @GetMapping("createData")
    public ResponseVO<Object> createData(ControlRoomDuty controlRoomDuty) {

        int res= controlRoomDutyDomain.insert(controlRoomDuty);
        if(res>0){
            return ResponseVO.success();
        } else{
            return ResponseVO.editError();
        }
    }
}