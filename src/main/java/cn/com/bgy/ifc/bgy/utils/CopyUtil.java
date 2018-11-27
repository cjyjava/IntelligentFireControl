package cn.com.bgy.ifc.bgy.utils;

import cn.com.bgy.ifc.entity.bo.basic.UserBo;
import cn.com.bgy.ifc.entity.po.basic.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class CopyUtil {
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static void main(String[] args) {
        UserBo userBo= new UserBo();
        userBo.setEmail("213455");
        User user=new User();
        user.setDeptId(Long.valueOf(10));
        CopyUtil.copyProperties(userBo,user);
        System.out.println(user.toString());

    }
}
