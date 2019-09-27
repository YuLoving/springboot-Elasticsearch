package com.example.ccq.springelasticsearch.controller;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZTY
 * @date 2019/9/26 15:38
 * 描述：测试flowbale
 */
@RequestMapping("/flowable")
@RestController
public class flowableTestContrllor {

    /**
     * 并注入由flowable框架启动时自动注册的几个bean，下面的功能将会用到！
     */
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;

    /**
     * 添加报销
     *
     * @param userId 用户ID
     * @param money  报销金额
     * @param dec    描述
     * @return 代码通过接收用户的一个请求传入用户的ID和金额以及描述信息来开启一个报销流程，并返回给用户这个流程的Id
     */
    @GetMapping("/add")
    public String add(String userId, String money, String dec) {
        //启动流程
        Map<String, Object> map = new HashMap<>();
        map.put("taskUser", userId);
        map.put("money", money);
        ProcessInstance expense = runtimeService.startProcessInstanceByKey("Expense", map);
        return "提交流程成功，流程ID为" + expense.getId();
    }

    /**
     * 查询流程列表，待办列表
     *
     * @param userId 用户ID
     * @return 代码获取出此用户需要处理的流程
     */
    @GetMapping("/taglist")
    public Object taglist(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        tasks.forEach(e -> {
            //遍历一下，所有的流程
            System.out.println(e);
        });
        return tasks.toArray().toString();
    }

    /**
     * 批准，同意
     *
     * @param taskId 流程ID
     * @return 通过前端传入的任务ID来对此流程进行同意处理
     */
    @GetMapping("/apply")
    public String apply(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        //通过审核
        Map<String, Object> map = new HashMap<>();
        map.put("outcome", "通过");
        taskService.complete(taskId, map);
        return "流程批准通过";
    }

    /**
     * 拒绝，不同意
     *
     * @param taskId 流程ID
     * @return 通过前端传入的任务ID来对此流程进行拒绝处理
     */
    @GetMapping("/reject")
    public Object reject(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        //拒绝审核
        Map<String, Object> map = new HashMap<>();
        map.put("outcome", "驳回");
        taskService.complete(taskId, map);
        return "流程被拒绝";
    }

    /**
     * 生成当前流程图表
     *
     * @param response
     * @param processId 任务ID
     * @throws Exception
     */
    @GetMapping("/processDiagram")
    public void genProcessDiagram(HttpServletResponse response, String processId) throws Exception {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        //流程走完的不显示图
        if (pi == null) {
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        String instanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(instanceId).list();
        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        executions.forEach(e -> {
            List<String> ids = runtimeService.getActiveActivityIds(e.getId());
            activityIds.addAll(ids);

        });
        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = response.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }

        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }

        }


    }


}
