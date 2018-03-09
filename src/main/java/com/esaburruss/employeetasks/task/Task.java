package com.esaburruss.employeetasks.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "task")
@JsonIgnoreProperties(value={ "id" }, allowGetters=true)
public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "category")
    private String category;
	
	@Column(name = "assignee")
    private String assignee;
	
	@Column(name = "description")
    private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "due_date")
    private Date dueDate;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completed_date")
    private Date completedDate;
	
	@Column(name = "completed")
    private Boolean completed;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm")
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm")
	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
	public void updateWith(Task taskDelta) {
		this.name = updateTo(taskDelta.getName(), this.name);
		this.description = updateTo(taskDelta.getDescription(), this.description);
		this.assignee = updateTo(taskDelta.getAssignee(), this.assignee);
		this.category = updateTo(taskDelta.getCategory(), this.category);
		this.dueDate = updateTo(taskDelta.getDueDate(), this.dueDate);
		this.completedDate = updateTo(taskDelta.getCompletedDate(), this.completedDate);
		this.completed = updateTo(taskDelta.getCompleted(), this.completed);
	}
	
	private <T> T updateTo(T delta, T defaultValue) {
        if (delta != null) {
            return delta;
        }
        return defaultValue;
    }

}
