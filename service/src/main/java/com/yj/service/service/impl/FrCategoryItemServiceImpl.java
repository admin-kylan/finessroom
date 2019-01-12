package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.result.JsonResult;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.*;
import com.yj.dal.dto.CardConsumePladdsetDTO;
import com.yj.dal.dto.CategoryItemDTO;
import com.yj.dal.model.*;
import com.yj.service.service.IFrCategoryItemService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 场馆项目表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
@Service
public class FrCategoryItemServiceImpl extends BaseServiceImpl<FrCategoryItemMapper, FrCategoryItem> implements IFrCategoryItemService {

    @Resource
    private FrCategoryItemMapper  categoryItemMapper;
    @Resource
    private SdaduimMapper  sdaduimMapper;
    @Resource
    private ShopMapper shopMapper;

    @Resource
    private FrShopCtypeConsumeMapper consumeMapper;
   @Resource
   private FrShopCtypeConsumePladdsetMapper  ctypeConsumePladdsetMapper;

    @Resource
    private FrShopCtypeConsumePlsetMapper   ctypeConsumePlsetMapper;

    /*
 * @Author Sinyu
 * @Description 搜索场馆关联的项目
 * @param ssids 场馆 id
 * @return
 **/
    @Override
    public JsonResult getCategoryItemList(String[] ssids) {
        Shop shop=new Shop();
        List<List<FrCategoryItem>>categoryItemlist = new ArrayList<>();
        Map<String,List<Sdaduim>> map = new HashMap<String,List<Sdaduim>>();

       // List<CategoryItemDTO> categoryItemDTOList;
        Sdaduim sdaduim=new Sdaduim();

        for (int i = 0; i < ssids.length; i++) {
            //查场馆
            sdaduim = sdaduimMapper.selectById(ssids[i]);
            //根据场馆id：sdaduim_id 查询项目列表(场馆项目表 =>关联=> 会员卡类型-门店-场馆-项目关系表)


            List<CategoryItemDTO> items = categoryItemMapper.selectItemsBySdaduimId(ssids[i]);

            //  sdaduim.setCategoryItemlist(items);

            //查询 获取项目的设置
//            for (int j = 0; j < items.size(); j++) {
//                System.out.println(items.get(j).getSccId());
//                List<FrShopCtypeConsumePladdset> frShopCtypeConsumePladdsets = ctypeConsumePladdsetMapper.selectCtypeConsumePladdset(items.get(j).getSccId());
//                System.out.println("frShopCtypeConsumePladdsets=================="+frShopCtypeConsumePladdsets);
//
//                List<FrShopCtypeConsumePlset> frShopCtypeConsumePlsets = ctypeConsumePlsetMapper.selectCtypeConsumePlset(items.get(j).getSccId());
//
//
//                System.out.println("frShopCtypeConsumePlsets=====333333============="+frShopCtypeConsumePlsets);
//
//
//                items.get(j).setCardConsumePladdsetDTO(frShopCtypeConsumePladdsets);
//
//
//                items.get(j).setCardConsumePlsetDTO(frShopCtypeConsumePlsets);
//            }

            sdaduim.setCategoryItemlist(items);


            //查场馆项目
            //sdaduim.setCategoryItemlist(categoryItemMapper.selectList(new EntityWrapper<FrCategoryItem>().where("sdaduim_id = {0}", ssids[i])));
// 查关系
          List< FrShopCtypeConsume>  list=   consumeMapper.selectList((new EntityWrapper<FrShopCtypeConsume>().where("sdaduim_id = {0}", ssids[i])));

             sdaduim.setShopCtypeConsumes(list);

            if(map.get(sdaduim.getShopId()) == null){
                List<Sdaduim> sList=new ArrayList<>();
                sList.add(sdaduim);
                map.put(sdaduim.getShopId(),sList);
            }else{
                List<Sdaduim> sList= map.get(sdaduim.getShopId());
                sList.add(sdaduim);
                map.put(sdaduim.getShopId(),sList);
            }
        }


        return JsonResult.success(map);
    }




