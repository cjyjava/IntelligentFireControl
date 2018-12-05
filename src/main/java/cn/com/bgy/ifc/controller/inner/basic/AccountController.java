package cn.com.bgy.ifc.controller.inner.basic;

import cn.com.bgy.ifc.bgy.utils.CopyUtil;
import cn.com.bgy.ifc.domain.interfaces.basic.AccountDomain;
import cn.com.bgy.ifc.domain.interfaces.basic.UserDomain;
import cn.com.bgy.ifc.entity.po.basic.Account;
import cn.com.bgy.ifc.entity.po.basic.User;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.basic.AccountVo;
import cn.com.bgy.ifc.entity.vo.basic.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/basic/account")
public class AccountController {

    @Autowired
    private AccountDomain accountDomain;
    @GetMapping("/accountPage")
    public String userPage(){

        return "/basic/accountPage";
    }
    @PostMapping("add")
    @ResponseBody
    public   ResponseVO<Object>  add(Page<Account> page,@Validated AccountVo accountVo, BindingResult error){

        try {
            //todo userVO 做参数校检
            if(error.hasErrors()){
                return ResponseVO.error().setMsg(error.getFieldError().getDefaultMessage());
            }
            Account account= new Account();
            CopyUtil.copyProperties(accountVo,account);
            accountDomain.save(account);
            return ResponseVO.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.exception();
        }
    }
    @GetMapping("searchPage")
    @ResponseBody
    public ResponseVO<Object> searchPage(Page<Account> page,@Validated AccountVo accountVo, BindingResult error){
        Account account= new Account();
        CopyUtil.copyProperties(accountVo,account);
        PageInfo<Account> pageInfo=accountDomain.searchByPage(page,account);
        return ResponseVO.success().setData(pageInfo);
    }

    @PostMapping("deleteById")
    @ResponseBody
    public ResponseVO<Object> deleteById(Long id){

            Account account=accountDomain.findById(id);
            if(account==null) {
                ResponseVO.error().setMsg("请求数据不存在，请刷新重试！");
            }
          int res=accountDomain.deleteById(id);
        return ResponseVO.success().setMsg("数据删除成功！");
    }


}