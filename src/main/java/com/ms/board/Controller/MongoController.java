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
	
	//�Խñ� �ۼ� ������
	@RequestMapping(value="/post", method=RequestMethod.GET)
	public String write() {
		
		return "mongo/write";
	}
	
	//�Խñ� �ۼ� ó��
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public String write(BoardVo post) {
		
		mongoService.insertPost(post);
		
		return "redirect:/mongo/boardList";
	}
	
	//�Խñ� ���� ������
	@RequestMapping(value="/post/{id}", method=RequestMethod.GET)
	public ModelAndView update(@PathVariable String id) {

		BoardVo post = mongoService.detail(id);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("mongo/update");
		model.addObject("post", post);
		
		return model;
	}
	
	//�Խñ� ���� ó��
	@RequestMapping(value="/post/{id}", method=RequestMethod.PUT)
	public String update(@PathVariable String id, BoardVo updatePost) {
		
		BoardVo post = mongoService.updatePost(updatePost);
		
		return "redirect:/mongo/" + post.getId();
	}
	
	//�Խñ� ���� ó��
	@RequestMapping(value="/post/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable String id) {
		
		mongoService.deletePost(id);
		
		return "redirect:/mongo/boardList";
	}
}
