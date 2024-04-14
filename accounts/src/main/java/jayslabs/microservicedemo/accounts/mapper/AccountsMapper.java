package jayslabs.microservicedemo.accounts.mapper;

import jayslabs.microservicedemo.accounts.dto.AccountsDTO;
import jayslabs.microservicedemo.accounts.entity.Accounts;

public class AccountsMapper {
	
	public static AccountsDTO mapToAccountsDTO(Accounts acct, AccountsDTO acctdto) {
		acctdto.setAccountNumber(acct.getAccountNumber());
		acctdto.setAccountType(acct.getAccountType());
		acctdto.setBranchAddress(acct.getBranchAddress());
		return acctdto;
	}
	
	public static Accounts mapToAccounts(AccountsDTO acctdto, Accounts acct) {
		acct.setAccountNumber(acctdto.getAccountNumber());
		acct.setAccountType(acctdto.getAccountType());
		acct.setBranchAddress(acctdto.getBranchAddress());
		return acct;
	}
}
