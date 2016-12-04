package com.ohadr.common.types.c3p0;

public class ConnectionPoolStatus
{
	public int numBusyConnections;
	public int numBusyConnectionsAllUsers;

	public int numIdleConnections;
	public int numIdleConnectionsAllUsers;

	public int numConnections;
	public int numConnectionsAllUsers;
	
	public int numThreadsAwaitingCheckoutDefaultUser;
	
	public int numUnclosedOrphanedConnections;
	
	public String dataSourceName;
	
	@Override
	public String toString()
	{
		return dataSourceName + ": "
			+ " numBusyConnections=" + numBusyConnections 
			+ " numBusyConnectionsAllUsers=" + numBusyConnectionsAllUsers
			+ " numIdleConnections=" + numIdleConnections
			+ " numIdleConnectionsAllUsers=" + numIdleConnectionsAllUsers
			+ " numConnections=" + numConnections
			+ " numConnectionsAllUsers=" + numConnectionsAllUsers
			
			+ " numThreadsAwaitingCheckoutDefaultUser=" + numThreadsAwaitingCheckoutDefaultUser
			
			+ " numUnclosedOrphanedConnections=" + numUnclosedOrphanedConnections;
	}
}
