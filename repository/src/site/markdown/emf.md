# EMF

[Java API](apidocs/org.nasdanika.html.emf/apidocs/index.html) for generating HTML from EClass metadata, including annotations, and EObject data.

The philosophy of this bundle is generation of a Web UI for EObjects by adapting them to different UI generation participants as outlined below:

* Target EObject is adapted to [Application](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Application.html). Customization of this adapter will change a general appearance of the application. 
* Target EObject is adapted to [ApplicationBuilder](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/ApplicationBuilder.html), e.g. [ViewActionApplicationBuilder](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/ViewActionApplicationBuilder.html).
* ViewActionApplicationBuilder adapts the target EObject to [ViewAction](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/ViewAction.html), which is implemented by [EObjectViewAction](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/EObjectViewAction.html), which can be subclassed and customized as needed.
* It also adapts the target EObject to different view parts:
    * [HeaderViewPart](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/HeaderViewPart.html)
    * [NavigationBarViewPart](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/NavigationBarViewPart.html)
    * [NavigationPanelViewPart](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/NavigationPanelViewPart.html)
    * [ContentPanelViewPart](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/ContentPanelViewPart.html)
    * [FooterViewPart](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/FooterViewPartViewPart.html)

Access to contextual information can be done by adapting EObjects to context-providing interfaces. E.g. in the case of dynamic web applications access to servlet API's such as request/response may also be done by adapting EObject to HTTPServletRequest and HTTPServletResponse.



