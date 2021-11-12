/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.P0071
 * Title: Task Manager
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-10-9        1.0                AnhDV            First Implement
 */
package entity;

/**
 * The {@code TaskType} class contains attribute of TaskType object. This class
 * describes the job of an employee
 *
 * @author Dang Viet Anh
 */
public class TaskType {

    private int taskTypeID;  // id of task taskType
    private String taskType; // name of task taskType

    /**
     * This is a default constructor with no parameters
     */
    public TaskType() {
    }

    /**
     * This is the constructor to pass the parameter of taskType.
     *
     * @param taskTypeID // id of task type
     * @param taskType // name of task type
     */
    public TaskType(int taskTypeID, String taskType) {
        this.taskTypeID = taskTypeID;
        this.taskType = taskType;
    }

    /**
     * This method get Task type ID and return
     *
     * @return integer that is task ID.
     */
    public int getTaskTypeID() {
        return taskTypeID;
    }

    /**
     * This method get name of Task type and return it.
     *
     * @return String taskType
     */
    public String getTaskType() {
        return taskType;
    }

}
