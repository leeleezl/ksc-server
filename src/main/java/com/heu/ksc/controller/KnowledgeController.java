package com.heu.ksc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.ksc.entity.Knowledge;
import com.heu.ksc.service.KnowledgeService;
import com.heu.ksc.util.AjaxResult;
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
    @RequestMapping("/listCheck")
    @ResponseBody
    public String listCheckKnowledge(@RequestBody Knowledge knowledge) {
        List<Knowledge> knowledgeList = knowledgeService.listCheck(knowledge);
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
        knowledgeService.updateById(knowledge);
        if(knowledge.getStatus() == 1) {
            return JSON.toJSONString(AjaxResult.success("审核通过"));
        } else {
            //TODO: 通知上传人
            return JSON.toJSONString(AjaxResult.success("审核未通过，已通知上传人"));
        }
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(@RequestBody Knowledge knowledge) {
        knowledge.setStatus(0);
        knowledgeService.edit(knowledge);
        return JSON.toJSONString(AjaxResult.success("修改成功，请等待审核"));
    }

}
