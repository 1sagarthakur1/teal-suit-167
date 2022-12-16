package com.Interfaces;

import java.util.List;

//import javax.naming.directory.SearchControls;

import com.Beens.Crimes;
import com.Beens.CrimesAndCriminals;
import com.Beens.Criminal;
import com.Beens.PoliceStation;

public interface CrimeDao {
//	for see all Police Station
	public void showAllPS();
	
//	for ragistar police station
	public String forRagistarPS(PoliceStation policeStation);
	
//	for login a particular police station
	public String forloginPoliceStation(String PSid,String PSpass);
	
//	for add crime and criminal
	public String forAddCrimeAndCrimnal(Crimes crimes,Criminal criminal);
	
//	for show All Crimes
	public List<Crimes> forShowallCrimeList(String Psid);
	
//	for Solve crimes
	public List<Crimes> forshowAllSolveCrimes(String slove,String Psid);
	
//	for not solve crimes
	public List<Crimes> forShowAllUNsolveCrimes(String notsolve,String PSLid);
	
//	for change slove or not solve
	public String forChangeSVNSVcrimes(String sloveornot,int Crid);
	
//	for show all criminals
	public List<CrimesAndCriminals> forShowAllCriminal(String PSLid);
	
//	for show all criminals by date
	public List<CrimesAndCriminals> forShowCriminalbydate(String date1,String date2);
	
//	for count all police stations
	public int forcountAllpolicestation();
	
//	for count all crime
	public int forcountAllcrime();
	
//	for count all criminal
	public int forcountAllcriminal();

}
