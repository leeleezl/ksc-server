package com.heu.ksc.quartzTasks;

import com.heu.ksc.dao.KnowledgeMapper;
import com.heu.ksc.entity.Knowledge;
import com.heu.ksc.util.KscConstant;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Slf4j
public class AutoInvalidJob extends QuartzJobBean {
    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date now = new Date();

        //找到状态为已上架的知识
        List<Knowledge> knowledgeList = knowledgeMapper.autoInvalidList();
        for (Knowledge knowledge : knowledgeList) {
            Date invalidTime = knowledge.getInvalidTime();
            if (invalidTime != null) {
                // 下架时间在当前时间之前
                if(invalidTime.before(now)) {
                    log.error("知识" + knowledge.getId() + "已下架");
                    knowledge.setStatus(KscConstant.INVALID);
                    knowledgeMapper.edit(knowledge);
                }
            }
        }
    }
}
