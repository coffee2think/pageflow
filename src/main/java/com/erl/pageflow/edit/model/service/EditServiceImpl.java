package com.erl.pageflow.edit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("editService")
public class EditServiceImpl implements EditService {
	@Autowired
	private EditService editservice;
}
