package com.ihm.service;

import com.ihm.dao.StateInfoDAO;
import com.ihm.model.StateInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StateInfoServiceImpl implements StateInfoService {

	@Autowired
    StateInfoDAO stateInfoDAO;

	public long countAllStateInfoes() {
        return stateInfoDAO.count();
    }

	public void deleteStateInfo(StateInfo stateInfo) {
        stateInfoDAO.delete(stateInfo);
    }

	public StateInfo findStateInfo(Long id) {
        return stateInfoDAO.findOne(id);
    }

	public List<StateInfo> findAllStateInfoes() {
        return stateInfoDAO.findAll();
    }

	public List<StateInfo> findStateInfoEntries(int firstResult, int maxResults) {
        return stateInfoDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveStateInfo(StateInfo stateInfo) {
        stateInfoDAO.save(stateInfo);
    }

	public StateInfo updateStateInfo(StateInfo stateInfo) {
        return stateInfoDAO.save(stateInfo);
    }
}
