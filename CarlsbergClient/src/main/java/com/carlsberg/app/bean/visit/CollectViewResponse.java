package com.carlsberg.app.bean.visit;

import java.util.List;

/**
 * Created by LiesLee on 17/3/2.
 */

public class CollectViewResponse {

    private List<TaskCollect> task_collect;
    private List<TaskCollect> task_score;

    public List<TaskCollect> getTask_collect() {
        return task_collect;
    }

    public void setTask_collect(List<TaskCollect> task_collect) {
        this.task_collect = task_collect;
    }

    public List<TaskCollect> getTask_score() {
        return task_score;
    }

    public void setTask_score(List<TaskCollect> task_score) {
        this.task_score = task_score;
    }


}
