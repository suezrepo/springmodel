package com.ihm.model.customer;
import com.ihm.dao.customer.CustomerAddressDAO;
import com.ihm.model.StateInfoDataOnDemand;
import com.ihm.service.customer.CustomerAddressService;
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
public class CustomerAddressDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<CustomerAddress> data;

	@Autowired
    StateInfoDataOnDemand stateInfoDataOnDemand;

	@Autowired
    CustomerAddressService customerAddressService;

	@Autowired
    CustomerAddressDAO customerAddressDAO;

	public CustomerAddress getNewTransientCustomerAddress(int index) {
        CustomerAddress obj = new CustomerAddress();
        setAddress1(obj, index);
        setAddress2(obj, index);
        setAddress3(obj, index);
        setAddressType(obj, index);
        setCity(obj, index);
        setCreatedBy(obj, index);
        setCreatedOn(obj, index);
        setDisableFlg(obj, index);
        setLatitude(obj, index);
        setLongitude(obj, index);
        setUpdatedBy(obj, index);
        setUpdatedOn(obj, index);
        setZip(obj, index);
        return obj;
    }

	public void setAddress1(CustomerAddress obj, int index) {
        String address1 = "address1_" + index;
        obj.setAddress1(address1);
    }

	public void setAddress2(CustomerAddress obj, int index) {
        String address2 = "address2_" + index;
        obj.setAddress2(address2);
    }

	public void setAddress3(CustomerAddress obj, int index) {
        String address3 = "address3_" + index;
        obj.setAddress3(address3);
    }

	public void setAddressType(CustomerAddress obj, int index) {
        String addressType = String.valueOf(index);
        if (addressType.length() > 1) {
            addressType = addressType.substring(0, 1);
        }
        obj.setAddressType(addressType);
    }

	public void setCity(CustomerAddress obj, int index) {
        String city = "city_" + index;
        obj.setCity(city);
    }

	public void setCreatedBy(CustomerAddress obj, int index) {
        String createdBy = "createdBy_" + index;
        if (createdBy.length() > 16) {
            createdBy = createdBy.substring(0, 16);
        }
        obj.setCreatedBy(createdBy);
    }

	public void setCreatedOn(CustomerAddress obj, int index) {
        Date createdOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreatedOn(createdOn);
    }

	public void setDisableFlg(CustomerAddress obj, int index) {
        String disableFlg = String.valueOf(index);
        if (disableFlg.length() > 1) {
            disableFlg = disableFlg.substring(0, 1);
        }
        obj.setDisableFlg(disableFlg);
    }

	public void setLatitude(CustomerAddress obj, int index) {
        Long latitude = new Integer(index).longValue();
        obj.setLatitude(latitude);
    }

	public void setLongitude(CustomerAddress obj, int index) {
        Long longitude = new Integer(index).longValue();
        obj.setLongitude(longitude);
    }

	public void setUpdatedBy(CustomerAddress obj, int index) {
        String updatedBy = "updatedBy_" + index;
        if (updatedBy.length() > 16) {
            updatedBy = updatedBy.substring(0, 16);
        }
        obj.setUpdatedBy(updatedBy);
    }

	public void setUpdatedOn(CustomerAddress obj, int index) {
        Date updatedOn = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdatedOn(updatedOn);
    }

	public void setZip(CustomerAddress obj, int index) {
        String zip = "zip_" + index;
        obj.setZip(zip);
    }

	public CustomerAddress getSpecificCustomerAddress(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        CustomerAddress obj = data.get(index);
        Long id = obj.getId();
        return customerAddressService.findCustomerAddress(id);
    }

	public CustomerAddress getRandomCustomerAddress() {
        init();
        CustomerAddress obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return customerAddressService.findCustomerAddress(id);
    }

	public boolean modifyCustomerAddress(CustomerAddress obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = customerAddressService.findCustomerAddressEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'CustomerAddress' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<CustomerAddress>();
        for (int i = 0; i < 10; i++) {
            CustomerAddress obj = getNewTransientCustomerAddress(i);
            try {
                customerAddressService.saveCustomerAddress(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            customerAddressDAO.flush();
            data.add(obj);
        }
    }
}
