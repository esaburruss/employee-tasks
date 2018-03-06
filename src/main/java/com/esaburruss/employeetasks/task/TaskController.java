package com.esaburruss.employeetasks.task;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Controller
@RequestMapping(value = "/v1/tasks")
public class TaskController {
	
	@Autowired
	private TaskDao dao;
	
	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Task> getAll() {
        return dao.findAll();
    }
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> add(@RequestBody Task task) {
        try {
        	return new ResponseEntity<>(dao.save(task), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
        	return get400(ex);
        }
    }
	
	@RequestMapping(method = RequestMethod.PUT, value="/{taskId}")
    @ResponseBody
    public ResponseEntity<?> add(@PathVariable("taskId") Long taskId, @RequestBody Task task) {
        if(dao.existsById(taskId)) {
        	try {
        		Task taskToUpdate = dao.findById(taskId).get();
        		taskToUpdate.updateWith(task);
        		return new ResponseEntity<>(dao.save(task), HttpStatus.CREATED);
            } catch (DataIntegrityViolationException ex) {
            	return get400(ex);
            }
        } else {
        	return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{taskId}")
    @ResponseBody
    public ResponseEntity<?> add(@PathVariable("taskId") Long taskId) {
        if(dao.existsById(taskId)) {
        	try {
        		dao.deleteById(taskId);
        	} catch(Exception ex) {
        		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
        	return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }
	
	private ResponseEntity<?> get400(DataIntegrityViolationException ex) {
		if(ex.getCause().getCause() instanceof MySQLIntegrityConstraintViolationException) {
    		return new ResponseEntity<>("Name and Due Date have a unique together constraint", HttpStatus.BAD_REQUEST);
    	} else if(ex.getCause().getCause() instanceof MysqlDataTruncation) {
    		return new ResponseEntity<>(ex.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST);
    	} else {
    		return new ResponseEntity<>(ex.getCause().getMessage(), HttpStatus.BAD_REQUEST);
    	}
	}
}
