package com.example.ccq.springelasticsearch.config.flowable;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author ZTY
 * @date 2019/9/26 15:34
 * 描述：flowable关于配置文件的两个代理类
 * 为了方便，也可以去掉这两个JAVA类，将其对应的task改写为如下的形式：
 * <userTask id="holidayApprovedTask" name="Holiday approved" flowable:assignee="${employee}"/>
 */
public class BossTaskHandler implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("老板");
    }
}
