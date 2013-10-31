package cz.muni.fi.pa165.pujcovnastroju.test.service;

import org.mockito.stubbing.Answer;
import cz.muni.fi.pa165.pujcovnastroju.entity.Loan;
import org.mockito.Mockito;
import cz.muni.fi.pa165.pujcovnastroju.dao.LoanDAO;
import cz.muni.fi.pa165.pujcovnastroju.dto.LoanDTO;
import cz.muni.fi.pa165.pujcovnastroju.service.LoanService;
import cz.muni.fi.pa165.pujcovnastroju.serviceimpl.LoanServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Matchers;
import org.mockito.internal.stubbing.answers.AnswerReturnValuesAdapter;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;

import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author xguttner
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LoanServiceTest extends AbstractTest {
    
    @Mock LoanDAO mockLoanDao;
    @InjectMocks LoanServiceImpl loanService;
    
    @Test
    public void testCreate() {
	LoanDTO loanDTO = null;
	LoanDTO loanDTOProcessed = null;

	Mockito.when(mockLoanDao.create(Matchers.any(Loan.class))).thenAnswer(new Answer<Loan>() {
		@Override
		public Loan answer(InvocationOnMock inv) throws Throwable {
		    Object[] args = inv.getArguments();
		    return (Loan)args[0];
		}
	    });
	Mockito.when(mockLoanDao.create(null)).thenThrow(new DataAccessResourceFailureException("Error occured during storing loan."));
	
	try {
	    loanService.create(loanDTO);
	    assertNotNull(loanDTO); //if the exception is not thrown, test doesn't pass
	} catch (DataAccessResourceFailureException e) {
	    assertNull(loanDTO);
	}
	loanDTO = new LoanDTO();
	loanDTOProcessed = loanService.create(loanDTO);
	assertNotNull(loanDTOProcessed);
	assertEquals(loanDTO, loanDTOProcessed);
    }
    
    @Test
    public void testUpdate() {
    }
    
    @Test
    public void testRead() {
    }
    
    @Test
    public void testDelete() {
    }
    
    @Test
    public void testGetAllLoans() {
    }
    
    @Test
    public void tetsGetLoansByParams() {
    }
}
