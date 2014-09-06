package com.ihm.model.customer;
import com.ihm.dao.customer.CustomerUserCredentialDAO;
import com.ihm.service.customer.CustomerUserCredentialService;
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

@Component
@Configurable
public class CustomerUserCredentialDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<CustomerUserCredential> data;

	@Autowired
    CustomerUserCredentialService customerUserCredentialService;

	@Autowired
    CustomerUserCredentialDAO customerUserCredentialDAO;

	public CustomerUserCredential getNewTransientCustomerUserCredential(int index) {
        CustomerUserCredential obj = new CustomerUserCredential();
        setCreatedBy(obj, index);
        setCreatedOn(obj, index);
        setDisableFlg(obj, index);
        setPassKey(obj, index);
        setPasswordExpiryDate(obj, index);
        setUpdatedBy(obj, index);
        setUpdatedOn(obj, index);
        setUserActivationDate(obj, index);
        setUserActiveFlg(obj, index);
        setUserCategory(obj, index);
        setUserExpiryDate(obj, index);
        setUserId(obj, index);
        setUserLockedFlg(obj, index);
        setUserLoginAttempt(obj, index);
        return obj;
    }

	public void setCreatedBy(CustomerUserCredential obj, int index) {
        String createdBy = "createdBy_" + index;
        if (createdBy.length() > 16) {
            createdBy = createdBy.substring(0, 16);
        }
        obj.setCreatedBy(createdBy);
    }

	public void setCreatedOn(CustomerUserCredential obj, int index) {
        Date createdOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreatedOn(createdOn);
    }

	public void setDisableFlg(CustomerUserCredential obj, int index) {
        String disableFlg = String.valueOf(index);
        if (disableFlg.length() > 1) {
            disableFlg = disableFlg.substring(0, 1);
        }
        obj.setDisableFlg(disableFlg);
    }

	public void setPassKey(CustomerUserCredential obj, int index) {
        String passKey = "passKey_" + index;
        if (passKey.length() > 20) {
            passKey = passKey.substring(0, 20);
        }
        obj.setPassKey(passKey);
    }

	public void setPasswordExpiryDate(CustomerUserCredential obj, int index) {
        Date passwordExpiryDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setPasswordExpiryDate(passwordExpiryDate);
    }

	public void setUpdatedBy(CustomerUserCredential obj, int index) {
        String updatedBy = "updatedBy_" + index;
        if (updatedBy.length() > 16) {
            updatedBy = updatedBy.substring(0, 16);
        }
        obj.setUpdatedBy(updatedBy);
    }

	public void setUpdatedOn(CustomerUserCredential obj, int index) {
        Date updatedOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdatedOn(updatedOn);
    }

	public void setUserActivationDate(CustomerUserCredential obj, int index) {
        Date userActivationDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUserActivationDate(userActivationDate);
    }

	public void setUserActiveFlg(CustomerUserCredential obj, int index) {
        String userActiveFlg = String.valueOf(index);
        if (userActiveFlg.length() > 1) {
            userActiveFlg = userActiveFlg.substring(0, 1);
        }
        obj.setUserActiveFlg(userActiveFlg);
    }

	public void setUserCategory(CustomerUserCredential obj, int index) {
        String userCategory = String.valueOf(index);
        if (userCategory.length() > 1) {
            userCategory = userCategory.substring(0, 1);
        }
        obj.setUserCategory(userCategory);
    }

	public void setUserExpiryDate(CustomerUserCredential obj, int index) {
        Date userExpiryDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUserExpiryDate(userExpiryDate);
    }

	public void setUserId(CustomerUserCredential obj, int index) {
        String userId = "userId_" + index;
        obj.setUserId(userId);
    }

	public void setUserLockedFlg(CustomerUserCredential obj, int index) {
        String userLockedFlg = String.valueOf(index);
        if (userLockedFlg.length() > 1) {
            userLockedFlg = userLockedFlg.substring(0, 1);
        }
        obj.setUserLockedFlg(userLockedFlg);
    }

	public void setUserLoginAttempt(CustomerUserCredential obj, int index) {
        Integer userLoginAttempt = new Integer(index);
        obj.setUserLoginAttempt(userLoginAttempt);
    }

	public CustomerUserCredential getSpecificCustomerUserCredential(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        CustomerUserCredential obj = data.get(index);
        Long id = obj.getId();
        return customerUserCredentialService.findCustomerUserCredential(id);
    }

	public CustomerUserCredential getRandomCustomerUserCredential() {
        init();
        CustomerUserCredential obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return customerUserCredentialService.findCustomerUserCredential(id);
    }

	public boolean modifyCustomerUserCredential(CustomerUserCredential obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = customerUserCredentialService.findCustomerUserCredentialEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'CustomerUserCredential' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<CustomerUserCredential>();
        for (int i = 0; i < 10; i++) {
            CustomerUserCredential obj = getNewTransientCustomerUserCredential(i);
            try {
                customerUserCredentialService.saveCustomerUserCredential(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            customerUserCredentialDAO.flush();
            data.add(obj);
        }
    }
}
