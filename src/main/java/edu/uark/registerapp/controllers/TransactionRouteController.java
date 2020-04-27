package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.products.ProductsQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.enums.EmployeeClassification;

//import edu.uark.registerapp.models.LookupServlet;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionRouteController extends BaseRouteController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showTransaction(@RequestParam final Map<String, String> queryParameters,
	final HttpServletRequest request)
	{
			//ModelAndView modelAndView = new ModelAndView(ViewNames.TRANSACTION.getViewName());

			final Optional<ActiveUserEntity> activeUserEntity = this.getCurrentUser(request);
			if(!activeUserEntity.isPresent())
			{
					return buildInvalidSessionResponse();
			}

			ModelAndView modelAndView =
			this.setErrorMessageFromQueryString(
				new ModelAndView(ViewNames.TRANSACTION.getViewName()),
				queryParameters);

//This is my attempt at trying to get the lookupinput from the url. 
//I cant tell if this works or not and cant find a way to check.
		String lookupInput = "Before Get"; 
		lookupInput = request.getParameter("lookupInput");
		//lookupInput = request.getAttribute("lookupInput");
		modelAndView.addObject("Search", lookupInput);
		
//This is my attempt at a partial search
//The idea was to have the GET request and compare it to the lookupIds of the products
//If it partially matches it goes on the modelAndView.
//IF it doesnt it wont show up
//Heroku throws a whitepage error and wont specify what the problem is so I dont know where to start
//If you take the try catch out of the if-else statement all of the products will show up on the page.
//The code for when this was working is saved on Coles computer.

		if(ViewModelNames.PRODUCTS.getValue().contains(lookupInput))
		{
			modelAndView.addObject(
			ViewModelNames.IS_ELEVATED_USER.getValue(),
			this.isElevatedUser(activeUserEntity.get()));

			try {
				modelAndView.addObject(
					ViewModelNames.PRODUCTS.getValue(),
					this.productsQuery.execute());
			} catch (final Exception e) {
				modelAndView.addObject(
					ViewModelNames.ERROR_MESSAGE.getValue(),
					e.getMessage());
				modelAndView.addObject(
					ViewModelNames.PRODUCTS.getValue(),
					(new Product[0]));
			}
		}
		else
		{
			modelAndView.addObject(ViewModelNames.PRODUCTS.getValue(), (new Product[0]));
		}
		
		return modelAndView;
	}

	// Properties
	@Autowired
	private ProductsQuery productsQuery;	
}
