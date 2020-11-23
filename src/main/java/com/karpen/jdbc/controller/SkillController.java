package com.karpen.jdbc.controller;

import com.karpen.jdbc.repository.SkillRepository;
import com.karpen.jdbc.repository.jdbc.JdbcSkillsRepositoryImpl;

public class SkillController {
private SkillRepository skillRepository = new JdbcSkillsRepositoryImpl();



}
