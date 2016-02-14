package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Batch;
import com.bean.Session;
import com.bean.User;
import com.bean.User_type;
import com.dao.BatchDao;
import com.dao.UserDao;
import com.service.AddBatchAndCreateTTwithSubPriority;
import com.service.CheckLogin;


@Controller
public class MyController {

	@Autowired
	Validator validator1,validator2;
	@Autowired
	private UserDao udao;
	@Autowired
	private BatchDao bdao;
	@Autowired
	private CheckLogin checkLogin;
	@Autowired
	private AddBatchAndCreateTTwithSubPriority tt;

	private static boolean flag=false;
	//private static User result;


	@RequestMapping("welcome")
	public ModelAndView method1() throws Exception {

		List<User> users = udao.getAllUsers();
		for (User user : users) {
			if(user.getUsername().equals("admin")&&user.getPassword().equals("admin")){
				flag=true;
				break;
			}
		}
		if(flag==false){

			User u = new User("admin", "admin", User_type.ADMIN,"ADMIN","admin@persistent.com");
			checkLogin.create_user(u);
			flag=true;
		}
		return new ModelAndView("login");

	}


	@RequestMapping(value="next",method=RequestMethod.POST)
	public ModelAndView method2(HttpServletRequest request, ModelMap map)
			throws Exception {
		User result=null;
		HttpSession session=null;
		String un = request.getParameter("Username");
		String pw = request.getParameter("Password");
		result = checkLogin.checkLogin(un, pw);
		if (result != null) {
			session=request.getSession();
			session.setAttribute("result", result);
			//map.addAttribute(result);
			return new ModelAndView("index_manager");

		} else {
			
			request.setAttribute("error"," \" USERNAME or PASSWORD is WRONG !! \"");
			return new ModelAndView("login");
		}

	}


	@RequestMapping("index_manager")
	public ModelAndView dashboard() throws Exception {

		return new ModelAndView("index_manager");

	}


	@RequestMapping("create_user")
	public ModelAndView create(ModelMap map)throws Exception{

		User user = new User();
		map.addAttribute(user);
		return new ModelAndView("index_manager");

	}

	@ModelAttribute("user")
	public User createModel() {

		return new User();

	}


	@RequestMapping(value="create_user_1",method=RequestMethod.POST)
	public ModelAndView create_1(@ModelAttribute("user")User user,HttpServletRequest request,BindingResult result1)throws Exception{

		validator2.validate(user, result1);
		if(result1.hasErrors()){
			request.setAttribute("message", " \"  MESSAGE : USER with USERNAME \""+user.getUsername()+"\" already EXISTS !! \"");
			return new ModelAndView("index_manager");
		}

		user.setUser_type(User_type.MANAGER);
		checkLogin.create_user(user);
		request.setAttribute("message", " \"  MESSAGE : USER with USERNAME \""+user.getUsername()+"\" CREATED SUCCESSFULLY !! \"");
		return new ModelAndView("index_manager");

	}


	@RequestMapping("del_user")
	public ModelAndView delete_user(HttpServletRequest request,Model map) throws Exception {

		User user=new User();
		map.addAttribute(user);
		return new ModelAndView("delete_user");

	}


	@RequestMapping(value ="del_user_1", method = RequestMethod.POST)
	public ModelAndView delete_user_1(@ModelAttribute("user")User user,HttpServletRequest request,BindingResult result1) {

		List<User> userlist=udao.getAllUsers();
		User u=null;
		for (User u1 :userlist) {
			if(u1.getUsername().equals(user.getUsername()))
			{
				u=u1;
				break;

			}
		}
		udao.deleteUser(u);
		request.setAttribute("message", " \"  MESSAGE : USER with USERNAME \""+u.getUsername()+"\" DELETED SUCCESSFULLY !! \"");
		return new ModelAndView("index_manager");
	}


	@RequestMapping("add_batch")
	public ModelAndView add_batch(HttpServletRequest request, ModelMap map)
			throws Exception {

		Batch batch = new Batch();
		map.addAttribute(batch);
		return new ModelAndView("batch_new");

	}


	@RequestMapping(value="add_batch_1",method=RequestMethod.POST)
	public ModelAndView add_batch_1(@ModelAttribute("batch")Batch batch,
			HttpServletRequest request,BindingResult result1) throws Exception {

		validator1.validate(batch, result1);
		if(result1.hasErrors()){
			System.out.println("errors");
			return new ModelAndView("batch_new");
		}
		tt.addBatch(batch);
		request.setAttribute("message", " \"  MESSAGE : BATCH with BATCH NAME \""+batch.getBatch_name()+"\" ADDED SUCCESSFULLY !! \"");
		return new ModelAndView("index_manager");

	}

	@RequestMapping("del_batch")
	public ModelAndView delete_batch(HttpServletRequest request,Model map) throws Exception {

		Batch batch = new Batch();
		map.addAttribute(batch);
		return new ModelAndView("delete_batch");

	}


	@RequestMapping(value ="del_batch_1", method = RequestMethod.POST)
	public ModelAndView delete_batch_1(@ModelAttribute("batch")Batch batch,HttpServletRequest request) {

		List<Batch> batches=bdao.getAllBatch();
		Batch b=null;
		for (Batch batch1 : batches) {
			if(batch1.getBatch_name().equals(batch.getBatch_name()))
			{
				b=batch1;
				break;

			}
		}
		request.setAttribute("message", " \"  MESSAGE : BATCH with BATCH NAME \""+b.getBatch_name()+"\" DELETED SUCCESSFULLY !! \"");
		bdao.deleteBatch(b);
		return new ModelAndView("index_manager");

	}


	@RequestMapping(value="show_batch")
	public ModelAndView show_batch(HttpServletRequest request,Model map) throws Exception {

		int val1=Integer.parseInt(request.getParameter("val"));
		request.setAttribute("val2", new Integer(val1));
		return new ModelAndView("show_batch");

	}


	@RequestMapping(value="download",method=RequestMethod.POST)
	public ModelAndView download(HttpServletRequest request,Model map) throws Exception {

		Batch batch = new Batch();
		map.addAttribute(batch);
		return new ModelAndView("download");

	}


	@RequestMapping(value = "downloadExcel", method = RequestMethod.POST)
	public ModelAndView downloadExcel(@ModelAttribute("batch") Batch batch,HttpServletRequest request,BindingResult result1) {

		List<Batch> batches=bdao.getAllBatch();
		List<Session> sessions=null;;
		for (Batch batche : batches) {
			if(batche.getBatch_name().equalsIgnoreCase(batch.getBatch_name())){
				sessions=batche.getSessions();
				break;
			}
		}
		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("excelView", "sessions", sessions);

	}


	@RequestMapping(value = "downloadPDF", method = RequestMethod.POST)
	public ModelAndView downloadPDF(@ModelAttribute("batch") Batch batch,HttpServletRequest request) {

		List<Batch> batches=bdao.getAllBatch();
		List<Session> sessions=null;
		for (Batch batche : batches) {
			if(batche.getBatch_name().equalsIgnoreCase(batch.getBatch_name())){
				sessions=batche.getSessions();
				break;
			}
		}
		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "sessions", sessions);

	}


	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session) throws Exception {
		//result=null;
	    session.invalidate();
		return new ModelAndView("login");

	}

}
