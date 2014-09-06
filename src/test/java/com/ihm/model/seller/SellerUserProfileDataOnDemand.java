package com.ihm.model.seller;
import com.ihm.dao.seller.SellerUserProfileDAO;
import com.ihm.service.seller.SellerUserProfileService;
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
public class SellerUserProfileDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<SellerUserProfile> data;

	@Autowired
    SellerUserCredentialDataOnDemand sellerUserCredentialDataOnDemand;

	@Autowired
    SellerInfoDataOnDemand sellerInfoDataOnDemand;

	@Autowired
    SellerUserProfileService sellerUserProfileService;

	@Autowired
    SellerUserProfileDAO sellerUserProfileDAO;

	public SellerUserProfile getNewTransientSellerUserProfile(int index) {
        SellerUserProfile obj = new SellerUserProfile();
        setCreatedBy(obj, index);
        setCreatedOn(obj, index);
        setDisableFlg(obj, index);
        setDob(obj, index);
        setEmailAddress(obj, index);
        setEmergencyContactNo(obj, index);
        setGender(obj, index);
        setGivenName(obj, index);
        setLastName(obj, index);
        setPrimaryPhoneNo(obj, index);
        setSecondaryPhoneNo(obj, index);
        setUpdatedBy(obj, index);
        setUpdatedOn(obj, index);
        return obj;
    }

	public void setCreatedBy(SellerUserProfile obj, int index) {
        String createdBy = "createdBy_" + index;
        if (createdBy.length() > 16) {
            createdBy = createdBy.substring(0, 16);
        }
        obj.setCreatedBy(createdBy);
    }

	public void setCreatedOn(SellerUserProfile obj, int index) {
        Date createdOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreatedOn(createdOn);
    }

	public void setDisableFlg(SellerUserProfile obj, int index) {
        String disableFlg = String.valueOf(index);
        if (disableFlg.length() > 1) {
            disableFlg = disableFlg.substring(0, 1);
        }
        obj.setDisableFlg(disableFlg);
    }

	public void setDob(SellerUserProfile obj, int index) {
        Date dob = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setDob(dob);
    }

	public void setEmailAddress(SellerUserProfile obj, int index) {
        String emailAddress = "foo" + index + "@bar.com";
        obj.setEmailAddress(emailAddress);
    }

	public void setEmergencyContactNo(SellerUserProfile obj, int index) {
        String emergencyContactNo = "emergencyConta_" + index;
        if (emergencyContactNo.length() > 16) {
            emergencyContactNo = emergencyContactNo.substring(0, 16);
        }
        obj.setEmergencyContactNo(emergencyContactNo);
    }

	public void setGender(SellerUserProfile obj, int index) {
        String gender = "gen_" + index;
        if (gender.length() > 5) {
            gender = gender.substring(0, 5);
        }
        obj.setGender(gender);
    }

	public void setGivenName(SellerUserProfile obj, int index) {
        String givenName = "givenName_" + index;
        obj.setGivenName(givenName);
    }

	public void setLastName(SellerUserProfile obj, int index) {
        String lastName = "lastName_" + index;
        obj.setLastName(lastName);
    }

	public void setPrimaryPhoneNo(SellerUserProfile obj, int index) {
        String primaryPhoneNo = "primaryPhoneNo_" + index;
        if (primaryPhoneNo.length() > 16) {
            primaryPhoneNo = primaryPhoneNo.substring(0, 16);
        }
        obj.setPrimaryPhoneNo(primaryPhoneNo);
    }

	public void setSecondaryPhoneNo(SellerUserProfile obj, int index) {
        String secondaryPhoneNo = "secondaryPhone_" + index;
        if (secondaryPhoneNo.length() > 16) {
            secondaryPhoneNo = secondaryPhoneNo.substring(0, 16);
        }
        obj.setSecondaryPhoneNo(secondaryPhoneNo);
    }

	public void setUpdatedBy(SellerUserProfile obj, int index) {
        String updatedBy = "updatedBy_" + index;
        if (updatedBy.length() > 16) {
            updatedBy = updatedBy.substring(0, 16);
        }
        obj.setUpdatedBy(updatedBy);
    }

	public void setUpdatedOn(SellerUserProfile obj, int index) {
        Date updatedOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdatedOn(updatedOn);
    }

	public SellerUserProfile getSpecificSellerUserProfile(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        SellerUserProfile obj = data.get(index);
        Long id = obj.getId();
        return sellerUserProfileService.findSellerUserProfile(id);
    }

	public SellerUserProfile getRandomSellerUserProfile() {
        init();
        SellerUserProfile obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return sellerUserProfileService.findSellerUserProfile(id);
    }

	public boolean modifySellerUserProfile(SellerUserProfile obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = sellerUserProfileService.findSellerUserProfileEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'SellerUserProfile' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<SellerUserProfile>();
        for (int i = 0; i < 10; i++) {
            SellerUserProfile obj = getNewTransientSellerUserProfile(i);
            try {
                sellerUserProfileService.saveSellerUserProfile(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            sellerUserProfileDAO.flush();
            data.add(obj);
        }
    }
}
