package cz.muni.fi.pa165.pujcovnaStroju.web.controller;

import cz.muni.fi.pa165.pujcovnastroju.dto.LoanDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.LoanStateEnum;
import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.service.LoanService;
import cz.muni.fi.pa165.pujcovnastroju.service.SystemUserService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author xguttner
 */
@Controller
@RequestMapping("/loan")
public class LoanController {
    private LoanService loanService;
    private SystemUserService customerService;
    
    @Autowired
    public LoanController(LoanService loanService, SystemUserService customerService){
        this.loanService = loanService;
	this.customerService = customerService;
    }
    
    @RequestMapping("")
    public String redirectToList(ModelMap model) {
            return "redirect:/loan/list";
    }

    @RequestMapping("/")
    public String redirectToListBackslash(ModelMap model) {
            return "redirect:/loan/list";
    }
    
    @RequestMapping(value = "/list")
    public ModelAndView listLoans(ModelMap model,
        @RequestParam(value = "storeStatus", required = false, defaultValue = "") String storeStatus,
        @RequestParam(value = "errorMessage", required = false, defaultValue = "") String errorMessage
        ) {
        model.addAttribute("loans", loanService.getAllLoans());
        model.addAttribute("loanStates", LoanStateEnum.class.getEnumConstants());
	model.addAttribute("customers", customerService.getSystemUsersByParams(null, null, null));
        model.addAttribute("list", "list of loans");
        model.addAttribute("pageTitle", "lang.listLoansTitle");
        DefaultController.addHeaderFooterInfo(model);
        System.out.println(storeStatus);
        System.out.println(errorMessage);
        if (storeStatus.equalsIgnoreCase("true")) {
                model.addAttribute("storeStatus","true");
        }
        if (storeStatus.equalsIgnoreCase("false")) {
                model.addAttribute("storeStatus","false");
                model.addAttribute("errorMessage",errorMessage);	
        }
        return new ModelAndView("listLoans", "command", new LoanDTO());
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addLoan(@ModelAttribute("loan") LoanDTO loan,
                    BindingResult result, ModelMap model) {
        boolean stored = false;
        String errorMsg = null;
        try {
	    System.out.println(loan.getCustomer().getId()+"<-id");
	    SystemUserDTO customer = customerService.read(loan.getCustomer().getId());
	    loan.setCustomer(customer);
            stored = loanService.create(loan) != null;
        } catch (DataAccessException e) {
            stored = false;
            errorMsg = e.getMessage();
        }
        model.addAttribute("storeStatus", stored);
        if (errorMsg != null) {
            model.addAttribute("errorMessage", errorMsg);
        }
        return "redirect:list";
    }
    
    @RequestMapping("/detail/{id}")
    public String viewLoan(@PathVariable String id, ModelMap model) {
        DefaultController.addHeaderFooterInfo(model);
        model.addAttribute("pageTitle", "lang.detailLoanTitle");
        LoanDTO loan = null;
        boolean found = false;
        try {
            Long loanID = Long.valueOf(id);
            loan = loanService.read(loanID);
            found = true;
        } catch (DataAccessException | NumberFormatException e) {
            // TODO log
        }
        model.addAttribute("loan", loan);
        if (!found) {
            model.addAttribute("id", id);
        }
        return "loanDetail";
    }
    
    @RequestMapping(value = "/delete/{id}")
    public String deleteLoan(@PathVariable String id, ModelMap model) {
        boolean deleted = false;
        String errorMsg = null;
        LoanDTO loanDTO = new LoanDTO();
        try {
            Long loanID = Long.valueOf(id);
            loanDTO = loanService.read(loanID);
            loanService.delete(loanDTO.getId());
            deleted = true;
        } catch (DataAccessException | NumberFormatException
                        | NullPointerException e) {
            // TODO log
            deleted = false;
            errorMsg = e.getMessage();
        }
        model.addAttribute("deleteStatus", deleted);
        if (errorMsg != null) {
            model.addAttribute("errorMessage", errorMsg);
        }
        return "redirect:/loan/list";
    }
    
    @RequestMapping(value = "/update/{id}")
    public ModelAndView updateLoan(@PathVariable String id, ModelMap model) {
        DefaultController.addHeaderFooterInfo(model);
        model.addAttribute("pageTitle", "lang.updateLoanTitle");
        LoanDTO loan = null;
        boolean found = false;
        try {
            Long loanID = Long.valueOf(id);
            loan = loanService.read(loanID);
            found = true;
        } catch (DataAccessException | NumberFormatException e) {
            // TODO log
        }
        
        // prevent the actual type of the user to show in the list twice
        List<LoanStateEnum> enums = new LinkedList<>();
        for(LoanStateEnum enum1 : LoanStateEnum.class.getEnumConstants()){
            if (!enum1.toString().equals(loan.getLoanState().getTypeLabel())){
                enums.add(enum1);
            }
        }
        LoanStateEnum[] loanStates = (LoanStateEnum[]) enums.toArray(new LoanStateEnum[enums.size()]);
        
        model.addAttribute("loanStates", loanStates);
        model.addAttribute("loan", loan);
        if (!found) {
            model.addAttribute("id", id);
        }
        return new ModelAndView("updateLoan", "command", new LoanDTO());
    }
    
    @RequestMapping(value = "/update/update", method = RequestMethod.POST)
    public String editLoan(@ModelAttribute("loan") LoanDTO loan,
                    BindingResult result, ModelMap model) {
        boolean updated = false;
        String errorMsg = null;
        try {
            updated = loanService.update(loan) != null;
        } catch (DataAccessException e) {
            updated = false;
            errorMsg = e.getMessage();
        }
        model.addAttribute("updateStatus", updated);
        if (errorMsg != null) {
            model.addAttribute("errorMessage", errorMsg);
        }
        return "redirect:/loan/list";
    }
}
