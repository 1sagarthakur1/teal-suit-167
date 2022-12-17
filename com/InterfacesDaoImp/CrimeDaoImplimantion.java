package com.InterfacesDaoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Beens.Crimes;
import com.Beens.CrimesAndCriminals;
import com.Beens.Criminal;
import com.Beens.PoliceStation;
import com.Interfaces.CrimeDao;
import com.exceptionshendal.CrimeException;
import com.exceptionshendal.CrimnalException;
import com.exceptionshendal.PoliceStationException;
import com.utility.GetConnectionFromDBMS;

public class CrimeDaoImplimantion implements CrimeDao {
	
	@Override
	public void showAllPS() throws PoliceStationException{
		// TODO Auto-generated method stub
		try(Connection connection = GetConnectionFromDBMS.tackConnection()){
			PreparedStatement pStatement = connection.prepareStatement("select * from policestation");
			
			ResultSet resultSet = pStatement.executeQuery();
			
			while(resultSet.next()) {
				int psid            = resultSet.getInt("PSid");
				String psareaString = resultSet.getString("PSarea");
				String psidString   = resultSet.getString("PSloginid");
				
                System.out.println("Police Station area : "+psareaString+"                                               "+"Station id : "+psid);
				System.out.println("Station  login id   : "+ psidString);
				System.out.println();
				System.out.println("----------------------------------------------------------------------------");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new PoliceStationException(e.getMessage());
		}
		
	}

	@Override
	public String forloginPoliceStation(String PSid, String PSpass) throws PoliceStationException {
		// TODO Auto-generated method stub
		String string = "Invalid ID Or Password";
		
		try(Connection connection= GetConnectionFromDBMS.tackConnection()){
		   PreparedStatement pStatement = connection.prepareStatement("Select * from policestation where Psloginid = ? and PSpassword = ?");
		   pStatement.setString(1, PSid);
		   pStatement.setString(2, PSpass);
		   
		   ResultSet resultSet = pStatement.executeQuery();
		   
		   while(resultSet.next()) {
			   String psareaString       = resultSet.getString("PSarea");
			   int pSidString            = resultSet.getInt("PSid");
			   String pSinchargeString   = resultSet.getString("PSincharge");
			   String pSloginidString    = resultSet.getString("PSloginid");
			   if(pSinchargeString.length()>0) {
				   string="Login Successfuly.." + "\n" 
						   +"Welcom to "+ psareaString + " Police Station" +"\n"
						   +"Incharge of Police Station    : " + pSinchargeString +"\n"
						   +"Login ID of it Police Station : " + pSloginidString +"\n"
						   +"Police Station Id             : " + pSidString;
			   }
		   }
		   
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new PoliceStationException(e.getMessage());
			
		}
		
		return string;
	}

	@Override
	public String forRagistarPS(PoliceStation policeStation) throws PoliceStationException{
        String string = "Police Station not Registed";
        
		try(Connection connection= GetConnectionFromDBMS.tackConnection()){
		   PreparedStatement pStatement = connection.prepareStatement(" insert into policestation values(?,?,?,?,?)");
		   pStatement.setInt(1, policeStation.getPsid());
		   pStatement.setString(2, policeStation.getPsareaString());
		   pStatement.setString(3, policeStation.getPsinchargeString());
		   pStatement.setString(4, policeStation.getPsloginidString());
		   pStatement.setString(5, policeStation.getPsloginidString());
		   int i = pStatement.executeUpdate();
		   if(i>0) {
			   string = "Police station Registetion successfully";
		   }
		   
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new PoliceStationException(e.getMessage());
		}
		return string;
	}

	@Override
	public String forAddCrimeAndCrimnal(Crimes crimes, Criminal criminal)throws CrimnalException {
        String string = "Crime not inserted..";
		
        int i = 0;
        
		try(Connection connection= GetConnectionFromDBMS.tackConnection()){
		   PreparedStatement pStatement = connection.prepareStatement(" insert into crimes values(?,?,?,?,?,?,?,?,?)");
		   pStatement.setInt(1, crimes.getCrid());
		   pStatement.setString(2, crimes.getcRdateString());
		   pStatement.setString(3, crimes.getCrtypeString());
		   pStatement.setString(4, crimes.getVictimString());
		   pStatement.setString(5, crimes.getCrdetailString());
		   pStatement.setString(6, crimes.getCriminalString());
		   pStatement.setString(7, crimes.getSolveornotString());
		   pStatement.setInt(8, crimes.getPsid());
		   pStatement.setString(9, crimes.getCrplaceString());
		   
		   int j = pStatement.executeUpdate();
		   if(j>0) {
			   i++;
		   }
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CrimnalException(e.getMessage());
//			e.getMessage();
		}
		
		
		try(Connection connection= GetConnectionFromDBMS.tackConnection()){
			   PreparedStatement pStatement = connection.prepareStatement(" insert into criminal values(?,?,?,?,?,?,?,?)");
			   pStatement.setInt(1, criminal.getCrno());
			   pStatement.setString(2, criminal.getCnameString());
			   pStatement.setInt(3, criminal.getCage());
			   pStatement.setString(4, criminal.getCgenderString());
			   pStatement.setString(5, criminal.getCaddressString());
			   pStatement.setString(6, criminal.getcMarkfString());
			   pStatement.setString(7, criminal.getArrestareaString());
			   pStatement.setInt(8, criminal.getCrimeid());
			   
			   int j = pStatement.executeUpdate();
			   if(j>0) {
				   i++;
			   }
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				e.getMessage();
			}
		if(i>0) {
			string = "Crime has been successfully added.";
		}
		
		return string;
	}

	@Override
	public List<Crimes> forShowallCrimeList(String Psid)throws CrimeException {
		// TODO Auto-generated method stub
		List<Crimes> list = new ArrayList<>();
		try(Connection connection = GetConnectionFromDBMS.tackConnection()){
			PreparedStatement pStatement =	connection.prepareStatement("select * from crimes c inner join policestation p on c.psid = p.PSid and p.PSloginid = ?;");
			pStatement.setString(1, Psid);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				int CRid          = resultSet.getInt("crid");
				String CRdate     = resultSet.getString("crtime");
				String crtype     = resultSet.getString("crtype");
				String victim     = resultSet.getString("victim");
				String crdetail   = resultSet.getString("crdetail");
				String criminal   = resultSet.getString("Criminal");
				String solveornot = resultSet.getString("solveornot");
				int PSid          = resultSet.getInt("psid");
				String crplace    = resultSet.getString("crplace");
				
				Crimes crimes = new Crimes();
				
				crimes.setCrid(CRid);
				crimes.setcRdateString(CRdate);
				crimes.setCrtypeString(crtype);
				crimes.setVictimString(victim);
				crimes.setCrdetailString(crdetail);
				crimes.setCriminalString(criminal);
				crimes.setSolveornotString(solveornot);
				crimes.setPsid(PSid);
				crimes.setCrplaceString(crplace);
				
				list.add(crimes);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CrimeException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public List<Crimes> forshowAllSolveCrimes(String slove,String PSid) throws CrimeException{
		// TODO Auto-generated method stub
		List<Crimes> list = new ArrayList<>();
		try(Connection connection = GetConnectionFromDBMS.tackConnection()){
		  PreparedStatement pStatement =	connection.prepareStatement("select * from crimes c inner join policestation p on c.psid = p.PSid and c.solveornot = ? and p.PSloginid = ?;");
		  pStatement.setString(1, slove);
		  pStatement.setString(2, PSid);
		  
		  ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				int CRid          = resultSet.getInt("crid");
				String CRdate     = resultSet.getString("crtime");
				String crtype     = resultSet.getString("crtype");
				String victim     = resultSet.getString("victim");
				String crdetail   = resultSet.getString("crdetail");
				String criminal   = resultSet.getString("Criminal");
				String solveornot = resultSet.getString("solveornot");
				int Psid          = resultSet.getInt("psid");
				String crplace    = resultSet.getString("crplace");
				
				Crimes crimes = new Crimes();
				
				crimes.setCrid(CRid);
				crimes.setcRdateString(CRdate);
				crimes.setCrtypeString(crtype);
				crimes.setVictimString(victim);
				crimes.setCrdetailString(crdetail);
				crimes.setCriminalString(criminal);
				crimes.setSolveornotString(solveornot);
				crimes.setPsid(Psid);
				crimes.setCrplaceString(crplace);
				
				list.add(crimes);
		  
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CrimeException(e.getMessage());
		}
	
		
		return list;
	}

	@Override
	public List<Crimes> forShowAllUNsolveCrimes(String notsolve,String PSLid) throws CrimeException{
		// TODO Auto-generated method stub
		List<Crimes> list = new ArrayList<>();
		try(Connection connection = GetConnectionFromDBMS.tackConnection()){
		  PreparedStatement pStatement =	connection.prepareStatement("select * from crimes c inner join policestation p on c.psid = p.PSid and c.solveornot = ? and p.PSloginid = ?;");
		  pStatement.setString(1, notsolve);
		  pStatement.setString(2, PSLid);
		  
		  ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				int CRid          = resultSet.getInt("crid");
				String CRdate     = resultSet.getString("crtime");
				String crtype     = resultSet.getString("crtype");
				String victim     = resultSet.getString("victim");
				String crdetail   = resultSet.getString("crdetail");
				String criminal   = resultSet.getString("Criminal");
				String solveornot = resultSet.getString("solveornot");
				int Psid          = resultSet.getInt("psid");
				String crplace    = resultSet.getString("crplace");
				
				Crimes crimes = new Crimes();
				
				crimes.setCrid(CRid);
				crimes.setcRdateString(CRdate);
				crimes.setCrtypeString(crtype);
				crimes.setVictimString(victim);
				crimes.setCrdetailString(crdetail);
				crimes.setCriminalString(criminal);
				crimes.setSolveornotString(solveornot);
				crimes.setPsid(Psid);
				crimes.setCrplaceString(crplace);
				
				list.add(crimes);
		  
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CrimeException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public String forChangeSVNSVcrimes(String sloveornot,int CRid) throws CrimeException {
		// TODO Auto-generated method stub
		String string = "NOT UPDATE";
		try(Connection connection = GetConnectionFromDBMS.tackConnection()){
			  PreparedStatement pStatement =	connection.prepareStatement("UPDATE crimes set solveornot = ? where CRID = ?");
			  pStatement.setString(1, sloveornot);
			  pStatement.setInt(2, CRid);
			  int i = pStatement.executeUpdate();
			  if(i>0) {
				  string = "UPDATE";
			  }
			  
				
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CrimeException(e.getMessage());
		}
		
		return string;
	}

	@Override
	public List<CrimesAndCriminals> forShowAllCriminal(String PSLid)throws CrimnalException {
		List<CrimesAndCriminals> list = new ArrayList<>();
		try(Connection connection = GetConnectionFromDBMS.tackConnection()){
		  PreparedStatement pStatement =	connection.prepareStatement("select * from criminal cr inner join crimes c inner join policestation p on cr.crid = c.crid and c.psid = p.PSid and p.PSloginid = ?");
		  pStatement.setString(1, PSLid);
		  
		  ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				String crmName        = resultSet.getString("Ccrname");
			    int age               = resultSet.getInt("age");
			    String crmgender      = resultSet.getString("gender");
			    String crmaddress     = resultSet.getString("address");
			    String crmMark        = resultSet.getString("Markinface");
			    String cdate          = resultSet.getString("crtime");
			    String crType         = resultSet.getString("crtype");
			    String crdetail       = resultSet.getString("crdetail");
			    String crmarrestarean = resultSet.getString("arrestedarea");
			    String cvictim        = resultSet.getString("victim");
			    
//			    System.out.println(crmName);
			    CrimesAndCriminals crimesAndCriminals = new CrimesAndCriminals();
			    
			    crimesAndCriminals.setCrmNameString(crmName);
			    crimesAndCriminals.setAge(age);
			    crimesAndCriminals.setCrmgenderString(crmgender);
			    crimesAndCriminals.setCrmaddressString(crmaddress);
			    crimesAndCriminals.setCrmMarkString(crmMark);
			    crimesAndCriminals.setCdateString(cdate);
			    crimesAndCriminals.setCrTypeString(crType);
			    crimesAndCriminals.setCrdetailString(crdetail);
			    crimesAndCriminals.setCrmarrestareaString(crmarrestarean);
			    crimesAndCriminals.setCvictimString(cvictim);
			    
			    list.add(crimesAndCriminals);
		  
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CrimnalException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public List<CrimesAndCriminals> forShowCriminalbydate(String date1, String date2)throws CrimnalException {
		List<CrimesAndCriminals> list = new ArrayList<>();
		try(Connection connection = GetConnectionFromDBMS.tackConnection()){
		  PreparedStatement pStatement =	connection.prepareStatement("select * from criminal cr inner join crimes c on cr.crid = c.crid and c.crtime>= ? and c.crtime<= ?");
		  pStatement.setString(1, date1);
		  pStatement.setString(2, date2);
		  
		  ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				String crmName        = resultSet.getString("Ccrname");
			    int age               = resultSet.getInt("age");
			    String crmgender      = resultSet.getString("gender");
			    String crmaddress     = resultSet.getString("address");
			    String crmMark        = resultSet.getString("Markinface");
			    String cdate          = resultSet.getString("crtime");
			    String crType         = resultSet.getString("crtype");
			    String crdetail       = resultSet.getString("crdetail");
			    String crmarrestarean = resultSet.getString("arrestedarea");
			    String cvictim        = resultSet.getString("victim");
			    
//			    System.out.println(crmName);
			    CrimesAndCriminals crimesAndCriminals = new CrimesAndCriminals();
			    
			    crimesAndCriminals.setCrmNameString(crmName);
			    crimesAndCriminals.setAge(age);
			    crimesAndCriminals.setCrmgenderString(crmgender);
			    crimesAndCriminals.setCrmaddressString(crmaddress);
			    crimesAndCriminals.setCrmMarkString(crmMark);
			    crimesAndCriminals.setCdateString(cdate);
			    crimesAndCriminals.setCrTypeString(crType);
			    crimesAndCriminals.setCrdetailString(crdetail);
			    crimesAndCriminals.setCrmarrestareaString(crmarrestarean);
			    crimesAndCriminals.setCvictimString(cvictim);
			    
			    list.add(crimesAndCriminals);
		  
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CrimnalException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public int forcountAllpolicestation() throws PoliceStationException{
		int i=0;
		try(Connection connection = GetConnectionFromDBMS.tackConnection()){
			  PreparedStatement pStatement =	connection.prepareStatement("select * from policestation");
			  
			  ResultSet resultSet = pStatement.executeQuery();
				while(resultSet.next()) {
					i++;
				}
				
		} 
		catch (SQLException e) {
				// TODO: handle exception
			e.printStackTrace();
			throw new PoliceStationException(e.getMessage());
		}
		
		return i;
	}

	@Override
	public int forcountAllcrime() throws CrimeException{
		int i=0;
		try(Connection connection = GetConnectionFromDBMS.tackConnection()){
			  PreparedStatement pStatement =	connection.prepareStatement("select * from crimes");
			  
			  ResultSet resultSet = pStatement.executeQuery();
				while(resultSet.next()) {
					i++;
				}
				
		} 
		catch (SQLException e) {
				// TODO: handle exception
			e.printStackTrace();
			throw new CrimeException(e.getMessage());
		}
		
		return i;
	}

	@Override
	public int forcountAllcriminal()throws CrimnalException {
		int i=0;
		try(Connection connection = GetConnectionFromDBMS.tackConnection()){
			  PreparedStatement pStatement =	connection.prepareStatement("select * from criminal");
			  
			  ResultSet resultSet = pStatement.executeQuery();
				while(resultSet.next()) {
					i++;
				}
				
		} 
		catch (SQLException e) {
				// TODO: handle exception
			e.printStackTrace();
			throw new CrimnalException(e.getMessage());
		}
		
		return i;
	}

}
