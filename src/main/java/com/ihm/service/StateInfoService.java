package com.ihm.service;
import com.ihm.model.StateInfo;
import java.util.List;

public interface StateInfoService {

	public abstract long countAllStateInfoes();


	public abstract void deleteStateInfo(StateInfo stateInfo);


	public abstract StateInfo findStateInfo(Long id);


	public abstract List<StateInfo> findAllStateInfoes();


	public abstract List<StateInfo> findStateInfoEntries(int firstResult, int maxResults);


	public abstract void saveStateInfo(StateInfo stateInfo);


	public abstract StateInfo updateStateInfo(StateInfo stateInfo);

}
