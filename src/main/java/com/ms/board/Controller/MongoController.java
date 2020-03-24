package com.ms.board.Controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ms.board.Service.MongoService;
import com.ms.board.Vo.BoardVo;

@Controller
@RequestMapping("/mongo")
public class MongoController {

	@Inject
	MongoService mongoService;
	
	//�Խ��� ����Ʈ
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public ModelAndView boardList() {
		
		List<BoardVo> list = mongoService.boardList(); //�Խñ� ����Ʈ
		
		ModelAndView model = new ModelAndView();
		model.setViewName("mongo/index");
		model.addObject("list", list);
		
		return model;
	}
	
	//�󼼺���
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView detail(@PathVariable String id) {
		
		BoardVo post = mongoService.detail(id); //�Խñ� �� ����
		
		ModelAndView model = new ModelAndView();
		model.setViewName("mongo/detail");
		model.addObject("post", post);
		
		return model;
	}
}
