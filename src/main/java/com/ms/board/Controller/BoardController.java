package com.ms.board.Controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ms.board.Service.BoardService;
import com.ms.board.Vo.BoardVo;

@Controller
public class BoardController {

	@Inject
	private BoardService boardService;
	
	//�Խ��� �ʱ������� (�� ����Ʈ)
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public ModelAndView boardList() throws Exception{
		
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		
		List<BoardVo> boardList = boardService.getBoardList();
		model.addObject("boardList", boardList);
		
		return model;
	}
	
	//�Խñ� �ۼ�
	@RequestMapping(value="/posts", method=RequestMethod.GET)
	public ModelAndView posts() throws Exception{
		
		ModelAndView model = new ModelAndView();
		model.setViewName("write");
				
		return model;
	}
	
	//�Խñ� �ۼ� ó��
	@RequestMapping(value="/posts", method=RequestMethod.POST)
	public String createPosts(BoardVo posts) throws Exception{
		
		boardService.insertPosts(posts);
				
		return "redirect:/boardList";
	}
	
	//�Խñ� ��ȸ
	@RequestMapping(value="/{bno}", method=RequestMethod.GET)
	public ModelAndView detail(@PathVariable int bno) throws Exception{
		
		BoardVo post = boardService.detailPosts(bno);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("detail");
		model.addObject("post", post);
		
		return model;
	}
	
	//�Խñ� ����
	@RequestMapping(value="/posts/{bno}", method=RequestMethod.DELETE)
	public String delete(@PathVariable int bno) throws Exception{
		
		boardService.deletePosts(bno);
		
		return "redirect:/boardList";
	}
	
	//�Խñ� ����
	@RequestMapping(value="/posts/{bno}", method=RequestMethod.GET)
	public ModelAndView update(@PathVariable int bno) throws Exception{
		
		BoardVo posts = boardService.detailPosts(bno);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("update");
		model.addObject("posts", posts);
		
		return model;
	}
	
	//�Խñ� ���� ó��
	@RequestMapping(value="/posts/{bno}", method=RequestMethod.PUT)
	public String updatePosts(@PathVariable int bno, BoardVo posts) throws Exception{
		
		boardService.updatePosts(posts);
		
		return "redirect:/" + bno;
	}
}
