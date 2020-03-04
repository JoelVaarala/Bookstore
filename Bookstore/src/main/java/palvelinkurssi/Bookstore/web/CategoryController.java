package palvelinkurssi.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import palvelinkurssi.Bookstore.Domain.CategoryRepository;
import palvelinkurssi.Bookstore.Domain.Category;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@RequestMapping(value="/categorylist", method=RequestMethod.GET)
	public String getCategories(Model model) {
		List<Category> category = (List<Category>) categoryRepository.findAll();
		model.addAttribute("category", category);
		return "listCategories";
	}
	
	@RequestMapping(value = "/newcategory", method = RequestMethod.GET)
	public String getNewCategoryForm(Model model) {
		model.addAttribute("category", new Category()); 
		return "addCategory"; 
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute Category category) {
		categoryRepository.save(category);	
		return "redirect:/listCategories"; 
	}

}
