package com.ihm.model.seller;
import com.ihm.dao.seller.SellerInfoDAO;
import com.ihm.service.seller.SellerInfoService;
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
public class SellerInfoIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    SellerInfoDataOnDemand dod;

	@Autowired
    SellerInfoService sellerInfoService;

	@Autowired
    SellerInfoDAO sellerInfoDAO;

	@Test
    public void testCountAllSellerInfoes() {
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to initialize correctly", dod.getRandomSellerInfo());
        long count = sellerInfoService.countAllSellerInfoes();
        Assert.assertTrue("Counter for 'SellerInfo' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindSellerInfo() {
        SellerInfo obj = dod.getRandomSellerInfo();
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to provide an identifier", id);
        obj = sellerInfoService.findSellerInfo(id);
        Assert.assertNotNull("Find method for 'SellerInfo' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'SellerInfo' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllSellerInfoes() {
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to initialize correctly", dod.getRandomSellerInfo());
        long count = sellerInfoService.countAllSellerInfoes();
        Assert.assertTrue("Too expensive to perform a find all test for 'SellerInfo', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<SellerInfo> result = sellerInfoService.findAllSellerInfoes();
        Assert.assertNotNull("Find all method for 'SellerInfo' illegally returned null", result);
        Assert.assertTrue("Find all method for 'SellerInfo' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindSellerInfoEntries() {
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to initialize correctly", dod.getRandomSellerInfo());
        long count = sellerInfoService.countAllSellerInfoes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<SellerInfo> result = sellerInfoService.findSellerInfoEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'SellerInfo' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'SellerInfo' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        SellerInfo obj = dod.getRandomSellerInfo();
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to provide an identifier", id);
        obj = sellerInfoService.findSellerInfo(id);
        Assert.assertNotNull("Find method for 'SellerInfo' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySellerInfo(obj);
        Integer currentVersion = obj.getVersion();
        sellerInfoDAO.flush();
        Assert.assertTrue("Version for 'SellerInfo' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateSellerInfoUpdate() {
        SellerInfo obj = dod.getRandomSellerInfo();
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to provide an identifier", id);
        obj = sellerInfoService.findSellerInfo(id);
        boolean modified =  dod.modifySellerInfo(obj);
        Integer currentVersion = obj.getVersion();
        SellerInfo merged = sellerInfoService.updateSellerInfo(obj);
        sellerInfoDAO.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'SellerInfo' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveSellerInfo() {
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to initialize correctly", dod.getRandomSellerInfo());
        SellerInfo obj = dod.getNewTransientSellerInfo(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'SellerInfo' identifier to be null", obj.getId());
        try {
            sellerInfoService.saveSellerInfo(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        sellerInfoDAO.flush();
        Assert.assertNotNull("Expected 'SellerInfo' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteSellerInfo() {
        SellerInfo obj = dod.getRandomSellerInfo();
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SellerInfo' failed to provide an identifier", id);
        obj = sellerInfoService.findSellerInfo(id);
        sellerInfoService.deleteSellerInfo(obj);
        sellerInfoDAO.flush();
        Assert.assertNull("Failed to remove 'SellerInfo' with identifier '" + id + "'", sellerInfoService.findSellerInfo(id));
    }
}
