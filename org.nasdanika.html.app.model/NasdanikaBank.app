<?xml version="1.0" encoding="UTF-8"?>
<org.nasdanika.html.app:ThemedContentAction xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:org.nasdanika.html.app="urn:org.nasdanika.html.app" color="PRIMARY" icon="fas fa-university" text="Bank of Nasdanika" theme="Default">
  <children xsi:type="org.nasdanika.html.app:ContentAction" icon="fas fa-user-circle" text="John Doe" content="John Doe home page">
    <children icon="fas fa-money-check-alt" text="Checking-1234" tooltip="Balance $208.35"/>
    <children icon="fas fa-piggy-bank" text="Savings-5678" tooltip="Balance $1205.33"/>
    <children xsi:type="org.nasdanika.html.app:ContentAction" icon="far fa-credit-card" text="Credit card 9012" tooltip="Balance $570.25" content="Credit card details here."/>
    <contextActions icon="fas fa-list-ul" text="Accounts"/>
    <contextActions icon="fas fa-invoice-dollar" text="Bill Pay"/>
    <contextActions icon="fas fa-exchange-alt" text="Transfer">
      <children text="Between your accounts"/>
      <children text="Outside the bank"/>
    </contextActions>
  </children>
  <children icon="far fa-envelope" text="Contact Us"/>
  <children icon="fas fa-map-marked-alt" text="Locations"/>
</org.nasdanika.html.app:ThemedContentAction>
