package AllcallImp;

import java.util.List;
import java.util.Scanner;

import com.Beens.Crimes;
import com.Beens.CrimesAndCriminals;
import com.Beens.Criminal;
import com.Beens.PoliceStation;
import com.Interfaces.CrimeDao;
import com.InterfacesDaoImp.CrimeDaoImplimantion;
import com.exceptionshendal.CrimeException;
import com.exceptionshendal.CrimnalException;
import com.exceptionshendal.PoliceStationException;
import com.mysql.cj.protocol.x.ContinuousOutputStream;

public class AllcallimpMethod {
//	private static final String String = null;

	public static void allClassholder(){
		Scanner scanner1 = new Scanner(System.in);
		System.out.println("              ________________________________________________________________________________");
		System.out.println("             |                                                                                |");
		System.out.println("             |                     ****Welcom to Crime information System****                 |");
		System.out.println("             |________________________________________________________________________________|");
		System.out.println();
		System.out.println("-------These are all registered Police Station--------");
		System.out.println();
		CrimeDao crimeDao = new CrimeDaoImplimantion();
		int NoPo;
		try {
			NoPo = crimeDao.forcountAllpolicestation();
			System.out.println("                                                                                         Total Police Station : "+NoPo);
		} catch (PoliceStationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			crimeDao.showAllPS();
		} catch (PoliceStationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
		System.out.println("Enter 1 - For Police Station Ragistation");
		System.out.println("Enter 2 - Login Police Station");
		System.out.print("Enter Number : ");
		int num1 = scanner1.nextInt();
		System.out.println();
		
		if(num1==1) {
			System.out.println();
			AllcallimpMethod.policestationRagistetion();
		}
		else if(num1==2) {
			System.out.println();
            AllcallimpMethod.loginCallMethod();
		}
		else {
			System.out.println("Enter valid Number");
			AllcallimpMethod.allClassholder();
		}
	}
	
	
	public static void loginCallMethod() {
		Scanner scanner2 = new Scanner(System.in);
		
		System.out.print("Enter Login ID : ");
		String PsLid = scanner2.next();
		System.out.print("Enter Password : ");
		String pass = scanner2.next();
		System.out.println();
		CrimeDao cDao = new CrimeDaoImplimantion();
		String string = null;
		try {
			string = cDao.forloginPoliceStation(PsLid, pass);
		} catch (PoliceStationException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		if(string != "Invalid ID Or Password") {
			System.out.println(string);
			AllcallimpMethod.alladdCrimeCll(PsLid);
		}
		else {
			System.out.println();
			System.out.println(string);
			AllcallimpMethod.loginCallMethod();
		}
	}
	public static void policestationRagistetion() {
		Scanner scanner7 = new Scanner(System.in);
		CrimeDao crimeDao = new CrimeDaoImplimantion();
		System.out.println();
		int psid = 0;
		try {
			psid = (crimeDao.forcountAllpolicestation())+1;
		} catch (PoliceStationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.print("Enter police Station area : ");
		String psarea = scanner7.nextLine();
		System.out.print("Enter police Station incharge name : ");
		String psinchname = scanner7.nextLine();
		System.out.print("Enter new police Station loginid : ");
		String psloinid = scanner7.nextLine();
		System.out.print("Set Passwrod it should be 6 digit password Enter : ");
		String pspassword = scanner7.nextLine();
		
		PoliceStation policeStation = new PoliceStation();
		policeStation.setPsid(psid);
		policeStation.setPsareaString(psarea);
		policeStation.setPsinchargeString(psinchname);
		policeStation.setPsloginidString(psloinid);
		policeStation.setpSpasswordString(pspassword);
		
		
		String string = null;
		try {
			string = crimeDao.forRagistarPS(policeStation);
		} catch (PoliceStationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println(string);
		System.out.println();
		System.out.println("Enter any number for back");
		System.out.print("Enter Number : ");
		int num12 = scanner7.nextInt();
		if(num12>0){
			System.out.println();
			AllcallimpMethod.allClassholder();
		}
				
	}
	public static void alladdCrimeCll(String PsLid) {
		Scanner scanner2 = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter 1- See All Crimes of it Police Station");
		System.out.println("Enter 2- See All criminals of it Police Station");
		System.out.println("Enter 3- Add Crime");
		System.out.println("Enter 4- Logout");
		System.out.println("Enter 5- Search criminals of any police Station By Date");
		System.out.print("Enter Number : ");
		int num2 = scanner2.nextInt();
		if(num2 == 1) {
			AllcallimpMethod.allcrimesCall(PsLid);
			System.out.println();
			System.out.println("This is all crime");
		}
		else if(num2 == 3) {
			AllcallimpMethod.allAddCrimeAndCriminalCall(PsLid);
		}
		else if(num2 == 4) {
			AllcallimpMethod.allClassholder();
		}
		else if(num2 == 2){
			AllcallimpMethod.allcriminalsCall(PsLid);
		}
		else if(num2 == 5) {
			AllcallimpMethod.allCriminalBydate(PsLid);
		}
		else {
			System.out.println();
			System.out.println("Enter valid Number");
			AllcallimpMethod.alladdCrimeCll(PsLid);
		}
		
	}
	
	public static void allAddCrimeAndCriminalCall(String pString) {
		Scanner scanner6 = new Scanner(System.in);
		CrimeDao crimeDao = new CrimeDaoImplimantion();
		System.out.println("---------------Hello please enter details carefully---------------");
		System.out.println();
		int crmimid = 0;
		try {
			crmimid = (crimeDao.forcountAllcrime())+1;
		} catch (CrimeException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		System.out.print("Enter Crime date like - 2022-11-09 : ");
		String crmdate = scanner6.nextLine();
		System.out.print("Enter Crime type : ");
		String crmtype = scanner6.nextLine();
		System.out.print("Enter Victim Name : ");
		String victim = scanner6.nextLine();
		System.out.print("Enter Crime Description : ");
		String decription = scanner6.nextLine();
		System.out.print("Enter Criminal Name : ");
		String criminal = scanner6.nextLine();
		System.out.print("Enter SLOVE,NOT SOLVE : ");
		String SorN = scanner6.nextLine();
		System.out.print("Enter Police station ID : ");
		int psid = scanner6.nextInt();
		System.out.print("Enter Crime place : ");
		String crmplace = scanner6.next();
		
		Crimes crimes = new Crimes();
		
		crimes.setCrid(crmimid);
		crimes.setcRdateString(crmdate);
		crimes.setCrtypeString(crmtype);
		crimes.setVictimString(victim);
		crimes.setCrdetailString(decription);
		crimes.setCriminalString(criminal);
		crimes.setSolveornotString(SorN);
		crimes.setPsid(psid);
		crimes.setCrplaceString(crmplace);
		
		System.out.println();
		System.out.println("---------Good Now you will fill criminal details--------");
		System.out.println();
		
		int cid = 0;
		try {
			cid = (crimeDao.forcountAllcriminal())+1;
		} catch (CrimnalException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.print("Enter above Criminal Name again : ");
		String cname = scanner6.nextLine();
		System.out.print("Enter Criminal age : ");
		int cage = scanner6.nextInt();
		System.out.print("Enter Criminal gender : ");
		String cgender = scanner6.next();
		System.out.print("Enter Criminal address : ");
		String caddress = scanner6.nextLine();
		System.out.print("Enter Criminal Mark in face : ");
		String cMarkface = scanner6.nextLine();
		System.out.print("Enter Criminal arrested area : ");
		String carrestedarea = scanner6.next();
		
		Criminal criminal2 = new Criminal();
		
		criminal2.setCrno(cid);
		criminal2.setCnameString(cname);
		criminal2.setCage(cage);
		criminal2.setCgenderString(cgender);
		criminal2.setCaddressString(caddress);
		criminal2.setcMarkfString(cMarkface);
		criminal2.setArrestareaString(carrestedarea);
		criminal2.setCrimeid(crmimid);
		
		
		String string = null;
		try {
			string = crimeDao.forAddCrimeAndCrimnal(crimes, criminal2);
		} catch (CrimnalException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println(string);
		
		System.out.println("Enter 1- fill again");
		System.out.println("Enter 2- LogOut");
		System.out.println("Enter any number for back");
		System.out.print("Enter Number : ");
		int num11 = scanner6.nextInt();
		if(num11==1) {
			System.out.println();
			AllcallimpMethod.allAddCrimeAndCriminalCall(pString);
		}
		else if(num11 == 2) {
			System.out.println();
			AllcallimpMethod.allClassholder();
		}
		else {
			AllcallimpMethod.alladdCrimeCll(pString);
		}
	}
	
	public static void allcrimesCall(String PSLid) {
		Scanner scanner3 = new Scanner(System.in);
		
		CrimeDao crimeDao = new CrimeDaoImplimantion();
	
		List<Crimes> list = null;
		try {
			list = crimeDao.forShowallCrimeList(PSLid);
		} catch (CrimeException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println("--------------------Crime details of it Police station----------------------");
		System.out.println();
		list.forEach((l) -> System.out.println(
				"Date         : "+l.getcRdateString()+"                                     Crime ID : "+l.getCrid()+"\n"+
				"Criminal     : "+l.getCriminalString()+"\n"+
				"Crime        : "+l.getCrdetailString()+"\n"+
				"Solve or not : "+l.getSolveornotString()+"\n"+
				"---------------------------------------------------------------------------"+"\n"
				));
		System.out.println();
		System.out.println("Enter 1- See Slove Crimes");
		System.out.println("Enter 2- See Not Slove Crimes");
		System.out.println("Enter 3- Change Status");
		System.out.println("Enter 4- Back");
		System.out.println("Enter 5- LogOut");
		System.out.print("Enter Number : ");
		int num3 = scanner3.nextInt();
		if(num3==1) {
			String solve = "SOLVE";
			CrimeDao crimeDao2 = new CrimeDaoImplimantion();
			List<Crimes> list1 = null;
			try {
				list1 = crimeDao2.forshowAllSolveCrimes(solve, PSLid);
			} catch (CrimeException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			if(list1.size()>0) {
				System.out.println("-----------Solve crimes of it police station--------------");
				System.out.println();
				list1.forEach((l) -> System.out.println(
						"Date          : "+l.getcRdateString()+"\n"+
						"Criminal      : "+l.getCriminalString()+"\n"+
						"Crime         : "+l.getCrdetailString()+"\n"+
						"Solve or Not  : "+l.getSolveornotString()+"\n"+
						"---------------------------------------------------------------------------"+"\n"
						));
			}
			else {
				System.out.println(" ____________________________________________");
				System.out.println("|                                            |");
				System.out.println("| In this police station no any solve crime  |");
				System.out.println("|____________________________________________|");
				System.out.println();
			}
			System.out.println("Enter any number Back");
			System.out.print("Enter Number : ");
			int num5 = scanner3.nextInt();
			if(num5>=0) {
				System.out.println();
				AllcallimpMethod.allcrimesCall(PSLid);
			}
		}
		else if(num3 == 2) {
			String notSolve = "NOT SOLVE";
			CrimeDao crimeDao3 = new CrimeDaoImplimantion();
			List<Crimes> list2 = null;
			try {
				list2 = crimeDao3.forShowAllUNsolveCrimes(notSolve, PSLid);
			} catch (CrimeException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			if(list2.size()>0) {
				System.out.println("---------------Un solve crimes of it police station--------------");
				System.out.println();
				list2.forEach((l) -> System.out.println(
						"Date          : "+l.getcRdateString()+"\n"+
						"Criminal      : "+l.getCriminalString()+"\n"+
						"Crime         : "+l.getCrdetailString()+"\n"+
						"Solve or Not  : "+l.getSolveornotString()+"\n"+
						"---------------------------------------------------------------------------"+"\n"
						));
			}
			else {
				System.out.println(" ____________________________________________");
				System.out.println("|                                            |");
				System.out.println("| In it police station all crimes are solve  |");
				System.out.println("|____________________________________________|");
			}
			System.out.println();
			System.out.println("Enter any number Back");
			System.out.print("Enter Number : ");
			int num6 = scanner3.nextInt();
			if(num6>=0) {
				AllcallimpMethod.allcrimesCall(PSLid);
			}
			
		}
        else if(num3 == 3) {
        	CrimeDao crimeDao4 = new CrimeDaoImplimantion();
        	System.out.println();
			System.out.print("Enter Crime ID : ");
			int num7 = scanner3.nextInt();
			System.out.print("Enter SOLVE or NOT SOLVE : ");
			String soString = scanner3.next();
			
			String geString = null;
			try {
				geString = crimeDao4.forChangeSVNSVcrimes(soString, num7);
			} catch (CrimeException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			System.out.println(geString);
			System.out.println("Enter any number for Back");
			System.out.print("Enter Number : ");
			int num8 = scanner3.nextInt();
			if(num8>0) {
				AllcallimpMethod.allcrimesCall(PSLid);
			}
		}
		else if(num3 == 4) {
			AllcallimpMethod.alladdCrimeCll(PSLid);
		}
		else if(num3 == 5) {
			AllcallimpMethod.allClassholder();
		}
		else {
			System.out.println();
			System.out.println("Enter Valid Number");
			AllcallimpMethod.allcrimesCall(PSLid);
		}
	}
	
	public static void allcriminalsCall(String PSid) {
		Scanner scanner4 = new Scanner(System.in);
		System.out.println();
		CrimeDao crimeDao = new CrimeDaoImplimantion();
		List<CrimesAndCriminals> list = null;
		try {
			list = crimeDao.forShowAllCriminal(PSid);
		} catch (CrimnalException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
//		System.out.println(list);
		list.forEach((l) -> System.out.println(
				"Criminal Name         : "+l.getCrmNameString()+"\n"+
				"Criminal age          : "+l.getAge()+"\n"+
				"Criminal Gender       : "+l.getCrmgenderString()+"\n"+
				"Criminal address      : "+l.getCrmaddressString()+"\n"+
				"Criminal Mark in face : "+l.getCrmMarkString()+"\n"+
				"Crime date            : "+l.getCdateString()+"\n"+
				"Crime Type            : "+l.getCrTypeString()+"\n"+
				"Crime                 : "+l.getCrdetailString()+"\n"+
				"Criminal arrest area  : "+l.getCrmarrestareaString()+"\n"+
				"Victim                : "+l.getCvictimString()+"\n"+
				"=============================================================="
				));
		System.out.println();
		System.out.println("Enter 1- LogOut");
		System.out.println("Enter any number for back");
		System.out.print("Enter Number : ");
		int num9 = scanner4.nextInt();
		if(num9==1) {
			AllcallimpMethod.allClassholder();
		}
		else {
			AllcallimpMethod.alladdCrimeCll(PSid);;
		}
	}
	
	public static void allCriminalBydate(String PSLid) {
		Scanner scanner4 = new Scanner(System.in);
		System.out.println();
		System.out.print("Enter first Date like(2022-11-03) : ");
		String date1 = scanner4.next();
		System.out.print("Enter Second Date like(2022-11-03) : ");
		String date2 = scanner4.next();
		System.out.println();
		CrimeDao crimeDao = new CrimeDaoImplimantion();
		List<CrimesAndCriminals> list = null;
		try {
			list = crimeDao.forShowCriminalbydate(date1, date2);
		} catch (CrimnalException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		if(list.size()>0) {
			list.forEach((l) -> System.out.println(
					"Criminal Name         : "+l.getCrmNameString()+"\n"+
							"Criminal age          : "+l.getAge()+"\n"+
							"Criminal Gender       : "+l.getCrmgenderString()+"\n"+
							"Criminal address      : "+l.getCrmaddressString()+"\n"+
							"Criminal Mark in face : "+l.getCrmMarkString()+"\n"+
							"Crime date            : "+l.getCdateString()+"\n"+
							"Crime Type            : "+l.getCrTypeString()+"\n"+
							"Crime                 : "+l.getCrdetailString()+"\n"+
							"Criminal arrest area  : "+l.getCrmarrestareaString()+"\n"+
							"Victim                : "+l.getCvictimString()+"\n"+
							"=============================================================="
					));
			
		}
		else {
			System.out.println("No any crime between in these date");
		}
		System.out.println("Enter 1- Search Again");
		System.out.println("Enter any Number for Back");
		System.out.print("Enter Number : ");
		int num10 = scanner4.nextInt();
		if(num10 == 1) {
			System.out.println();
			AllcallimpMethod.allCriminalBydate(PSLid);
		}
		else {
			System.out.println();
			AllcallimpMethod.alladdCrimeCll(date2);
		}
		
	}
}
