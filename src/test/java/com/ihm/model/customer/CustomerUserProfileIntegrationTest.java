package com.ihm.model.customer;
import com.ihm.dao.customer.CustomerUserProfileDAO;
import com.ihm.service.customer.CustomerUserProfileService;
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

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class CustomerUserProfileIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    CustomerUserProfileDataOnDemand dod;

	@Autowired
    CustomerUserProfileService customerUserProfileService;

	@Autowired
    CustomerUserProfileDAO customerUserProfileDAO;

	@Test
    public void testCountAllCustomerUserProfiles() {
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to initialize correctly", dod.getRandomCustomerUserProfile());
        long count = customerUserProfileService.countAllCustomerUserProfiles();
        Assert.assertTrue("Counter for 'CustomerUserProfile' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindCustomerUserProfile() {
        CustomerUserProfile obj = dod.getRandomCustomerUserProfile();
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to provide an identifier", id);
        obj = customerUserProfileService.findCustomerUserProfile(id);
        Assert.assertNotNull("Find method for 'CustomerUserProfile' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'CustomerUserProfile' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllCustomerUserProfiles() {
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to initialize correctly", dod.getRandomCustomerUserProfile());
        long count = customerUserProfileService.countAllCustomerUserProfiles();
        Assert.assertTrue("Too expensive to perform a find all test for 'CustomerUserProfile', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<CustomerUserProfile> result = customerUserProfileService.findAllCustomerUserProfiles();
        Assert.assertNotNull("Find all method for 'CustomerUserProfile' illegally returned null", result);
        Assert.assertTrue("Find all method for 'CustomerUserProfile' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindCustomerUserProfileEntries() {
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to initialize correctly", dod.getRandomCustomerUserProfile());
        long count = customerUserProfileService.countAllCustomerUserProfiles();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<CustomerUserProfile> result = customerUserProfileService.findCustomerUserProfileEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'CustomerUserProfile' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'CustomerUserProfile' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        CustomerUserProfile obj = dod.getRandomCustomerUserProfile();
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to provide an identifier", id);
        obj = customerUserProfileService.findCustomerUserProfile(id);
        Assert.assertNotNull("Find method for 'CustomerUserProfile' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyCustomerUserProfile(obj);
        Integer currentVersion = obj.getVersion();
        customerUserProfileDAO.flush();
        Assert.assertTrue("Version for 'CustomerUserProfile' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateCustomerUserProfileUpdate() {
        CustomerUserProfile obj = dod.getRandomCustomerUserProfile();
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to provide an identifier", id);
        obj = customerUserProfileService.findCustomerUserProfile(id);
        boolean modified =  dod.modifyCustomerUserProfile(obj);
        Integer currentVersion = obj.getVersion();
        CustomerUserProfile merged = customerUserProfileService.updateCustomerUserProfile(obj);
        customerUserProfileDAO.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'CustomerUserProfile' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveCustomerUserProfile() {
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to initialize correctly", dod.getRandomCustomerUserProfile());
        CustomerUserProfile obj = dod.getNewTransientCustomerUserProfile(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'CustomerUserProfile' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'CustomerUserProfile' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteCustomerUserProfile() {
        CustomerUserProfile obj = dod.getRandomCustomerUserProfile();
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CustomerUserProfile' failed to provide an identifier", id);
        obj = customerUserProfileService.findCustomerUserProfile(id);
        customerUserProfileService.deleteCustomerUserProfile(obj);
        customerUserProfileDAO.flush();
        Assert.assertNull("Failed to remove 'CustomerUserProfile' with identifier '" + id + "'", customerUserProfileService.findCustomerUserProfile(id));
    }
}
