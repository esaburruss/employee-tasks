package com.esaburruss.employeetasks.task;

import org.springframework.data.repository.CrudRepository;

public interface TaskDao extends CrudRepository<Task, Long> {
	
}
