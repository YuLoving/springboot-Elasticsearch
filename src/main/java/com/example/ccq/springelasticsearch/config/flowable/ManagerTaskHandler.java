package com.example.ccq.springelasticsearch.config.flowable;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author ZTY
 * @date 2019/9/26 15:31
 * 描述：flowable关于配置文件的两个代理类
 */
public class ManagerTaskHandler implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("经理");
    }
}
