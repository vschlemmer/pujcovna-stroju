package cz.muni.fi.pa165.pujcovnastroju.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
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

import cz.muni.fi.pa165.pujcovnastroju.dao.LoanDAO;
import cz.muni.fi.pa165.pujcovnastroju.dto.LoanDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.Loan;
import cz.muni.fi.pa165.pujcovnastroju.entity.LoanStateEnum;
import cz.muni.fi.pa165.pujcovnastroju.entity.Machine;
import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.service.LoanService;
import cz.muni.fi.pa165.pujcovnastroju.serviceimpl.LoanServiceImpl;

/**
 *
 * @author xguttner
 */
@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest extends AbstractTest {
    
    @Mock LoanDAO mockLoanDao;
    @InjectMocks LoanService loanService = new LoanServiceImpl();
    
    @Before
    public void initMock() {
	Mockito.when(mockLoanDao.create(Matchers.any(Loan.class))).thenAnswer(new Answer<Loan>() {
		@Override
		public Loan answer(InvocationOnMock inv) throws Throwable {
		    Object[] args = inv.getArguments();
		    return (Loan)args[0];
		}
	    });
	Mockito.when(mockLoanDao.create(null)).thenThrow(new IllegalArgumentException("Error occured during storing loan."));
	
	Mockito.when(mockLoanDao.update(Matchers.any(Loan.class))).thenAnswer(new Answer<Loan>() {
		@Override
		public Loan answer(InvocationOnMock inv) throws Throwable {
		    Object[] args = inv.getArguments();
		    return (Loan)args[0];
		}
	    });
	Mockito.when(mockLoanDao.update(null)).thenThrow(new IllegalArgumentException("Error occured during updating loan."));
	
	Mockito.when(mockLoanDao.read((long)1)).thenReturn(null);
	Mockito.when(mockLoanDao.read((long)2)).thenReturn(new Loan());
	Mockito.when(mockLoanDao.read(null)).thenThrow(new IllegalArgumentException("Error occured during reading loan."));
	
	Mockito.when(mockLoanDao.delete((long)1)).thenReturn(new Loan());
	Mockito.when(mockLoanDao.delete(null)).thenThrow(new IllegalArgumentException("Error occured during deleting loan."));
    }
    
    @Test
    public void testCreate() {
	LoanDTO loanDTO = null;
	LoanDTO loanDTOProcessed = null;
	
	try {
	    loanService.create(loanDTO);
	    assertNotNull(loanDTO); //if the exception is not thrown, test doesn't pass
	} catch (DataAccessException e) {
	    assertNull(loanDTO);
	}
	
	loanDTO = new LoanDTO();
	loanDTOProcessed = loanService.create(loanDTO);
	assertNotNull(loanDTOProcessed);
	assertEquals(loanDTO, loanDTOProcessed);
    }
    
    @Test
    public void testUpdate() {
	LoanDTO loanDTO = null;
	LoanDTO loanDTOProcessed = null;
	
	try {
	    loanService.update(loanDTO);
	    assertNotNull(loanDTO); //if the exception is not thrown, test doesn't pass
	} catch (DataAccessException e) {
	    assertNull(loanDTO);
	}
	
	loanDTO = new LoanDTO();
	loanDTOProcessed = loanService.update(loanDTO);
	assertNotNull(loanDTOProcessed);
	assertEquals(loanDTO, loanDTOProcessed);
    }
    
    @Test
    public void testRead() {
	Long id = null;
	LoanDTO loanDTOProcessed = null;
	
	try {
	    loanService.read(id);
	    assertNotNull(id); //if the exception is not thrown, test doesn't pass
	} catch (DataAccessException e) {
	    assertNull(id);
	}
	
	id = (long)1;
	loanDTOProcessed = loanService.read(id);
	assertNull(loanDTOProcessed);
	
	id = (long)2;
	loanDTOProcessed = loanService.read(id);
	assertNotNull(loanDTOProcessed);
    }
    
    @Test
    public void testDelete() {
	Long id = null;
	LoanDTO loanDTOProcessed = null;
	
	try {
	    loanService.delete(loanDTOProcessed);
	    assertNotNull(id); //if the exception is not thrown, test doesn't pass
	} catch (DataAccessException e) {
	    assertNull(id);
	}
	
	id = (long)1;
	LoanDTO loanDTO = new LoanDTO();
	loanDTO.setId(id);
	loanDTOProcessed = loanService.delete(loanDTO);
	assertNotNull(loanDTOProcessed);
    }
    
    @Test
    public void testGetAllLoans() {
	List<LoanDTO> loanDTOs = null;
	List<Loan> loanList = new ArrayList<>();
	loanList.add(new Loan());
	loanList.add(new Loan());
	
	Mockito.when(mockLoanDao.getAllLoans()).thenReturn(null);
	
	loanDTOs = loanService.getAllLoans();
	assertNull(loanDTOs);
	
	Mockito.when(mockLoanDao.getAllLoans()).thenReturn(loanList);
	
	loanDTOs = loanService.getAllLoans();
	assertEquals(loanDTOs.size(), 2);
	
	loanList.add(new Loan());
	loanDTOs = loanService.getAllLoans();
	assertEquals(loanDTOs.size(), 3);
    }
    
    @Test
    public void testGetLoansByParams() {
	List<LoanDTO> loanDTOs = null;
	List<Loan> loanList = new ArrayList<>();
	loanList.add(new Loan());
	loanList.add(new Loan());
	
	Mockito.when(mockLoanDao.getLoansByParams(Matchers.any(Date.class), Matchers.any(Date.class), Matchers.any(LoanStateEnum.class), Matchers.any(SystemUser.class), Matchers.any(Machine.class))).thenReturn(null);
	
	loanDTOs = loanService.getLoansByParams(null, null, null, null, null);
	assertNull(loanDTOs);
	
	Mockito.when(mockLoanDao.getLoansByParams(Matchers.any(Date.class), Matchers.any(Date.class), Matchers.any(LoanStateEnum.class), Matchers.any(SystemUser.class), Matchers.any(Machine.class))).thenReturn(loanList);
	
	loanDTOs = loanService.getLoansByParams(null, null, null, null, null);
	assertEquals(loanDTOs.size(), 2);
	
	loanList.add(new Loan());
	loanDTOs = loanService.getLoansByParams(null, null, null, null, null);
	assertEquals(loanDTOs.size(), 3);
    }
}
