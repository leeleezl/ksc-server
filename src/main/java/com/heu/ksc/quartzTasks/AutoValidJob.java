package com.heu.ksc.quartzTasks;

import com.heu.ksc.dao.KnowledgeMapper;
import com.heu.ksc.entity.Knowledge;
import com.heu.ksc.service.impl.KnowledgeServiceImpl;
import com.heu.ksc.util.KscConstant;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import sun.awt.datatransfer.DataTransferer;

import java.util.Date;
import java.util.List;

@Slf4j
public class AutoValidJob extends QuartzJobBean {

    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date validTime  = new Date();
        List<Knowledge> knowledgeList = knowledgeMapper.autoValidList(validTime);
        for (Knowledge knowledge : knowledgeList) {
            knowledge.setStatus(KscConstant.VALID);
            knowledgeMapper.updateById(knowledge);
        }
        log.info(validTime + "：执行了一次自动上架");
    }
}
