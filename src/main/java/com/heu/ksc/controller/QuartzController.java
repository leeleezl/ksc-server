package com.heu.ksc.controller;

import com.alibaba.fastjson.JSON;
import com.heu.ksc.entity.QuartzBean;
import com.heu.ksc.service.impl.QuartzServiceImpl;
import com.heu.ksc.util.AjaxResult;
import com.heu.ksc.util.QuartzUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzServiceImpl quartzService;

    @RequestMapping("/createJob")
    @ResponseBody
    @Transactional
    public String createJob(@RequestBody QuartzBean quartzBean)  {
        try {
            //进行测试所以写死
            quartzBean.setJobClass(quartzBean.getJobClass());
            quartzBean.setJobName(quartzBean.getJobName());
            quartzBean.setCronExpression(quartzBean.getCronExpression());  //"*/10 * * * * ?"
            quartzService.insertJob(quartzBean);
            QuartzUtils.createScheduleJob(scheduler,quartzBean);
        } catch (Exception e) {
            return JSON.toJSONString(AjaxResult.success("创建失败"));
        }
        return JSON.toJSONString(AjaxResult.success("创建成功"));
    }

    @RequestMapping("/pauseJob")
    @ResponseBody
    @Transactional
    public String  pauseJob(@RequestBody QuartzBean quartzBean)  {
        try {
            QuartzUtils.pauseScheduleJob (scheduler, quartzBean.getJobName());
            quartzService.updateJob(quartzBean);
        } catch (Exception e) {
            return JSON.toJSONString(AjaxResult.success("暂停失败"));
        }
        return JSON.toJSONString(AjaxResult.success("暂停成功"));
    }

    @RequestMapping("/runOnce")
    @ResponseBody
    @Transactional
    public String  runOnce(@RequestBody QuartzBean quartzBean)  {
        try {
            QuartzUtils.runOnce (scheduler, quartzBean.getJobName());
        } catch (Exception e) {
            return JSON.toJSONString(AjaxResult.success("运行一次失败"));
        }
        return JSON.toJSONString(AjaxResult.success("运行一次成功"));
    }

    @RequestMapping("/resume")
    @ResponseBody
    @Transactional
    public String resume(@RequestBody QuartzBean quartzBean)  {
        try {
            QuartzUtils.resumeScheduleJob(scheduler, quartzBean.getJobName());
            quartzService.updateJob(quartzBean);
        } catch (Exception e) {
            return JSON.toJSONString(AjaxResult.success("启动失败"));
        }
        return JSON.toJSONString(AjaxResult.success("启动成功"));
    }

    @RequestMapping("/update")
    @ResponseBody
    @Transactional
    public String update(@RequestBody QuartzBean quartzBean)  {
        try {
            //进行测试所以写死
            quartzBean.setJobClass(quartzBean.getJobClass());
            quartzBean.setJobName(quartzBean.getJobName());
            quartzBean.setCronExpression(quartzBean.getCronExpression());
            QuartzUtils.updateScheduleJob(scheduler,quartzBean);
            quartzService.updateJob(quartzBean);
        } catch (Exception e) {
            return JSON.toJSONString(AjaxResult.success("启动失败"));
        }
        return JSON.toJSONString(AjaxResult.success("启动成功"));
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Transactional
    public  String delete(@RequestBody QuartzBean quartzBean) {
        try {
            QuartzUtils.deleteScheduleJob(scheduler, quartzBean.getJobName());
            //quartzService.updateJob(quartzBean);
        } catch (Exception e) {
            return JSON.toJSONString(AjaxResult.success("删除失败"));
        }
        return JSON.toJSONString(AjaxResult.success("删除成功"));
    }

    @RequestMapping("/jobList")
    @ResponseBody
    public String SelectAll(@RequestBody QuartzBean quartzBean) {
        List<QuartzBean> quartzBeans = quartzService.selectAll(quartzBean);
        return JSON.toJSONString(AjaxResult.success("查询成功", quartzBeans));
    }
}
