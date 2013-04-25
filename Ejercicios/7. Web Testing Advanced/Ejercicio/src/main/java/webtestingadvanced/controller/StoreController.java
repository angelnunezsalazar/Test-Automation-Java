package webtestingadvanced.controller;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import webtestingadvanced.model.Order;
import webtestingadvanced.model.Product;

@Controller
public class StoreController {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	@RequestMapping("/")
	public ModelAndView chooseproduct(Model model) {
		List<Product> products = sessionFactory.getCurrentSession().createQuery("from Product").list();
		return new ModelAndView("chooseproduct", "products", products);
	}

	@Transactional
	@RequestMapping("/placeorder/{productId}")
	public String placeorder(@PathVariable("productId") int productId) {
		Product products = (Product) sessionFactory.getCurrentSession().createQuery("from Product p where p.Id=:id").setParameter("id", productId)
				.uniqueResult();
		return "placeorder";
	}

	@Transactional
	@RequestMapping(value = "/placeorder", method = RequestMethod.POST)
	public String placeorder(@ModelAttribute("order") Order order, BindingResult result) {
		sessionFactory.getCurrentSession().save(order);
		return "redirect:/confirmation";
	}

	@Transactional
	@RequestMapping("/confirmation")
	public String confirmation() {
		return "confirmation";
	}
}
