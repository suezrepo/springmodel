package com.ihm.dao;
import com.ihm.model.StateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StateInfoDAO extends JpaRepository<StateInfo, Long>, JpaSpecificationExecutor<StateInfo> {
}
