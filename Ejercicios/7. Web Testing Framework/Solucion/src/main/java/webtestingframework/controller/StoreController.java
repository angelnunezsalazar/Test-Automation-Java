package webtestingframework.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import webtestingframework.data.DataAccess;
import webtestingframework.model.Order;
import webtestingframework.model.Product;

@Controller
public class StoreController {

	DataAccess dataAccess=new DataAccess();
	
	@RequestMapping("/")
	public ModelAndView chooseproduct(Model model) throws Exception {
		List<Product> products = dataAccess.listAll(Product.class);
		return new ModelAndView("chooseproduct", "products", products);
	}

	@RequestMapping("/placeorder/{productId}")
	public ModelAndView placeorder(@PathVariable("productId") String productId) throws Exception {
		Product product = (Product) dataAccess.getProduct(productId);
		return new ModelAndView("placeorder", "product", product);
	}

	@RequestMapping(value = "/placeorder", method = RequestMethod.POST)
	public String placeorder(@ModelAttribute("order") Order order, BindingResult result) throws Exception {
		dataAccess.saveOrder(order);
		return "redirect:/confirmation";
	}

	@RequestMapping("/confirmation")
	public String confirmation() {
		return "confirmation";
	}
}
