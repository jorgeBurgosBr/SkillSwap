package com.skill_swap.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.skill_swap.entidades.Skill;

@Repository
public interface SkillRepositorio extends CrudRepository<Skill, Long>{

}