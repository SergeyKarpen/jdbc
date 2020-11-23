package com.karpen.jdbc.repository;
import com.karpen.jdbc.model.Skill;

import java.io.IOException;
import java.sql.SQLException;

public interface SkillRepository extends GenericRepository <Skill, Long> {

    Skill create(Skill skill) throws IOException, SQLException;
}
