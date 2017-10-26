package com.ohadr.common.utils.resultset;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import junit.framework.Assert;

public class ResultSetConvertersTest
{
	@Test
	public void testcreateResultSet() throws SQLException, IOException
	{
		final String expectedResult = "{\"Balance_USD\":13.91,\"SIGNUPDATE\":2017-10-26,\"CASINONAME\":\"WHPoker\",\"Seniority\":1696}\n{\"Balance_USD\":13.91,\"SIGNUPDATE\":2017-10-26,\"CASINONAME\":\"WHPoker\",\"Seniority\":1696}\n";
		ResultSet resultSet = new ResultSetImpl();
		
		//prepare the result-set
		for(int i=0; i < 2; ++i)
		{
			Date signupDate = new Date( System.currentTimeMillis() );
			resultSet.moveToInsertRow();
			
			resultSet.updateString("CASINONAME", "WHPoker");
			resultSet.updateDate("SIGNUPDATE", signupDate);		
			resultSet.updateInt("Seniority", 1696);
			resultSet.updateDouble("Balance_USD", 13.9100);
			
			resultSet.insertRow();
		}
		resultSet.first();

		StringWriter stringWriter = new StringWriter();

		//test the converter:
		ResultSetConverters.writeResultSetToWriter(resultSet, stringWriter);
		stringWriter.close();
		Assert.assertEquals(expectedResult, stringWriter.toString());
	}

//	@Test
	public void createResultSet() throws SQLException, IOException
	{
		ResultSet resultSet = new ResultSetImpl();
		
		//prepare the result-set, 500K lines and write to a file (PrintWriter)
		for(int i=0; i < 500000; ++i)
		{
			Date signupDate = new Date( System.currentTimeMillis() );
			Date lastLoginDate = new Date( System.currentTimeMillis() );
			Date updateDate = new Date( System.currentTimeMillis() );
			resultSet.moveToInsertRow();
			
			resultSet.updateString("PLAYERUSERNAME", "_GIGGLY6_");
			resultSet.updateString("ORBISACCOUNTID", "05320KZ");
			resultSet.updateString("CASINONAME", "WHPoker");
			resultSet.updateDate("SIGNUPDATE", signupDate);		
			resultSet.updateDate("LASTLOGINDATE", lastLoginDate);	
			resultSet.updateDate("FIRSTDEPOSITDATE", updateDate);
			resultSet.updateInt("Seniority", 1696);
			resultSet.updateString("CUSTOM18", null);
			resultSet.updateString("VIPLEVEL", "-2");					//to be converted to int
			resultSet.updateString("CURRENCYCODE", "GBP");
			resultSet.updateString("STATSDATE", "2017-01-29");					//should be Date
			resultSet.updateDouble("Balance_USD", 13.9100);
			resultSet.updateDouble("MaxBalance_USD", 0.2400);
			resultSet.updateDouble("Bets_USD", 0.0000);
			resultSet.updateDouble("Wins_USD", 20713.3890);
			resultSet.updateInt("Gamecount", 2922);
			resultSet.updateDouble("Deposits_USD", 2105.5513);
			resultSet.updateDouble("AvgDeposits_USD", 10.3721);
			resultSet.updateDouble("Chargebacks_USD", 0.0000);
			resultSet.updateString("FROZEN", "N");
			resultSet.updateString("BONUSSEEKER", null);
			resultSet.updateString("ADVERTISER", "defaultsport");
			resultSet.updateString("COUNTRYCODE", "GB");
			resultSet.updateDouble("TOTALCOMPPOINTS", 0);
			resultSet.updateString("LANGUAGECODE", "English");
			resultSet.updateInt("Depositcount", 203);
			resultSet.updateDouble("Withdraw_USD", 239.7301);
			resultSet.updateDouble("Slot_Bets_USD", 0.0);
			resultSet.updateInt("Credits_USD", 0);
			resultSet.updateDouble("NGR_USD", 0.000);
			resultSet.updateDouble("Bonus_USD", 0.0000);
			resultSet.updateInt("n_ClientParams", 0);
			
			resultSet.insertRow();
		}
		resultSet.first();

		
		String fileName = "/ohad/dev/tmp/RESULTS_FILE_NAME.txt";
		File file = new File(fileName);
		// clean if already exist
		Files.deleteIfExists(file.toPath());

		FileWriter writerFile = new FileWriter(fileName, true);
		PrintWriter writer = new PrintWriter(writerFile);

		ResultSetConverters.writeResultSetToWriter(resultSet, writer);
		writer.close();
	}

}
