package com.ihm.model.seller;
import com.ihm.dao.seller.SellerUserProfileDAO;
import com.ihm.service.seller.SellerUserProfileService;
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
public class SellerUserProfileIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    SellerUserProfileDataOnDemand dod;

	@Autowired
    SellerUserProfileService sellerUserProfileService;

	@Autowired
    SellerUserProfileDAO sellerUserProfileDAO;

	@Test
    public void testCountAllSellerUserProfiles() {
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to initialize correctly", dod.getRandomSellerUserProfile());
        long count = sellerUserProfileService.countAllSellerUserProfiles();
        Assert.assertTrue("Counter for 'SellerUserProfile' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindSellerUserProfile() {
        SellerUserProfile obj = dod.getRandomSellerUserProfile();
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to provide an identifier", id);
        obj = sellerUserProfileService.findSellerUserProfile(id);
        Assert.assertNotNull("Find method for 'SellerUserProfile' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'SellerUserProfile' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllSellerUserProfiles() {
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to initialize correctly", dod.getRandomSellerUserProfile());
        long count = sellerUserProfileService.countAllSellerUserProfiles();
        Assert.assertTrue("Too expensive to perform a find all test for 'SellerUserProfile', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<SellerUserProfile> result = sellerUserProfileService.findAllSellerUserProfiles();
        Assert.assertNotNull("Find all method for 'SellerUserProfile' illegally returned null", result);
        Assert.assertTrue("Find all method for 'SellerUserProfile' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindSellerUserProfileEntries() {
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to initialize correctly", dod.getRandomSellerUserProfile());
        long count = sellerUserProfileService.countAllSellerUserProfiles();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<SellerUserProfile> result = sellerUserProfileService.findSellerUserProfileEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'SellerUserProfile' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'SellerUserProfile' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        SellerUserProfile obj = dod.getRandomSellerUserProfile();
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to provide an identifier", id);
        obj = sellerUserProfileService.findSellerUserProfile(id);
        Assert.assertNotNull("Find method for 'SellerUserProfile' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySellerUserProfile(obj);
        Integer currentVersion = obj.getVersion();
        sellerUserProfileDAO.flush();
        Assert.assertTrue("Version for 'SellerUserProfile' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateSellerUserProfileUpdate() {
        SellerUserProfile obj = dod.getRandomSellerUserProfile();
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to provide an identifier", id);
        obj = sellerUserProfileService.findSellerUserProfile(id);
        boolean modified =  dod.modifySellerUserProfile(obj);
        Integer currentVersion = obj.getVersion();
        SellerUserProfile merged = sellerUserProfileService.updateSellerUserProfile(obj);
        sellerUserProfileDAO.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'SellerUserProfile' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveSellerUserProfile() {
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to initialize correctly", dod.getRandomSellerUserProfile());
        SellerUserProfile obj = dod.getNewTransientSellerUserProfile(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'SellerUserProfile' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'SellerUserProfile' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteSellerUserProfile() {
        SellerUserProfile obj = dod.getRandomSellerUserProfile();
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerUserProfile' failed to provide an identifier", id);
        obj = sellerUserProfileService.findSellerUserProfile(id);
        sellerUserProfileService.deleteSellerUserProfile(obj);
        sellerUserProfileDAO.flush();
        Assert.assertNull("Failed to remove 'SellerUserProfile' with identifier '" + id + "'", sellerUserProfileService.findSellerUserProfile(id));
    }
}
