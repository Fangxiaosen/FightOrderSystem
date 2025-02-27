package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.JipiaoEntity;
import com.entity.view.JipiaoOrderView;
import com.entity.view.JipiaoView;
import com.service.*;
import com.utils.CommonUtil;
import com.utils.PageUtils;
import com.utils.PoiUtil;
import com.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 航班信息
 * 后端接口
 *
 * @author
 * @email
 */
@RestController
@Controller
@RequestMapping("/jipiao")
public class JipiaoController {
    private static final Logger logger = LoggerFactory.getLogger(JipiaoController.class);

    private static final String TABLE_NAME = "jipiao";

    @Autowired
    private JipiaoService jipiaoService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChatService chatService;//客服聊天
    @Autowired
    private DictionaryService dictionaryService;//字典表
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private JipiaoCollectionService jipiaoCollectionService;//航班收藏
    @Autowired
    private JipiaoOrderService jipiaoOrderService;//机票预订
    @Autowired
    private NewsService newsService;//民航新闻
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        logger.debug("page方法:,,Controller:{},,params:{}", this.getClass().getName(), JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if (false)
            return R.error(511, "永不会进入");
        else if ("用户".equals(role))
            params.put("yonghuId", request.getSession().getAttribute("userId"));
        params.put("jipiaoDeleteStart", 1);
        params.put("jipiaoDeleteEnd", 1);
        CommonUtil.checkMap(params);
        PageUtils page = jipiaoService.queryPage(params);

        //字典表数据转换
        List<JipiaoView> list = (List<JipiaoView>) page.getList();
        for (JipiaoView c : list) {
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    @RequestMapping("/quxiaohangban")
    public R quxiaohangban(Integer id, HttpServletRequest request) {
        JipiaoEntity jipiaoEntity = new JipiaoEntity();
        jipiaoEntity.setId(id);
        jipiaoEntity.setHangbanTypes(2);
        jipiaoService.updateById(jipiaoEntity);
        return R.ok();
    }


    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        logger.debug("info方法:,,Controller:{},,id:{}", this.getClass().getName(), id);
        JipiaoEntity jipiao = jipiaoService.selectById(id);
        if (jipiao != null) {
            //entity转view
            JipiaoView view = new JipiaoView();
            BeanUtils.copyProperties(jipiao, view);//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        } else {
            return R.error(511, "查不到数据");
        }

    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JipiaoEntity jipiao, HttpServletRequest request) {
        logger.debug("save方法:,,Controller:{},,jipiao:{}", this.getClass().getName(), jipiao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if (false)
            return R.error(511, "永远不会进入");

        Wrapper<JipiaoEntity> queryWrapper = new EntityWrapper<JipiaoEntity>()
                .eq("jipiao_name", jipiao.getJipiaoName())
                .eq("jipiao_types", jipiao.getJipiaoTypes())
                .eq("jipiao_chufadi", jipiao.getJipiaoChufadi())
                .eq("jipiao_mudidi", jipiao.getJipiaoMudidi())
                .eq("zuowei_number", jipiao.getZuoweiNumber())
                .eq("shangxia_types", jipiao.getShangxiaTypes())
                .eq("hangban_types", jipiao.getHangbanTypes())
                .eq("jipiao_delete", 1);

        logger.info("sql语句:" + queryWrapper.getSqlSegment());
        JipiaoEntity jipiaoEntity = jipiaoService.selectOne(queryWrapper);
        if (jipiaoEntity == null) {
            jipiao.setShangxiaTypes(1);
            jipiao.setJipiaoDelete(1);
            jipiao.setCreateTime(new Date());
            jipiaoService.insert(jipiao);
            return R.ok();
        } else {
            return R.error(511, "表中有相同数据");
        }
    }

    /**
     * 后端修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody JipiaoEntity jipiao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jipiao:{}", this.getClass().getName(), jipiao.toString());
        JipiaoEntity oldJipiaoEntity = jipiaoService.selectById(jipiao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if ("".equals(jipiao.getJipiaoPhoto()) || "null".equals(jipiao.getJipiaoPhoto())) {
            jipiao.setJipiaoPhoto(null);
        }

        jipiaoService.updateById(jipiao);//根据id更新
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request) {
        logger.debug("delete:,,Controller:{},,ids:{}", this.getClass().getName(), ids.toString());
        List<JipiaoEntity> oldJipiaoList = jipiaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<JipiaoEntity> list = new ArrayList<>();
        for (Integer id : ids) {
            JipiaoEntity jipiaoEntity = new JipiaoEntity();
            jipiaoEntity.setId(id);
            jipiaoEntity.setJipiaoDelete(2);
            list.add(jipiaoEntity);
        }
        if (list != null && list.size() > 0) {
            jipiaoService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save(String fileName, HttpServletRequest request) {
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}", this.getClass().getName(), fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<JipiaoEntity> jipiaoList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields = new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if (lastIndexOf == -1) {
                return R.error(511, "该文件没有后缀");
            } else {
                String suffix = fileName.substring(lastIndexOf);
                if (!".xls".equals(suffix)) {
                    return R.error(511, "只支持后缀为xls的excel文件");
                } else {
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if (!file.exists()) {
                        return R.error(511, "找不到上传文件，请联系管理员");
                    } else {
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for (List<String> data : dataList) {
                            //循环
                            JipiaoEntity jipiaoEntity = new JipiaoEntity();
//                            jipiaoEntity.setJipiaoName(data.get(0));                    //航班标题 要改的
//                            jipiaoEntity.setJipiaoPhoto("");//详情和图片
//                            jipiaoEntity.setJipiaoTypes(Integer.valueOf(data.get(0)));   //航班类型 要改的
//                            jipiaoEntity.setJipiaoNewMoney(data.get(0));                    //现价 要改的
//                            jipiaoEntity.setJipiaoChufadi(data.get(0));                    //出发地 要改的
//                            jipiaoEntity.setJipiaoMudidi(data.get(0));                    //目的地 要改的
//                            jipiaoEntity.setJipiaoTime(sdf.parse(data.get(0)));          //出发时间 要改的
//                            jipiaoEntity.setZuoweiNumber(Integer.valueOf(data.get(0)));   //座位 要改的
//                            jipiaoEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            jipiaoEntity.setHangbanTypes(Integer.valueOf(data.get(0)));   //航班状态 要改的
//                            jipiaoEntity.setJipiaoDelete(1);//逻辑删除字段
//                            jipiaoEntity.setJipiaoContent("");//详情和图片
//                            jipiaoEntity.setCreateTime(date);//时间
                            jipiaoList.add(jipiaoEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jipiaoService.insertBatch(jipiaoList);
                        return R.ok();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(511, "批量插入数据异常，请联系管理员");
        }
    }


    /**
     * 个性推荐
     */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}", this.getClass().getName(), JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<JipiaoView> returnJipiaoViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);
        params1.put("sort", "id");
        params1.put("yonghuId", request.getSession().getAttribute("userId"));
        PageUtils pageUtils = jipiaoOrderService.queryPage(params1);
        List<JipiaoOrderView> orderViewsList = (List<JipiaoOrderView>) pageUtils.getList();
        Map<Integer, Integer> typeMap = new HashMap<>();//购买的类型list
        for (JipiaoOrderView orderView : orderViewsList) {
            Integer jipiaoTypes = orderView.getJipiaoTypes();
            if (typeMap.containsKey(jipiaoTypes)) {
                typeMap.put(jipiaoTypes, typeMap.get(jipiaoTypes) + 1);
            } else {
                typeMap.put(jipiaoTypes, 1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for (Integer type : typeList) {
            Map<String, Object> params2 = new HashMap<>(params);
            params2.put("jipiaoTypes", type);
            PageUtils pageUtils1 = jipiaoService.queryPage(params2);
            List<JipiaoView> jipiaoViewList = (List<JipiaoView>) pageUtils1.getList();
            returnJipiaoViewList.addAll(jipiaoViewList);
            if (returnJipiaoViewList.size() >= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = jipiaoService.queryPage(params);
        if (returnJipiaoViewList.size() < limit) {//返回数量还是小于要求数量
            int toAddNum = limit - returnJipiaoViewList.size();//要添加的数量
            List<JipiaoView> jipiaoViewList = (List<JipiaoView>) page.getList();
            for (JipiaoView jipiaoView : jipiaoViewList) {
                Boolean addFlag = true;
                for (JipiaoView returnJipiaoView : returnJipiaoViewList) {
                    if (returnJipiaoView.getId().intValue() == jipiaoView.getId().intValue()) {
                        addFlag = false;//返回的数据中已存在此商品
                        break;
                    }
                }
                if (addFlag) {
                    toAddNum = toAddNum - 1;
                    returnJipiaoViewList.add(jipiaoView);
                    if (toAddNum == 0) break;//够数量了
                }
            }
        } else {
            returnJipiaoViewList = returnJipiaoViewList.subList(0, limit);
        }

        for (JipiaoView c : returnJipiaoViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnJipiaoViewList);
        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        logger.debug("list方法:,,Controller:{},,params:{}", this.getClass().getName(), JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = jipiaoService.queryPage(params);

        //字典表数据转换
        List<JipiaoView> list = (List<JipiaoView>) page.getList();
        for (JipiaoView c : list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request) {
        logger.debug("detail方法:,,Controller:{},,id:{}", this.getClass().getName(), id);
        JipiaoEntity jipiao = jipiaoService.selectById(id);
        if (jipiao != null) {


            //entity转view
            JipiaoView view = new JipiaoView();
            BeanUtils.copyProperties(jipiao, view);//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        } else {
            return R.error(511, "查不到数据");
        }
    }


    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JipiaoEntity jipiao, HttpServletRequest request) {
        logger.debug("add方法:,,Controller:{},,jipiao:{}", this.getClass().getName(), jipiao.toString());
        Wrapper<JipiaoEntity> queryWrapper = new EntityWrapper<JipiaoEntity>()
                .eq("jipiao_name", jipiao.getJipiaoName())
                .eq("jipiao_types", jipiao.getJipiaoTypes())
                .eq("jipiao_chufadi", jipiao.getJipiaoChufadi())
                .eq("jipiao_mudidi", jipiao.getJipiaoMudidi())
                .eq("zuowei_number", jipiao.getZuoweiNumber())
                .eq("shangxia_types", jipiao.getShangxiaTypes())
                .eq("hangban_types", jipiao.getHangbanTypes())
                .eq("jipiao_delete", jipiao.getJipiaoDelete())
//            .notIn("jipiao_types", new Integer[]{102})
                ;
        logger.info("sql语句:" + queryWrapper.getSqlSegment());
        JipiaoEntity jipiaoEntity = jipiaoService.selectOne(queryWrapper);
        if (jipiaoEntity == null) {
            jipiao.setJipiaoDelete(1);
            jipiao.setCreateTime(new Date());
            jipiaoService.insert(jipiao);

            return R.ok();
        } else {
            return R.error(511, "表中有相同数据");
        }
    }

}

