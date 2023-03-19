package com.heu.ksc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.ksc.entity.Knowledge;
import com.heu.ksc.service.KnowledgeService;
import com.heu.ksc.util.AjaxResult;
import com.heu.ksc.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/knowledge")
@Slf4j
@CrossOrigin
public class KnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private TokenUtil tokenUtil;

    @RequestMapping("/list")
    public String listByPage(@RequestBody Knowledge knowledge) {
        //查询知识列表
        List<Knowledge> knowledgeList = knowledgeService.list(knowledge);
        //封装到pageInfo
        PageInfo<Knowledge> pageInfo = new PageInfo<>(knowledgeList);
        return JSON.toJSONString(AjaxResult.success("查询成功",pageInfo));
    }


    /**
     * 新增知识
     * @param knowledge
     * @return
     */
    @RequestMapping ("/addKnowledge")
    @ResponseBody
    public String addKnowledge(@RequestBody Knowledge knowledge) {
        log.info(knowledge.toString());
        knowledgeService.add(knowledge);
        return JSON.toJSONString(AjaxResult.success("新增成功，请等待审核"));
    }

    /**
     * 审核列表
     * @param knowledge
     * @return
     */
    @RequestMapping("/checkList")
    @ResponseBody
    public String listCheckKnowledge(@RequestBody Knowledge knowledge) {
        List<Knowledge> knowledgeList = knowledgeService.checkList(knowledge);
        PageInfo<Knowledge> pageInfo = new PageInfo<>(knowledgeList);
        return JSON.toJSONString(AjaxResult.success("查询成功",pageInfo));
    }
    /**
     * 审核
     * @param knowledge
     * @return
     */
    @RequestMapping("/check")
    @ResponseBody
    public String check(@RequestBody Knowledge knowledge) {
        knowledge.setCheckedName(tokenUtil.getLoginUser().getUsername());
        knowledgeService.updateById(knowledge);
        if(knowledge.getStatus() == 3) {
            return JSON.toJSONString(AjaxResult.success("审核通过"));
        } else {
            //TODO: 通知上传人
            return JSON.toJSONString(AjaxResult.success("审核未通过，已通知上传人"));
        }
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(@RequestBody Knowledge knowledge) {
        knowledge.setStatus(1);
        knowledgeService.edit(knowledge);
        return JSON.toJSONString(AjaxResult.success("修改成功，请等待审核"));
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public String selectById(Integer id) {
        Knowledge knowledge = new Knowledge();
        knowledge.setId(id);
        Knowledge k = knowledgeService.selectById(knowledge);
        return JSON.toJSONString(AjaxResult.success("查询成功", k));
    }

    @RequestMapping("/myKnowledge")
    @ResponseBody
    public String getMyKnowledgeList(@RequestBody Knowledge knowledge) {
        List<Knowledge> myKnowledgeList = knowledgeService.getMyKnowledgeList(knowledge);
        PageInfo<Knowledge> myKnowledgePage = new PageInfo<>(myKnowledgeList);
        return JSON.toJSONString(AjaxResult.success("查询成功",myKnowledgePage));
    }

    @RequestMapping("/collect")
    @ResponseBody
    public String collect(@RequestBody Knowledge knowledge) {
        knowledgeService.collect(knowledge);
        return JSON.toJSONString(AjaxResult.success("操作成功"));
    }

    @RequestMapping("/myCollect")
    @ResponseBody
    public String MyCollectList(@RequestBody Knowledge knowledge){
        List<Knowledge> myCollectList = knowledgeService.myCollectList(knowledge);
        PageInfo<Knowledge> myCollectPage = new PageInfo<>(myCollectList);
        return JSON.toJSONString(AjaxResult.success("查询成功", myCollectPage));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteKnowledge(Integer id) {
        knowledgeService.deleteKnowledge(id);
        return JSON.toJSONString(AjaxResult.success("删除成功"));
    }

}
