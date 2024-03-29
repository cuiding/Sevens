package seven.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import seven.dao.BaseDAO;
import seven.entity.PageBean;
import seven.entity.Section;
import seven.service.SectionService;
import seven.util.StringUtil;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {

	@Resource
	private BaseDAO<Section> baseDAO;
	
	@Override
	public void saveSection(Section section) {
		baseDAO.merge(section);
	}

	@Override
	public void deleteSection(Section section) {
		baseDAO.delete(section);
	}
/*查询section*/
	@Override
	public List<Section> findSectionList(Section s_section,PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Section");
		if (s_section!=null) {
			if (StringUtil.isNotEmpty(s_section.getName())) {
				hql.append(" and name like ?");
				param.add("%"+s_section.getName()+"%");
			}
		}
		if (s_section!=null) {
			if (s_section.getZone()!=null&&s_section.getZone().getId()>0) {
				hql.append(" and zoneId = ?");
				param.add(s_section.getZone().getId());
			}
		}
		if (s_section!=null) {
			if (s_section.getMaster()!=null&&s_section.getMaster().getId()>0) {
				hql.append(" and masterId = ?");
				param.add(s_section.getMaster().getId());
			}
		}
		if (pageBean!=null) {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}
	
	@Override
	public Long getSectionCount(Section s_section) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Section");
		if (s_section!=null) {
			if (StringUtil.isNotEmpty(s_section.getName())) {
				hql.append(" and name like ?");
				param.add("%"+s_section.getName()+"%");
			}
			if (s_section!=null) {
				if (s_section.getZone()!=null&&s_section.getZone().getId()>0) {
					hql.append(" and zoneId = ?");
					param.add(s_section.getZone().getId());
				}
			}
			if (s_section!=null) {
				if (s_section.getMaster()!=null&&s_section.getMaster().getId()>0) {
					hql.append(" and masterId = ?");
					param.add(s_section.getMaster().getId());
				}
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public Section findSectionById(int sectionId) {
		return baseDAO.get(Section.class, sectionId);
	}

}
