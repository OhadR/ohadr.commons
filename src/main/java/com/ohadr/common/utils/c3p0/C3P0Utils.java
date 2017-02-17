package com.ohadr.common.utils.c3p0;

import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ohadr.common.types.c3p0.ConnectionPoolStatus;

public class C3P0Utils
{
	public static ConnectionPoolStatus getConnectionPoolStatus(ComboPooledDataSource comboPooledDataSource)
	{
		ConnectionPoolStatus connectionPoolStatus = new ConnectionPoolStatus();
		try
		{
			connectionPoolStatus.dataSourceName = comboPooledDataSource.getDataSourceName();
			connectionPoolStatus.numBusyConnections = comboPooledDataSource.getNumBusyConnections();
			connectionPoolStatus.numBusyConnectionsAllUsers = comboPooledDataSource.getNumBusyConnectionsAllUsers();

			connectionPoolStatus.numIdleConnections = comboPooledDataSource.getNumIdleConnections();
			connectionPoolStatus.numIdleConnectionsAllUsers = comboPooledDataSource.getNumIdleConnectionsAllUsers();

			connectionPoolStatus.numConnections = comboPooledDataSource.getNumConnections();
			connectionPoolStatus.numConnectionsAllUsers = comboPooledDataSource.getNumConnectionsAllUsers();
			
			connectionPoolStatus.numThreadsAwaitingCheckoutDefaultUser = comboPooledDataSource.getNumThreadsAwaitingCheckoutDefaultUser();
			
			connectionPoolStatus.numUnclosedOrphanedConnections = comboPooledDataSource.getNumUnclosedOrphanedConnections();
		}
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return connectionPoolStatus;
		
	}
}

