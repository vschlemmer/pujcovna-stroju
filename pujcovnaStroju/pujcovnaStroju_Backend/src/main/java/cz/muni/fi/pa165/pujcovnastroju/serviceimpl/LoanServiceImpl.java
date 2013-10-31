package cz.muni.fi.pa165.pujcovnastroju.serviceimpl;

import cz.muni.fi.pa165.pujcovnastroju.dao.LoanDAO;
import cz.muni.fi.pa165.pujcovnastroju.dto.LoanDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.LoanStateEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import cz.muni.fi.pa165.pujcovnastroju.service.LoanService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author xguttner
 */
@Service("loanService")
public class LoanServiceImpl implements LoanService {
    
    @Autowired
    LoanDAO loanDAO;

    @Override
    public LoanDTO create(LoanDTO loanDTO) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LoanDTO update(LoanDTO loanDTO) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LoanDTO read(Long id) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LoanDTO delete(Long id) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<LoanDTO> getAllLoans() {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<LoanDTO> getLoansByParams(Date loanedFrom, Date loanedTill, LoanStateEnumDTO loanStateEnumDTO, SystemUserDTO loanedBy, MachineDTO includedMachine) {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
