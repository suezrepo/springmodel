package com.ihm.model.seller;
import com.ihm.dao.seller.SellerUserCredentialDAO;
import com.ihm.service.seller.SellerUserCredentialService;
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
public class SellerUserCredentialIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    SellerUserCredentialDataOnDemand dod;

	@Autowired
    SellerUserCredentialService sellerUserCredentialService;

	@Autowired
    SellerUserCredentialDAO sellerUserCredentialDAO;

	@Test
    public void testCountAllSellerUserCredentials() {
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to initialize correctly", dod.getRandomSellerUserCredential());
        long count = sellerUserCredentialService.countAllSellerUserCredentials();
        Assert.assertTrue("Counter for 'SellerUserCredential' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindSellerUserCredential() {
        SellerUserCredential obj = dod.getRandomSellerUserCredential();
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to provide an identifier", id);
        obj = sellerUserCredentialService.findSellerUserCredential(id);
        Assert.assertNotNull("Find method for 'SellerUserCredential' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'SellerUserCredential' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllSellerUserCredentials() {
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to initialize correctly", dod.getRandomSellerUserCredential());
        long count = sellerUserCredentialService.countAllSellerUserCredentials();
        Assert.assertTrue("Too expensive to perform a find all test for 'SellerUserCredential', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<SellerUserCredential> result = sellerUserCredentialService.findAllSellerUserCredentials();
        Assert.assertNotNull("Find all method for 'SellerUserCredential' illegally returned null", result);
        Assert.assertTrue("Find all method for 'SellerUserCredential' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindSellerUserCredentialEntries() {
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to initialize correctly", dod.getRandomSellerUserCredential());
        long count = sellerUserCredentialService.countAllSellerUserCredentials();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<SellerUserCredential> result = sellerUserCredentialService.findSellerUserCredentialEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'SellerUserCredential' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'SellerUserCredential' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        SellerUserCredential obj = dod.getRandomSellerUserCredential();
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to provide an identifier", id);
        obj = sellerUserCredentialService.findSellerUserCredential(id);
        Assert.assertNotNull("Find method for 'SellerUserCredential' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySellerUserCredential(obj);
        Integer currentVersion = obj.getVersion();
        sellerUserCredentialDAO.flush();
        Assert.assertTrue("Version for 'SellerUserCredential' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateSellerUserCredentialUpdate() {
        SellerUserCredential obj = dod.getRandomSellerUserCredential();
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to provide an identifier", id);
        obj = sellerUserCredentialService.findSellerUserCredential(id);
        boolean modified =  dod.modifySellerUserCredential(obj);
        Integer currentVersion = obj.getVersion();
        SellerUserCredential merged = sellerUserCredentialService.updateSellerUserCredential(obj);
        sellerUserCredentialDAO.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'SellerUserCredential' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveSellerUserCredential() {
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to initialize correctly", dod.getRandomSellerUserCredential());
        SellerUserCredential obj = dod.getNewTransientSellerUserCredential(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'SellerUserCredential' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'SellerUserCredential' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteSellerUserCredential() {
        SellerUserCredential obj = dod.getRandomSellerUserCredential();
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerUserCredential' failed to provide an identifier", id);
        obj = sellerUserCredentialService.findSellerUserCredential(id);
        sellerUserCredentialService.deleteSellerUserCredential(obj);
        sellerUserCredentialDAO.flush();
        Assert.assertNull("Failed to remove 'SellerUserCredential' with identifier '" + id + "'", sellerUserCredentialService.findSellerUserCredential(id));
    }
}
