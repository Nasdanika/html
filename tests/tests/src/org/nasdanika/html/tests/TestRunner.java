package org.nasdanika.html.tests;

import java.io.File;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.nasdanika.html.ecore.EcoreDocumentationGenerator;

public class TestRunner implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		
        return runJUnit();
	}

	private Object runJUnit() {
		JUnitCore jUnitCore = new JUnitCore();
        RunListener runListener = new TextListener(System.out);
        jUnitCore.addListener(runListener);

        Result result = jUnitCore.run(Computer.serial(), HtmlTests.class);
        
        java.awt.Toolkit.getDefaultToolkit().beep();
        
        //System.out.println(result);
        
        return result.getFailureCount()==0 ? 0 : 1;
	}
	
	/**
	 * JUnit reflective invocation fails. Trying non-reflective way.
	 * @throws Exception
	 */
	@Test
	public void testEcoreDocumentation() {		
		EcoreDocumentationGenerator generator = new EcoreDocumentationGenerator("Nasdanika Bank Model", null);
		generator.loadGenModel("urn:org.nasdanika.bank");
		generator.generate(new File("target/test-dumps/ecore"));		
	}
	

	@Override
	public void stop() {
		// NOP
	}

}
