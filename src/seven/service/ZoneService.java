package seven.service;

import java.util.List;

import seven.entity.PageBean;
import seven.entity.Zone;

public interface ZoneService {

	public void saveZone(Zone zone);
	
	public void deleteZone(Zone zone);
	
	public List<Zone> findZoneList(Zone s_zone,PageBean pageBean);
	
	public Long getZoneCount(Zone s_zone);
	
	public Zone findZoneById(int zoneId);
}
