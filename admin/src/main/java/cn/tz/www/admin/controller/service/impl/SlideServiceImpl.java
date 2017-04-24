package cn.tz.www.admin.controller.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tz.www.admin.controller.service.SlideService;
import cn.tz.www.customer.entity.repository.slide.SlideRepository;
import cn.tz.www.customer.entity.table.Slide;
import cn.tz.www.customer.entity.tools.Groups;
import cn.tz.www.customer.entity.tools.Page;

@Service
public class SlideServiceImpl implements SlideService {
	@Autowired
	private SlideRepository slideRepository;
    @Transactional
	public void createSlide(Slide slide) {
		slideRepository.persist(slide);
	}
    @Transactional
  	public void updateSlide(Slide slide) {
  		slideRepository.update(slide);
  	}

	@Override
	public Page<Slide> loadSlideList(Groups groups, Page<Slide> page) {
		return slideRepository.findEntityPageByGroups(groups, page);
	}

	public Boolean checkSn(Integer sn) {
		Groups g = new Groups();
		g.Add("sn", sn);
		List<Slide> list = slideRepository.findEntityByGroups(g);
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}
}
