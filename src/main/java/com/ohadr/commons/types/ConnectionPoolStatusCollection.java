package com.ohadr.commons.types;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement  
public class ConnectionPoolStatusCollection
{
	public List<ConnectionPoolStatus> collection;

}
