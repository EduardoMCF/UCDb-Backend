package com.psoft.UCDb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.psoft.UCDb.rest.model.Subject;
import com.psoft.UCDb.service.SubjectService;

@Component
public class DataLoader implements ApplicationRunner {

    private SubjectService subjectService;

    @Autowired
    public DataLoader(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public void run(ApplicationArguments args) throws FileNotFoundException {
		this.populateDataBase();
    }
    
    public void populateDataBase() throws FileNotFoundException {
    	HashMap<String,String> subjects = this.loadData();
    	
    	for (Entry<String, String> item : subjects.entrySet()) {
        	int id = Integer.parseInt(item.getKey()) + 1;
        	String name = item.getValue();
        	this.insertSubject(id,name);
		}
    }
    
    private HashMap<String, String> loadData() throws FileNotFoundException {
    	Gson gson = new Gson();
        String path = new File("src/main/resources/subject.json").getAbsolutePath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        HashMap<String, String> subjects = gson.fromJson(bufferedReader, HashMap.class);
        return subjects;
    }
    
    
    private void insertSubject(int id, String name) {
    	Subject subject = new Subject(id,name);
    	Subject newSubject = this.subjectService.create(subject);

		if (newSubject == null) {
			throw new InternalError("Something went wrong");
		}
    }
}