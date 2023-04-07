package com.heu.ksc.controller;

import com.alibaba.fastjson.JSON;
import com.heu.ksc.entity.Comment;
import com.heu.ksc.service.CommentService;
import com.heu.ksc.service.impl.CommentServiceImpl;
import com.heu.ksc.util.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @RequestMapping("/add/{knowledgeId}")
    @ResponseBody
    public String addComment(@PathVariable("knowledgeId") String knowledgeId, @RequestBody Comment comment) {
        log.info(comment.getContent());
        commentService.addComment(Integer.valueOf(knowledgeId), comment);
        return JSON.toJSONString(AjaxResult.success("操作成功"));
    }
}
