<?xml version="1.0" encoding="UTF-8"?>
<org.nasdanika.html.app:ThemedContentAction xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:org.nasdanika.html.app="urn:org.nasdanika.html.app" color="PRIMARY" icon="fas fa-university" id="bank" text="Bank of Nasdanika" theme="Default" content="Bank of Nasdanika home page">
  <children xsi:type="org.nasdanika.html.app:ContentAction" icon="fas fa-user-circle" id="john-doe" text="John Doe" content="John Doe home page">
  	<roles>navigation</roles>
  
    <children icon="fas fa-money-check-alt" text="Checking-1234" tooltip="Balance $208.35">
  	<roles>navigation</roles>
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="checking-1234.html"/>
    </children>
    <children icon="fas fa-piggy-bank" text="Savings-5678" tooltip="Balance $1205.33">
  	<roles>navigation</roles>
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="476579021.html"/>
    </children>
    <children xsi:type="org.nasdanika.html.app:ContentAction" icon="far fa-credit-card" text="Credit card 9012" tooltip="Balance $570.25" content="Credit card details here.">
  	<roles>navigation</roles>
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="credit-card-9012.html"/>
    </children>
    <children icon="fas fa-list-ul" text="Accounts">
  	<roles>context</roles>
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="accounts.html"/>
    </children>
    <children icon="fas fa-invoice-dollar" text="Bill Pay">
  	<roles>context</roles>
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="bill-pay.html"/>
    </children>
    <children icon="fas fa-exchange-alt" id="transfer" text="Transfer">
	  	<roles>context</roles>
      <children text="Between your accounts">
        <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="transfer-internal.html"/>
      </children>
      <children text="Outside the bank">
        <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="transfer-external.html"/>
      </children>
      <children xsi:type="org.nasdanika.html.app:ContentAction" text="Categorized action 1.1" content="Categorized action 1.1">
        <category icon="fas fa-user" id="c1" text="Category 1" tooltip="Groups several actions..."/>
      </children>
      <children xsi:type="org.nasdanika.html.app:ContentAction" text="Categorized action 1.2" content="Categorized action 1.2 content">
        <category icon="fas fa-user" id="c1" text="Category 1" tooltip="Groups several actions..."/>
      </children>
      <children xsi:type="org.nasdanika.html.app:ContentAction" text="Categorized action 2.1" content="Categorized action 2.1 content">
        <category icon="" id="c2" text="" tooltip="Anonymous category c2"/>
      </children>
      <children xsi:type="org.nasdanika.html.app:ContentAction" text="Categorized action 2.2" content="Categorized action 2.2 content">
        <category icon="" id="c2" text="" tooltip=""/>
      </children>
      <children xsi:type="org.nasdanika.html.app:ContentAction" text="Categorized action 1.3" content="Categorized action 1.3 content">
        <category icon="fas fa-user" id="c1" text="Category 1" tooltip="Groups several actions..."/>
      </children>
    </children>
    <children icon="fas fa-envelope-square" text="Message center" notification="2">
  	<roles>context</roles>
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="message-center.html"/>
    </children>
    <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="john-doe.html"/>
  </children>
  <children icon="far fa-envelope" id="contact-us" text="Contact Us">
  	<roles>context</roles>
    <activator xsi:type="org.nasdanika.html.app:ScriptActionActivator" code="alert('Contact Us Script Action Activator welcomes you!')"/>
  </children>
  <children icon="fas fa-map-marked-alt" id="locations" text="Locations">
  	<roles>context</roles>
    <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="bank-locations.html"/>
  </children>
  <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="bank-of-nasdanika.html"/>
</org.nasdanika.html.app:ThemedContentAction>
