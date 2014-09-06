package com.ihm.model;
import com.ihm.dao.StateInfoDAO;
import com.ihm.service.StateInfoService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class StateInfoDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<StateInfo> data;

	@Autowired
    CountryDataOnDemand countryDataOnDemand;

	@Autowired
    StateInfoService stateInfoService;

	@Autowired
    StateInfoDAO stateInfoDAO;

	public StateInfo getNewTransientStateInfo(int index) {
        StateInfo obj = new StateInfo();
        setCode(obj, index);
        setCreatedBy(obj, index);
        setCreatedOn(obj, index);
        setDescription(obj, index);
        setDisableFlg(obj, index);
        setUpdatedBy(obj, index);
        setUpdatedOn(obj, index);
        return obj;
    }

	public void setCode(StateInfo obj, int index) {
        String code = "code_" + index;
        obj.setCode(code);
    }

	public void setCreatedBy(StateInfo obj, int index) {
        String createdBy = "createdBy_" + index;
        if (createdBy.length() > 16) {
            createdBy = createdBy.substring(0, 16);
        }
        obj.setCreatedBy(createdBy);
    }

	public void setCreatedOn(StateInfo obj, int index) {
        Date createdOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreatedOn(createdOn);
    }

	public void setDescription(StateInfo obj, int index) {
        String description = "description_" + index;
        obj.setDescription(description);
    }

	public void setDisableFlg(StateInfo obj, int index) {
        String disableFlg = String.valueOf(index);
        if (disableFlg.length() > 1) {
            disableFlg = disableFlg.substring(0, 1);
        }
        obj.setDisableFlg(disableFlg);
    }

	public void setUpdatedBy(StateInfo obj, int index) {
        String updatedBy = "updatedBy_" + index;
        if (updatedBy.length() > 16) {
            updatedBy = updatedBy.substring(0, 16);
        }
        obj.setUpdatedBy(updatedBy);
    }

	public void setUpdatedOn(StateInfo obj, int index) {
        Date updatedOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdatedOn(updatedOn);
    }

	public StateInfo getSpecificStateInfo(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        StateInfo obj = data.get(index);
        Long id = obj.getId();
        return stateInfoService.findStateInfo(id);
    }

	public StateInfo getRandomStateInfo() {
        init();
        StateInfo obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return stateInfoService.findStateInfo(id);
    }

	public boolean modifyStateInfo(StateInfo obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = stateInfoService.findStateInfoEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'StateInfo' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<StateInfo>();
        for (int i = 0; i < 10; i++) {
            StateInfo obj = getNewTransientStateInfo(i);
            try {
                stateInfoService.saveStateInfo(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            stateInfoDAO.flush();
            data.add(obj);
        }
    }
}
