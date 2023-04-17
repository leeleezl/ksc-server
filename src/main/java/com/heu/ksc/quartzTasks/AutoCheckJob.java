package com.heu.ksc.quartzTasks;

import com.heu.ksc.dao.KnowledgeMapper;
import com.heu.ksc.entity.Knowledge;
import com.heu.ksc.util.KscConstant;
import com.heu.ksc.util.SensitiveFilter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

@Slf4j
public class AutoCheckJob extends QuartzJobBean {
    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // 找到所有状态为审核中（1）的知识
        List<Knowledge> newKnowledgeList = knowledgeMapper.selectNew();
        // 检查Knowledge
        for (Knowledge knowledge : newKnowledgeList) {
            if (sensitiveFilter.filter(knowledge.getContent()) || sensitiveFilter.filter(knowledge.getAbstractInfo()) || sensitiveFilter.filter(knowledge.getkName())) {
                //修改状态为未通过
                log.error("知识" + knowledge.getId() + "审核未通过，存在敏感词");
                knowledge.setStatus(KscConstant.FAILED_PASS);
            } else {
                log.error("知识" + knowledge.getId() + "审核通过");
                knowledge.setStatus(KscConstant.RO_PASS);
            }
            knowledgeMapper.edit(knowledge);
        }
    }
}
