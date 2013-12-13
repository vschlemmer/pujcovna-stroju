package cz.muni.fi.pa165.pujcovnastroju.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

import cz.muni.fi.pa165.pujcovnastroju.dao.RevisionDAO;
import cz.muni.fi.pa165.pujcovnastroju.dto.RevisionDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.Machine;
import cz.muni.fi.pa165.pujcovnastroju.entity.Revision;
import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.service.RevisionService;
import cz.muni.fi.pa165.pujcovnastroju.serviceimpl.RevisionServiceImpl;

/**
 *
 * @author Matej fucek
 */
@RunWith(MockitoJUnitRunner.class)
public class RevisionServiceTest extends AbstractTest {

    @Mock
    RevisionDAO mockRevisionDao;
    @InjectMocks
    RevisionService revisionService = new RevisionServiceImpl();

    @Before
    public void initMock() {
        Mockito.when(mockRevisionDao.create(Matchers.any(Revision.class))).thenAnswer(new Answer<Revision>() {
            @Override
            public Revision answer(InvocationOnMock inv) throws Throwable {
                Object[] args = inv.getArguments();
                return (Revision) args[0];
            }
        });
        Mockito.when(mockRevisionDao.create(null)).thenThrow(new IllegalArgumentException("Error occured during storing revision."));

        Mockito.when(mockRevisionDao.update(Matchers.any(Revision.class))).thenAnswer(new Answer<Revision>() {
            @Override
            public Revision answer(InvocationOnMock inv) throws Throwable {
                Object[] args = inv.getArguments();
                return (Revision) args[0];
            }
        });

        Mockito.when(mockRevisionDao.update(null)).thenThrow(new IllegalArgumentException("Error occured during updating Revision."));

        Mockito.when(mockRevisionDao.read((long) 1)).thenReturn(null);
        Mockito.when(mockRevisionDao.read((long) 2)).thenReturn(new Revision());
        Mockito.when(mockRevisionDao.read(null)).thenThrow(new IllegalArgumentException("Error occured during reading Revision."));

        Mockito.when(mockRevisionDao.delete(Matchers.any(Revision.class))).thenReturn(new Revision());
        Mockito.when(mockRevisionDao.delete(null)).thenThrow(new IllegalArgumentException("Error occured during deleting Revision."));
    }

    @Test
    public void TestCreateBizRevision() {
        RevisionDTO revisionDTO = null;
        RevisionDTO revisionDTOProcessed = null;

        try {
            revisionService.createBizRevision(revisionDTO);
            assertNotNull(revisionDTO); //if the exception is not thrown, test doesn't pass
        } catch (DataAccessException e) {
            assertNull(revisionDTO);
        }

        revisionDTO = new RevisionDTO();
        revisionDTOProcessed = revisionService.createBizRevision(revisionDTO);
        assertNotNull(revisionDTOProcessed);
        assertEquals(revisionDTO, revisionDTOProcessed);
    }

    @Test
    public void TestUpdateBizRevision() {
        RevisionDTO revisionDTO = null;
        RevisionDTO revisionDTOProcessed = null;
        try {
            revisionService.updateBizRevision(revisionDTO);
            assertNotNull(revisionDTO); //if the exception is not thrown, test doesn't pass
        } catch (DataAccessResourceFailureException e) {
            assertNull(revisionDTO);
        }

        revisionDTO = new RevisionDTO();
        revisionDTOProcessed = revisionService.updateBizRevision(revisionDTO);
        assertNotNull(revisionDTOProcessed);
        assertEquals(revisionDTO, revisionDTOProcessed);
    }

    @Test
    public void TestReadBizRevision() {
        Long revID = null;
        RevisionDTO revisionDTOProcessed = null;
        try {
            revisionService.readBizRevision(revID);
            assertNotNull(revID); //if the exception is not thrown, test doesn't pass
        } catch (DataAccessException e) {
            assertNull(revID);
        }
        revID = (long) 1;
        revisionDTOProcessed = revisionService.readBizRevision(revID);
        assertNull(revisionDTOProcessed);

        revID = (long) 2;
        revisionDTOProcessed = revisionService.readBizRevision(revID);
        assertNotNull(revisionDTOProcessed);
    }

    @Test
    public void TestDeleteBizRevision() {
        RevisionDTO revID = null;
        RevisionDTO revisionDTOProcessed = null;
        try {
            revisionService.deleteBizRevision(revID);
            assertNotNull(revID); //if the exception is not thrown, test doesn't pass
        } catch (DataAccessException e) {
            assertNull(revID);
        }

        revID = new RevisionDTO();
        revisionDTOProcessed = revisionService.deleteBizRevision(revID);
        assertNotNull(revisionDTOProcessed);
    }

    @Test
    public void TestFindAllrevisionsBizRevision() {
        List<RevisionDTO> revisionDTOs = null;
        List<Revision> revisionList = new ArrayList<>();
        revisionList.add(new Revision());
        revisionList.add(new Revision());

        Mockito.when(mockRevisionDao.findAllrevisions()).thenReturn(null);

        revisionDTOs = revisionService.findAllrevisionsBizRevision();
        assertNull(revisionDTOs);

        Mockito.when(mockRevisionDao.findAllrevisions()).thenReturn(revisionList);

        revisionDTOs = revisionService.findAllrevisionsBizRevision();
        assertEquals(revisionDTOs.size(), 2);

        revisionList.add(new Revision());
        revisionDTOs = revisionService.findAllrevisionsBizRevision();
        assertEquals(revisionDTOs.size(), 3);
    }

    @Test
    public void TestFindRevisionsByDateBizRevision() {

        List<Revision> revisionList = new ArrayList<>();
        revisionList.add(new Revision());
        revisionList.add(new Revision());
        
        Mockito.when(mockRevisionDao.findRevisionsByDate(Matchers.any(Date.class), Matchers.any(Date.class))).thenReturn(revisionList);
        List<Revision> returnedRevisions = mockRevisionDao.findRevisionsByDate(new Date(324), new Date(2344));
        assertEquals(returnedRevisions, revisionList);
    }
    
    @Test
     public void findRevisionsByParams() {
        List<Revision> revisionList = new ArrayList<>();
	revisionList.add(new Revision());
	revisionList.add(new Revision());
	
	Mockito.when(mockRevisionDao.findRevisionsByParams(
                Matchers.any(String.class),
                Matchers.any(Date.class),
                Matchers.any(Machine.class),
                Matchers.any(SystemUser.class))).thenReturn(null);
	
	Mockito.when(mockRevisionDao.findRevisionsByParams(
                Matchers.any(String.class),
                Matchers.any(Date.class),
                Matchers.any(Machine.class),
                Matchers.any(SystemUser.class))).thenReturn(revisionList);
    }
}
