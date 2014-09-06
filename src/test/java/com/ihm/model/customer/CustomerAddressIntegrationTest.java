package com.ihm.model.customer;
import com.ihm.dao.customer.CustomerAddressDAO;
import com.ihm.service.customer.CustomerAddressService;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class CustomerAddressIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    CustomerAddressDataOnDemand dod;

	@Autowired
    CustomerAddressService customerAddressService;

	@Autowired
    CustomerAddressDAO customerAddressDAO;

	@Test
    public void testCountAllCustomerAddresses() {
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to initialize correctly", dod.getRandomCustomerAddress());
        long count = customerAddressService.countAllCustomerAddresses();
        Assert.assertTrue("Counter for 'CustomerAddress' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindCustomerAddress() {
        CustomerAddress obj = dod.getRandomCustomerAddress();
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to provide an identifier", id);
        obj = customerAddressService.findCustomerAddress(id);
        Assert.assertNotNull("Find method for 'CustomerAddress' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'CustomerAddress' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllCustomerAddresses() {
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to initialize correctly", dod.getRandomCustomerAddress());
        long count = customerAddressService.countAllCustomerAddresses();
        Assert.assertTrue("Too expensive to perform a find all test for 'CustomerAddress', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<CustomerAddress> result = customerAddressService.findAllCustomerAddresses();
        Assert.assertNotNull("Find all method for 'CustomerAddress' illegally returned null", result);
        Assert.assertTrue("Find all method for 'CustomerAddress' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindCustomerAddressEntries() {
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to initialize correctly", dod.getRandomCustomerAddress());
        long count = customerAddressService.countAllCustomerAddresses();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<CustomerAddress> result = customerAddressService.findCustomerAddressEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'CustomerAddress' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'CustomerAddress' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        CustomerAddress obj = dod.getRandomCustomerAddress();
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to provide an identifier", id);
        obj = customerAddressService.findCustomerAddress(id);
        Assert.assertNotNull("Find method for 'CustomerAddress' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyCustomerAddress(obj);
        Integer currentVersion = obj.getVersion();
        customerAddressDAO.flush();
        Assert.assertTrue("Version for 'CustomerAddress' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateCustomerAddressUpdate() {
        CustomerAddress obj = dod.getRandomCustomerAddress();
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to provide an identifier", id);
        obj = customerAddressService.findCustomerAddress(id);
        boolean modified =  dod.modifyCustomerAddress(obj);
        Integer currentVersion = obj.getVersion();
        CustomerAddress merged = customerAddressService.updateCustomerAddress(obj);
        customerAddressDAO.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'CustomerAddress' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveCustomerAddress() {
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to initialize correctly", dod.getRandomCustomerAddress());
        CustomerAddress obj = dod.getNewTransientCustomerAddress(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'CustomerAddress' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'CustomerAddress' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteCustomerAddress() {
        CustomerAddress obj = dod.getRandomCustomerAddress();
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CustomerAddress' failed to provide an identifier", id);
        obj = customerAddressService.findCustomerAddress(id);
        customerAddressService.deleteCustomerAddress(obj);
        customerAddressDAO.flush();
        Assert.assertNull("Failed to remove 'CustomerAddress' with identifier '" + id + "'", customerAddressService.findCustomerAddress(id));
    }
}
