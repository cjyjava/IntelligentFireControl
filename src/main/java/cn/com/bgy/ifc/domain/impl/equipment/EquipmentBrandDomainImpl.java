package cn.com.bgy.ifc.domain.impl.equipment;

import cn.com.bgy.ifc.bgy.constant.ExternalConstant;
import cn.com.bgy.ifc.bgy.utils.DBUtil;
import cn.com.bgy.ifc.dao.equipment.EquipmentBrandDao;
import cn.com.bgy.ifc.domain.interfaces.system.ExternalInterfaceMsgDomain;
import cn.com.bgy.ifc.domain.interfaces.equipment.EquipmentBrandDomain;
import cn.com.bgy.ifc.entity.po.equipment.EquipmentBrand;
import cn.com.bgy.ifc.entity.po.equipment.EquipmentBrand;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.equipment.BgyEquipmentBrandVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZhangCheng
 * @description:设备品牌
 * @date: 2018-12-21 15:01
 **/
@Service
public class EquipmentBrandDomainImpl implements EquipmentBrandDomain {
    /**
     * @Author huxin
     * @Description 查
     * @Date 2018/12/21 18:37
     */
    @Override
    public void queryListEquipmentBrand() {

    }
    /**
     * @Author huxin
     * @Description 增
     * @Date 2018/12/21 18:37
     */
    @Override
    public int addEquipmentBrand( EquipmentBrand equipmentBrand ) {
        return 0;
    }
    /**
     * @Author huxin
     * @Description 删除
     * @Date 2018/12/21 18:37
     */
    @Override
    public int updateEquipmentBrand( EquipmentBrand equipmentBrand ) {
        return 0;
    }
    /**
     * @Author huxin
     * @Description 添加
     * @Date 2018/12/21 18:38
     */
    @Override
    public int deleteEquipmentBrand( String str ) {
        return 0;
    }

    private static Logger logger = LoggerFactory.getLogger(EquipmentBrandDomainImpl.class);

    @Resource
    private EquipmentBrandDao equipmentBrandDao;

    @Autowired
    private ExternalInterfaceMsgDomain externalInterfaceMsgDomain;

    @Override
    public ResponseVO<Object> saveBgyEquipmentBrand(List<BgyEquipmentBrandVo> list, Long orgId) {
        try {
            List<EquipmentBrand> brandList = new ArrayList<>();
            for (BgyEquipmentBrandVo bgyEquipmentBrandVo : list) {
                EquipmentBrand brand = new EquipmentBrand();
                brand.setId(bgyEquipmentBrandVo.getId());
                brand.setName(bgyEquipmentBrandVo.getName());
                brand.setDescription(bgyEquipmentBrandVo.getDescription());
                brand.setStatus(bgyEquipmentBrandVo.getStatus());
                brandList.add(brand);
            }
            int totalCount = DBUtil.insertByList("equipment_brand", brandList);
            if (totalCount != brandList.size()) {
                return ResponseVO.error().setMsg("同步集成平台设备品牌异常");
            } else {
                externalInterfaceMsgDomain.successInterfaceMsg(orgId, ExternalConstant.MsgTypeValue.BGY_EQUIPMENT_BRAND_OBTAIN.getValue(), totalCount);
                return ResponseVO.success().setMsg("同步集成平台设备品牌总条数：" + totalCount + "，新增条数：" + totalCount + ",成功条数：" + totalCount + "，失败条数" + 0 + "");
            }
        } catch (Exception e) {
            logger.error("同步集成平台设备品牌doMain异常:" + e);
            return ResponseVO.error().setMsg("同步集成平台设备品牌异常");
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public ResponseVO<Object> alterBgyEquipmentBrand(List<BgyEquipmentBrandVo> list, Long orgId) {
        int addType = ExternalConstant.OperationType.ADD.getValue();
        int updateType = ExternalConstant.OperationType.UPDATE.getValue();
        int deleteType = ExternalConstant.OperationType.DELETE.getValue();
        int status = ExternalConstant.StatusType.DELETE.getValue();
        int totalCount = list.size();
        int addCount = 0;
        int updateCount = 0;
        int deleteCount = 0;
        for (BgyEquipmentBrandVo bgyEquipmentBrandVo : list) {
            EquipmentBrand brand = new EquipmentBrand();
            brand.setId(bgyEquipmentBrandVo.getId());
            brand.setName(bgyEquipmentBrandVo.getName());
            brand.setDescription(bgyEquipmentBrandVo.getDescription());
            brand.setStatus(bgyEquipmentBrandVo.getStatus());
            int operType = bgyEquipmentBrandVo.getOperType();
            //新增
            if (operType == addType) {
                int count = equipmentBrandDao.insertSelective(brand);
                if (count == 1) {
                    addCount++;
                }
            }
            //修改
            if (operType == updateType) {
                int count = equipmentBrandDao.updateSelective(brand);
                if (count == 1) {
                    updateCount++;
                }
            }
            //删除
            if (operType == deleteType) {
                brand.setStatus(status);
                int count = equipmentBrandDao.updateSelective(brand);
                if (count == 1) {
                    deleteCount++;
                }
            }
        }
        if (addCount + updateCount + deleteCount != totalCount) {
            throw new RuntimeException("批量同步设备品牌增量数据失败!");
        } else {
            int msgType = ExternalConstant.MsgTypeValue.BGY_EQUIPMENT_BRAND_OBTAIN.getValue();
            externalInterfaceMsgDomain.alterInterfaceMsg(orgId, msgType, totalCount, addCount, updateCount, deleteCount);
            return ResponseVO.success().setMsg("同步集成平台设备品牌增量总条数：" + totalCount + "，新增条数：" + addCount + ",修改条数：" + updateCount + ",删除条数：" + deleteCount + ",成功条数：" + totalCount + "，失败条数" + 0 + "");
        }
    }
}
