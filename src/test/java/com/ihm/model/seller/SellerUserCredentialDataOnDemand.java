package com.ihm.model.seller;
import com.ihm.dao.seller.SellerUserCredentialDAO;
import com.ihm.service.seller.SellerUserCredentialService;
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
public class SellerUserCredentialDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<SellerUserCredential> data;

	@Autowired
    SellerUserCredentialService sellerUserCredentialService;

	@Autowired
    SellerUserCredentialDAO sellerUserCredentialDAO;

	public SellerUserCredential getNewTransientSellerUserCredential(int index) {
        SellerUserCredential obj = new SellerUserCredential();
        setCreatedBy(obj, index);
        setCreatedOn(obj, index);
        setDisableFlg(obj, index);
        setInternalUserId(obj, index);
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

	public void setCreatedBy(SellerUserCredential obj, int index) {
        String createdBy = "createdBy_" + index;
        if (createdBy.length() > 16) {
            createdBy = createdBy.substring(0, 16);
        }
        obj.setCreatedBy(createdBy);
    }

	public void setCreatedOn(SellerUserCredential obj, int index) {
        Date createdOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreatedOn(createdOn);
    }

	public void setDisableFlg(SellerUserCredential obj, int index) {
        String disableFlg = String.valueOf(index);
        if (disableFlg.length() > 1) {
            disableFlg = disableFlg.substring(0, 1);
        }
        obj.setDisableFlg(disableFlg);
    }

	public void setInternalUserId(SellerUserCredential obj, int index) {
        String internalUserId = "internalUserId_" + index;
        if (internalUserId.length() > 16) {
            internalUserId = internalUserId.substring(0, 16);
        }
        obj.setInternalUserId(internalUserId);
    }

	public void setPassKey(SellerUserCredential obj, int index) {
        String passKey = "passKey_" + index;
        if (passKey.length() > 20) {
            passKey = passKey.substring(0, 20);
        }
        obj.setPassKey(passKey);
    }

	public void setPasswordExpiryDate(SellerUserCredential obj, int index) {
        Date passwordExpiryDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setPasswordExpiryDate(passwordExpiryDate);
    }

	public void setUpdatedBy(SellerUserCredential obj, int index) {
        String updatedBy = "updatedBy_" + index;
        if (updatedBy.length() > 16) {
            updatedBy = updatedBy.substring(0, 16);
        }
        obj.setUpdatedBy(updatedBy);
    }

	public void setUpdatedOn(SellerUserCredential obj, int index) {
        Date updatedOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdatedOn(updatedOn);
    }

	public void setUserActivationDate(SellerUserCredential obj, int index) {
        Date userActivationDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUserActivationDate(userActivationDate);
    }

	public void setUserActiveFlg(SellerUserCredential obj, int index) {
        String userActiveFlg = String.valueOf(index);
        if (userActiveFlg.length() > 1) {
            userActiveFlg = userActiveFlg.substring(0, 1);
        }
        obj.setUserActiveFlg(userActiveFlg);
    }

	public void setUserCategory(SellerUserCredential obj, int index) {
        String userCategory = String.valueOf(index);
        if (userCategory.length() > 1) {
            userCategory = userCategory.substring(0, 1);
        }
        obj.setUserCategory(userCategory);
    }

	public void setUserExpiryDate(SellerUserCredential obj, int index) {
        Date userExpiryDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUserExpiryDate(userExpiryDate);
    }

	public void setUserId(SellerUserCredential obj, int index) {
        String userId = "userId_" + index;
        obj.setUserId(userId);
    }

	public void setUserLockedFlg(SellerUserCredential obj, int index) {
        String userLockedFlg = String.valueOf(index);
        if (userLockedFlg.length() > 1) {
            userLockedFlg = userLockedFlg.substring(0, 1);
        }
        obj.setUserLockedFlg(userLockedFlg);
    }

	public void setUserLoginAttempt(SellerUserCredential obj, int index) {
        Integer userLoginAttempt = new Integer(index);
        obj.setUserLoginAttempt(userLoginAttempt);
    }

	public SellerUserCredential getSpecificSellerUserCredential(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        SellerUserCredential obj = data.get(index);
        Long id = obj.getId();
        return sellerUserCredentialService.findSellerUserCredential(id);
    }

	public SellerUserCredential getRandomSellerUserCredential() {
        init();
        SellerUserCredential obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return sellerUserCredentialService.findSellerUserCredential(id);
    }

	public boolean modifySellerUserCredential(SellerUserCredential obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = sellerUserCredentialService.findSellerUserCredentialEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'SellerUserCredential' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<SellerUserCredential>();
        for (int i = 0; i < 10; i++) {
            SellerUserCredential obj = getNewTransientSellerUserCredential(i);
            try {
                sellerUserCredentialService.saveSellerUserCredential(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            sellerUserCredentialDAO.flush();
            data.add(obj);
        }
    }
}
