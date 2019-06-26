package seven.service;

import java.util.List;

import seven.entity.PageBean;
import seven.entity.Section;

public interface SectionService {

	public void saveSection(Section section);
	
	public void deleteSection(Section section);
	
	public List<Section> findSectionList(Section s_section,PageBean pageBean);
	
	public Long getSectionCount(Section s_section);
	
	public Section findSectionById(int sectionId);
	
}
