package com.karpen.jdbc.repository;
import com.karpen.jdbc.model.Skill;

import java.io.IOException;

public interface SkillRepository extends GenericRepository <Skill, Long> {

    Skill create(Skill skill) throws IOException;
}
