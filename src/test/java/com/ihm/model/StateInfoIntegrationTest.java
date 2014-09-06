package com.ihm.model;
import com.ihm.dao.StateInfoDAO;
import com.ihm.service.StateInfoService;
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
public class StateInfoIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    StateInfoDataOnDemand dod;

	@Autowired
    StateInfoService stateInfoService;

	@Autowired
    StateInfoDAO stateInfoDAO;

	@Test
    public void testCountAllStateInfoes() {
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to initialize correctly", dod.getRandomStateInfo());
        long count = stateInfoService.countAllStateInfoes();
        Assert.assertTrue("Counter for 'StateInfo' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindStateInfo() {
        StateInfo obj = dod.getRandomStateInfo();
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to provide an identifier", id);
        obj = stateInfoService.findStateInfo(id);
        Assert.assertNotNull("Find method for 'StateInfo' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'StateInfo' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllStateInfoes() {
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to initialize correctly", dod.getRandomStateInfo());
        long count = stateInfoService.countAllStateInfoes();
        Assert.assertTrue("Too expensive to perform a find all test for 'StateInfo', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<StateInfo> result = stateInfoService.findAllStateInfoes();
        Assert.assertNotNull("Find all method for 'StateInfo' illegally returned null", result);
        Assert.assertTrue("Find all method for 'StateInfo' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindStateInfoEntries() {
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to initialize correctly", dod.getRandomStateInfo());
        long count = stateInfoService.countAllStateInfoes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<StateInfo> result = stateInfoService.findStateInfoEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'StateInfo' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'StateInfo' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        StateInfo obj = dod.getRandomStateInfo();
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to provide an identifier", id);
        obj = stateInfoService.findStateInfo(id);
        Assert.assertNotNull("Find method for 'StateInfo' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStateInfo(obj);
        Integer currentVersion = obj.getVersion();
        stateInfoDAO.flush();
        Assert.assertTrue("Version for 'StateInfo' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateStateInfoUpdate() {
        StateInfo obj = dod.getRandomStateInfo();
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to provide an identifier", id);
        obj = stateInfoService.findStateInfo(id);
        boolean modified =  dod.modifyStateInfo(obj);
        Integer currentVersion = obj.getVersion();
        StateInfo merged = stateInfoService.updateStateInfo(obj);
        stateInfoDAO.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'StateInfo' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveStateInfo() {
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to initialize correctly", dod.getRandomStateInfo());
        StateInfo obj = dod.getNewTransientStateInfo(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'StateInfo' identifier to be null", obj.getId());
        try {
            stateInfoService.saveStateInfo(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        stateInfoDAO.flush();
        Assert.assertNotNull("Expected 'StateInfo' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteStateInfo() {
        StateInfo obj = dod.getRandomStateInfo();
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'StateInfo' failed to provide an identifier", id);
        obj = stateInfoService.findStateInfo(id);
        stateInfoService.deleteStateInfo(obj);
        stateInfoDAO.flush();
        Assert.assertNull("Failed to remove 'StateInfo' with identifier '" + id + "'", stateInfoService.findStateInfo(id));
    }
}
