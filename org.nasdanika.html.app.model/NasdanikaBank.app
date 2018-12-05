<?xml version="1.0" encoding="UTF-8"?>
<org.nasdanika.html.app:ThemedContentAction xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:org.nasdanika.html.app="urn:org.nasdanika.html.app" color="PRIMARY" icon="fas fa-university" id="bank" text="Bank of Nasdanika" theme="Default">
  <children xsi:type="org.nasdanika.html.app:ContentAction" icon="fas fa-user-circle" id="john-doe" text="John Doe" content="John Doe home page">
    <children icon="fas fa-money-check-alt" text="Checking-1234" tooltip="Balance $208.35">
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="checking-1234.html"/>
    </children>
    <children icon="fas fa-piggy-bank" text="Savings-5678" tooltip="Balance $1205.33">
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="476579021.html"/>
    </children>
    <children xsi:type="org.nasdanika.html.app:ContentAction" icon="far fa-credit-card" text="Credit card 9012" tooltip="Balance $570.25" content="Credit card details here.">
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="credit-card-9012.html"/>
    </children>
    <contextActions icon="fas fa-list-ul" text="Accounts">
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="accounts.html"/>
    </contextActions>
    <contextActions icon="fas fa-invoice-dollar" text="Bill Pay">
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="bill-pay.html"/>
    </contextActions>
    <contextActions icon="fas fa-exchange-alt" id="transfer" text="Transfer">
      <children text="Between your accounts">
        <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="transfer-internal.html"/>
      </children>
      <children text="Outside the bank">
        <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="transfer-external.html"/>
      </children>
    </contextActions>
    <contextActions icon="fas fa-envelope-square" text="Message center" notification="2">
      <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="message-center.html"/>
    </contextActions>        
    <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="john-doe.html"/>
  </children>
  <contextActions icon="far fa-envelope" id="contact-us" text="Contact Us">
    <activator xsi:type="org.nasdanika.html.app:ScriptActionActivator" code="alert('Contact Us Script Action Activator welcomes you!')"/>
  </contextActions>
  <contextActions icon="fas fa-map-marked-alt" id="locations" text="Locations">
    <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="bank-locations.html"/>
  </contextActions>
  <activator xsi:type="org.nasdanika.html.app:NavigationActionActivator" url="bank-of-nasdanika.html"/>
</org.nasdanika.html.app:ThemedContentAction>
