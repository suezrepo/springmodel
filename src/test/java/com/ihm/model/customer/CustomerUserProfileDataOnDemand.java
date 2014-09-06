package com.ihm.model.customer;
import com.ihm.dao.customer.CustomerUserProfileDAO;
import com.ihm.service.customer.CustomerUserProfileService;
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
public class CustomerUserProfileDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<CustomerUserProfile> data;

	@Autowired
    CustomerAddressDataOnDemand customerAddressDataOnDemand;

	@Autowired
    CustomerUserCredentialDataOnDemand customerUserCredentialDataOnDemand;

	@Autowired
    CustomerUserProfileService customerUserProfileService;

	@Autowired
    CustomerUserProfileDAO customerUserProfileDAO;

	public CustomerUserProfile getNewTransientCustomerUserProfile(int index) {
        CustomerUserProfile obj = new CustomerUserProfile();
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

	public void setCreatedBy(CustomerUserProfile obj, int index) {
        String createdBy = "createdBy_" + index;
        if (createdBy.length() > 16) {
            createdBy = createdBy.substring(0, 16);
        }
        obj.setCreatedBy(createdBy);
    }

	public void setCreatedOn(CustomerUserProfile obj, int index) {
        Date createdOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreatedOn(createdOn);
    }

	public void setDisableFlg(CustomerUserProfile obj, int index) {
        String disableFlg = String.valueOf(index);
        if (disableFlg.length() > 1) {
            disableFlg = disableFlg.substring(0, 1);
        }
        obj.setDisableFlg(disableFlg);
    }

	public void setDob(CustomerUserProfile obj, int index) {
        Date dob = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setDob(dob);
    }

	public void setEmailAddress(CustomerUserProfile obj, int index) {
        String emailAddress = "foo" + index + "@bar.com";
        obj.setEmailAddress(emailAddress);
    }

	public void setEmergencyContactNo(CustomerUserProfile obj, int index) {
        String emergencyContactNo = "emergencyConta_" + index;
        if (emergencyContactNo.length() > 16) {
            emergencyContactNo = emergencyContactNo.substring(0, 16);
        }
        obj.setEmergencyContactNo(emergencyContactNo);
    }

	public void setGender(CustomerUserProfile obj, int index) {
        String gender = "gen_" + index;
        if (gender.length() > 5) {
            gender = gender.substring(0, 5);
        }
        obj.setGender(gender);
    }

	public void setGivenName(CustomerUserProfile obj, int index) {
        String givenName = "givenName_" + index;
        obj.setGivenName(givenName);
    }

	public void setLastName(CustomerUserProfile obj, int index) {
        String lastName = "lastName_" + index;
        obj.setLastName(lastName);
    }

	public void setPrimaryPhoneNo(CustomerUserProfile obj, int index) {
        String primaryPhoneNo = "primaryPhoneNo_" + index;
        if (primaryPhoneNo.length() > 16) {
            primaryPhoneNo = primaryPhoneNo.substring(0, 16);
        }
        obj.setPrimaryPhoneNo(primaryPhoneNo);
    }

	public void setSecondaryPhoneNo(CustomerUserProfile obj, int index) {
        String secondaryPhoneNo = "secondaryPhone_" + index;
        if (secondaryPhoneNo.length() > 16) {
            secondaryPhoneNo = secondaryPhoneNo.substring(0, 16);
        }
        obj.setSecondaryPhoneNo(secondaryPhoneNo);
    }

	public void setUpdatedBy(CustomerUserProfile obj, int index) {
        String updatedBy = "updatedBy_" + index;
        if (updatedBy.length() > 16) {
            updatedBy = updatedBy.substring(0, 16);
        }
        obj.setUpdatedBy(updatedBy);
    }

	public void setUpdatedOn(CustomerUserProfile obj, int index) {
        Date updatedOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdatedOn(updatedOn);
    }

	public CustomerUserProfile getSpecificCustomerUserProfile(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        CustomerUserProfile obj = data.get(index);
        Long id = obj.getId();
        return customerUserProfileService.findCustomerUserProfile(id);
    }

	public CustomerUserProfile getRandomCustomerUserProfile() {
        init();
        CustomerUserProfile obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return customerUserProfileService.findCustomerUserProfile(id);
    }

	public boolean modifyCustomerUserProfile(CustomerUserProfile obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = customerUserProfileService.findCustomerUserProfileEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'CustomerUserProfile' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<CustomerUserProfile>();
        for (int i = 0; i < 10; i++) {
            CustomerUserProfile obj = getNewTransientCustomerUserProfile(i);
            try {
                customerUserProfileService.saveCustomerUserProfile(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            customerUserProfileDAO.flush();
            data.add(obj);
        }
    }
}