    //新的方法         未修改完
    /*
     * @Author Sinyu
     * @Description 搜索场馆下的项目及设置
     * @param ssids  门店id
     * @return
     **/
    @Override
    public JsonResult CategoryItemList(String[] ssids) {
        List<Shop> shopList=null;
        Shop shop=new Shop();
        for (int i = 0; i <ssids.length ; i++) {
            //查门店
             shop = shopMapper.selectById(ssids[i]);
            //查场馆
            List<Sdaduim> sdaduims = sdaduimMapper.selectList((new EntityWrapper<Sdaduim>().where("ShopId = {0}", ssids[i])));
            for (int j = 0; j < sdaduims.size(); j++) {
                //查场馆项目
                List<FrCategoryItem> frCategoryItems = categoryItemMapper.selectList((new EntityWrapper<FrCategoryItem>().where("sdaduim_id = {0}", sdaduims.get(j).getId())));
                //查询项目设置
               // ctypeConsumePladdsetMapper.
             //  FrShopCtypeConsumePladdset
            }
            //根据场馆id：sdaduim_id 查询项目列表(场馆项目表 =>关联=> 会员卡类型-门店-场馆-项目关系表)

//            List< FrShopCtypeConsume>  list=   consumeMapper.selectList((new EntityWrapper<FrShopCtypeConsume>().where("sdaduim_id = {0}", ssids[i])));
//            sdaduim.setShopCtypeConsumes(list);

        }
        return null;
    }

    //查询项目的设置
    @Override
    public JsonResult selectConsume(String consumeid) {
        CategoryItemDTO categoryItemDTO=new CategoryItemDTO();//创建打包对象
        //  查询 获取项目的设置
        List<FrShopCtypeConsumePladdset> frShopCtypeConsumePladdsets = ctypeConsumePladdsetMapper.selectCtypeConsumePladdset(consumeid);
        List<FrShopCtypeConsumePlset> frShopCtypeConsumePlsets = ctypeConsumePlsetMapper.selectCtypeConsumePlset(consumeid);
        categoryItemDTO.setCardConsumePladdsetDTO(frShopCtypeConsumePladdsets);
        categoryItemDTO.setCardConsumePlsetDTO(frShopCtypeConsumePlsets);


        return JsonResult.success(categoryItemDTO);
    }

    @Override
    public JsonResult setConsumeDTO(CategoryItemDTO consume) {
       Integer rows=0;
        for (int i = 0; i < consume.getCardConsumePladdsetDTO().size() ; i++) {
            FrShopCtypeConsumePladdset pladdset=new FrShopCtypeConsumePladdset();
            pladdset= consume.getCardConsumePladdsetDTO().get(i);
            if (consume.getCardConsumePladdsetDTO().get(i).getId()!=null)
            {
                rows=  ctypeConsumePladdsetMapper.updateById(pladdset);
            }
            else {
                pladdset.setId(UUIDUtils.generateGUID());
                rows=    ctypeConsumePladdsetMapper.insert(pladdset);

            }
        }
        for (int i = 0; i <consume.getCardConsumePlsetDTO().size() ; i++) {
            FrShopCtypeConsumePlset plset=new FrShopCtypeConsumePlset();
            plset= consume.getCardConsumePlsetDTO().get(i);
            if (consume.getCardConsumePladdsetDTO().get(i).getId()!=null)
            {
                rows=    ctypeConsumePlsetMapper.updateById(plset);
            }
            else {
                plset.setId(UUIDUtils.generateGUID());
                rows=     ctypeConsumePlsetMapper.insert(plset);

            }
        }
        if ( rows!=0)
        {
            return   JsonResult.success();
        }

        return  JsonResult.failMessage("错误");
    }


}
