package com.osiristher.webapp.dbtest.service;

import com.osiristher.webapp.dbtest.domain.Trainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DesiresDesigner on 3/23/16.
 */
@Repository
public interface TrainerRepo extends CrudRepository<Trainer, Long> {
    Trainer findByLrid(long lrid);
}
