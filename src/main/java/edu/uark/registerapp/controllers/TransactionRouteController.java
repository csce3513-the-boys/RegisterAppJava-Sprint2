package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.products.ProductsQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.enums.EmployeeClassification;

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
		
		return modelAndView;
	}

	@RequestMapping(value = "/transactionSearch", method = RequestMethod.GET)
	public ModelAndView Search(@RequestParam final Map<String, String> queryParameters, final HttpServletRequest request)
	{
		final Optional<ActiveUserEntity> activeUserEntity = this.getCurrentUser(request);

		ModelAndView modelAndView =
			this.setErrorMessageFromQueryString(
				new ModelAndView(ViewNames.TRANSACTION.getViewName()),
				queryParameters);

//---------------------------------------------------------------------------------------
//Try to get the GET input for search
//---------------------------------------------------------------------------------------

		String lookupInput = "Before Get"; 
		lookupInput = request.getParameter("lookupInput");
		//lookupInput = request.getAttribute("lookupInput");
		modelAndView.addObject("Search", lookupInput);

//---------------------------------------------------------------------------------------
//Pull up Products that match that GET
//---------------------------------------------------------------------------------------

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
