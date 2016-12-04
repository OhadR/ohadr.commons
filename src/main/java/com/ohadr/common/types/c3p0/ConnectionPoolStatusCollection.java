package com.ohadr.common.types.c3p0;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement  
public class ConnectionPoolStatusCollection
{
	public List<ConnectionPoolStatus> collection;

}
