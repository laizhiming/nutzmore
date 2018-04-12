package org.nutz.integration.activiti.test;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class ProcessEndListener implements ExecutionListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateExecution execution) {
        System.out.println(execution.getProcessInstanceBusinessKey());
        System.out.println(execution.getProcessInstanceId());
    }

}