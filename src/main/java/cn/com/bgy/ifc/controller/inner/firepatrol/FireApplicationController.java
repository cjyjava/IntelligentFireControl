package cn.com.bgy.ifc.controller.inner.firepatrol;

import cn.com.bgy.ifc.bgy.annotation.RolePermission;
import cn.com.bgy.ifc.bgy.annotation.SystemLogAfterSave;
import cn.com.bgy.ifc.controller.inner.common.BaseController;
import cn.com.bgy.ifc.entity.po.firepatrol.FireApplication;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.service.interfaces.inner.firepatrol.FireApplicationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author huxin
 * @Date 2019/1/8 9:18
 * @Description  动火证申请情况
 **/
@RestController
@RequestMapping("/firepatrol/fireApplication")
@RolePermission
public class FireApplicationController extends BaseController {

    @Autowired
    private FireApplicationService fireApplicationService;

    /**
     * @Author huxin
     * @Description 增加动火证申请情况
     * @Date 2019/1/7 16:14
     */
    @PostMapping("createData")
    @SystemLogAfterSave(description = "动火证申请情况添加")
    public ResponseVO<Object> addFireApplication( FireApplication fireApplication){
        int count = fireApplicationService.addFireApplication(fireApplication);
        if(count==1){
            return ResponseVO.addSuccess();
        }else if(count==2){
            return ResponseVO.addError().setMsg("添加失败，已经存在数据！！");
        }
        return ResponseVO.addError();
    }
    /**
     * @Author huxin
     * @Description 删除动火证申请情况(含批量)
     * @Date 2019/1/7 16:19
     */
    @PostMapping("deleteData")
    @SystemLogAfterSave(description = "动火证申请情况删除")
    public  ResponseVO<Object> deleteFireApplication(String ids){
        int count =fireApplicationService.deleteFireApplication(ids);
        if(count>=1){
            return ResponseVO.deleteSuccess();
        }
        return ResponseVO.deleteError();
    }
    /**
     * @Author huxin
     * @Description 修改动火证申请情况
     * @Date 2019/1/7 16:22
     */
    @PostMapping("editData")
    @SystemLogAfterSave(description = "动火证申请情况修改")
    public ResponseVO<Object> updateFireApplication( FireApplication fireApplication){
        int count = fireApplicationService.updateFireApplication(fireApplication);
        if(count==1){
            return ResponseVO.editSuccess();
        }
        return  ResponseVO.editError();
    }
    /**
     * @Author huxin
     * @Description 查询动火证申请情况列表
     * @Date 2019/1/7 16:25
     */
    @GetMapping("queryPageData")
    public ResponseVO<PageInfo> listFireApplication( Page<Object> page, String keyword, Long recordId){
        PageInfo pageInfo = fireApplicationService.listFireApplication(page,keyword,recordId);
        return ResponseVO.<PageInfo>success().setData(pageInfo);
    }
    /**
     * @Author huxin
     * @Description 根据ID查询动火证申请情况明细
     * @Date 2019/1/7 16:27
     */
    @GetMapping("findById")
    public ResponseVO<Object> getFireApplicationById(Long id){
        Map<String,Object> map = fireApplicationService.getFireApplicationById(id);
        return ResponseVO.<Object>success().setData(map);
    }
}
